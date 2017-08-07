package com.example.redisdemo.repo

import com.example.redisdemo.model.Student
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import javax.annotation.PostConstruct

@Repository
class StudentRepositoryImpl @Autowired constructor(val redisTemplate: RedisTemplate<String, Student>) : StudentRepository {

    private val KEY = "Student"
    private lateinit var hashOps: HashOperations<String, Long, Student>

    @PostConstruct
    private fun init() {
        hashOps = redisTemplate.opsForHash<Long, Student>()
    }

    override fun saveStudent(student: Student) {
        hashOps.put(KEY, student.id, student)
    }

    override fun updateStudent(student: Student) {
        hashOps.put(KEY, student.id, student)
    }

    override fun findStudent(id: String): Student {
        return hashOps.get(KEY, id)
    }

    override fun findAllStudents(): Map<Long, Student> {
        return hashOps.entries(KEY)
    }

    override fun deleteStudent(id: String) {
        hashOps.delete(KEY, id)
    }

}
