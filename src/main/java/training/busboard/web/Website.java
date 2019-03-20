package training.busboard.web;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import training.busboard.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.ArrayList;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class Website {

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postcode) throws Exception {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

        try {
            PostcodeLocator location = GetLocation.getLonAndLat(client, postcode);
            List<BusStop> busStops = GetBusStops.locateStopsWithin1000Meters(client, location.getLatitude(), location.getLongitude());
            List<StopAndArrivals> stopsAndArrivals = new ArrayList<>();
            for (BusStop busStop : busStops) {
                List<ArrivalPrediction> arrivalsForThisStop = GetArrivalPredictions.getArrivals(client, busStop.getNaptanId());
                stopsAndArrivals.add(new StopAndArrivals(arrivalsForThisStop, busStop));
            }

            return new ModelAndView("info", "busInfo", new BusInfo(postcode, stopsAndArrivals));
        }
        catch (Exception e){
            return new ModelAndView("error", "errorInfo", new ErrorInfo(e.getMessage()));
        }
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Website.class, args);
    }

}