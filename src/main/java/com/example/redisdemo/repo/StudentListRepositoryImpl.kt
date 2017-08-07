package com.example.redisdemo.repo

import com.example.redisdemo.model.Student
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ListOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct

@Repository
open class StudentListRepositoryImpl @Autowired constructor(val redisTemplate: RedisTemplate<String, Student>,
                                                            val studentDBRepository: StudentDBRepository) : StudentListRepository {

    private lateinit var listOps: ListOperations<String, Student>
    private val KEY = "StudentList"
    private val logger = LoggerFactory.getLogger(StudentListRepositoryImpl::class.java)

    @PostConstruct
    private fun init() {
        listOps = redisTemplate.opsForList()
    }

    override fun saveStudents(students: List<Student>) {

        studentDBRepository.save<Student>(students)
        cacheStudents(students)
    }

    private fun cacheStudents(students: List<Student>) {


        // Require cache re-validation every 60secs
        val timeout = 60L

        logger.info("Caching new students")

        listOps.operations.delete(KEY)
        listOps.rightPushAll(KEY, students)
        listOps.operations.expire(KEY, timeout, TimeUnit.SECONDS)
    }

    private fun readStudentsFromCache(): List<Student>? {

        logger.info("Reading students from cache")

        return listOps.range(KEY, 0L, -1L)
    }

    override fun findStudents(): List<Student> {

        val cachedStudents = readStudentsFromCache()

        if (cachedStudents == null || cachedStudents.isEmpty()) {

            logger.info("No cached students...Reading from DB")
            val students = studentDBRepository.findAll()
            cacheStudents(students)

            return students

        } else {

            logger.info("Returning already cached students")
            return cachedStudents
        }
    }

}
