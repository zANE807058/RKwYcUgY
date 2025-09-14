// 代码生成时间: 2025-09-15 00:21:00
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;

/**
 * A utility class to transform JSON data using Hibernate framework.
 * This class provides methods to convert JSON data to and from domain objects.
 */
public class JsonDataTransformer {

    private final ObjectMapper objectMapper;

    /**
     * Constructor to initialize the ObjectMapper.
     */
    public JsonDataTransformer() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Converts a JSON string to a JSON node.
     *
     * @param jsonString The JSON string to be converted.
     * @return The JSON node representation of the string.
     * @throws IOException If there is an error during parsing.
     */
    public JsonNode convertJsonStringToJsonNode(String jsonString) throws IOException {
        return objectMapper.readTree(jsonString);
    }

    /**
     * Converts a JSON node to a JSON string.
     *
     * @param jsonNode The JSON node to be converted.
     * @return The JSON string representation of the node.
     * @throws JsonProcessingException If there is an error during serialization.
     */
    public String convertJsonNodeToJsonString(JsonNode jsonNode) throws JsonProcessingException {
        return objectMapper.writeValueAsString(jsonNode);
    }

    /**
     * Converts a JSON string to a domain object.
     *
     * @param jsonString The JSON string to be converted.
     * @param valueType The class of the domain object.
     * @param <T> The type of the domain object.
     * @return The domain object representation of the JSON string.
     * @throws IOException If there is an error during parsing.
     */
    public <T> T convertJsonStringToDomainObject(String jsonString, Class<T> valueType) throws IOException {
        return objectMapper.readValue(jsonString, valueType);
    }

    /**
     * Converts a domain object to a JSON string.
     *
     * @param domainObject The domain object to be converted.
     * @param valueType The class of the domain object.
     * @param <T> The type of the domain object.
     * @return The JSON string representation of the domain object.
     * @throws JsonProcessingException If there is an error during serialization.
     */
    public <T> String convertDomainObjectToJsonString(T domainObject, Class<T> valueType) throws JsonProcessingException {
        return objectMapper.writeValueAsString(domainObject);
    }

    /**
     * Main method to demonstrate the usage of the JsonDataTransformer class.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            JsonDataTransformer transformer = new JsonDataTransformer();
            String jsonInput = "{"name":"John", "age":30}";
            // Convert JSON string to JSON node
            JsonNode jsonNode = transformer.convertJsonStringToJsonNode(jsonInput);
            System.out.println("JSON Node: " + transformer.convertJsonNodeToJsonString(jsonNode));

            // Convert JSON string to domain object
            Person person = transformer.convertJsonStringToDomainObject(jsonInput, Person.class);
            System.out.println("Domain Object: " + transformer.convertDomainObjectToJsonString(person, Person.class));

            // Update domain object and convert back to JSON string
            person.setAge(31);
            System.out.println("Updated Domain Object: " + transformer.convertDomainObjectToJsonString(person, Person.class));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * A sample domain object class for demonstration purposes.
 */
class Person {
    private String name;
    private int age;

    // Constructors, getters, and setters
    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{"name":"" + name + "", "age":"" + age + ""}";
    }
}