package com.imooc.security.core.validate.code;

import com.imooc.security.core.properties.SecurityProperties;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码生成
 * @date 2020/06/10
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {

    private SecurityProperties securityProperties;

    /**
     * 图形验证码
     * @param request
     * @return
     */
    @Override
    public ImageCode generate(ServletWebRequest request) {
        // 定义图形验证码中绘制的字符的字体
        Font mFont = new Font("Arial Black", Font.PLAIN, 23);
        // 图形验证码的大小
//        int IMG_WIDTH = 72;
//        int IMG_HEIGTH = 27;
        int IMG_WIDTH = ServletRequestUtils.getIntParameter(request.getRequest(), "width", securityProperties.getCode().getImage().getWidth());
        int IMG_HEIGTH = ServletRequestUtils.getIntParameter(request.getRequest(), "height", securityProperties.getCode().getImage().getHeight());

        BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGTH, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        // 填充背景色
        g.fillRect(1, 1, IMG_WIDTH - 1, IMG_HEIGTH - 1);
        // 为图形验证码绘制边框
        g.setColor(new Color(102, 102, 102));
        g.drawRect(0, 0, IMG_WIDTH, IMG_HEIGTH);
        g.setColor(getRandColor(160, 200));
        // 生成随机干扰线
        for (int i = 0; i < 80; i++) {
            int x = random.nextInt(IMG_WIDTH - 1);
            int y = random.nextInt(IMG_HEIGTH - 1);
            int x1 = random.nextInt(6) + 1;
            int y1 = random.nextInt(12) + 1;
            g.drawLine(x, y, x + x1, y + y1);
        }
        g.setColor(getRandColor(160, 200));
        // 生成随机干扰线
        for (int i = 0; i < 80; i++) {
            int x = random.nextInt(IMG_WIDTH - 1);
            int y = random.nextInt(IMG_HEIGTH - 1);
            int x1 = random.nextInt(12) + 1;
            int y1 = random.nextInt(6) + 1;
            g.drawLine(x, y, x - x1, y - y1);
        }
        // 设置绘制字符的字体

        String sRand = "";
        for (int i = 0; i < securityProperties.getCode().getImage().getLength(); i++) {
            String tmp = getRandomChar();
            sRand += tmp;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(tmp, 15 * i + 10, 20);
        }

        return new ImageCode(image, sRand, securityProperties.getCode().getImage().getExpireIn());
    }


    // 获取随机字符串
    private String getRandomChar() {
        int rand = (int) Math.round(Math.random() * 2);
        long itmp = 0;
        char ctmp = '\u0000';
        switch (rand) {
            case 1:
                itmp = Math.round(Math.random() * 25 + 65);
                ctmp = (char) itmp;
                return String.valueOf(ctmp);
            case 2:
                itmp = Math.round(Math.random() * 25 + 97);
                ctmp = (char) itmp;
                return String.valueOf(ctmp);
            default:
                itmp = Math.round(Math.random() * 9);
                return itmp + "";
        }
    }
    // 获取随机颜色的方法
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
