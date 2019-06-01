//package com.ma.word;
//
//import org.jodconverter.office.OfficeManager;
//
//import java.io.File;
//import java.util.regex.Pattern;
//
///**
// * @author 何泳锋
// * @version V1.0
// * @Title:OpenOffice工具类
// * @description:利用OpenOffice将office的各种文件转为pdf
// * @date 20180515
// */
//
//public class OpenOfficeUtils {
//
//    private static OfficeManager manager;
//
//    /**
//     * 文档2pdf（自动启动服务）
//     *
//     * @param inputFile
//     * @param outputFile
//     * @return
//     * @throws Exception
//     * @date 2018年3月11日
//     */
//
//    public static boolean doc2pdf(File inputFile, File outputFile) {
//
//        boolean result = false;
//
//        if (inputFile.exists()) {
//
//            if (!outputFile.exists()) {
//
//                // 初始化OpenOffice配置
//
//                initOfficeManagerConfiguration();
//
//                // 启动服务
//
//                manager.start();
//
//                // 开始转换
//
//                OfficeDocumentConverter converter = new OfficeDocumentConverter(manager);
//                converter.convert(inputFile, outputFile);
//
//                // 停止服务
//
//                manager.stop();
//
//
//                result = true;
//
//            } else {
//
//                result = true;
//
//                System.out.println("****已经转换为pdf，不需要再进行转化****");
//
//            }
//
//        } else {
//
//            System.out.println("****需要转换的文档不存在，无法转换，文件路径=" + inputFile.getAbsolutePath() + "****");
//
//        }
//
//        return result;
//
//    }
//
//
//    /**
//     * 初始化OpenOffice配置
//     *
//     * @date 2018年5月16日
//     */
//
//    private static void initOfficeManagerConfiguration() {
//
//        DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
//
////		configuration.setWorkDir(new File("/opt"));
//
//        String officeHome = getOfficeHome();
//
//        configuration.setOfficeHome(officeHome);// 设置安装目录
//
//        configuration.setPortNumbers(8100); // 设置端口
//
//        configuration.setTaskExecutionTimeout(1000 * 60 * 5L); // 设置任务执行超时为5分钟
//
//        configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L); // 设置任务队列超时为24小时
//
//        manager = configuration.buildOfficeManager();
//
//
//        if (manager == null)
//
//            throw new RuntimeException("初始化OpenOffice失败");
//
//    }
//
//    /**
//     * 获取OpenOffice的安装路径
//     *
//     * @date 2018年5月16日
//     */
//
//    public static String getOfficeHome() {
//
//        String osName = System.getProperty("os.name");
//
//        System.out.println("操作系统名称:" + osName);
//
//        if (Pattern.matches("Linux.*", osName)) {
//
//            return "/opt/openoffice.org4";
//
//        } else if (Pattern.matches("Windows.*", osName)) {
//
//            return "D:\\software\\tools\\openOfficeSoftware";
//
//        } else if (Pattern.matches("Mac.*", osName)) {
//
//            return "/Application/OpenOffice.org.app/Contents";
//
//        }
//
//        return null;
//
//    }
//
//    public static void main(String[] args) {
//
//        String infile = "D:\\upload\\admin\\2018\\05\\15\\1.xlsx";
//
//        String targetfile = "D:\\upload\\admin\\2018\\05\\15\\1.pdf";
//
//        File inFile = new File(infile);
//
//        File targetFile = new File(targetfile);
//
//        try {
//
//            doc2pdf(inFile, targetFile);
//
//        } catch (Exception e) {
//
//            // TODO Auto-generated catch block
//
//            e.printStackTrace();
//
//        }
//
//    }
//
//}
