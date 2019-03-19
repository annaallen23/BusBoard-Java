package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

public class GetLocation {

    public static PostcodeLocator getLonAndLat(Client client, String postcodeInput) {
        return client.target("https://api.postcodes.io/postcodes")
                //adds on scanner input in URL
                .path(postcodeInput)
                //Application_JSON_type tells Jersey you're reading JSON
                .request(MediaType.APPLICATION_JSON_TYPE)
                //replace new generictype with class (which is made down below that reads a list)
                .get(PostcodeResult.class)
                .getResult();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class PostcodeResult {

        private PostcodeLocator result;

        private PostcodeResult() {
        }

        public PostcodeLocator getResult() {
            return result;
        }
    }
}
