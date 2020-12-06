package main.wordUtil;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Image2PDF {
    /*** @param picturePath 图片地址*/
    private static void createPic(Document document, String picturePath) {
        try {
            Image image = Image.getInstance(picturePath);
            float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
//            float documentWidth = document.getPageSize().getWidth();
            float documentHeight = document.getPageSize().getHeight();//重新设置宽高
            image.scaleAbsolute(documentWidth, documentHeight);//重新设置宽高
            document.add(image);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void image2pdf(String picturePath, String pdf) {
        try{
            Document document = new Document();
            OutputStream os = new FileOutputStream(new File(pdf));
            PdfWriter.getInstance(document,os);
            document.open();
            createPic(document,picturePath);
            document.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}