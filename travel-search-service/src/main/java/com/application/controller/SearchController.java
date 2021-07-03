package com.application.controller;

import com.Bean.VoyageBean;
import com.Bean.VoyageInfoBean;
import com.Bean.VoyagesListBean;
import com.Exception.SearchNotFoundException;
import com.Model.Travel;
import com.Proxy.TravelProxy;
import com.Service.TravelService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/voyages")
public class SearchController {
    @Autowired
    private TravelProxy travelProxy;

    @RequestMapping(value="", method= RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "findAllVoyagesFallBack")
    public List<VoyageBean> findAllVoyages(){
        return travelProxy.findAllVoyages();
    }
    public List<VoyageBean> findAllVoyagesFallBack(){
        //LocalDate.parse(dateVoyage, DateTimeFormatter.ofPattern("yyyy-MM-d"))
        return Arrays.asList(
                new VoyageBean("1",LocalDate.parse("2021-02-09"), LocalTime.parse("13:45:30"),"Agadir","Rabat",1, "N10"),
                new VoyageBean("2",LocalDate.parse("2021-03-09"), LocalTime.parse("14:45:30"),"Casablanca","Rabat",1, "N10")
        );
    }

    @RequestMapping(value="/all", method= RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "findAllFallBack")
    public VoyagesListBean findAll(){
        return travelProxy.findAll();
    }
    public VoyagesListBean findAllFallBack(){
        return new VoyagesListBean(this.findAllVoyagesFallBack());
    }

    @RequestMapping(value="/voyageInfo/{id}", method= RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "getVoyageInfoFallBack")
    public VoyageInfoBean getVoyageInfo(@PathVariable String id) {
        return travelProxy.getVoyageInfo(id);
    }
    public VoyageInfoBean getVoyageInfoFallBack(@PathVariable String id){
        return new VoyageInfoBean(
                new VoyageBean("Default "+id,LocalDate.parse("2021-02-09"), LocalTime.parse("13:45:30"),"Agadir","Rabat",1, "N10")
        );
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "getSearchByIdFallBack")
    public VoyageBean getSearchById(@PathVariable String id) {
        return travelProxy.findById(id);
    }
    public VoyageBean getSearchByIdFallBack(@PathVariable String id){
        return new VoyageBean();
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    @HystrixCommand(fallbackMethod = "addVoyageFallBack")
    public String addVoyage(@RequestBody VoyageBean voyage){
        return travelProxy.addVoyage(voyage);
    }
    public String addVoyageFallBack(@RequestBody VoyageBean voyage){
        return "addVoyageFallBack: travel-proxy service is down";
    }

    @RequestMapping(value="/travels/add", method= RequestMethod.POST)
    public ResponseEntity<Void> addNewSearch(@RequestBody Travel travel){
        Travel travel1=travelService.setNewSearch(travel);
        if(travel1==null)
            return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(travel1.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value="/update/{id}", method= RequestMethod.PUT)
    @HystrixCommand(fallbackMethod = "updateVoyageFallBack")
    public String updateVoyage(@PathVariable String id, @RequestBody VoyageBean voyage)  {
        if(travelProxy.findById(id)==null)
            throw new SearchNotFoundException("Product With id "+id+" NotFound");
        return travelProxy.updateVoyage(voyage);
    }
    public String updateVoyageFallBack(@RequestBody VoyageBean voyage){
        return "updateVoyageFallBack: travel-proxy service is down";
    }

    @RequestMapping(value="/delete/{id}", method= RequestMethod.DELETE)
    @HystrixCommand(fallbackMethod = "deleteVoyageFallBack")
    public String deleteVoyage(@RequestBody VoyageBean voyage)  {
        travelProxy.deleteVoyage(voyage);
        return "Delete Succeded";
    }
    public String deleteVoyageFallBack(@RequestBody VoyageBean voyage){
        return "deleteVoyageFallBack: travel-proxy service is down";
    }
    //--------------------
    @Autowired
    TravelService travelService;
    @RequestMapping(value="/search/{startStation}/{endStation}/{dateVoyage}/", method = RequestMethod.GET)
    public List<Travel> searchTravels(
            @PathVariable String startStation,
            @PathVariable String endStation,
            @PathVariable String dateVoyage
    ){
        return travelService.searchTravel(
                startStation,
                endStation,
                LocalDate.parse(dateVoyage, DateTimeFormatter.ofPattern("yyyy-MM-d"))
        );
    }
/*------------------------------
    @Autowired
    private TravelService travelService;

    @RequestMapping(value="/travels", method= RequestMethod.GET)
    public List<Travel> getAllSearches(){
        return travelService.getAllSearches();
    }

    @RequestMapping(value="/travels/search/{startStation}/{endStation}/{dateVoyage}/", method = RequestMethod.GET)
    public List<Travel> searchTravels(
            @PathVariable String startStation,
            @PathVariable String endStation,
            @PathVariable String dateVoyage
    ){
        return travelService.searchTravel(
                startStation,
                endStation,
                LocalDate.parse(dateVoyage, DateTimeFormatter.ofPattern("yyyy-MM-d"))
        );
    }

    @RequestMapping(value="/travels/{id}", method= RequestMethod.GET)
    public Travel getSearchById(@PathVariable int id) throws SearchNotFoundException {
        return travelService.getSearchById(id);
    }

    @RequestMapping(value="/travels/add", method= RequestMethod.POST)
    public ResponseEntity<Void> addNewSearch(@RequestBody Travel travel){
        Travel travel1=travelService.setNewSearch(travel);
        if(travel1==null)
            return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(travel1.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value="/travels/update/{id}", method= RequestMethod.PUT)
    public Travel addNewSearch(@PathVariable int id, @RequestBody Travel travel) throws SearchNotFoundException {
        return travelService.updateSearch(id, travel);
    }

    @RequestMapping(value="/travels/delete/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<?> deleteSearch(@PathVariable int id) throws SearchNotFoundException {
        travelService.deleteSearch(id);
        return ResponseEntity.ok().build();
    }*/
}
