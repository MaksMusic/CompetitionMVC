package com.maksmusic.competitionmvc.service;

import com.maksmusic.competitionmvc.model.entity.CompetitionEvent;
import com.maksmusic.competitionmvc.repo.CompetitionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompetitionService {

    private final CompetitionRepository repository;

    public CompetitionService(CompetitionRepository repository) {
        this.repository = repository;
    }

    public List<CompetitionEvent> getAllEvents() {
        return repository.findAll();
    }

    public Optional<CompetitionEvent> getEventById(Long id) {
        return repository.findById(id);
    }

    public CompetitionEvent saveEvent(CompetitionEvent event) {
        return repository.save(event);
    }

    public void deleteEventById(Long id) {
        repository.deleteById(id);
    }

    public CompetitionEvent updateTeamMembers(Long id, String team, String member) {
        CompetitionEvent event = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if ("red".equals(team)) {
            event.getRedTeam().add(member);
        } else if ("green".equals(team)) {
            event.getGreenTeam().add(member);
        }

        return repository.save(event);
    }
}
