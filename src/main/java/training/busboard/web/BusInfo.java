package training.busboard.web;

import training.busboard.ArrivalPrediction;
import training.busboard.BusStopsWithin;

import java.util.List;

public class BusInfo {
    private final String postcode;
    private final List<List<ArrivalPrediction>> arrivals;
    private final List<BusStopsWithin> busStops;

    public BusInfo(String postcode, List<List<ArrivalPrediction>> arrivals, List<BusStopsWithin> busStops) {
        this.postcode = postcode;
        this.arrivals = arrivals;
        this.busStops = busStops;
    }

    public String getPostcode() {
        return postcode;
    }

    public List<List<ArrivalPrediction>> getArrivals() {
        return arrivals;
    }

    public List<BusStopsWithin> getBusStops() {
        return busStops;
    }
}
