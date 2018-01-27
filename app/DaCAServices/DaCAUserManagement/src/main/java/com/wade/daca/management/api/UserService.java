package com.wade.daca.management.api;

import com.wade.daca.management.dataobjects.User;
import org.springframework.web.bind.annotation.*;


public interface UserService {

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    String createUser(@RequestBody User user);

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    String loginUser(@RequestParam("username") String username,
                     @RequestParam("password") String password);

    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    String logoutUser();

    @RequestMapping(value = "/user/{username:.+}", method = RequestMethod.GET)
    User getUserByName(@PathVariable("username") String username);

    @RequestMapping(value = "/user/{username:.+}", method = RequestMethod.PUT)
    String updateUser(@PathVariable("username") String username,
                      @RequestBody User user);

    @RequestMapping(value = "/user/{username:.+}", method = RequestMethod.DELETE)
    String deleteUser(@PathVariable("username") String username);

}
