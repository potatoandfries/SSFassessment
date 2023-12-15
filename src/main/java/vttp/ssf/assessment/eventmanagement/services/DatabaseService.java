package vttp.ssf.assessment.eventmanagement.services;

import org.springframework.stereotype.Service;
import vttp.ssf.assessment.eventmanagement.models.Event;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

@Service
public class DatabaseService {
//Remember service is the busines logic >> this isv similar to day16*
    public List<Event> readFile(String fileName) throws FileNotFoundException {
        Path jsonFilePath = Paths.get(fileName);
        JsonReader jsonReader = Json.createReader(new FileReader(jsonFilePath.toString()));
        JsonArray jsonArray = jsonReader.readArray();
        List<Event> events = new ArrayList<>(); //i need a list of sort?
        for (JsonValue jsonValue : jsonArray) {
            JsonObject jsonObject = (JsonObject) jsonValue;
            int eventId = jsonObject.getInt("eventId");
            String eventName = jsonObject.getString("eventName");
            int eventSize = jsonObject.getInt("eventSize");
            long eventDate = jsonObject.getJsonNumber("eventDate").longValue();// from the internet but looks like it works
            int participants = jsonObject.getInt("participants");
            Event event = new Event(eventId, eventName, eventSize, eventDate, participants);
            events.add(event);
        }
        return events;
    }
}
