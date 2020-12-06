package main.ElecServBill.servlet;

import main.ElecServBill.birth.FontText;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {

    public void test(String[] args){
        String filePath = "C:\\Users\\18099\\Desktop\\birth.png";
        String outPath = "C:\\Users\\18099\\Desktop\\birth456.png";
        System.out.println("name length:"+"纪 伟".length());
        String name = "木合塔尔·哈布都拉";
        drawTextInImg(filePath, outPath, new FontText(name + "   同志", "#323131",
                48, "华文楷体"));

    }

    public void drawTextInImg(String filePath,String outPath, FontText text) {
        ImageIcon imgIcon = new ImageIcon(filePath);
        Image img = imgIcon.getImage();
        int width = img.getWidth(null);
        int height = img.getHeight(null);
        BufferedImage bimage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bimage.createGraphics();
        g.setColor(getColor(text.getWm_text_color()));
        g.setBackground(Color.white);
        g.drawImage(img, 0, 0, null);
        Font font = null;
        if (!StringUtils.isEmpty(text.getWm_text_font())
                && text.getWm_text_size() != null) {
            font = new Font(text.getWm_text_font(), Font.TRUETYPE_FONT, text.getWm_text_size());
        } else {
            font = new Font(null, Font.BOLD, text.getWm_text_size());
        }

        g.setFont(font);
        FontMetrics metrics = new FontMetrics(font){};
        Rectangle2D bounds = metrics.getStringBounds(text.getText(), null);
        int textWidth = (int) bounds.getWidth();
        int textHeight = (int) bounds.getHeight();
        int left = 180;
        int top = textHeight+1035;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawString(text.getText(), left, top);
        g.dispose();

        try {
            FileOutputStream out = new FileOutputStream(outPath);
            ImageIO.write(bimage, "PNG", out);
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Color getColor(String color) {
        if (color.charAt(0) == '#') {
            color = color.substring(1);
        }
        if (color.length() != 6) {
            return null;
        }
        try {
            int r = Integer.parseInt(color.substring(0, 2), 16);
            int g = Integer.parseInt(color.substring(2, 4), 16);
            int b = Integer.parseInt(color.substring(4), 16);
            return new Color(r, g, b);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }


}
