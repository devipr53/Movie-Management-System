package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class AllotmentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer allotmentId;
    public String multiplexName;
    public String movieName;
    public Date availableFrom;
    public Date availableTo;
    public String screenName;
    public int movieId;
    public int multiplexId;
    public int ticketCost;

    public AllotmentDetails() {
    }

    public AllotmentDetails(Integer allotmentId, String multiplexName, String movieName, Date availableFrom, Date availableTo, String screenName,
                            int movieId, int multiplexId,int ticketCost) {
        this.allotmentId = allotmentId;
        this.multiplexName = multiplexName;
        this.movieName = movieName;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
        this.screenName = screenName;
        this.movieId = movieId;
        this.multiplexId = multiplexId;
        this.ticketCost=ticketCost;
    }

    public Integer getAllotmentId() {
        return allotmentId;
    }

    public void setAllotmentId(Integer allotmentId) {
        this.allotmentId = allotmentId;
    }

    public String getMultiplexName() {
        return multiplexName;
    }

    public void setMultiplexName(String multiplexName) {
        this.multiplexName = multiplexName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }

    public Date getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(Date availableTo) {
        this.availableTo = availableTo;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getMultiplexId() {
        return multiplexId;
    }

    public void setMultiplexId(int multiplexId) {
        this.multiplexId = multiplexId;
    }

    public int getTicketCost() { return ticketCost;    }

    public void setTicketCost(int ticketCost) {   this.ticketCost = ticketCost;    }
}
