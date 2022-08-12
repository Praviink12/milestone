package com.milestone3.student.VO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class College {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private int total_stu;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotal_stu() {
		return total_stu;
	}
	public void setTotal_stu(int total_stu) {
		this.total_stu = total_stu;
	}
	public College(long id, String name, int total_stu) {
		super();
		this.id = id;
		this.name = name;
		this.total_stu = total_stu;
	}
	public College() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
