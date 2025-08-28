// 代码生成时间: 2025-08-28 20:54:06
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import java.net.URL;
import java.net.MalformedURLException;
import javax.persistence.Entity;
import javax.persistence.Id;

// 实体类代表URL
@Entity
public class Url {
    @Id
    private String id;
    private String url;

    public Url() {}

    public Url(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

// 服务类用于验证URL有效性
public class UrlValidatorService {

    private final SessionFactory sessionFactory;

    public UrlValidatorService() {
        // 建立SessionFactory
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new Configuration().configure().buildSessionFactory(registry);
    }

    public void validateUrl(String url) {
        try {
            // 尝试将字符串转换为URL对象
            URL urlObj = new URL(url);
            System.out.println("Valid URL: " + url);
        } catch (MalformedURLException e) {
            // 捕获URL格式异常
            System.out.println("Invalid URL: " + url);
        }
    }

    public void addUrlToDatabase(Url url) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(url);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void main(String[] args) {
        UrlValidatorService validatorService = new UrlValidatorService();
        // 测试URL验证功能
        validatorService.validateUrl("http://www.example.com");
        validatorService.validateUrl("invalid-url");

        // 添加URL到数据库
        Url url = new Url("1", "http://www.example.com");
        validatorService.addUrlToDatabase(url);
    }
}