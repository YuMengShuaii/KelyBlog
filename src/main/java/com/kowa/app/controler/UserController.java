package com.kowa.app.controler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kowa.app.dao.UserDao;
import com.kowa.app.jsonmodel.Result;
import com.kowa.app.service.IUserService;
import com.kowa.app.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;

/**
 *
 * @Auther yumengshuai【kely】
 * @Date   17/7/3 下午1:56
 * User控制器
 */
@Controller
public class UserController {
    /**
     * Json解析器
     */
    ObjectMapper mapper = new ObjectMapper();

    /**
     * User表操作类
     */
    @Autowired
    private UserDao userDao;


    @Autowired
    private IUserService userService;

    /**
     * 检查是否登录
     * @return json
     * @throws IOException json解析异常
     */
    @RequestMapping(value = "/islogin", method = RequestMethod.GET)
    @ResponseBody
    public String isLogin() throws IOException {
        UserVo currentUser = userService.isLogin();
        if (currentUser == null) {
            return mapper.writeValueAsString(new Result("未登录!"));
        } else {
            return mapper.writeValueAsString(new Result("已登录",currentUser));
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
    public String login(@RequestParam("username") String uname, @RequestParam("password") String password) throws IOException {
        UserVo user = userService.login(uname,password);
        if (user==null){
            return mapper.writeValueAsString(new Result("登录失败，请重试！"));
        }else{
            return mapper.writeValueAsString(new Result("登陆成功！",user));
        }
    }

    /**
     * 退出登录
     * @return json
     * @throws IOException json解析异常
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public String logout() throws IOException {
        userService.logout();
        return mapper.writeValueAsString(new Result("退出登录成功！"));
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
    public String editInfo(@RequestParam("nikename") String name
            , @RequestParam("phone") String phone
            , @RequestParam("sex") int sex
            , @RequestParam("content") String content) throws IOException {
        UserVo user = userService.editInfo(name,phone,sex,content);
        if (user == null) {
            return mapper.writeValueAsString(new Result("尚未登录！"));
        } else {
            return mapper.writeValueAsString(new Result("修改成功！",user));
        }
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
    public String saveUser(@RequestParam("nikename") String nikename,@RequestParam("username") String uname, @RequestParam("password") String password) throws IOException {
        if (!userService.checkUname(uname)){
           return mapper.writeValueAsString(new Result("该账号已存在！"));
        }
        UserVo user = userService.register(nikename,uname,password);
        if (user==null){
            return mapper.writeValueAsString(new Result("注册失败，请检查账号密码！"));
        }else{
            return mapper.writeValueAsString(new Result("注册成功！",user));
        }
    }
}  
