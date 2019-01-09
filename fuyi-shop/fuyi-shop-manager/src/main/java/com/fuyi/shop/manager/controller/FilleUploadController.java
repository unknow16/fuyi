package com.fuyi.shop.manager.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName FilleUploadController
 * @Description 文件上传控制器，包括图片
 * @Author fuyi
 * @Date 2019/1/9 16:01
 * @Version 1.0
 */
@Controller
@RequestMapping("/upload")
public class FilleUploadController {


//    @Value("${IMAGE_SERVER_URL}")
//    private String IMAGE_SERVER_URL;

    /**
     * produces: 响应Content-Type类型
     */
    @RequestMapping(value="/pic", produces= MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
    @ResponseBody
    public String fileUpload(MultipartFile uploadFile) {
        Map<String, Object> map = new HashMap<>();

        //1.取文件的扩展名
        String originalFilename = uploadFile.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        //2.创建一个FastDFS的客户端
        try {
            //3.执行上传处理
            //FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
            //String path = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);

            //4.拼接返回的url和ip地址，
            String url = "https://www.baidu.com/img/bd_logo1.png?where=super";//IMAGE_SERVER_URL + path;

            //5.返回map
            map.put("error", 0);
            map.put("url", url);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", 1);
            map.put("message", "图片上传错误");
        }

        return JSON.toJSONString(map);
    }
}
