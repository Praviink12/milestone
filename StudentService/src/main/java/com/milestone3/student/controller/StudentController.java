package com.milestone3.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.milestone3.student.VO.ResponseTemplate;
import com.milestone3.student.entity.Student;
import com.milestone3.student.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentService stser;
	
	@PostMapping
	public Student add(@RequestBody Student stu)
	{
		return stser.add(stu);
	}
	
	@GetMapping
	public List<Student> listStu(){
		return stser.list();
	}
	
	@GetMapping("/{stuId}")
	public ResponseTemplate stuWithclg(@PathVariable long stuId)
	{
		return stser.stuWithClg(stuId);
	}
	
	@PutMapping("/{stuId}/{clgId}")
	public String updateDepartment(@PathVariable long stuId,@PathVariable long clgId)
	{
		Student emp = stser.assignCollege(stuId,clgId);
		if(emp!=null)
		{
			return emp.toString();
		}
		return "Sorry. College or Student Not Found";
	}
	
	@GetMapping("/student-with-ascname/{clg_id}")
	public List<Student> getByClgId(@PathVariable long Clg_id)
	{
		return stser.getByClgId(Clg_id);
	}
	
	@GetMapping("/student-with-desc-age/{clg_id}")
	public List<Student> getStudentByCollegeWithDescAge(@PathVariable long clg_id)
	{
		return stser.getStudentByCollegeWithDescAge(clg_id);
	}
}
