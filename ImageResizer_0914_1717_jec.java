// 代码生成时间: 2025-09-14 17:17:25
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 图片尺寸批量调整器
 * @author Your Name
 * @version 1.0
 */
public class ImageResizer {

    private static final String SOURCE_FOLDER = "path/to/source"; // 源文件夹路径
    private static final String DESTINATION_FOLDER = "path/to/destination"; // 目标文件夹路径
    private static final int NEW_WIDTH = 800; // 新宽度
    private static final int NEW_HEIGHT = 600; // 新高度

    public static void main(String[] args) {
        try {
            resizeImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 调整图片尺寸
     * @throws IOException IO异常
     */
    private static void resizeImages() throws IOException {
        File sourceDir = new File(SOURCE_FOLDER);
        File[] files = sourceDir.listFiles();

        if (files == null) {
            throw new IOException("Source folder is empty or not found.");
        }

        for (File file : files) {
            if (file.isFile() && isImage(file)) {
                BufferedImage originalImage = ImageIO.read(file);
                BufferedImage resizedImage = resizeImage(originalImage, NEW_WIDTH, NEW_HEIGHT);

                String fileName = file.getName();
                File destinationFile = new File(DESTINATION_FOLDER + File.separator + fileName);
                ImageIO.write(resizedImage, getFileExtension(file), destinationFile);
            }
        }
    }

    /**
     * 判断文件是否为图片
     * @param file 文件
     * @return 是否为图片
     */
    private static boolean isImage(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg\) || fileName.endsWith(".gif");
    }

    /**
     * 获取文件扩展名
     * @param file 文件
     * @return 文件扩展名
     */
    private static String getFileExtension(File file) {
        String fileName = file.getName().toLowerCase();
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }

    /**
     * 调整图片尺寸
     * @param originalImage 原始图片
     * @param newWidth 新宽度
     * @param newHeight 新高度
     * @return 调整后的图片
     */
    private static BufferedImage resizeImage(BufferedImage originalImage, int newWidth, int newHeight) {
        // 根据新尺寸创建一个新图片
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());

        // 使用Graphics2D绘制图片
        java.awt.Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(originalImage.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }
}