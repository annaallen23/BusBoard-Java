package training.busboard.web;

import training.busboard.ArrivalPrediction;
import training.busboard.BusStop;

import java.util.List;

public class BusInfo {
    private final String postcode;
    private final List<StopAndArrivals> stopsAndArrivals;

    public BusInfo(String postcode, List<StopAndArrivals> stopsAndArrivals) {
        this.postcode = postcode;
        this.stopsAndArrivals = stopsAndArrivals;
    }

    public String getPostcode() {
        return postcode;
    }

    public List<StopAndArrivals> getStopsAndArrivals() {
        return stopsAndArrivals;
    }
}

