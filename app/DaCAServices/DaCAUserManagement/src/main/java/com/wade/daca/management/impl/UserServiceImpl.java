package com.wade.daca.management.impl;

import com.wade.daca.management.api.UserService;
import com.wade.daca.management.api.UserStorage;
import com.wade.daca.management.dataobjects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserServiceImpl implements UserService {

    @Autowired
    UserStorage userStorage;

    @Autowired
    public UserServiceImpl() {
        userStorage = new UserStorageImpl();
    }

    @Override
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String createUser(@RequestBody User user) {
        boolean result = userStorage.addUser(user);
        return result ? "Success" : "Failure";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public String loginUser(@RequestParam("username") String username,
                     @RequestParam("password") String password) {
        // TODO: Do login as it should be done
        return "Success";
    }

    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    public String logoutUser() {
        // TODO: Do logout
        return "Success";
    }

    @Override
    @RequestMapping(value = "/user/{username:.+}", method = RequestMethod.GET)
    public User getUserByName(@PathVariable("username") String username) {
        return userStorage.getUserById(username);
    }

    @RequestMapping(value = "/user/{username:.+}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable("username") String username,
                             @RequestBody User user) {
        boolean result = userStorage.updateUser(username, user);
        return result ? "Success" : "Failure";
    }

    @RequestMapping(value = "/user/{username:.+}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("username") String username) {
        boolean result = userStorage.removeUser(username);
        return result ? "Success" : "Failure";
    }
}
