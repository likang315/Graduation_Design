package com.ly.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;

/**
 * Created by yinyutai on 2017/2/18 0018.
 */
public class ConverToHtml {
    public ConverToHtml() {
    }

    public static void writeFile(String content, String path) {
        FileOutputStream fos = null;
        BufferedWriter bw = null;

        try {
            File ioe = new File(path);
            fos = new FileOutputStream(ioe);
            bw = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"));
            bw.write(content);
        } catch (FileNotFoundException var15) {
            var15.printStackTrace();
        } catch (IOException var16) {
            var16.printStackTrace();
        } finally {
            try {
                if(bw != null) {
                    bw.close();
                }

                if(fos != null) {
                    fos.close();
                }
            } catch (IOException var14) {
                ;
            }

        }

    }

    public  String convert2Html(String fileName, final HttpServletRequest request) throws TransformerException, IOException, ParserConfigurationException {
        final UUID uuid = UUID.randomUUID();
        // 读取doc文档获取为HWPFDocument对象
        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(fileName));
        //获得wordToHtml对象
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
                //return "D:/class/work_code/eclipseWorkspace/qwt_qdwy/classes/artifacts/qwt_qdwy_Web_exploded/Activity/upload/" + uuid + suggestedName;
                return request.getServletContext().getRealPath("/upload/activity")+ "/" + uuid + suggestedName;
            }
        });
        wordToHtmlConverter.processDocument(wordDocument);
        //获得图片对象
//     PicturesTable pTable=wordDocument.getPicturesTable();
//        for (int i=0;i<wordDocument.characterLength();i++){
//            Range range=new Range(i, i+1,wordDocument);
//            CharacterRun cr=range.getCharacterRun(0);
//            if(pTable.hasPicture(cr)){
//                Picture pic=pTable.extractPicture(cr, false);
//                String afileName=pic.suggestFullFileName();
//                OutputStream out=new FileOutputStream(new File("D:/class/"+ UUID.randomUUID()+afileName));
//                pic.writeImageContent(out);
//
//            }
//        }

        List pics = wordDocument.getPicturesTable().getAllPictures();
        if(pics != null) {
            for(int htmlDocument = 0; htmlDocument < pics.size(); ++htmlDocument) {
                Picture out = (Picture)pics.get(htmlDocument);
                System.out.println(out.suggestFullFileName());
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(request.getServletContext().getRealPath("/upload/activity")+ "/" + uuid + out.suggestFullFileName());
                    //输出图片信息
                    out.writeImageContent(fileOutputStream);
                    //关闭流
                    fileOutputStream.close();
                    //获取文件的本地路径并且将文件上传至服务器
                    this.getFileName(request.getServletContext().getRealPath("/upload/activity"));
                } catch (FileNotFoundException var10) {
                    var10.printStackTrace();
                }
            }
        }

        Document var11 = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream var12 = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(var11);
        StreamResult streamResult = new StreamResult(var12);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty("encoding", "utf-8");
        serializer.setOutputProperty("indent", "yes");
        serializer.setOutputProperty("method", "html");
        serializer.transform(domSource, streamResult);
        var12.close();

        return new String(var12.toByteArray()).replace(request.getServletContext().getRealPath("/upload/activity"), "http://112.74.88.25/Activity/upload/");
    }


    /**
     * 读取文件夹下的文件
     * 并上传至服务器
     */
    public  void getFileName(String path) {
        File f = new File(path);
        if (!f.exists()) {
            System.out.println(path + " not exists");
            return;
        }
        File fa[] = f.listFiles();
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (fs.isDirectory()) {
                System.out.println(fs.getName() + " [目录]");
            } else {
                System.out.println(fs.getName());
            }
            //将目录下文件上传至FPT服务器
            FTPLinuxUtils ftpLinux = new FTPLinuxUtils();
            String path1 = path + "/"+ fs.getName();
            boolean upFlag = ftpLinux.uploadFile("112.74.88.25", "21", "mkl_ftp", "makalu_ftp", fs.getName(), path1,"Activity/upload/");
            //如果文件上传成功则删除文件
            if(upFlag) {
                //执行删除文件的方法
                boolean delState = this.deleteFile(path1);
                System.out.println("=============删除文件状态为:"+ delState +"本地路径为："+ path1 +"===================");
            }
            System.out.println("================文件上传的状态为："+ upFlag +"本地路径为：" + path1 + "=================");
        }
    }

    /**
     * 删除单个文件件的方法
     * @param   sPath    被删除文件的文件路径名
     * @return 单个文件删除成功返回true
     *			单个文件删除成功返回false
     */
    public boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }



}
