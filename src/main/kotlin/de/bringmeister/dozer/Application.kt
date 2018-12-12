package de.bringmeister.dozer

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
