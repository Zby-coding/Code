import com.zby.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class UserTest {
    public static void main(String[] args) throws Exception {
        // 加载配置文件，获取sqlSessionFactory对象
        InputStream resourceAsStream = Resources.getResourceAsStream("com/zby/config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获取sqlSession对象 执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> userList = sqlSession.selectList("test.selectAll", User.class);
        for (User user : userList) {
            System.out.println(user);
        }
        // 释放资源
        sqlSession.close();
        resourceAsStream.close();


    }
}
