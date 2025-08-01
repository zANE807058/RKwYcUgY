// 代码生成时间: 2025-08-02 02:06:36
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * ExcelGenerator is a utility class that generates Excel files based on provided data.
 */
public class ExcelGenerator {

    /**
     * Generates an Excel file with the given data.
     *
     * @param data The list of lists containing data for each row.
     * @param outputFilename The path and filename of the output Excel file.
     * @throws IOException If an I/O error occurs when creating or writing the Excel file.
     */
    public static void generateExcel(List<List<String>> data, String outputFilename) throws IOException {
        Workbook workbook = new XSSFWorkbook(); // Create a new Excel workbook
        Sheet sheet = workbook.createSheet(