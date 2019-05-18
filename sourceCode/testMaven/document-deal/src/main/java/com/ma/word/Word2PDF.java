package com.ma.word;

import com.artofsolving.jodconverter.BasicDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.DocumentFormatRegistry;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import java.io.File;

public class Word2PDF {
    public static void convert(File sourceFile, File targetFile) {

        try {
            // 1: 打开连接
            OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
            connection.connect();

            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            // 2:获取Format
            DocumentFormatRegistry factory = new BasicDocumentFormatRegistry();
            DocumentFormat inputDocumentFormat = factory
                    .getFormatByFileExtension("doc");
            DocumentFormat outputDocumentFormat = factory
                    .getFormatByFileExtension("pdf");
            // 3:执行转换
            converter.convert(sourceFile, inputDocumentFormat, targetFile, outputDocumentFormat);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("GAME OVER!");
        }
    }

    public static void main(String[] args) {
        File source = new File("D:\\Study\\code\\aspose\\word\\aspose test document\\test\\source.doc");
        File target = new File("D:\\Study\\code\\aspose\\word\\aspose test document\\test\\target.pdf");
        convert(source,target);
    }
}
