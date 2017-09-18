package com.sr.test;

import com.sr.batis.mapper.UserMapper;
import com.sr.batis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class findUserTest {
    static SqlSessionFactory ssf = null;
    static  SqlSession session = null;
    @Before
    public void init () throws Exception{
        String confFilePath="SqlMapConfig.xml";
        InputStream is = Resources.getResourceAsStream(confFilePath);
        ssf = new SqlSessionFactoryBuilder().build(is);
        session = ssf.openSession();
        System.out.println("session initialized……");
    }
    @After
    public void destroy(){
        if(session != null){
            session.close();
            System.out.println("session closed……");
        }
    }

    //根据id查询对象
    @Test
    public void findUserByIdTest() throws Exception {
        User user = session.selectOne("test.findUserById", 1);
        System.out.println(user);
    }

    //根据id查询对象 mapper 接口开发
    @Test
    public void findUserByIdTest_UserMapper() throws Exception {
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.findUserById(1);
        System.out.println(user);
    }

    //根据pojo入参查询
    @Test
    public void findUserByVOTest_UserMapper() throws Exception {
        User vo = new User();
        vo.setId(1);
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.findUserByVo(vo);
        System.out.println(user);
    }

    //根据map入参查询
    @Test
    public void findUserByMapTest_UserMapper() throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id",1);
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.findUserByMap(map);
        System.out.println(user);
    }

    //根据id查询name
    @Test
    public void findNameByIdTest() throws Exception {

        UserMapper userMapper = session.getMapper(UserMapper.class);

        String name  = userMapper.findUserNameById(1) ;
        System.out.println("userid=1:name:"+name);
    }

    //动态sql
    @Test
    public void findNameByNameAndSexTest() throws Exception {

        User vo = new User();
        vo.setUsername("王五");
        vo.setSex("2");
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.findUserByNameAndSex(vo);
        System.out.println(user);
    }
}