package org.example.eventsystem.Controller;

import org.example.eventsystem.Api.ApiResponse;
import org.example.eventsystem.Event.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private ArrayList<Event> events = new ArrayList<>();


    @GetMapping("/show")
    public ArrayList<Event> getEvents() {
        return events;
    }

    @PostMapping("/create")
    public ApiResponse addEvent(@RequestBody Event event) {
        for (Event e : events) {
            if(e.getStartDate().equals(event.getStartDate()) && e.getEndDate().equals(event.getEndDate())) {
                return new ApiResponse("there is already an event with the same start date and end date");
            }
        }
        events.add(event);
        return new ApiResponse("Event added successfully");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateEvent(@RequestBody Event event, @PathVariable int index) {
        events.set(index, event);
        return new ApiResponse("Event updated successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteEvent(@PathVariable int index) {
        events.remove(index);
        return new ApiResponse("Event deleted successfully");
    }

    @PutMapping("/update/capacity/{capacity}/index/{index}")
    public ApiResponse updateCapacity(@PathVariable int capacity,@PathVariable int index) {
        events.get(index).setCapacity(capacity);
        return new ApiResponse("Event capacity updated successfully");
    }


    @GetMapping("/find/id/{id}")
    public ApiResponse findEventById(@PathVariable String id) {
        for (Event e : events) {
            if(e.getId().equalsIgnoreCase(id)){
                return new ApiResponse("Event found successfully "+ e);
            }
        }

        return new ApiResponse("Event not found");
    }


}
