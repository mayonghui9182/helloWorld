package com.ma.toos.Tracker;

/*
 * Copyright (c) 2012, 2015, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * -Redistribution of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *
 * -Redistribution in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 *
 * Neither the name of Oracle or the names of contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MICROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL,
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of any
 * nuclear facility.
 */

import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.io.IOException;
import java.net.SocketException;

import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * A daemon that listens for and logs UsageTracker information.
 */
public class UsageTrackerServer {

    static boolean verbose = false;
    boolean initialized = false;
    String logFileName = null;
    File logFile;
    OutputStreamWriter writer;
    int port = 32139;
    InetAddress address = null;
    DatagramSocket socket;
    private static final int BUFFERSIZE = 65536;
    long received = 0;
    boolean running = true;

    /**
     * Main entry point for starting this daemon.
     */
    public static void main(String [] args) {
        try {
            UsageTrackerServer uts = new UsageTrackerServer(args);
            uts.run();
        } catch (Exception e) {
            System.out.println("UsageTrackerServer: " + e.getMessage());
            if (verbose) {
                e.printStackTrace();
            }
            System.exit(1);
        }
    }

    public static void usage() {
        System.out.println("UsageTrackerServer [-v] [-o filename] [host]:port\n" +
                "e.g. UsageTrackerServer -o usagetracker.out :32139\n");
    }

    /**
     * Initialize a UsageTrackerServer given some arguments.
     */
    UsageTrackerServer(String [] args) throws Exception {

        boolean usage = false;
        for (int i=0; i<args.length; i++) {

            if (args[i].equals("-?") || args[i].equals("--h")) {
                usage = true;
                break;
            } else if (args[i].equals("-v")) {
                verbose = true;
            } else if (args[i].equals("-o")) {
                logFileName = args[i+1];
                i++;
            } else if (args[i].contains(":")) {
                // parse [address]:port
                int colon = args[i].indexOf(':');
                if (colon > 0) {
                    try {
                        address = InetAddress.getByName(
                                args[i].substring(0, colon));
                    } catch (Exception ae) {
                        System.out.println("UsageTrackerServer: " +
                                "problem setting listen address: " + ae);
                        usage = true;
                    }
                }
                try {
                    port = Integer.parseInt(args[i].substring(colon+1));
                } catch (NumberFormatException nfe) {
                    System.out.println("UsageTrackerServer: cannot set port: " +
                            args[i].substring(colon+1));
                    usage = true;
                }
            } else {
                usage = true;
            }
        }
        // Argument failure or request for usage gets the usage message only:
        if (usage) {
            usage();
            return;
        }
        // Otherwise, continue to proper initialization:
        socket = null;
        try {
            if (address != null) {
                socket = new DatagramSocket(port, address);
            } else {
                socket = new DatagramSocket(port);
            }
        } catch (SocketException se) {
            throw new Exception("problem creating socket: " + se);
        }
        if (logFileName != null) {
            try {
                File logFile = new File(logFileName);
                if (verbose) {
                    System.out.println("Using logfile: " + logFileName);
                    if (logFile.exists()) {
                        System.out.println("File exists, will append.");
                    }
                }
                FileOutputStream fos = new FileOutputStream(logFile, true);
                writer = new OutputStreamWriter(fos, "UTF-8");
            } catch (IOException ioe) {
                throw new Exception("problem using file " + logFileName + ": " +
                        ioe);
            }
        }
        initialized = true;
    }

    public void run() throws Exception {
        if (!initialized) {
            return;
        }
        Runnable r = null;
        if (writer == null) {
            r = new UsageTrackerServerRunnable(socket);
        } else {
            r = new UsageTrackerServerRunnable(socket, writer);
        }
        Thread t = new Thread(r, "UsageTrackerServerRunnable");
        t.start();
        t.join();
    }

    /**
     * Runnable that listens and logs.
     */
    private class UsageTrackerServerRunnable implements Runnable {
        DatagramSocket listenSocket;
        OutputStreamWriter writer = null;

        UsageTrackerServerRunnable(DatagramSocket socket) {
            listenSocket = socket;
        }
        UsageTrackerServerRunnable(DatagramSocket socket,
                                   OutputStreamWriter writer) {
            this(socket);
            this.writer = writer;
        }

        public void run() {
            byte [] buf = new byte[BUFFERSIZE];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            // Ready to receive data
            if (verbose) {
                String addr = listenSocket.getLocalAddress().getHostAddress();
                if (addr.equals("0.0.0.0")) {
                    addr = "localhost";
                }
                System.out.println("UsageTrackerServer: ready to receive on " +
                        addr + ":" + listenSocket.getLocalPort());
            }
            while (running) {
                try {
                    listenSocket.receive(packet);
                    String dataReceived = new String(packet.getData(), 0,
                            packet.getLength());

                    // The format of a UsageTracker record contains a newline at
                    // the end; if that is missing, we have a truncated/corrupt
                    // packet.
                    if (!dataReceived.endsWith("\n")) {
                        System.out.println("Incomplete message received: " +
                                "size = " + packet.getLength() + ", data =  " +
                                dataReceived);
                        dataReceived = dataReceived + "\n";
                    }
                    received++;
                    if (verbose) {
                        System.out.println("Received message size: " +
                                dataReceived.length());
                    }
                    if (writer != null) {
                        writer.write(dataReceived, 0, dataReceived.length());
                        writer.flush();
                    } else {
                        System.out.print(dataReceived);
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
}
