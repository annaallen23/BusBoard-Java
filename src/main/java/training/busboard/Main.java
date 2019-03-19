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
        System.out.println(busStops);


        //Jersey won't read string change to List
        /*List <ArrivalPrediction> response = client.target("https://api.tfl.gov.uk/StopPoint")
                //adds on scanner input in URL
                .path(busstopcodeInput)
                .path("Arrivals")
                //Application_JSON_type tells Jersey you're reading JSON
                .request(MediaType.APPLICATION_JSON_TYPE)
                // Generictype tells jersey to accept list
                .get(new GenericType<List<ArrivalPrediction>>(){});

        // remember Comparator.comparing compares getTimeToStation within list responses and sorts ascending
        response.sort(Comparator.comparing(ArrivalPrediction::getTimeToStation));
        // prints our first 5 responses
        System.out.println(response.subList(0,5));*/
    }
}


