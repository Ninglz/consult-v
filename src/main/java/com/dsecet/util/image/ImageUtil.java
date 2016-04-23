package com.dsecet.util.image;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtil{

    public static void resize(File originalFile, File resizedFile,
                              int newWidth, int newHeight, float quality) {

        try {
            if (quality > 1) {
                throw new IllegalArgumentException(
                        "Quality has to be between 0 and 1");
            }

            ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
            Image i = ii.getImage();
            Image resizedImage = null;

            int iWidth = i.getWidth(null);
            int iHeight = i.getHeight(null);

            resizedImage = i.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            // This code ensures that all the pixels in the image are loaded.
            Image temp = new ImageIcon(resizedImage).getImage();

            // Create the buffered image.
            BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),
                    temp.getHeight(null), BufferedImage.TYPE_INT_RGB);

            // Copy image to buffered image.
            Graphics g = bufferedImage.createGraphics();

            // Clear background and paint the image.
            g.setColor(Color.white);
            g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
            g.drawImage(temp, 0, 0, null);
            g.dispose();

            // Soften.
            float softenFactor = 0.05f;
            float[] softenArray = {0, softenFactor, 0, softenFactor,
                    1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0};
            Kernel kernel = new Kernel(3, 3, softenArray);
            ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
            bufferedImage = cOp.filter(bufferedImage, null);

            // Write the jpeg to a file.
            FileOutputStream out = new FileOutputStream(resizedFile);

            // Encodes image as a JPEG data stream
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

            JPEGEncodeParam param = encoder
                    .getDefaultJPEGEncodeParam(bufferedImage);

            param.setQuality(quality, true);

            encoder.setJPEGEncodeParam(param);
            encoder.encode(bufferedImage);
            out.close();
        } catch (IOException e) {
        }
    }


    public final static void waterMark(String waterMarkImg,String srcImg, String targetImg, int margin_left, int margin_bottom) {
        try {
            //读原始文件
            File _file = new File(srcImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);


            //读水印文件
            File _filebiao = new File(waterMarkImg);
            Image src_biao = ImageIO.read(_filebiao);
            int wideth_biao = src_biao.getWidth(null);
            int height_biao = src_biao.getHeight(null);
            g.drawImage(src_biao, margin_left, height - height_biao - margin_bottom,
                    wideth_biao, height_biao, null);

            //输出目标文件
            g.dispose();
            FileOutputStream out = new FileOutputStream(targetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}