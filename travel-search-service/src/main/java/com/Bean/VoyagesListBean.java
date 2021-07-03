package com.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VoyagesListBean implements Serializable {

    private List<VoyageBean> voyages;

    public VoyagesListBean(){
        voyages = new ArrayList<>();
    }

    public VoyagesListBean(List<VoyageBean> voy){
        this();
        for(VoyageBean v: voy)
            voyages.add(v);
    }

    public List<VoyageBean> getVoyages() {
        return voyages;
    }

    public void setVoyages(List<VoyageBean> voyages) {
        this.voyages = voyages;
    }
}

