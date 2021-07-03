package com.Bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class VoyageBean{
    private String id;
    private LocalDate dateVoyage;
    private String endStation;
    private LocalTime heureDepart;
    private String startStation;
    private Integer trainId;
    private String trajetId;

    public VoyageBean() {}

    public VoyageBean(String id, LocalDate dateVoyage, LocalTime heureDepart, String startStation, String endStation, Integer trainId, String trajetId) {
        this.dateVoyage = dateVoyage;
        this.endStation = endStation;
        this.heureDepart = heureDepart;
        this.startStation = startStation;
        this.trainId = trainId;
        this.trajetId = trajetId;
        this.id = id;
    }

    public LocalDate getDateVoyage() {
        return dateVoyage;
    }

    public void setDateVoyage(LocalDate dateVoyage) {
        this.dateVoyage = dateVoyage;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getTrajetId() {
        return trajetId;
    }

    public void setTrajetId(String trajetId) {
        this.trajetId = trajetId;
    }

    public String getVoyageId() {
        return id;
    }

    public void setVoyageId(String voyageId) {
        this.id = voyageId;
    }
}