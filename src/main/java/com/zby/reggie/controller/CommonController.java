package com.zby.reggie.controller;

import com.zby.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
    @Value("${reggie.path}")
    private String path;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        log.info(file.toString());

        //获取原始文件名
        String originalFilename = file.getOriginalFilename();//9ec6fc2d-50d2-422e-b954-de87dcd04198.jpeg
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));//.jpeg
        //创建目录
        File dir =new File(path);
        //如果不存在 就创建
        if(!dir.exists()){
            dir.mkdirs();
        }

        //使用UUID生成不重复的文件名 避免替换
        String name = UUID.randomUUID().toString()+substring;//hfssajhfsjd
        //将文件保存到指定位置
        try {
            file.transferTo(new File(path+name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(name);

    }

    /**
     * 文件下载
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void downLoad(String name, HttpServletResponse response){

        try {
            //输入流
            FileInputStream fileInputStream = new FileInputStream(new File(path+name));
            //输出流
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");
            int len=0;
            byte[] bytes= new byte[1024];
            while((len=fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
