package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MultiplexScreenDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer screenId;
    public int multiplexId;
    public String screenName;

    public MultiplexScreenDetails() {
    }

    public MultiplexScreenDetails(int multiplexId, String screenName) {
        this.screenId = screenId;
        this.multiplexId = multiplexId;
        this.screenName = screenName;
    }

    public Integer getScreenId() {
        return screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }

    public int getMultiplexId() {
        return multiplexId;
    }

    public void setMultiplexId(int multiplexId) {
        this.multiplexId = multiplexId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}
