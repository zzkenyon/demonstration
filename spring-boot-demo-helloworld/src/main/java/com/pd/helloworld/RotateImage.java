package com.pd.helloworld;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author zhaozhengkang
 * @description
 * @date 2019/12/16 16:06
 */
public class RotateImage {
    /**
     * 对图片进行旋转
     *
     * @param src   被旋转图片
     * @param angel 旋转角度
     * @return 旋转后的图片
     */
    public static BufferedImage rotate(Image src, int angel) {
        int srcWidth = src.getWidth(null);
        int srcHeight = src.getHeight(null);
        // 计算旋转后图片的尺寸
        Rectangle rectDes = calcRotatedSize(new Rectangle(new Dimension(
                srcWidth, srcHeight)), angel);
        BufferedImage res = null;
        res = new BufferedImage(rectDes.width, rectDes.height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = res.createGraphics();
        // 进行转换
        g2.translate((rectDes.width - srcWidth) / 2,
                (rectDes.height - srcHeight) / 2);
        g2.rotate(Math.toRadians(angel), (double)srcWidth / 2, (double) srcHeight / 2);

        g2.drawImage(src, null, null);
        return res;
    }

    /**
     * 计算旋转后的图片
     *
     * @param src   被旋转的图片
     * @param angel 旋转角度
     * @return 旋转后的图片
     */
    public static Rectangle calcRotatedSize(Rectangle src, int angel) {
        // 如果旋转的角度大于90度做相应的转换
        if (angel >= 90) {
            if (angel / 90 % 2 == 1) {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }

        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angelAlpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angelDaltaWidth = Math.atan((double) src.height / src.width);
        double angelDaltaHeight = Math.atan((double) src.width / src.height);

        int lenDaltaWidth = (int) (len * Math.cos(Math.PI - angelAlpha
                - angelDaltaWidth));
        int lenDaltaHeight = (int) (len * Math.cos(Math.PI - angelAlpha
                - angelDaltaHeight));
        int desWidth = src.width + lenDaltaWidth * 2;
        int desHeight = src.height + lenDaltaHeight * 2;
        return new Rectangle(new Dimension(desWidth, desHeight));
    }

    public static void main(String[] args) throws IOException {
        BufferedImage src = ImageIO.read(new File("e:/verifyCode/zrze.jpg"));
        //顺时针旋转90度
        BufferedImage des1 = RotateImage.rotate(src, 90);
        ImageIO.write(des1, "jpg", new File("e:/90.jpg"));
        //顺时针旋转180度
        BufferedImage des2 = RotateImage.rotate(src, 180);
        ImageIO.write(des2, "jpg", new File("E:/180.jpg"));
        //顺时针旋转270度
        BufferedImage des3 = RotateImage.rotate(src, 270);
        ImageIO.write(des3, "jpg", new File("E:/270.jpg"));
    }

}
