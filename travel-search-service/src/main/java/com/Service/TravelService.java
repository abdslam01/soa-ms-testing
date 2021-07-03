package com.Service;

import com.Exception.SearchNotFoundException;
import com.Model.Travel;

import java.time.LocalDate;
import java.util.List;

public interface TravelService {
    List<Travel> getAllSearches();
    Travel getSearchById(int id) throws SearchNotFoundException;
    Travel setNewSearch(Travel travel);
    Travel updateSearch(int id, Travel travel) throws SearchNotFoundException;
    void deleteSearch(int id) throws SearchNotFoundException;
    List<Travel> searchTravel(String startStation, String endStation, LocalDate dateVoyage);
}
