// 代码生成时间: 2025-08-05 19:05:05
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.selector.spi.StrategySelector;
import org.hibernate.dialect.H2Dialect;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// 性能测试脚本，用于测试Hibernate操作的执行时间
public class HibernatePerformanceTest {

    private static SessionFactory sessionFactory;

    // 初始化SessionFactory
    public static void initSessionFactory() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("hibernate.cfg.xml"));
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();
        sessionFactory = new Configuration().configure().buildSessionFactory(serviceRegistry);
    }

    // 关闭SessionFactory
    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    // 执行性能测试
    public static void performanceTest() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            // 模拟业务操作，例如查询操作
            long startTime = System.currentTimeMillis();
            // 这里添加具体业务操作
            // 例如：session.createQuery("FROM EntityName").list();
            long endTime = System.currentTimeMillis();

            System.out.println("Operation took: " + (endTime - startTime) + " ms");

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSessionFactory();
        }
    }

    public static void main(String[] args) {
        try {
            initSessionFactory();
            performanceTest();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSessionFactory();
        }
    }
}
