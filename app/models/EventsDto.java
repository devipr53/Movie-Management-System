package models;

public class EventsDto {
    public Integer id;
    public String eventName;
    public String eventDesc;
    public String eventDate;
    public String eventPOCName;
    public String eventLocation;

    public EventsDto(){}

    public EventsDto(Integer id, String eventName, String eventDesc, String eventDate, String eventPOCName, String eventLocation) {
        this.id = id;
        this.eventName = eventName;
        this.eventDesc = eventDesc;
        this.eventDate = eventDate;
        this.eventPOCName = eventPOCName;
        this.eventLocation = eventLocation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventPOCName() {
        return eventPOCName;
    }

    public void setEventPOCName(String eventPOCName) {
        this.eventPOCName = eventPOCName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
}
