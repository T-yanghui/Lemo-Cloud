package top.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.entity.User;

/**
 * @program: SpringMVC_template
 * @description: userMapper
 * @author: T_yang
 * @create: 2021-07-14 23:52
 **/
public interface userMapper {
    @Select("select * from user_info where username = #{username}" )
    User getUserByname(@Param("username") String username);

    @Insert("insert into user_info(username,password)values(#{username},#{password})")
    void saveUser(User user);
    @Delete("delete from user_info where username=#{username}")
    void delete(String username);
}
