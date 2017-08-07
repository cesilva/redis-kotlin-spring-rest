package com.example.redisdemo.config

import com.example.redisdemo.model.BaseModel
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
@EnableJpaRepositories("com.example.redisdemo.repo")
class RedisConfig {

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        return JedisConnectionFactory()
    }

    @Bean
    fun <T : BaseModel> redisTemplate(): RedisTemplate<String, T> {
        val template = RedisTemplate<String, T>()
        template.connectionFactory = jedisConnectionFactory()
        return template
    }
}
