package com.cn.commons.utils;

import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * @author SaddyFire
 * @date 2022/3/10
 * @TIME:21:23
 */
public class BASE64Utils {

    /**
     * Description: 将pdf文件转换为Base64编码
     * @para  要转的的pdf文件
     * @Author fuyuwei
     * Create Date: 2015年8月3日 下午9:52:30
     */
    public String PDFToBase64(FileInputStream fin) {
        BASE64Encoder encoder = new BASE64Encoder();
        BufferedInputStream bin =null;
        ByteArrayOutputStream baos = null;
        BufferedOutputStream bout =null;
        try {
            bin = new BufferedInputStream(fin);
            baos = new ByteArrayOutputStream();
            bout = new BufferedOutputStream(baos);
            byte[] buffer = new byte[1024];
            int len = bin.read(buffer);
            while(len != -1){
                bout.write(buffer, 0, len);
                len = bin.read(buffer);
            }
            //刷新此输出流并强制写出所有缓冲的输出字节
            bout.flush();
            byte[] bytes = baos.toByteArray();
            return encoder.encodeBuffer(bytes).trim();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fin.close();
                bin.close();
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
