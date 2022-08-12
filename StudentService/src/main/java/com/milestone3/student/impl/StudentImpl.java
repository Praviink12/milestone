package com.milestone3.student.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.milestone3.student.VO.College;
import com.milestone3.student.VO.ResponseTemplate;
import com.milestone3.student.entity.Student;
import com.milestone3.student.repository.StudentRepository;
import com.milestone3.student.service.StudentService;

@Component
public class StudentImpl implements StudentService {

	@Autowired
	private StudentRepository strepo;
	@Autowired
	private RestTemplate rtemp;
	
	@Override
	public Student add(Student stu) {
		return strepo.save(stu);
	}

	@Override
	public List<Student> list() {
		return strepo.findAll();
	}

	@Override
	public Student searchById(long id) {
		return strepo.findById(id);
	}

	@Override
	public ResponseTemplate stuWithClg(long stuId) {
		ResponseTemplate RT = new ResponseTemplate();
		Student stu = strepo.findById(stuId);
		
		long clg_id = stu.getClg_id();
		College clg = rtemp.getForObject("http://COLLEGE-SERVICE/college/"+clg_id, College.class);
		RT.setCollege(clg);
		RT.setStudent(stu);
		return RT;
	}

	@Override
	public Student assignCollege(long stuId, long clgId) {
		Student stu = strepo.findById(stuId);
		College clg = rtemp.getForObject("http://COLLEGE-SERVICE/department/"+clgId, College.class);
		if(stu==null || clg==null)
		{
			return null;
		}
		stu.setClg_id(clgId);
		strepo.save(stu);
		return stu;
	}

	@Override
	public List<Student> getByClgId(long clg_id) {
		return strepo.getByClgId(clg_id);
	}

	@Override
	public List<Student> getStudentByCollegeWithDescAge(long clg_id) {
		return strepo.getStudentByCollegeWithDescAge(clg_id);
	}
	

}
