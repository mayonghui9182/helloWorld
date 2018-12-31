package test;

import com.test.downfile.ParseUrl;

import java.net.MalformedURLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {


        String url = "http://download.labs.sogou.com/dl/sogoulabdown/SogouQ/SogouQ.reduced.tar.gz";
        ParseUrl parseUrl = new ParseUrl();
        //System.out.println(parseUrl.ParseFile("D:\\SogouQ.reduced.tar.gz"));
        try {
            System.out.println(parseUrl.ParseFile(parseUrl.DownloadNet(url)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
