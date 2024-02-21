package components;

/**
 * Represents a custom return object.
 */
public class CustomReturn {

    boolean isValid;
    Integer typeOfResult;

    String line;

    /**
     * Constructs a CustomReturn object with the provided parameters.
     * @param isValid Indicates if the return is valid.
     * @param typeOfResult The type of result.
     * @param line The line content.
     */
    public CustomReturn(boolean isValid, Integer typeOfResult, String line) {
        this.isValid = isValid;
        this.typeOfResult = typeOfResult;
        this.line = line;
    }
}
