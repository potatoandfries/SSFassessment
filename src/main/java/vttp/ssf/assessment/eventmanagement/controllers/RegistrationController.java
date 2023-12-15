package vttp.ssf.assessment.eventmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp.ssf.assessment.eventmanagement.Utils;
import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.models.Member;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;

@Controller
@RequestMapping(path={"/register"})
public class RegistrationController {
    
    @Autowired
    @Qualifier(Utils.BEAN_REDIS)
    private RedisTemplate<String, String> template;
    //dont panic this is just like day 15
    @Autowired
    private RedisRepository repo;

    @GetMapping(path={"/{eventId}"}) // you have to make the page available.
    public ModelAndView showForm(@PathVariable("eventId") int eventId, @ModelAttribute("member") Member member) {
        ModelAndView mav = new ModelAndView();
        Event event = repo.getEvent(eventId);
        Member mem = new Member();
        mav.addObject("event", event);
        mav.addObject("member", mem);
        mav.setViewName("eventregister");
        return mav;
        }
    // i think the idea is to get the number from repo then do a counter if member.getticker exceeds event size then give error or smth
    @PostMapping(path={"/register/{eventId}"})
    public ModelAndView register(@PathVariable("eventId") int eventId, @ModelAttribute("member") Member member) {
        ModelAndView mav = new ModelAndView();
//?! why cant it get the post ? ... strange
        // Get the event details
        Event event = repo.getEvent(eventId);

        // Check if the number of tickets exceeds the available capacity
        if (member.getTicket() > event.getEventSize()) {
            mav.setViewName("error");
            mav.addObject("message", "Number of tickets exceeds the available capacity");
            mav.setStatus(HttpStatus.valueOf(400)); 
        }
        else
        mav.setViewName("success");
        mav.addObject("event", event);
        mav.addObject("member", member);
        mav.setStatus(HttpStatus.valueOf(200)); 
        return mav;
    }
}


