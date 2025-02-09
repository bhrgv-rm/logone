package com.logone.backend.Repository;

import com.logone.backend.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
  UserModel findByEmail(String email);

}
