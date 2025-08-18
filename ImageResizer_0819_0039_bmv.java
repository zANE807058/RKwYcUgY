// 代码生成时间: 2025-08-19 00:39:09
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

// 定义一个用于图片尺寸调整的服务类
public class ImageResizer {

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;

    // 调整图片尺寸的方法
    public void resizeImage(String sourcePath, String targetPath, int targetWidth, int targetHeight) {
        try {
            File inputFile = new File(sourcePath);
            File outputFile = new File(targetPath);

            // 读取图片文件
            BufferedImage originalImage = ImageIO.read(inputFile);

            // 如果源文件不存在或不是图片文件，则抛出异常
            if (originalImage == null) {
                throw new IllegalArgumentException("Source file is not a valid image.");
            }

            // 创建新的缓冲图像，用于调整尺寸
            BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, originalImage.getType());

            // 绘制调整后的图像
            resizedImage.getGraphics().drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);

            // 保存调整后的图像文件
            ImageIO.write(resizedImage, "jpg", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 批量调整图片尺寸的方法
    public void batchResizeImages(String directoryPath, int targetWidth, int targetHeight) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files == null) {
            throw new IllegalArgumentException("Directory is not valid or empty.");
        }

        // 遍历目录中的所有文件
        Arrays.stream(files).forEach(file -> {
            try {
                // 构建目标文件路径
                String fileName = file.getName();
                String targetPath = directoryPath + "/resized_" + fileName;

                // 调整图片尺寸
                resizeImage(file.getAbsolutePath(), targetPath, targetWidth, targetHeight);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // 测试方法，用于演示
    public static void main(String[] args) {
        ImageResizer resizer = new ImageResizer();
        resizer.batchResizeImages("/path/to/image/directory", DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
