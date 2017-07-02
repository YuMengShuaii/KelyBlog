package com.kowa.app.controler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kowa.app.config.ProjectConfig;
import com.kowa.app.dao.UserDao;
import com.kowa.app.jsonmodel.result;
import com.kowa.app.po.UserPo;
import com.kowa.app.sessionutils.ContextHolder;
import com.kowa.app.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class UserController {

    private final ResourceLoader resourceLoader;
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private UserDao userDao;

    @Autowired
    public UserController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @RequestMapping(value = "/islogin", method = RequestMethod.GET)
    @ResponseBody
    public String isLogin() throws IOException {
        result re = new result();
        UserPo currentUser = ContextHolder.getCurrentMember();
        if (currentUser == null) {
            re.setResult(0);
            re.setMessage("未登录");
        } else {
            re.setResult(1);
            re.setMessage("已登录");
            re.setData(new UserVo(currentUser));
        }
        return mapper.writeValueAsString(re);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String login(@RequestParam("username") String uname, @RequestParam("password") String password) throws IOException {
        result re = new result();
        if (uname.isEmpty() || password.isEmpty()) {
            re.setResult(0);
            re.setMessage("输入不合法！");
        }
        List<UserPo> user = userDao.findByUsername(uname);
        if (user == null || user.size() == 0) {
            re.setResult(0);
            re.setMessage("用户名错误，核对后输入！");
        } else {
            if (user.get(0).getPassword().equals(password)) {
                re.setResult(1);
                re.setMessage("登陆成功！");
                re.setData(new UserVo(user.get(0)));
                ContextHolder.setCurrentMember(user.get(0), 60 * 60 * 24 * 14);
            }
        }
        return mapper.writeValueAsString(re);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public String logout() throws IOException {
        result re = new result();
        if (ContextHolder.getCurrentMember() != null) {
            ContextHolder.clearCurrentMember();
            ContextHolder.clearSesstion();
        }
        re.setResult(1);
        re.setMessage("退出登录成功！");
        return mapper.writeValueAsString(re);
    }

    @RequestMapping(value = "/editinfo", method = RequestMethod.GET)
    @ResponseBody
    public String editInfo(@RequestParam("nikename") String name
            , @RequestParam("phone") String phone
            , @RequestParam("sex") int sex
            , @RequestParam("content") String content) throws IOException {
        result re = new result();
        UserPo user = ContextHolder.getCurrentMember();
        if (user == null) {
            re.setMessage("尚未登录！");
            re.setResult(0);
            return mapper.writeValueAsString(re);
        } else {
            user.setName(name);
            user.setPhone(phone);
            user.setSex(sex);
            user.setContent(content);
            userDao.save(user);
            ContextHolder.updataCurrentMember(user);
            re.setMessage("修改成功！");
            re.setResult(1);
            re.setData(new UserVo(user));
            return mapper.writeValueAsString(re);
        }
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        String filename = System.currentTimeMillis() + "k.jpg";
        System.out.println(filename);
        Files.copy(file.getInputStream(), Paths.get(ProjectConfig.IMAGESOURCE, filename));
        return ProjectConfig.LocalImageurl + filename;
    }

    @RequestMapping(method = RequestMethod.GET, value = "static/file/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {

        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ProjectConfig.IMAGESOURCE, filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String saveUser(@RequestParam("username") String uname, @RequestParam("password") String password) throws IOException {
        result re = new result();
        if (uname.isEmpty() || password.isEmpty()) {
            re.setResult(0);
            re.setMessage("注册失败：用户名或者密码为空！");
            return mapper.writeValueAsString(re);
        } else {
            UserPo user = new UserPo();
            user.setUsername(uname);
            user.setPassword(password);
            userDao.save(user);
            re.setResult(1);
            re.setMessage("注册成功！");
            ContextHolder.setCurrentMember(user, 60 * 60 * 24 * 14);
            re.setData(new UserVo(user));
            return mapper.writeValueAsString(re);
        }
    }
}  
