package top.dao;

import top.entity.User;
import top.mapper.userMapper;

/**
 * @program: SpringMVC_template
 * @description: userdao实现类
 * @author: T_yang
 * @create: 2021-07-14 23:58
 **/
public class UserDaoImpl implements UserDao {
    private userMapper userMapper;

    public void setUserMapper(userMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void saveUser(User user) {
        this.userMapper.saveUser(user);
    }

    @Override
    public User getuserByname(String username) {
        return this.userMapper.getUserByname(username);
    }

    @Override
    public void deleteUser(String username) {
        this.userMapper.delete(username);
    }
}
