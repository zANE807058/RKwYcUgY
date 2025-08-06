// 代码生成时间: 2025-08-06 15:56:39
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.HashMap;
import java.util.Map;

/**
 * ApiResponseFormatter is a utility class to format API responses.
 * It provides a standardized way to return responses,
 * making it easier to maintain and extend.
 */
public class ApiResponseFormatter {

    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    private static final String DATA = "data";

    /**
     * Formats a successful API response.
     *
     * @param data The data to include in the response.
     * @return A formatted API response map.
     */
    public static Map<String, Object> successResponse(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put(STATUS, "success");
        response.put(MESSAGE, "Request processed successfully");
        response.put(DATA, data);
        return response;
    }

    /**
     * Formats an API response with an error.
     *
     * @param errorMessage The error message to include in the response.
     * @return A formatted API error response map.
     */
    public static Map<String, Object> errorResponse(String errorMessage) {
        Map<String, Object> response = new HashMap<>();
        response.put(STATUS, "error");
        response.put(MESSAGE, errorMessage);
        return response;
    }

    /**
     * Converts the response map to a JSON string.
     * This method assumes that the response map is already formatted.
     *
     * @param response The response map to convert to JSON.
     * @return A JSON string representation of the response.
     */
    public static String toJson(Map<String, Object> response) {
        // Assuming a JSON library like Jackson is used for serialization
        // ObjectMapper mapper = new ObjectMapper();
        // try {
        //     return mapper.writeValueAsString(response);
        // } catch (JsonProcessingException e) {
        //     e.printStackTrace();
        //     return null;
        // }

        // For simplicity, this is a placeholder. Replace with actual JSON serialization code.
        return response.toString();
    }

    // Add more utility methods if needed, such as for pagination, validation errors, etc.
}
