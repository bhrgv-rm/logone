package com.logone.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.logone.backend.Model.UserModel;
import com.logone.backend.Repository.UserRepository;
import java.util.List;
import java.util.ArrayList;

@Service
public class UserService {
  @Autowired
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<UserModel> getAllUsers() {
    return userRepository.findAll();
  }

  // public UserModel findByEmail(String email) {
  // return userRepository.findByEmail(email);
  // }

  public UserDetails findByEmail(String email) {
    UserModel user = userRepository.findByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
        new ArrayList<>());
  }

  public UserModel createUser(UserModel user) {
    return userRepository.save(user);
  }
}
