package com.hf.springboottest

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringboottestApplication

fun main(args: Array<String>) {
	runApplication<SpringboottestApplication>(*args)
}
