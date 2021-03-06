package com.kowa.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kowa.app.config.ProjectConfig;
import com.kowa.app.jsonmodel.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 文件服务Controller
 *
 * @Auther yumengshuai【kely】
 * @Date 17/7/3 下午7:18
 */
@RestController
@RequestMapping("/api/file")
public class FileController {

    /**
     * 资源加载器
     */
    private final ResourceLoader resourceLoader;

    @Autowired
    public FileController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return json
     * @throws JsonProcessingException json解析异常
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(@RequestParam MultipartFile file) throws JsonProcessingException {
        String filename = System.currentTimeMillis() + "kowo_file.jpg";
        try {
            Files.copy(file.getInputStream(), Paths.get(ProjectConfig.getImageSource(), filename));
            return JsonUtils.getSuccessJson("上传成功！", ProjectConfig.LocalImageurl + filename);
        } catch (IOException e) {
            return JsonUtils.getErrorJson("上传失败！");
        }
    }

}
