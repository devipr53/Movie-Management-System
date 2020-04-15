package service;

import entities.Events;
import models.EventsDto;
import repo.JPAEventRepo;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class EventService {
    @Inject
    private JPAEventRepo JPAEventRepo;

    public void addEvents(EventsDto eventsDto){
        // map model -> entity
        Events events = new Events(eventsDto.eventName,eventsDto.eventDesc,eventsDto.eventDate,
                eventsDto.eventPOCName,eventsDto.eventLocation);
        this.JPAEventRepo.insert(events);
    }

    public List<EventsDto> getAllEventDetails(){
        List<EventsDto> events = new ArrayList<>();
        // map entity -> model
        List<Events> eventsList = this.JPAEventRepo.list();
        events = eventsList.stream()
                .map(event-> new EventsDto(event.getId(),event.getEventName(),event.getEventDesc(),event.getEventDate()
                ,event.getEventPOCName(),event.getEventLocation()))
                .collect(Collectors.toList());

        return events;
    }

}
