package com.example.redisdemo.repo;

import com.example.redisdemo.model.Student;

import java.util.Map;

public interface StudentRepository {

    void saveStudent(Student student);

    void updateStudent(Student student);

    Student findStudent(String id);

    Map<Long, Student> findAllStudents();

    void deleteStudent(String id);
}
