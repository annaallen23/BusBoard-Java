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

        PostcodeLocator location = client.target("https://api.postcodes.io/postcodes/")
                //adds on scanner input in URL
                .path(postcodeInput)
                //Application_JSON_type tells Jersey you're reading JSON
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(PostcodeResult.class)
                .getResult();
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        System.out.println(location.getLatitude() + " " + location.getLongitude());
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class PostcodeResult {

        private PostcodeLocator result;

        private PostcodeResult (){}

        public PostcodeLocator getResult() {
            return result;
        }
    }




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

