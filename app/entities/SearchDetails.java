package entities;

public class SearchDetails {
    public String multiplexName;
    public String movieName;
    public String availableFrom;
    public String availableTo;
    public String screenName;
    public String ticketCost;
    public String movieImageUrl;

    public SearchDetails(){}
    public SearchDetails(String multiplexName, String movieName, String availableFrom, String availableTo, String screenName, String ticketCost, String movieImageUrl) {
        this.multiplexName = multiplexName;
        this.movieName = movieName;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
        this.screenName = screenName;
        this.ticketCost = ticketCost;
        this.movieImageUrl = movieImageUrl;
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

    public String getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(String availableFrom) {
        this.availableFrom = availableFrom;
    }

    public String getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(String availableTo) {
        this.availableTo = availableTo;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(String ticketCost) {
        this.ticketCost = ticketCost;
    }

    public String getMovieImageUrl() {
        return movieImageUrl;
    }

    public void setMovieImageUrl(String movieImageUrl) {
        this.movieImageUrl = movieImageUrl;
    }
}
