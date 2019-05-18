//package com.ma.word;
//
//import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;
//import org.apache.xmlbeans.XmlToken;
//import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
//import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
//import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
//import org.springframework.util.StringUtils;
//
//import java.math.BigInteger;
//import java.text.SimpleDateFormat;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.Date;
//
//public class PoiWordUtil {
//
//    private static final String DOC = "doc";
//    private static final String DOCX = "docx";
//    private static final String SYSTEM_TYPE_W = "Windows";
//    private static final String SYSTEM_TYPE_L = "Linux";
//
//    private static String errorMsg = "";
//    private static String templatePath = "";
//    private static String newFilePath = "";
//
//    private static OfficeManager officeManager;
//
//    /**
//     * 替换模板方法
//     *
//     * @param templateName 模板名称
//     * @param params       替换参数
//     * @return
//     * @throws Exception
//     */
//    public static Map<String, String> executeWord(String templateName, List<?> params) throws Exception {
//        //获取模板路径
//        getTemplate(templateName);
//        //获取新文件生成路径
//        getFileStore(templateName);
//        //判断模板类型
//        if (templatePath.endsWith(DOC)) {
//            //处理03版word
//            replaceWordDoc(params);
//        } else if (templatePath.endsWith(DOCX)) {
//            //处理07版word
//            replaceWordDocx(params);
//        }
//
//        //返回处理结果(新生成文件路径、错误信息)
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("newFilePath", newFilePath);
//        map.put("errorMsg", errorMsg);
//        return map;
//    }
//
//    /**
//     * 替换模板文本内容（2007）
//     *
//     * @param params 替换参数
//     * @throws Exception
//     */
//    private static void replaceWordDocx(List<?> params) throws Exception {
//        InputStream is = new FileInputStream(templatePath);
//        XWPFDocument doc = new XWPFDocument(is);
//        Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
//        XWPFParagraph para;
//        //替换文本
//        while (iterator.hasNext()) {
//            para = iterator.next();
//            replaceInParaDocx(doc, para, params);
//        }
//        //写出文件
//        OutputStream out = new FileOutputStream(newFilePath);
//        doc.write(out);
//        //关闭输出流
//        close(out);
//    }
//
//    /**
//     * 替换方法（2007）
//     *
//     * @param para
//     * @param params
//     * @throws Exception
//     */
//    @SuppressWarnings("unchecked")
//    private static void replaceInParaDocx(XWPFDocument doc, XWPFParagraph para, List<?> params) throws Exception {
//        List<XWPFRun> runs;
//        Matcher matcher;
//        String imgPath = "";
//        int width = 0, height = 0;
//        if (matcher(para.getParagraphText()).find()) {
//            runs = para.getRuns();
//            for (int i = 0; i < runs.size(); i++) {
//                XWPFRun run = runs.get(i);
//                String runText = run.toString();
//                matcher = matcher(runText);
//                if (matcher.find()) {
//                    Map<String, Object> infoMap = new HashMap<String, Object>();
//                    while ((matcher = matcher(runText)).find()) {
//                        System.err.println("查找替换标签：" + matcher.group(1));
//                        int index = Integer.valueOf(matcher.group(1)) - 1;
//                        if (index >= params.size()) {
//                            break;
//                        }
//                        if (params.get(index) instanceof Map) {
//                            infoMap = (Map<String, Object>) params.get(index);
//                            if (infoMap.get("img") != null) {
//                                //处理图片
//                                imgPath = infoMap.get("Path").toString();
//                                width = Integer.parseInt(infoMap.get("Width").toString());
//                                height = Integer.parseInt(infoMap.get("Height").toString());
//                                //如果为图片替换空值
//                                runText = matcher.replaceFirst("");
//                            } else {
//                                runText = matcher.replaceFirst(String.valueOf(infoMap.get("Text")));
//                            }
//                        } else if (params.get(index) instanceof String) {
//                            runText = matcher.replaceFirst(String.valueOf(params.get(index)));
//                        }
//                    }
//                    //直接调用XWPFRun的setText()方法设置文本时，在底层会重新创建一个XWPFRun，把文本附加在当前文本后面，
//                    //所以我们不能直接设值，需要先删除当前run,然后再自己手动插入一个新的run。
//                    para.removeRun(i);
//                    XWPFRun newRun = para.insertNewRun(i);
//                    newRun.setText(runText);
//                    if (!infoMap.isEmpty()) {
//                        //设置字体样式
//                        setFont(newRun, infoMap);
//                    }
//                    if (StringUtils.isEmpty(imgPath)) {
//                        //获得当前CTInline
//                        CTInline inline = newRun.getCTR().addNewDrawing().addNewInline();
//                        doc.addPictureData(new FileInputStream(imgPath), 5);
//                        insertPicture(doc, inline, width, height);
//                    }
//                    imgPath = "";
//                    infoMap.clear();
//                    System.err.println("替换");
//                }
//            }
//        }
//    }
//
//    /**
//     * 2007字体样式设置
//     *
//     * @param run
//     * @param param
//     */
//    private static void setFont(XWPFRun run, Map<String, Object> param) {
//        CTRPr pRpr = null;
//        if (run.getCTR() != null) {
//            pRpr = run.getCTR().getRPr();
//            if (pRpr == null) {
//                pRpr = run.getCTR().addNewRPr();
//            }
//        }
//
//        // 设置字体
//        if (param.get("Name") != null) {
//            CTFonts fonts = pRpr.isSetRFonts() ? pRpr.getRFonts() : pRpr.addNewRFonts();
//            fonts.setAscii(param.get("Name").toString());
//            fonts.setEastAsia(param.get("Name").toString());
//            fonts.setHAnsi(param.get("Name").toString());
//        }
//        //粗体
//        run.setBold(param.get("Bold") != null ? true : false);
//        //斜体
//        run.setItalic(param.get("Italic") != null ? true : false);
//        //下划线
//        if (param.get("Underline") != null) {
//            run.setUnderline(UnderlinePatterns.SINGLE);
//        }
//        //字体大小
//        if (param.get("Size") != null) {
//            CTHpsMeasure sz = pRpr.isSetSz() ? pRpr.getSz() : pRpr.addNewSz();
//            sz.setVal(new BigInteger(param.get("Size").toString()));
//            CTHpsMeasure szCs = pRpr.isSetSzCs() ? pRpr.getSzCs() : pRpr.addNewSzCs();
//            szCs.setVal(new BigInteger(param.get("Size").toString()));
//        }
//        //字体颜色
//        if (param.get("Color") != null) {
//            run.setColor(param.get("Color").toString());
//        }
//    }
//
//    /**
//     * 插入图片
//     *
//     * @param document
//     * @param inline
//     * @param width
//     * @param height
//     * @throws Exception
//     */
//    public static void insertPicture(XWPFDocument document, CTInline inline, int width, int height) throws Exception {
//        int id = document.getAllPictures().size() - 1;
//        final int EMU = 9525;
//        width *= EMU;
//        height *= EMU;
//        String blipId = document.getAllPictures().get(id).getPackageRelationship().getId();
//        String picXml = ""
//                + "<a:graphic xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\">"
//                + "   <a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">"
//                + "      <pic:pic xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">"
//                + "         <pic:nvPicPr>"
//                + "            <pic:cNvPr id=\"" + id + "\" name=\"Generated\"/>"
//                + "            <pic:cNvPicPr/>"
//                + "         </pic:nvPicPr>"
//                + "         <pic:blipFill>"
//                + "            <a:blip r:embed=\"" + blipId + "\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"/>"
//                + "            <a:stretch>"
//                + "               <a:fillRect/>"
//                + "            </a:stretch>"
//                + "         </pic:blipFill>"
//                + "         <pic:spPr>"
//                + "            <a:xfrm>"
//                + "               <a:off x=\"0\" y=\"0\"/>"
//                + "               <a:ext cx=\"" + width + "\" cy=\"" + height + "\"/>"
//                + "            </a:xfrm>"
//                + "            <a:prstGeom prst=\"rect\">"
//                + "               <a:avLst/>"
//                + "            </a:prstGeom>"
//                + "         </pic:spPr>"
//                + "      </pic:pic>"
//                + "   </a:graphicData>"
//                + "</a:graphic>";
//
//        inline.addNewGraphic().addNewGraphicData();
//        XmlToken xmlToken = null;
//        xmlToken = XmlToken.Factory.parse(picXml);
//        inline.set(xmlToken);
//        inline.setDistT(0);
//        inline.setDistB(0);
//        inline.setDistL(0);
//        inline.setDistR(0);
//        CTPositiveSize2D extent = inline.addNewExtent();
//        extent.setCx(width);
//        extent.setCy(height);
//        CTNonVisualDrawingProps docPr = inline.addNewDocPr();
//        docPr.setId(id);
//        docPr.setName("IMG_" + id);
//        docPr.setDescr("IMG_" + id);
//    }
//
//    /**
//     * 替换模板文本内容（2003）
//     *
//     * @param params 替换参数
//     * @throws Exception
//     */
//    private static void replaceWordDoc(List<?> params) throws Exception {
//        InputStream is = new FileInputStream(templatePath);
//        HWPFDocument doc = new HWPFDocument(is);
//        Range bodyRange = doc.getRange();
//        //替换文本
//        replaceInParaDoc(bodyRange, params);
//        //输出新文件
//        FileOutputStream out = new FileOutputStream(newFilePath);
//        doc.write(out);
//        out.flush();
//        //关闭输出流
//        out.close();
//    }
//
//    /**
//     * 替换方法（2003）
//     *
//     * @param range
//     * @param params
//     */
//    @SuppressWarnings("unchecked")
//    private static void replaceInParaDoc(Range range, List<?> params) {
//        Matcher matcher;
//        while ((matcher = matcher(range.text())).find()) {
//            int index = Integer.valueOf(matcher.group(1)) - 1;
//            if (index >= params.size()) {
//                break;
//            }
//            if (params.get(index) instanceof String) {
//                range.replaceText(matcher.group(), String.valueOf(params.get(index)));
//            } else if (params.get(index) instanceof Map) {
//                Map<String, Object> infoMap = (Map<String, Object>) params.get(index);
//                range.replaceText(matcher.group(), String.valueOf(infoMap.get("Text")));
//            }
//        }
//    }
//
//    /**
//     * 正则匹配字符串
//     *
//     * @param str 带替换字符串
//     * @return
//     */
//    private static Matcher matcher(String str) {
//        Pattern pattern = Pattern.compile("\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(str);
//        return matcher;
//    }
//
//    /**
//     * 获取文件模板路径
//     *
//     * @param templateName 模板文件名称
//     * @return
//     * @throws IOException
//     */
//    private static void getTemplate(String templateName) throws Exception {
//        String path = JacobWordUtil.class.getClassLoader().getResource("word-template").getPath();
//        String docPath = path + templateName + ".doc";
//        String docxPath = path + templateName + ".docx";
//
//        if (new File(docPath).exists()) {
//            templatePath = docPath;
//        } else if (new File(docxPath).exists()) {
//            templatePath = docxPath;
//        } else {
//            System.err.println("docPath:" + docPath);
//            System.err.println("docxPath:" + docxPath);
//            errorMsg = "模板文件不存在";
//        }
//    }
//
//    /**
//     * 获取文件存储路径
//     *
//     * @param templateName 模板文件名称
//     * @return
//     * @throws IOException
//     */
//    private static void getFileStore(String templateName) throws IOException {
//        Prop propFile = PropKit.use("fileserver.properties");
//        //操作系统类型(Windows、Linux)
//        String sysType = propFile.get("fileserver.system.type");
//        //文件存储跟目录
//        String fileRoot = propFile.get("fileserver.local.root");
//        //文件生产目录
//        String fileStore = propFile.get("fileserver.store");
//        //生成文件名称
//        String name = templateName + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//
//        if (SYSTEM_TYPE_W.equals(sysType)) {
//            //Windows
//            newFilePath = fileRoot + fileStore + "/" + templateName + "/";
//        } else if (SYSTEM_TYPE_L.equals(sysType)) {
//            //Linux
//            newFilePath = File.separator + "usr/fileserver/";
//        } else {
//            //其他服务器系统处理
//        }
//
//        //生成目录不存在创建目录
//        File file = new File(newFilePath);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//
//        newFilePath = newFilePath + name + ".doc";
//    }
//
//    /**
//     * word文件转PDF文件
//     *
//     * @param sfileName  待转word文件
//     * @param toFileName pdf保存路径
//     * @throws Exception
//     */
//    public static void wordToPDF(String sfileName, String toFileName) throws Exception {
//        //打开服务
//        startService();
//        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
//        //开始转换
//        converter.convert(new File(sfileName), new File(toFileName));
//        //关闭
//        stopService();
//    }
//
//    /**
//     * 启动openOffice服务
//     *
//     * @throws Exception
//     */
//    private static void startService() throws Exception {
//        Prop propFile = PropKit.use(Bs.Prop.BS_FILESERVER);
//        DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
//        configuration.setOfficeHome(propFile.get("bs.openoffice.server.dir"));//设置安装目录
//        configuration.setPortNumbers(8100); //设置端口
//        configuration.setTaskExecutionTimeout(1000 * 60 * 5L);
//        configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);
//        officeManager = configuration.buildOfficeManager();
//        officeManager.start();    //启动服务
//    }
//
//    /**
//     * 关闭openOffice服务
//     */
//    private static void stopService() {
//        if (officeManager != null) {
//            officeManager.stop();
//        }
//    }
//
//    /**
//     * 关闭输出流
//     *
//     * @param os
//     */
//    public static void close(OutputStream os) {
//        if (os != null) {
//            try {
//                os.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 关闭输入流
//     * @param os
//     */
//    public static void close(InputStream in) {
//        if (in != null) {
//            try {
//                in.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void main(String[]args){
//        /**图片替换参数（图片参数均为必传参数）**/
//        Map<String, Object> map=new HashMap<String, Object>();
//        map.put("img",true);
//        map.put("Width",30);
//        map.put("Height",40);
//        map.put("Path","D:\\wyh.png");
//        /**带格式字符替换参数**/
//        Map<String, Object> mapF=new HashMap<String, Object>();
//        mapF.put("Text","地址：张川县竹园镇");
//        mapF.put("Name","华文隶书");  //字体
//        mapF.put("Bold",true);        //粗体
//        mapF.put("Italic",true);      //斜体
//        mapF.put("Underline",true);  //下划线
//        mapF.put("Color","FF0000");   //颜色（例如红s色：FF0000）
//        mapF.put("Size",26);           //字体大小
//        /**无格式字符替换参数**/
//        List<Object> params=Lists.newArrayList();
//        params.add("李四");
//        params.add("610321000000111111");
//        params.add(mapF);
//
//        //备注：模板名称test.docx
//        PoiWordUtil.executeWord("test",params);
//    }
//}
