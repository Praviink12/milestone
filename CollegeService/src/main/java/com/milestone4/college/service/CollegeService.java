package com.milestone4.college.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.milestone4.college.VO.RequestTemplate;
import com.milestone4.college.entity.College;

@Service
public interface CollegeService {
	College add(College clg);
	List<College> list();
	College update(long id,String name);
	College searchById(long id);
	List<RequestTemplate> listWithStu();
	RequestTemplate specificClgStudent(long clg_id);
}
