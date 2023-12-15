package vttp.ssf.assessment.eventmanagement.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import vttp.ssf.assessment.eventmanagement.Utils;
import vttp.ssf.assessment.eventmanagement.models.Event;

@Repository
public class RedisRepository {
    //same as day 15* dont panic dont panic
    @Autowired
    @Qualifier(Utils.BEAN_REDIS)
    private RedisTemplate<String, String> template;

    public void saveRecord(Event event) {
        ListOperations<String, String> opsList = template.opsForList();
        opsList.rightPush("events", String.format("%d-%s-%d-%d-%d",
                event.getEventId(), event.getEventName(), event.getEventSize(), event.getEventDate(), event.getParticipants()));
    }

    public long getNumberOfEvents() {
        ListOperations<String, String> opsList = template.opsForList();
        return opsList.size("events");
    }

    public Event getEvent(Integer index) {
        ListOperations<String, String> opsList = template.opsForList();
        String eventString = opsList.index("events", index);
        if (eventString != null) {
            String[] parts = eventString.split("-");
            if (parts.length == 5) {
                int eventId = Integer.parseInt(parts[0]);
                String eventName = parts[1];
                int eventSize = Integer.parseInt(parts[2]);
                long eventDate = Long.parseLong(parts[3]);
                int participants = Integer.parseInt(parts[4]);
                return new Event(eventId, eventName, eventSize, eventDate, participants);
            }
        }
        return null;
    }
}
