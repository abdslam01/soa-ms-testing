package com.Proxy;

import com.Bean.VoyageBean;
import com.Bean.VoyageInfoBean;
import com.Bean.VoyagesListBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="voyage-service/voyages")
public interface TravelProxy {
    @RequestMapping(value="/", method= RequestMethod.GET)
    List<VoyageBean> findAllVoyages();

    @RequestMapping(value="/all", method= RequestMethod.GET)
    VoyagesListBean findAll();

    @GetMapping("/{id}")
    VoyageBean findById(@PathVariable String id);

    @GetMapping("/voyageInfo/{id}")
    VoyageInfoBean getVoyageInfo(@PathVariable String id);

    @PostMapping
    String addVoyage(@RequestBody VoyageBean voyage);

    @PutMapping
    String updateVoyage(@RequestBody VoyageBean voyage);

    @DeleteMapping
    void deleteVoyage(@RequestBody VoyageBean voyage);
}
