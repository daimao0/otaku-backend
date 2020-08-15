package com.otaku.otaku;

import com.otaku.otaku.common.utils.BCPEUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ：weihuanwen
 * @date ：Created in 2019/5/23 00:14
 * @description ：
 * @version: 1.0
 */
@SpringBootTest
public class BCPETest {

    @Test
    public void testEncode(){
        //生成密码@mima@的密文
        String encode = BCPEUtils.encode("123456");
        //$2a$10$iHyiM/E1jdQaP32HmXDfHe.APmCHH7uyvFn4w2Z6stdwV/VaO49ru
        System.out.println(encode);
    }
 
    @Test
    public void testMatch(){
        String encodePWD = "$2a$10$iHyiM/E1jdQaP32HmXDfHe.APmCHH7uyvFn4w2Z6stdwV/VaO49ru";
        boolean result = BCPEUtils.matches("@mima@", encodePWD);
        System.out.println(result);
    }
}