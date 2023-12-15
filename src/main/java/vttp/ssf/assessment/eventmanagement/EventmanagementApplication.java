package vttp.ssf.assessment.eventmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;

import java.util.List;

@SpringBootApplication
public class EventmanagementApplication implements CommandLineRunner {

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private RedisRepository redisRepository;

    public static void main(String[] args) {
        SpringApplication.run(EventmanagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // hello turns up ? but not my list... means something wrong w file reading
        System.out.println("Hello");
        List<Event> events = databaseService.readFile("D:\\SSF speedrun\\vttp2023-batch4-ssf-assessment\\events.json");

        // Print events for verification
        for (Event event : events) {
            System.out.println(event);

            // Save each event to Redis
            redisRepository.saveRecord(event);
        }
    }
}