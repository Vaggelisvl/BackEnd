package com.backend.technlog.service;

import com.backend.technlog.domain.User;
import com.backend.technlog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
    private final UserRepository userRepository;

    @Override
    public MongoRepository<User, String> getRepository() {
        return userRepository;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
