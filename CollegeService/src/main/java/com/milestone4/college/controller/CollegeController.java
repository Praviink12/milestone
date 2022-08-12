package com.milestone4.college.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.milestone4.college.VO.RequestTemplate;
import com.milestone4.college.entity.College;
import com.milestone4.college.service.CollegeService;


@RestController
@RequestMapping("/college")
public class CollegeController {
	
	@Autowired
	CollegeService cser;
	
	@PostMapping
	public College add(@RequestBody College clg)
	{
		return cser.add(clg);
	}
	
	@GetMapping
	public List<College> list()
	{
		return cser.list();
	}
	
	@GetMapping("/{id}")
	public College searchById(@PathVariable long id)
	{
		return cser.searchById(id);
	}
	
	@GetMapping("/all-college-student")
	public List<RequestTemplate> listWithStu()
	{
		return cser.listWithStu();
	}
	
	@GetMapping("/college-with-student/{clg_id}")
	public RequestTemplate specificClgStu(@PathVariable long clg_id)
	{
		return cser.specificClgStudent(clg_id);
	}
	
	
}








