package com.cn.resume;

import com.cn.commons.utils.PdfUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author SaddyFire
 * @date 2022/3/8
 * @TIME:11:35
 */
@SpringBootTest(classes = ResumeApplication.class)
@RunWith(SpringRunner.class)
public class ResumeTest {
    //pdf解析测试
    @Test
    public void resolve(){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("D:\\tmp\\浅绿小清新简约通用求职简历模板.pdf");
            PdfUtil.READPDF(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
