package com.cn.commons.utils;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;

/**
 * @author SaddyFire
 * @date 2022/3/8
 * @TIME:11:35
 */
public class PdfUtil {
    public static void READPDF(InputStream inputFile){
        //创建文档对象
        PDDocument doc = null;
        String content="";
        try {
            //加载一个pdf对象
            doc = PDDocument.load(inputFile);
            //获取一个PDFTextStripper文本剥离对象
            PDFTextStripper textStripper =new PDFTextStripper();
            content=textStripper.getText(doc);
            System.out.println("内容:"+content);
            System.out.println("全部页数"+doc.getNumberOfPages());
            //关闭文档
            doc.close();
        } catch (Exception e) {

        }
    }

    public static String resolvePdf(InputStream inputFile){
        //创建文档对象
        PDDocument doc = null;
        String content="";
        try {
            //加载一个pdf对象
            doc = PDDocument.load(new BufferedInputStream(inputFile));
            //获取一个PDFTextStripper文本剥离对象
            PDFTextStripper textStripper =new PDFTextStripper();
            content=textStripper.getText(doc);
            //关闭文档
            doc.close();
            return content;
        } catch (Exception e) {
            return "解析错误";
        }

    }

}
