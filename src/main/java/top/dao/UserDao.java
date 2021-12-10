package top.dao;

import top.entity.User;

public interface UserDao {
    User getuserByname(String username);
    void saveUser(User user);
    void deleteUser(String username);
}