// 代码生成时间: 2025-08-08 17:02:20
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ImageResizer class for batch resizing images using Java and Hibernate.
 * This class is designed to be easy to understand, maintain, and extend.
 */
public class ImageResizer {

    /**
     * Resizes a list of images to a specified width and height.
     * 
     * @param sourceFiles List of File objects representing the images to resize.
     * @param targetWidth Target width for the resized images.
     * @param targetHeight Target height for the resized images.     * @param outputDirectory Directory where resized images will be saved.
     * @throws IOException If an I/O error occurs during processing.
     */
    public static void resizeImages(List<File> sourceFiles, int targetWidth, int targetHeight, String outputDirectory) throws IOException {
        File outputDir = new File(outputDirectory);
        if (!outputDir.exists()) {
            outputDir.mkdirs(); // Create the directory if it does not exist.
        }

        for (File file : sourceFiles) {
            try {
                BufferedImage originalImage = ImageIO.read(file);
                BufferedImage resizedImage = resizeImage(originalImage, targetWidth, targetHeight);
                File outputFile = new File(outputDir, file.getName());
                ImageIO.write(resizedImage, "jpg", outputFile); // Assuming the images are in JPG format.
            } catch (IOException e) {
                System.err.println("Error resizing image: " + file.getName());
                throw e; // Rethrow the exception to handle it further up the stack.
            }
        }
    }

    /**
     * Resizes a single image to the specified width and height while maintaining aspect ratio.
     * 
     * @param originalImage The original image to resize.
     * @param targetWidth Target width for the resized image.
     * @param targetHeight Target height for the resized image.
     * @return A new BufferedImage object with the resized image.
     */
    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        double aspectRatio = (double) originalImage.getWidth() / (double) originalImage.getHeight();
        int newWidth, newHeight;

        if (originalImage.getWidth() > originalImage.getHeight()) {
            newWidth = targetWidth;
            newHeight = (int) (targetWidth / aspectRatio);
        } else {
            newHeight = targetHeight;
            newWidth = (int) (targetHeight * aspectRatio);
        }

        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
        resizedImage.getGraphics().drawImage(
                originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
        return resizedImage;
    }

    /**
     * Main method to test the ImageResizer class.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            List<File> filesToResize = new ArrayList<>();
            filesToResize.add(new File("path/to/image1.jpg")); // Add image file paths here.
            filesToResize.add(new File("path/to/image2.jpg"));
            // ...
            resizeImages(filesToResize, 800, 600, "path/to/output/directory");
        } catch (IOException e) {
            System.err.println("An error occurred during image resizing: " + e.getMessage());
        }
    }
}
