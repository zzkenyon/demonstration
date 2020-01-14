package com.pd.helloworld.verficode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-01-13 15:33
 */
public class VerifyCodeGenerator {
    private final int WIDTH = 160;
    private final int HEIGHT = 60;
    private SecureRandom secureRandom = new SecureRandom();
    /**
     * {"宋体", "华文楷体", "黑体", "华文新魏", "华文隶书", "微软雅黑", "楷体_GB2312"}
     */
    private String[] fontNames =
            { "宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312" };
    /**
     * 可选字符
     */
    private static final String CODES = "3456789abcdefghjkmnpqrstuvwxyABCDEFGHJKMNPQRSTUVWXY";
    /**
     * 背景色
     */
    private final Color bgColor = new Color(200, 200, 200);
    /**
     * / 验证码上的文本
     */
    private VerifyCode verifyCode = new VerifyCode();

    private int[] xs = {20,40,60,80};

    /**
     * 生成随机的颜色
     * @return
     */
    private Color randomColor() {
        int red = secureRandom.nextInt(150);
        int green = secureRandom.nextInt(150);
        int blue = secureRandom.nextInt(150);
        return new Color(red, green, blue);
    }


    private Font randomFont() {
        int index = secureRandom.nextInt(fontNames.length);
        // 生成随机的字体名称
        String fontName = fontNames[index];
        // 生成随机的样式, 0(无样式), 1(粗体), 2(斜体), 3(粗体+斜体)
        int style = secureRandom.nextInt(4);
        // 生成随机字号, 24 ~ 28
        int size = secureRandom.nextInt(5) + 42;
        return new Font(fontName, style, size);
    }

    /**
     * / 画干扰线
     * @param image
     */
    private void drawLine(BufferedImage image) {
        // 一共画3条
        int num = 3;
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        for (int i = 0; i < num; i++) {
            // 生成两个点的坐标，即4个值
            int x1 = secureRandom.nextInt(WIDTH);
            int y1 = secureRandom.nextInt(HEIGHT);
            int x2 = secureRandom.nextInt(WIDTH);
            int y2 = secureRandom.nextInt(HEIGHT);
            g2.setStroke(new BasicStroke(1.5F));
            // 干扰线是蓝色
            g2.setColor(Color.BLUE);
            // 画线
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * / 随机生成一个字符
     * @return
     */
    private char randomChar() {
        int index = secureRandom.nextInt(CODES.length());
        return CODES.charAt(index);
    }

    /**
     * / 创建BufferedImage
     * @return
     */
    private BufferedImage createImage() {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.setColor(this.bgColor);
        g2.fillRect(0, 0, WIDTH, HEIGHT);
        return image;
    }

    private BufferedImage generateCodeImage() {
        // 创建图片缓冲区
        BufferedImage image = createImage();
        // 得到绘制环境
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        // 用来装载生成的验证码文本
        StringBuilder sb = new StringBuilder();
        // 向图片中画4个字符
        for (int i = 0; i < 4; i++) {
            // 循环四次，每次生成一个字符
            // 随机生成一个字母
            String s = randomChar() + "";
            // 把字母添加到sb中
            sb.append(s);
            //float x = i * 1.0F * WIDTH / 4; // 设置当前字符的x轴坐标
            float x = xs[i];
            // 设置随机字体
            g2.setFont(randomFont());
            // 设置随机颜色
            g2.setColor(randomColor());
            // 画图
            g2.drawString(s, x, HEIGHT - 5);
        }
        // 把生成的字符串赋给了this.text
        this.verifyCode.setContext(sb.toString());
        // 添加干扰线
        drawLine(image);
        return image;
    }
    private String generateBase64VerifyCode() {
        String result = null;
        byte[] captchaChallengeAsJpeg;
        BufferedImage image = generateCodeImage();
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(image, "jpg", outputStream);
            captchaChallengeAsJpeg = outputStream.toByteArray();
            result = Base64.getEncoder().encodeToString(captchaChallengeAsJpeg);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public VerifyCode generateVerifyCode(){
        verifyCode.setBase64VerifyCode(generateBase64VerifyCode());
        return verifyCode;
    }

    /**
     * / 保存图片到指定的输出流
     * @param image
     * @param out
     * @throws IOException
     */
    public static void output(BufferedImage image, OutputStream out)
            throws IOException {
        ImageIO.write(image, "JPEG", out);
    }

    public static void main(String[] args) throws IOException{
        VerifyCodeGenerator generator = new VerifyCodeGenerator();
        int i = 0;
        while(i++ < 100) {
            BufferedImage img = generator.generateCodeImage();
            String str = "/Users/zhaozhengkang/verify/" + generator.verifyCode.getContext()+".jpg";
            File out = new File(str);
            VerifyCodeGenerator.output(img, new FileOutputStream(out));
        }
    }
}
