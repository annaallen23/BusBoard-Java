package training.busboard;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.Comparator;
import java.util.List;

public class GetArrivalPredictions {

    public static List<ArrivalPrediction> getArrivals(Client client, String naptanId) {
        List<ArrivalPrediction> response = client.target("https://api.tfl.gov.uk/StopPoint")
                //adds on scanner input in URL
                .path(naptanId)
                .path("Arrivals")
                //Application_JSON_type tells Jersey you're reading JSON
                .request(MediaType.APPLICATION_JSON_TYPE)
                // Generictype tells jersey to accept list
                .get(new GenericType<List<ArrivalPrediction>>(){});

        // remember Comparator.comparing compares getTimeToStation within list responses and sorts ascending
        response.sort(Comparator.comparing(ArrivalPrediction::getTimeToStation));
        // prints our first 5 responses
        return response.subList(0, Math.min(5, response.size()));
    }
}
