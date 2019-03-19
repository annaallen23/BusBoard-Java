package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import java.util.Comparator;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class BusStopsWithin {

    private String naptanId;
    private String commonName;
    private double distance;

    private BusStopsWithin (){}

    public String getNaptanId() {
        return naptanId;
    }

    public String getCommonName() {
        return commonName;
    }

    public double getDistance() {
        return distance;
    }


}
