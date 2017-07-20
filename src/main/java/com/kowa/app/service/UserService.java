package com.kowa.app.service;

import com.kowa.app.dao.UserDao;
import com.kowa.app.po.UserPo;
import com.kowa.app.context.ContextHolder;
import com.kowa.app.utils.Utils;
import com.kowa.app.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/2.
 */
@Service("userService")
public class UserService implements IUserService {
    /**
     * User表操作类
     */
    @Autowired
    private UserDao userDao;


    @Override
    public UserVo login(String username, String password){
        UserPo user = userDao.findByUsername(username);
        if (user==null){
            return null;
        }else if(!user.getPassword().equals(password)) {
            return null;
        }else {
            ContextHolder.setCurrentMember(user, 60 * 60 * 24 * 14);
            return new UserVo(user);
        }
    }

    @Override
    public UserVo isLogin() {
        UserPo userPo = ContextHolder.getCurrentMember();
        if (userPo==null){
            return null;
        }else{
            return new UserVo(userPo);
        }
    }

    @Override
    public void logout() {
        if (ContextHolder.getCurrentMember() != null) {
            ContextHolder.clearCurrentMember();
            ContextHolder.clearSesstion();
        }
    }

    @Override
    public UserVo editInfo(String name, String phone, int sex, String content) {
        UserPo user = ContextHolder.getCurrentMember();
        if (user==null){
            return null;
        }else{
            user.setName(name);
            user.setPhone(phone);
            user.setSex(sex);
            user.setContent(content);
            userDao.save(user);
            ContextHolder.updataCurrentMember(user);
            return new UserVo(user);
        }
    }

    @Override
    public UserVo register(String nikename, String username, String password) {
        if (Utils.isEmpty(nikename)||Utils.isEmpty(username)||Utils.isEmpty(password)){
            return null;
        }else{
            UserPo user = new UserPo();
            user.setUsername(username);
            user.setName(nikename);
            user.setPassword(password);
            userDao.save(user);
            ContextHolder.setCurrentMember(user, 60 * 60 * 24 * 14);
            return new UserVo(user);
        }
    }

    @Override
    public UserVo editFace(String face) {
        if (Utils.isEmpty(face)){
            return null;
        }
        UserPo user = ContextHolder.getCurrentMember();
        if (user==null){
            return null;
        }else{
            user.setFace(face);
            userDao.save(user);
            ContextHolder.updataCurrentMember(user);
            return new UserVo(user);
        }
    }

    @Override
    public boolean checkUname(String uname) {

        if (userDao.findByUsername(uname)==null){return true;}
        return false;
    }


}
