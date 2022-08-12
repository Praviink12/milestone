package com.milestone4.college.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.milestone4.college.VO.RequestTemplate;
import com.milestone4.college.VO.Student;
import com.milestone4.college.entity.College;
import com.milestone4.college.repository.CollegeRepository;
import com.milestone4.college.service.CollegeService;





@Component
public class CollegeImpl implements CollegeService {

	@Autowired
	CollegeRepository crepo;
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public College add(College clg) {
		return crepo.save(clg);
	}

	@Override
	public List<College> list() {
		return crepo.findAll();
	}

	@Override
	public College update(long id, String name) {
		College clg = crepo.findById(id);
		clg.setName(name);
		return crepo.save(clg);
	}

	@Override
	public College searchById(long id) {
		if(crepo.findById(id)==null)
		{
			return null;
		}
		return crepo.findById(id);
	}

	@Override
	public List<RequestTemplate> listWithStu() {
		List<RequestTemplate> fullList = new ArrayList<RequestTemplate>();
		List<College> allClg = this.list();
		Iterator<College> ir = allClg.iterator();
		while(ir.hasNext())
		{
			College clg = ir.next();
			ResponseEntity<Student[]> response =
					  restTemplate.getForEntity(
							  "http://COLLEGE-SERVICE/college/college-with-ascname/"+clg.getId(),
							  Student[].class);
			Student[] students = response.getBody();
			List<Student> studs = Arrays.asList(students);
 			RequestTemplate RTm = new RequestTemplate();
			RTm.setCollege(clg);
			RTm.setStudentList(studs);
			fullList.add(RTm);
		}
		return fullList;
	}

	@Override
	public RequestTemplate specificClgStudent(long clg_id) {
		College department  = this.searchById(clg_id);
		
		ResponseEntity<Student[]> response =
				  restTemplate.getForEntity(
						  "http://STUDENT-SERVICE/student/student-with-desc-age/"+department.getId(),
						  Student[].class);
		Student[] student = response.getBody();
		List<Student> students = Arrays.asList(student);
		RequestTemplate rt = new RequestTemplate();
		
		rt.setCollege(department);
		rt.setStudentList(students);
		
		return rt;
	}
}