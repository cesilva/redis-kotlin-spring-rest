package com.example.redisdemo.repo

import com.example.redisdemo.model.Student

interface StudentListRepository {

    fun saveStudents(students: List<Student>)

    fun findStudents(): List<Student>

}
