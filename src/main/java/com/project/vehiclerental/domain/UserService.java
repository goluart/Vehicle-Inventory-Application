package com.project.vehiclerental.domain;

import java.util.List;

public interface UserService {
    void saveUser(UserData userData);

    User findUserByEmail(String email);

    List<UserData> findAllUsers();
}
