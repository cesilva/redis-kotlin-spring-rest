package com.example.redisdemo.repo

import com.example.redisdemo.model.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentDBRepository : JpaRepository<Student, Long>
