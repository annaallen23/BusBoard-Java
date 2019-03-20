package training.busboard.web;

import training.busboard.ArrivalPrediction;
import training.busboard.BusStop;

import java.util.List;

public class StopAndArrivals {

    private List <ArrivalPrediction> arrivals;
    private BusStop stop;

    public StopAndArrivals(List<ArrivalPrediction> arrivals, BusStop stop) {
        this.arrivals = arrivals;
        this.stop = stop;
    }

    public List<ArrivalPrediction> getArrivals() {
        return arrivals;
    }

    public BusStop getStop() {
        return stop;
    }
}
