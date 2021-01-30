package de.diskostu.learn.springBootKotlinJUnit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootKotlinJUnitApplication

fun main(args: Array<String>) {
	runApplication<SpringBootKotlinJUnitApplication>(*args)
}
