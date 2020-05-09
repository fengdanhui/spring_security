package com.imooc.web.controller;

import com.imooc.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * @date 2020/5/7
 */
@RestController
@RequestMapping("/file")
public class FileController {
    private String folder = "C:\\zoo\\upload";
    /**
     * 3.9文件上传
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping
    public FileInfo upload(MultipartFile file) throws Exception {

        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile = new File(folder, new Date().getTime() + ".txt");

        //把传递过来的file写到本地的localFile文件
        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }

    /**
     * 3.9文件下载
     * @param id
     * @param request
     * @param response
     */
    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        /**
         * jdk1.7，关闭流，try-with-resource
         */
        try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
             OutputStream outputStream = response.getOutputStream();) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename-test.txt");

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();

        }
    }
}
