package com.logone.backend.Repository;

import com.logone.backend.Model.RecruiterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface RecruiterRepository extends JpaRepository<RecruiterModel, UUID> {
  RecruiterModel findByEmail(String email);

}
