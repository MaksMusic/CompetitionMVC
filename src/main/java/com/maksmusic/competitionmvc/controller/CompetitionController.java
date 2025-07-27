package com.maksmusic.competitionmvc.controller;

import com.maksmusic.competitionmvc.model.entity.CompetitionEvent;
import com.maksmusic.competitionmvc.service.CompetitionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/events")
public class CompetitionController {

    private final CompetitionService eventService;

    public CompetitionController(CompetitionService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public String getEventDetails(@PathVariable Long id, Model model) {
        CompetitionEvent event = eventService.getEventById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        model.addAttribute("event", event);
        return "events/details";
    }

    @PostMapping("/{id}/join")
    public String joinTeam(
            @PathVariable Long id,
            @RequestParam("team") String team,
            @RequestParam("username") String username) {

        eventService.updateTeamMembers(id, team, username);
        return "redirect:/events/" + id;
    }



    // Показать все события
    @GetMapping
    public String getAllEvents(Model model) {
        List<CompetitionEvent> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "events/list"; // Thymeleaf шаблон: src/main/resources/templates/events/list.html
    }

    // Форма для добавления события
    @GetMapping("/new")
    public String showEventForm(Model model) {
        model.addAttribute("event", new CompetitionEvent());
        return "events/form"; // src/main/resources/templates/events/form.html
    }

    // Сохранить новое событие
    @PostMapping
    public String createEvent(@ModelAttribute("event") CompetitionEvent event,
                              @RequestParam String redTeam,
                              @RequestParam String greenTeam
    ) {
        String[] redTeamNames = redTeam.split(",");
        List<String> redTeamList = Arrays.stream(redTeamNames)
                       .map(String::trim)
                       .toList();

        String[] greenTeamNames = greenTeam.split(",");
        List<String> greenTeamList = Arrays.stream(greenTeamNames)
                .map(String::trim)
                .toList();


        event.setGreenTeam(greenTeamList);
        event.setRedTeam(redTeamList);

        eventService.saveEvent(event);
        return "redirect:/events";
    }

    // Удалить событие
    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteEventById(id);
        return "redirect:/events";
    }
}
