package de.diskostu.learn.springBootKotlinJUnit.controller

import de.diskostu.learn.springBootKotlinJUnit.model.BankDto
import de.diskostu.learn.springBootKotlinJUnit.service.BankService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/banks")
class BankController(private val service: BankService) {

    @GetMapping
    fun getBanks(): Collection<BankDto> {
        return service.getBanks()
    }
}
