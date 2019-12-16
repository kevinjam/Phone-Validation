package com.cellulant.validation;

import com.cellulant.validation.model.ValidateModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
@EnableRedisRepositories
@Configuration
public class ValidationApplication {


	@Value(value = "${spring.redis.host}")
	private String redisHostname;

	@Value(value = "${spring.redis.port}")
	private int redisPort;


	public static void main(String[] args) {
		SpringApplication.run(ValidationApplication.class, args);

	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHostname, redisPort);
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

	@Bean
	public RedisTemplate<String, ValidateModel> userTemplate() {
		RedisTemplate<String, ValidateModel> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		return template;
	}

}
