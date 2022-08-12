package com.milestone3.student;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.milestone3.student.entity.Student;
import com.milestone3.student.repository.StudentRepository;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class EmployeeApplicationTests {

	@Autowired
    private MockMvc mockMvc; 
	
	@Autowired
    private StudentRepository studentRepository;
	
	@BeforeEach
    void setup(){
		studentRepository.deleteAll();
    }

	@Test
    public void givenListOfEmployees_whenGetAllEmployees_thenReturnEmployeesList() throws Exception{
        // given - precondition or setup
        List<Student> listOfStudent = new ArrayList<>();
       listOfStudent.add(new Student(101,"Praveen","Male",22,"arts",111));
        listOfStudent.add(new Student(102,"uppi","Male",21,"Science",222));
        listOfStudent.add(new Student(103,"sourav","Male",20,"commerce",333));
        
        studentRepository.saveAll(listOfStudent);
        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("http://localhost:8472/student"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfStudent.size())));

    }
}