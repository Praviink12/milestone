package com.milestone4.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.milestone4.college.entity.College;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {

	College findById(long id);

}
