package com.milestone3.student.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.milestone3.student.VO.ResponseTemplate;
import com.milestone3.student.entity.Student;

@Service
public interface StudentService {
	
	Student add(Student stu);
	List<Student> list();
	Student searchById(long id);
	ResponseTemplate stuWithClg(long clgId);
	Student assignCollege(long stuId, long clgId);
	List<Student> getByClgId(long clg_id);
	List<Student> getStudentByCollegeWithDescAge(long clg_id);
}
