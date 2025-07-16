package com.maksmusic.competitionmvc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<CompetitionEvent, Long> {


}