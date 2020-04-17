package models;

public class MultiplexDto {
    public Integer multiplexId;
    public String multiplexName;
    public String multiplexAddress;
    public String noOfScreens;
    public String ticketCost;
    public int movieId;
    public String editInd;

    public MultiplexDto(){

    }
    public MultiplexDto(Integer multiplexId, String multiplexName, String multiplexAddress, String noOfScreens, String ticketCost, int movieId) {
        this.multiplexId = multiplexId;
        this.multiplexName = multiplexName;
        this.multiplexAddress = multiplexAddress;
        this.noOfScreens = noOfScreens;
        this.ticketCost = ticketCost;
        this.movieId = movieId;
    }

    public Integer getMultiplexId() {
        return multiplexId;
    }

    public void setMultiplexId(Integer multiplexId) {
        this.multiplexId = multiplexId;
    }

    public String getMultiplexName() {
        return multiplexName;
    }

    public void setMultiplexName(String multiplexName) {
        this.multiplexName = multiplexName;
    }

    public String getMultiplexAddress() {
        return multiplexAddress;
    }

    public void setMultiplexAddress(String multiplexAddress) {
        this.multiplexAddress = multiplexAddress;
    }

    public String getNoOfScreens() {
        return noOfScreens;
    }

    public void setNoOfScreens(String noOfScreens) {
        this.noOfScreens = noOfScreens;
    }

    public String getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(String ticketCost) {
        this.ticketCost = ticketCost;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getEditInd() {
        return editInd;
    }

    public void setEditInd(String editInd) {
        this.editInd = editInd;
    }
}

