package com.example.redisdemo;

import com.example.redisdemo.model.Student;
import com.example.redisdemo.repo.StudentListRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RedisDemoApplication {

    public static void main(String[] args) {

        final ConfigurableApplicationContext applicationContext = SpringApplication.run(RedisDemoApplication.class,
                args);
        final StudentListRepository studentRepository = applicationContext.getBean(StudentListRepository.class);

        final List<Student> studentList = new ArrayList<>();
        final Student student1 = new Student();
        student1.setGender(Student.Gender.FEMALE);
        student1.setGrade(5);
        student1.setId(5L);
        student1.setName("Mary Jane");
        studentList.add(student1);

        final Student student2 = new Student();
        student2.setGender(Student.Gender.MALE);
        student2.setGrade(15);
        student2.setId(50L);
        student2.setName("John Doe");
        studentList.add(student2);

        studentRepository.saveStudents(studentList);

    }

}
