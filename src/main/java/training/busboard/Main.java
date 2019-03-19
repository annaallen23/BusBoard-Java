package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String args[]) {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

        System.out.println("Please enter Postcode:");
        Scanner postcodeScanner = new Scanner(System.in);
        String postcodeInput = postcodeScanner.next();

        PostcodeLocator location = GetLocation.getLonAndLat(client, postcodeInput);
        List<BusStopsWithin> busStops = GetBusStops.locateStopsWithin1000Meters(client, location.getLatitude(), location.getLongitude());

        for (BusStopsWithin busStop : busStops) {
            System.out.println(busStop.getCommonName());
            List<ArrivalPrediction> arrivals = GetArrivalPredictions.getArrivals(client, busStop.getNaptanId());
            for (ArrivalPrediction arrival : arrivals) {
                System.out.println(arrival.getLineName() + " towards " + arrival.getDestinationName() + " arriving in " + arrival.getTimeToStation() / 60 + " minutes. ");
            }


        }
    }
}


