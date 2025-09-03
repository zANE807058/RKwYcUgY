// 代码生成时间: 2025-09-03 14:47:29
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.cfg.ConfigurationException;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import java.net.URL;
import java.net.MalformedURLException;
import org.apache.commons.validator.routines.UrlValidator;

public class UrlValidatorService {

    private static SessionFactory sessionFactory;

    static {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory(registry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public boolean validateUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            // 使用Apache Commons Validator库验证URL格式
            UrlValidator urlValidator = new UrlValidator();
            return urlValidator.isValid(urlString);
        } catch (MalformedURLException e) {
            // 如果URL格式不正确，则返回false
            return false;
        }
    }

    public static void main(String[] args) {
        UrlValidatorService urlValidatorService = new UrlValidatorService();
        String testUrl = "http://www.example.com";
        boolean isValid = urlValidatorService.validateUrl(testUrl);
        System.out.println("Is the URL valid? " + isValid);
    }
}
