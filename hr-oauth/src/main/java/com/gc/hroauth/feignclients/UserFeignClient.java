package com.gc.hroauth.feignclients;

import com.gc.hroauth.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "hr-user", path = "/users")
public interface UserFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    ResponseEntity<User> findByEmail(@RequestParam String email);
}
