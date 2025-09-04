// 代码生成时间: 2025-09-04 11:09:33
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 哈希值计算工具类
 *
 * @author YourName
 * @version 1.0
 *
 * 提供计算字符串的哈希值的功能，支持SHA-256哈希算法。
 * 代码结构清晰，易于理解，包含适当的错误处理，
 * 添加必要的注释和文档，遵循JAVA最佳实践。
 */
public class HashCalculator {

    // 指定哈希算法
    private static final String HASH_ALGORITHM = "SHA-256";

    /**
     * 计算字符串的哈希值
     *
     * @param input 要计算哈希值的字符串
     * @return 字符串的哈希值
     * @throws NoSuchAlgorithmException 如果指定的哈希算法不存在
     */
    public static String calculateHash(String input) throws NoSuchAlgorithmException {
        // 使用指定的哈希算法创建MessageDigest实例
        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);

        // 对输入字符串进行哈希处理
        byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

        // 将哈希值转换为Base64编码字符串
        return Base64.getEncoder().encodeToString(hashBytes);
    }

    /**
     * 主方法，用于演示哈希值计算工具的使用方法
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        try {
            // 计算示例字符串的哈希值
            String input = "Hello, World!";
            String hashValue = calculateHash(input);

            // 打印哈希值
            System.out.println("哈希值: " + hashValue);
        } catch (NoSuchAlgorithmException e) {
            // 处理哈希算法不存在的异常
            System.err.println("错误: 指定的哈希算法不存在。");
            e.printStackTrace();
        }
    }
}