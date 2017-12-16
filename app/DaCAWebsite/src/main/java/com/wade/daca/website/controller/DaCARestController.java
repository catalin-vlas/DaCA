//package com.tradingnetworks.website.controller;
//
//import com.tradingnetworks.dataobjects.Event;
//import com.tradingnetworks.dataobjects.User;
//import com.tradingnetworks.dataobjects.UserFriends;
//import com.tradingnetworks.exceptions.TradingNetworkStorageException;
//import com.tradingnetworks.website.service.EventService;
//import com.tradingnetworks.website.service.GameService;
//import com.tradingnetworks.website.service.UserFriendsService;
//import com.tradingnetworks.website.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.sql.SQLException;
//import java.util.List;
//
///**
// * Created by Virgil Barcan
// * on Dec 7, 2016.
// */
//@RestController
//public class TradingNetworksRestController {
//
//    @Autowired
//    UserService userService;  //Service which will do all data retrieval/manipulation work
//
//    @Autowired
//    UserFriendsService  userFriendsService; //Service which will do all data retrieval/manipulation work
//
//    @Autowired
//    GameService gameService; //Service which will do all data retrieval/manipulation work
//
//    @Autowired
//    EventService eventService; //Service which will do all data retrieval/manipulation work
//
//    @RequestMapping(value = "/event/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Event>> listAllGames() {
//        System.out.println("Fetching all events from the DB");
//
//        List<Event> events = null;
//
//        try {
//            events = eventService.getAllEvents();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        if (events == null || events.isEmpty()) {
//            //You may decide to return HttpStatus.NOT_FOUND
//            return new ResponseEntity<List<Event>>(HttpStatus.NO_CONTENT);
//        }
//
//        return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/user/friend/{username:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<UserFriends> getFriendsOfUser(@PathVariable("username") String username) {
//        System.out.println("Fetching friends of user with username " + username);
//
//        UserFriends friends = null;
//
//        try {
//            friends = userFriendsService.getFriendsOfUser(username);
//        } catch (SQLException | TradingNetworkStorageException e) {
//            e.printStackTrace();
//        }
//
//        if (friends == null) {
//            System.out.println("User with username " + username + " not found");
//            return new ResponseEntity<UserFriends>(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<UserFriends>(friends, HttpStatus.OK);
//    }
//    /**
//     * Retrieve all users from the database
//     * @return a list containing all users
//     */
//    @RequestMapping(value = "/user/", method = RequestMethod.GET)
//    public ResponseEntity<List<User>> listAllUsers() {
//        System.out.println("TradingNetworksRestController.listAllUsers");
//
//        List<User> users = null;
//
//        try {
//            users = userService.getAllUsers();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        if(users == null || users.isEmpty()){
//            //You may decide to return HttpStatus.NOT_FOUND
//            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
//        }
//
//        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
//    }
//
//    /**
//     * Retrieve an user given by its username
//     * @param username - the username of the user
//     * @return the user with the given username
//     */
//    @RequestMapping(value = "/user/{username:.+}",
//                    method = RequestMethod.GET,
//                    produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
//        System.out.println("Fetching User with username " + username);
//
//        User user = null;
//
//        try {
//            user = userService.getUserByUsername(username);
//        } catch (SQLException | TradingNetworkStorageException e) {
//            e.printStackTrace();
//        }
//
//        if (user == null) {
//            System.out.println("User with username " + username + " not found");
//            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<User>(user, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/user/create/", method = RequestMethod.POST,
//                    consumes = MediaType.APPLICATION_JSON_VALUE,
//                    produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        System.out.println("Creating user: " + user);
//
//        User addedUser = null;
//
//        try {
//            addedUser = userService.addUser(user);
//        } catch (SQLException | TradingNetworkStorageException e) {
//            e.printStackTrace();
//        }
//
//        if (addedUser == null) {
//            System.out.println("User " + user + " not added");
//            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<User>(addedUser, HttpStatus.OK);
//    }
//
//}