package cn.javaexception.controller;


import cn.javaexception.config.UploadConfig;
import cn.javaexception.model.Brand;
import cn.javaexception.utils.Upload;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import utils.JsonData;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huchao
 * @since 2019-04-04
 */
@RestController
@RequestMapping("/api/img")
public class ImgController {
    @PostMapping("/api/upload")
    public JsonData upload(@RequestParam("file") MultipartFile[] files){
        Client client = new Client();
        List list = new ArrayList();
        for (MultipartFile file : files) {
            //获取后缀
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            String date = format.format(new Date());
            //按日期创建存储路径
            String path="";
            if (StringUtils.isEmpty(extension)){
                path="/upload/"+ UUID.randomUUID().toString();
            }else {
                path = "/upload/"+ UUID.randomUUID().toString()+"."+extension;
            }
            System.out.println(path);
            String url = UploadConfig.SERVERPATH+path;
            System.out.println(url);
            list.add(url);
            WebResource resource = client.resource(url);
            try {
                resource.put(String.class,file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return JsonData.buildSuccess(list);
    }
}

