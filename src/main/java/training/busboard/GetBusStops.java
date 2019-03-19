package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import java.util.Comparator;
import java.util.List;

public class GetBusStops {

    public static List<BusStopsWithin> locateStopsWithin1000Meters (Client client, double latitude, double longitude) {
        List<BusStopsWithin> locateStopsWithin1000Meters = client.target("https://api.tfl.gov.uk/StopPoint")
                //queryParam replaces = in URL
                .queryParam("stopTypes", "NaptanPublicBusCoachTram")
                .queryParam("radius", "1000")
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)

                //Application_JSON_type tells Jersey you're reading JSON
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(StopPoints.class)
                .getStopPoints();

        locateStopsWithin1000Meters.sort(Comparator.comparing(BusStopsWithin::getDistance));
        return locateStopsWithin1000Meters.subList(0,2);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class StopPoints {

        private List <BusStopsWithin> stopPoints;

        private StopPoints() { }

        public List<BusStopsWithin> getStopPoints() {
            return stopPoints;
        }
    }
}
