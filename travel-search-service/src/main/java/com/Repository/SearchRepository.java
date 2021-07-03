package com.Repository;

import com.Model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Travel, Integer> {
    List<Travel> findByStartStationAndEndStationAndDateVoyage(
            String startStation,
            String endStation,
            LocalDate dateVoyage
    );
}
