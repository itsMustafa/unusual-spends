package com.scw.ddd.unusual.spends.db;

import com.scw.ddd.unusual.spends.domain.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB {

    private final List<User> users;

    public UserDB() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public List<User> getAllUsers() {
        return this.users;
    }
}
