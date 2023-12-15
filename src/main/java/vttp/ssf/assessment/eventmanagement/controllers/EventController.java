package vttp.ssf.assessment.eventmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp.ssf.assessment.eventmanagement.Utils;
import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path={"/", "/index.html"})
public class EventController {

    @Autowired
    @Qualifier(Utils.BEAN_REDIS)
    private RedisTemplate<String, String> template;

    @Autowired
    private RedisRepository repo;

    @GetMapping(path={"/", "/index.html"})
    public ModelAndView displayEvents() {
        ModelAndView mav = new ModelAndView("index");
        List<Event> events = new ArrayList<>();

        long numberOfEvents = repo.getNumberOfEvents();
        //for every Event in the list add it in, same as day 15
        for (int i = 0; i < numberOfEvents; i++) {
            events.add(repo.getEvent(i));
        }

        mav.addObject("events", events);
        return mav;
    }
}
