package com.wade.daca.management.api;

import com.wade.daca.management.dataobjects.User;

public interface UserStorage {

    User getUserById(String id);
    boolean addUser(User user);
    boolean removeUser(User user);
    boolean removeUser(String id);
    boolean updateUser(String id, User user);
}
