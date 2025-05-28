import com.zby.mapper.BrandMapper;
import com.zby.pojo.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class BrandTest {
    @Test
    public void selectAll() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("com/zby/config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.selectAll().forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void selectById() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("com/zby/config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        System.out.println(mapper.selectById(1));
        sqlSession.close();
    }

    // 散装查询
    @Test
    public void selectByCondition() throws IOException {
        int status = 1;
        String bname = "华为";
        String cname = "华为";
        // 处理参数
        bname = "%" + bname + "%";
        cname = "%" + cname + "%";
        InputStream resourceAsStream = Resources.getResourceAsStream("com/zby/config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        System.out.println(mapper.selectByCondition(status, cname, bname));
        sqlSession.close();
    }

    // 对象查询
    @Test
    public void selectByConditionObject() throws IOException {
        int status = 1;
        String brandName = "华为";
        String companyName = "华为";
        brandName = "%" + brandName + "%";
        companyName = "%" + companyName + "%";
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        InputStream resourceAsStream = Resources.getResourceAsStream("com/zby/config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        System.out.println(mapper.selectByConditionObject(brand));
        sqlSession.close();
    }

    // 动态sql
    @Test
    public void selectByDynamicSql() throws IOException {
        int status = 1;
        Brand brand = new Brand();
        brand.setStatus(status);
        // brand.setBrandName("华为");
        // brand.setCompanyName("华为");
        InputStream resourceAsStream = Resources.getResourceAsStream("com/zby/config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        System.out.println(mapper.selectByDynamicSql(brand));
        sqlSession.close();
    }

    // 单条件查询
    @Test
    public void selectByDynamicSqlSingleQuery() throws IOException {
        int status = 1;
        String brandName = "华为";
        String companyName = "华为";
        brandName = "%" + brandName + "%";
        companyName = "%" + companyName + "%";
        Brand brand = new Brand();
        //brand.setStatus(status);
        //brand.setBrandName(brandName);
        //brand.setCompanyName(companyName);
        InputStream resourceAsStream = Resources.getResourceAsStream("com/zby/config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        System.out.println(mapper.selectByDynamicSqlSingleQuery(brand));
        sqlSession.close();
    }

    // 添加
    @Test
    public void add() throws IOException {
        int status = 1;
        String brandName = "华为";
        String companyName = "华为";
        int ordered = 100;
        String description = "华为5G,遥遥领先，龙头企业";
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setOrdered(ordered);
        brand.setDescription(description);
        //brand.setStatus(status);
        //brand.setBrandName(brandName);
        //brand.setCompanyName(companyName);
        InputStream resourceAsStream = Resources.getResourceAsStream("com/zby/config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(); // 传入true设置自动提交事务
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.add(brand);
        Integer id = brand.getId();
        System.out.println("返回的主键id值为:" + id);

        sqlSession.commit();
        sqlSession.close();
    }

    // map条件查询
    @Test
    public void selectByConditionMap() throws IOException {
        int status = 1;
        String description = "华为5G技术，龙头企业，遥遥领先！";
        String brandName = "华为";
        String companyName = "华为";
        int ordered = 100;
        // 处理参数
        brandName = "%" + brandName + "%";
        companyName = "%" + companyName + "%";
        Map map = new HashMap();
        map.put("status", status);
        map.put("brandName", brandName);
        map.put("companyName", companyName);
        map.put("description", description);
        map.put("ordered", ordered);
        InputStream resourceAsStream = Resources.getResourceAsStream("com/zby/config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        System.out.println(mapper.selectByConditionMap(map));
        sqlSession.close();
    }

    // 修改全部数据
    @Test
    public void updateAll() throws IOException {
        int status = 1;
        String brandName = "华为3";
        String companyName = "华为3";
        int ordered = 200;
        String description = "华为5G,遥遥领先，龙头企业";
        int id = 7;
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setOrdered(ordered);
        brand.setDescription(description);
        brand.setId(id);
        //brand.setStatus(status);
        //brand.setBrandName(brandName);
        //brand.setCompanyName(companyName);
        InputStream resourceAsStream = Resources.getResourceAsStream("com/zby/config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(); // 传入true设置自动提交事务
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.updateAll(brand);
        sqlSession.commit();
        sqlSession.close();
    }    // 修改全部数据
    @Test
    public void dynamicUpdateAll() throws IOException {
        int status = 1;
        String brandName = "华为2";
        String companyName = "华为3";
        int ordered = 800;
        String description = "华为5G,遥遥领先，龙头企业";
        int id = 6;
        Brand brand = new Brand();
        // brand.setStatus(status);
        brand.setBrandName(brandName);
        // brand.setCompanyName(companyName);
        brand.setOrdered(ordered);
        brand.setDescription(description);
        brand.setId(id);
        //brand.setStatus(status);
        //brand.setBrandName(brandName);
        //brand.setCompanyName(companyName);
        InputStream resourceAsStream = Resources.getResourceAsStream("com/zby/config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(); // 传入true设置自动提交事务
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.dynamicUpdateAll(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void dynamicUpdateSingle() throws IOException {
        int status = 1;
        String brandName = "华为2";
        String companyName = "华为3";
        int ordered = 800;
        String description = "华为5G,遥遥领先，龙头企业";
        int id = 6;
        Brand brand = new Brand();
        //brand.setStatus(status);
        brand.setBrandName(brandName);
        //brand.setCompanyName(companyName);
        brand.setOrdered(ordered);
        brand.setDescription(description);
        brand.setId(id);

        InputStream resourceAsStream = Resources.getResourceAsStream("com/zby/config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(); // 传入true设置自动提交事务
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.dynamicUpdateSingle(brand);
        sqlSession.commit();
        sqlSession.close();
    }
    // 删除一条数据
    @Test
    public void deleteById() throws IOException {
        int id = 6;
        InputStream resourceAsStream = Resources.getResourceAsStream("com/zby/config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteById(id);
        sqlSession.close();
    }
    @Test
    public void deleteByIds() throws IOException {
        int[] ids = {3,7};
        InputStream resourceAsStream = Resources.getResourceAsStream("com/zby/config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteByIds(ids);
    }
}
