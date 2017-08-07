package com.example.redisdemo.model

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class Student : BaseModel(), Serializable {

    var name: String? = null

    @Enumerated(EnumType.STRING)
    var gender: Gender? = null

    var grade: Int = 0

    enum class Gender {
        MALE,
        FEMALE
    }

    override fun toString(): String {
        val sb = StringBuilder("Student{")
        sb.append("id='").append(id).append('\'')
        sb.append(", name='").append(name).append('\'')
        sb.append(", gender=").append(gender)
        sb.append(", grade=").append(grade)
        sb.append('}')
        return sb.toString()
    }
}
