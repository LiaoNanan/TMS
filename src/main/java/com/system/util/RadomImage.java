package com.system.util;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Component
public class RadomImage {
    private Random r = new Random();
    private String[] fontNames = {"宋体", "华文楷体", "黑体", "华文新魏", "华文隶书", "微软雅黑", "楷体_GB2312"};


    public static char[] getSecurityCode() {
        int len;
        char[] result;
        //除去容易混淆的0和o，1和l
        char[] codes = {
                '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u',
                'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
                'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        len = codes.length;
        result = new char[4];
        for (int i = 0; i < 4; i++) {
            //索引0 and n-1
            int r = (int) (Math.random() * (len - 1));
            //将result中的第i个元素设为codes[r]存放的数值
            result[i] = codes[r];
        }

        return result;

    }

    private Color randomColor() {
        int red = r.nextInt(150);
        int green = r.nextInt(150);
        int blue = r.nextInt(150);
        return new Color(red, green, blue);
    }

    private Font randomFont() {
        int index = r.nextInt(fontNames.length);
        String fontName = fontNames[index];// 生成随机的字体名称
        int style = r.nextInt(4);// 生成随机的样式, 0(无样式), 1(粗体), 2(斜体), 3(粗体+斜体)
        int size = r.nextInt(5) + 24; // 生成随机字号, 24 ~ 28
        // int size = r.nextInt(5) + 15; // 生成随机字号, 20 ~ 24
        return new Font(fontName, style, size);
    }

    public BufferedImage createImage(String vericode) {
        BufferedImage image = new BufferedImage(100, 45, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        // 背景色,白色
        g2.setColor(new Color(184, 180, 180));
        g2.fillRect(0, 0, 100, 45);
        String code = vericode;
        // 向图片中画4个字符  String securityCode
        for (int i = 0; i <= 3; i++) {// 循环四次，每次生成一个字符
            String s = code.charAt(i) + "";// 随机生成一个字母
            // float x = i * 1.0F * CAPTCHA_WIDTH / NUMBER_CNT; // 设置当前字符的x轴坐标
            float x = i * 1.0F * 35 / 4 + 7F; // 设置当前字符的x轴坐标
            g2.setFont(randomFont()); // 设置随机字体
            g2.setColor(randomColor()); // 设置随机颜色
            g2.drawString(s, 13 + i * 20, 30); // 画图,依次将字符写入到图片的相应位置-------------------
        }

        return image;
    }

}

