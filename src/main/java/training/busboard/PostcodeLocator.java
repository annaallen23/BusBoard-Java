package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import java.util.Scanner;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PostcodeLocator {

    private double longitude;
    private double latitude;

    private PostcodeLocator() {
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
