package com.example.redisdemo.controller

import com.example.redisdemo.model.Student
import com.example.redisdemo.repo.StudentListRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StudentController {

    @Autowired
    private lateinit var studentListRepository: StudentListRepository

    @GetMapping("/students")
    fun getStudents(): List<Student> {
        return studentListRepository.findStudents()

    }
}

