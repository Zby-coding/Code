import com.zby.mapper.UserMapper;
import com.zby.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;
/*
* mapper代理
* */
public class UserTest2 {
    public static void main(String[] args) throws Exception {
        // 加载配置文件，获取sqlSessionFactory对象
        InputStream resourceAsStream = Resources.getResourceAsStream("com/zby/config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获取sqlSession对象 执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.selectAll().forEach(System.out::println);
        // 释放资源
        sqlSession.close();
        resourceAsStream.close();


    }
}
