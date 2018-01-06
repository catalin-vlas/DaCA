package com.wade.daca.management.api;

import com.wade.daca.management.dataobjects.User;

public interface UserStorage {

    User getUserById(String id);

}
