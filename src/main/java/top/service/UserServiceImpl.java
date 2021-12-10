package top.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.dao.UserDao;
import top.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public User getuserByname(String username){
        return userDao.getuserByname(username);
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public void deletUser(String username) {
        userDao.deleteUser(username);
    }

    @Override
    public void resetUser(String username, String password) {
        userDao.deleteUser(username);
        userDao.saveUser(new User(username,password));
    }
}
