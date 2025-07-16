package com.maksmusic.competitionmvc;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class CompetitionEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime eventDate;

    @ElementCollection
    @CollectionTable(name = "red_team_members", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "member")
    private List<String> redTeam;

    @ElementCollection
    @CollectionTable(name = "green_team_members", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "member")
    private List<String> greenTeam;

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public List<String> getRedTeam() {
        return redTeam;
    }

    public void setRedTeam(List<String> redTeam) {
        this.redTeam = redTeam;
    }

    public List<String> getGreenTeam() {
        return greenTeam;
    }

    public void setGreenTeam(List<String> greenTeam) {
        this.greenTeam = greenTeam;
    }
}