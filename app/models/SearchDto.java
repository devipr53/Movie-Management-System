package models;

public class SearchDto {
    public String searchParam;
    public String searchType;
    public String multiplexName;
    public String movieName;
    public String availableFrom;
    public String availableTo;
    public String screenName;
    public String ticketCost;
    public String movieImageUrl;
    public SearchDto(){}
    public SearchDto(String searchParam, String searchType, String multiplexName, String movieName, String availableFrom, String availableTo, String screenName, String ticketCost, String movieImageUrl) {
        this.searchParam = searchParam;
        this.searchType = searchType;
        this.multiplexName = multiplexName;
        this.movieName = movieName;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
        this.screenName = screenName;
        this.ticketCost = ticketCost;
        this.movieImageUrl = movieImageUrl;
    }

    public String getSearchParam() {
        return searchParam;
    }

    public void setSearchParam(String searchParam) {
        this.searchParam = searchParam;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
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
