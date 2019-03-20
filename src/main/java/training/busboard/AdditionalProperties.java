package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdditionalProperties {

    private String category;
    private String key;
    private String value;

    private AdditionalProperties() { }

    public String getCategory() {
        return category;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
