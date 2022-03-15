package com.cn.resume.controller;

import com.alibaba.excel.EasyExcel;
import com.cn.commons.utils.BASE64Utils;
import com.cn.commons.utils.HttpUtils;
import com.cn.model.pojo.Resume;
import com.cn.model.vo.PageResult;
import com.cn.resume.service.ResumeService;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SaddyFire
 * @date 2022/3/8
 * @TIME:10:44
 */
@RequestMapping("/resume")
@RestController
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    //简历显示 type: 1 为正序 , 2 为倒叙
    @GetMapping
    public ResponseEntity list(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "20") Integer pagesize,
                               Integer rule,
                               String type
                               ){
        PageResult list = resumeService.list(page, pagesize, rule, type);
        return ResponseEntity.ok(list);
    }

    //简历导入
    @PostMapping
    public ResponseEntity upload(MultipartFile pdfFile){
        Boolean sucess = resumeService.upload(pdfFile);
        return sucess == true ? ResponseEntity.ok(200):ResponseEntity.status(500).build();
    }

    //简历解析
    @PostMapping("/resolve")
    public ResponseEntity resolve(MultipartFile pdfFile){
        BASE64Utils base64Utils = new BASE64Utils();
        String pdfStr = null;
        try {
            InputStream inputStream = pdfFile.getInputStream();
            pdfStr = base64Utils.PDFToBase64((FileInputStream) inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String host = "http://jljxjk.market.alicloudapi.com";
        String path = "/aliyunapp/aliyunservice.aspx";
        String method = "POST";
        String appcode = "adc4473074cb49c1a024e80be86328f5";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        //bodys.put("content", "");
        //bodys.put("ext", "pdf");
        String bodys = "cid=1&content="+ pdfStr +"&ext=.pdf";


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            //System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //简历信息详情
    @GetMapping("/{id}")
    public ResponseEntity info(@PathVariable("id") Long id){
        Resume resume = resumeService.getById(id);
        return ResponseEntity.ok(resume);
    }
    //简历修改
    @PutMapping
    public ResponseEntity modify(@RequestBody Resume resume){
        return resumeService.modify(resume);
    }

    //导出word
    @RequestMapping("/output")
    public void resumeOutPut(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "20") Integer pagesize){
        try {


            String filename = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "_简历导出";
            String userAgent = request.getHeader("User-Agent");
            if(!StringUtils.isEmpty(userAgent) && (userAgent.contains("MSIE")||userAgent.contains("Trident"))){
                filename = URLEncoder.encode(filename,"UTF-8");
            }else {
                filename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
            }
            response.setContentType("application/json.ms-exce");
            response.setCharacterEncoding("utf-8");
            response.addHeader("Content-Disposition","filename = " + filename + ".xlsx");
            PageResult pageResult = resumeService.list(page, pagesize, null, null);
            List<?> items = pageResult.getItems();
            EasyExcel.write(response.getOutputStream(),Resume.class).sheet("sheet").doWrite(items);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
