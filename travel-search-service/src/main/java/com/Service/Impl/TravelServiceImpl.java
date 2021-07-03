package com.Service.Impl;

import com.Exception.SearchNotFoundException;
import com.Model.Travel;
import com.Repository.SearchRepository;
import com.Service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TravelServiceImpl implements TravelService {
    @Autowired
    private SearchRepository searchRepository;

    public List<Travel> getAllSearches(){
        return searchRepository.findAll();
    }

    public Travel getSearchById(int id) throws SearchNotFoundException {
        //return searchRepository.findById(id)
               // .orElseThrow(() -> new SearchNotFoundException(id));
        if(searchRepository.existsById(id))
            return searchRepository.findById(id).get();
        throw new SearchNotFoundException("product with id "+id+" not found");
    }

    public Travel setNewSearch(Travel travel) {
        return searchRepository.save(travel);
    }

    public Travel updateSearch(int id, Travel travel) throws SearchNotFoundException {
        Travel travelToModify = this.getSearchById(id);
        travelToModify.setAllValuesExceptId(travel);
        return setNewSearch(travelToModify);
    }

    public void deleteSearch(int id) throws SearchNotFoundException {
        if(searchRepository.existsById(id))
            searchRepository.deleteById(id);
        else
            throw new SearchNotFoundException("product with id "+id+" not found");
    }

    public List<Travel> searchTravel(String startStation, String endStation, LocalDate dateVoyage){
        return searchRepository.findByStartStationAndEndStationAndDateVoyage(
                startStation, endStation, dateVoyage
        );
    }
}
