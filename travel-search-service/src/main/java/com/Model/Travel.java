package com.Model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="Travel")
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate dateVoyage;
    private String endStation;
    private LocalTime heureDepart;
    private String startStation;
    private Integer trainId;
    private String trajetId;

    public Travel() {
    }

    public Travel(int id, LocalDate dateVoyage, String endStation, LocalTime heureDepart, String startStation, Integer trainId, String trajetId) {
        id = id;
        this.dateVoyage = dateVoyage;
        this.endStation = endStation;
        this.heureDepart = heureDepart;
        this.startStation = startStation;
        this.trainId = trainId;
        this.trajetId = trajetId;
    }
    public void setAllValuesExceptId(Travel travel){
        this.dateVoyage = travel.getDateVoyage();
        this.endStation = travel.getEndStation();
        this.heureDepart = travel.getHeureDepart();
        this.startStation = travel.getStartStation();
        this.trainId = travel.getTrainId();
        this.trajetId = travel.getTrajetId();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}