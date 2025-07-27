package com.maksmusic.competitionmvc.repo;

import com.maksmusic.competitionmvc.model.entity.CompetitionEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<CompetitionEvent, Long> {


}