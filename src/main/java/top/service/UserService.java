package top.service;

import top.entity.User;

/**
    * @Description //userService接口
    * @Param
        **/
public interface UserService {
    User getuserByname(String username);
    void saveUser(User user);
    void deletUser(String username);
    void resetUser(String username,String password);
}
