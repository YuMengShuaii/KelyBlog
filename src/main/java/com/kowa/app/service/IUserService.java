package com.kowa.app.service;

import com.kowa.app.vo.UserVo;

/**
 * Created by LDD on 17/7/3.
 */
public interface IUserService {
     UserVo  login(String username, String password);

     UserVo  isLogin();

     void    logout();

     UserVo  editInfo(String name,String phone,int sex,String content);

     UserVo  register(String nikename,String username,String password);

     UserVo  editFace(String face);

     boolean checkUname(String uname);
}
