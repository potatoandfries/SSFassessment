package vttp.ssf.assessment.eventmanagement.models;

import org.springframework.format.annotation.DateTimeFormat;

public class Event {

    private int eventId;
    private String eventName;
    private int eventSize;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private long eventDate;
    private int participants;

    public Event(){

    }
    // you need custom constructor to fit.
    public Event(int eventId, String eventName, int eventSize, long eventDate, int participants) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventSize = eventSize;
        this.eventDate = eventDate;
        this.participants = participants;
    }

    @Override
    public String toString() {
        return String.format(
                "Event ID: %d, Event Name: %s, Event Size: %d, Event Date: %d, Participants: %d",
                eventId, eventName, eventSize, eventDate, participants
        );
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventSize() {
        return eventSize;
    }

    public void setEventSize(int eventSize) {
        this.eventSize = eventSize;
    }

    public long getEventDate() {
        return eventDate;
    }

    public void setEventDate(long eventDate) {
        this.eventDate = eventDate;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    
}
