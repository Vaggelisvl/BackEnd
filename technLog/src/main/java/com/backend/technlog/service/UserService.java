package com.backend.technlog.service;

import com.backend.technlog.domain.User;

public interface UserService extends BaseService<User,String> {
    User findByEmail(String email);

}
