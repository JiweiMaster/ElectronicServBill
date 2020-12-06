package main.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class ImageBase64 {
	static BASE64Decoder decoder = new BASE64Decoder();
	
	
	public static String ImageToBase64(String imgPath) {
	    byte[] data = null;
	    // 读取图片字节数组
	    try {
	        InputStream in = new FileInputStream(imgPath);
	        data = new byte[in.available()];
	        in.read(data);
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	        toast(e.toString());
	    }
	    // 对字节数组Base64编码
	    BASE64Encoder encoder = new BASE64Encoder();
	    return encoder.encode(data);
	}
	
	public static String BuffImgToBase64Str(BufferedImage img){
		try{
			BASE64Encoder encoder = new BASE64Encoder();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(img, "png", baos);
			byte[] bytes = baos.toByteArray();
			String png_base64 =  encoder.encode(bytes).trim();
			return "data:image/png;base64,"+png_base64;
		}catch(Exception e){
			toast(e.toString());
			return null;
		}
	}
	
	
	public static BufferedImage base64ToImg(String base64Str){
		try{
			byte[] bytes1 = decoder.decodeBuffer(base64Str);
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
			BufferedImage bi1 = ImageIO.read(bais);
			return bi1;
		}catch(Exception e){
			toast(e.toString());
			return null;
		}
	}
	
	public static void toast(String msg){
		System.out.println(msg);
	}
}
