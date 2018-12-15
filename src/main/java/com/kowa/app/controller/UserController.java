package com.kowa.app.controller;

import com.kowa.app.aop.LoginHelper;
import com.kowa.app.jsonmodel.JsonUtils;
import com.kowa.app.service.IUserService;
import com.kowa.app.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 *
 * @Auther yumengshuai【kely】
 * @Date   17/7/3 下午1:56
 * User控制器
 */
@RestController
@RequestMapping("/api/member")
public class UserController {

    /**
     * User服务类
     */
    @Autowired
    private IUserService userService;

    /**
     * 检查是否登录
     * @return json
     * @throws IOException json解析异常
     */
    @RequestMapping(value = "/islogin", method = RequestMethod.GET)
    @ResponseBody
    public String isLogin() {
        UserVo currentUser = userService.isLogin();
        if (currentUser == null) {
            return JsonUtils.getErrorJson("未登录!");
        } else {
            return JsonUtils.getSuccessJson("已登录",currentUser);
        }
    }

    /**
     * 登录
     * @param uname     用户名
     * @param password  密码
     * @return
     * @throws IOException json解析异常
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String login(@RequestParam("username") String uname, @RequestParam("password") String password) {
        UserVo user = userService.login(uname,password);
        if (user==null){
            return JsonUtils.getErrorJson("登录失败，请重试！");
        }else{
            return JsonUtils.getSuccessJson("登陆成功！",user);
        }
    }

    /**
     * 退出登录
     * @return json
     * @throws IOException json解析异常
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public String logout() {
        userService.logout();
        return JsonUtils.getSuccessJson("退出登录成功！","");
    }

    /**
     * 修改个人资料
     * @param name    昵称
     * @param phone   手机号
     * @param sex     性别
     * @param content 个人签名
     * @return
     * @throws IOException  json解析异常
     */
    @RequestMapping(value = "/editinfo", method = RequestMethod.GET)
    @ResponseBody
    @LoginHelper
    public String editInfo(@RequestParam("nikename") String name
            , @RequestParam("phone") String phone
            , @RequestParam("sex") int sex
            , @RequestParam("content") String content) {
        UserVo user = userService.editInfo(name,phone,sex,content);
        return JsonUtils.getSuccessJson("修改成功！",user);
    }

    /**
     * 注册账号
     * @param uname     用户名
     * @param password  密码
     * @return          json
     * @throws IOException
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String saveUser(@RequestParam("nikename") String nikename,@RequestParam("username") String uname, @RequestParam("password") String password) {
        if (!userService.checkUname(uname)){
           return JsonUtils.getErrorJson("该账号已存在！");
        }
        UserVo user = userService.register(nikename,uname,password);
        if (user==null){
            return JsonUtils.getErrorJson("注册失败，请检查账号密码！");
        }else{
            return JsonUtils.getSuccessJson("注册成功！",user);
        }
    }

    /**
     * 修改头像
     * @param face 头像url
     * @return
     */
    @RequestMapping(value = "/updataface",method = RequestMethod.POST)
    @ResponseBody
    @LoginHelper
    public String upDataFace(@RequestParam("face") String face) {
       UserVo user =  userService.editFace(face);
       if (user==null){
           return JsonUtils.getErrorJson("头像修改失败！");
       }else{
           return JsonUtils.getSuccessJson("头像修改成功！",user);
       }
    }
}  
