package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String args[]) throws Exception {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

        System.out.println("Please enter Postcode:");
        Scanner postcodeScanner = new Scanner(System.in);
        String postcodeInput = postcodeScanner.next();

        PostcodeLocator location = GetLocation.getLonAndLat(client, postcodeInput);
        List<BusStop> busStops = GetBusStops.locateStopsWithin1000Meters(client, location.getLatitude(), location.getLongitude());

        for (BusStop busStop : busStops) {
            System.out.println(busStop.getCommonName());
            List<ArrivalPrediction> arrivals = GetArrivalPredictions.getArrivals(client, busStop.getNaptanId());
            for (ArrivalPrediction arrival : arrivals) {
                System.out.println(arrival.getLineName() + " towards " + arrival.getDestinationName() + " arriving in " + arrival.getTimeToStation() / 60 + " minutes. ");
            }


        }
    }
}


