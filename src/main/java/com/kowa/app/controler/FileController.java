package com.kowa.app.controler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kowa.app.config.ProjectConfig;
import com.kowa.app.jsonmodel.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
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
@Controller
public class FileController {

    /**
     * Json解析器
     */
    ObjectMapper mapper = new ObjectMapper();

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
        String filename = System.currentTimeMillis() + "k.jpg";
        try {
            Files.copy(file.getInputStream(), Paths.get(ProjectConfig.getImageSource(), filename));
            return mapper.writeValueAsString(new Result("上传成功！", ProjectConfig.LocalImageurl + filename));
        } catch (IOException e) {
            return mapper.writeValueAsString(new Result("上传失败！"));
        }
    }

    /**
     * 访问图片的公共接口
     *
     * @param filename 文件名
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "static/file/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ProjectConfig.getImageSource(), filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
