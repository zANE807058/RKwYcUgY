// 代码生成时间: 2025-09-07 01:53:07
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashMap;
import java.util.Map;

// ApiResponseFormatter class provides a standardized way to format API responses.
public class ApiResponseFormatter {

    // Method to format a successful response
    public Map<String, Object> formatSuccessResponse(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", data);
        return response;
    }

    // Method to format an error response
    public Map<String, Object> formatErrorResponse(String errorCode, String errorMessage) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("errorCode", errorCode);
        response.put("errorMessage", errorMessage);
        return response;
    }

    // Main method for demonstration purposes
    public static void main(String[] args) {
        ApiResponseFormatter formatter = new ApiResponseFormatter();

        // Simulate a successful API response
        Map<String, Object> successResponse = formatter.formatSuccessResponse("Data from successful API call");
        System.out.println("Success Response: \
" + successResponse);

        // Simulate an error API response
        Map<String, Object> errorResponse = formatter.formatErrorResponse("404", "Resource not found");
        System.out.println("Error Response: \
" + errorResponse);
    }
}