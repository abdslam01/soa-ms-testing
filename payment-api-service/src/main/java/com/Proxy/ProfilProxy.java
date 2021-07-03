package com.Proxy;

import com.Bean.ProfilBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="profil-service")
public interface ProfilProxy {
    @RequestMapping(value="/profil", method = RequestMethod.GET)
    ProfilBean getProfil(@PathVariable long id);
}
