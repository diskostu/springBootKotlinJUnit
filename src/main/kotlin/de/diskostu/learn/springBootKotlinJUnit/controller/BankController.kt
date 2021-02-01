package de.diskostu.learn.springBootKotlinJUnit.controller

import de.diskostu.learn.springBootKotlinJUnit.datasource.NoBankFoundException
import de.diskostu.learn.springBootKotlinJUnit.model.BankDto
import de.diskostu.learn.springBootKotlinJUnit.service.BankService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/banks")
class BankController(private val service: BankService) {

    @ExceptionHandler(NoBankFoundException::class)
    fun handleNotFound(ex: NoBankFoundException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }

    @GetMapping
    fun getBanks(): Collection<BankDto> {
        return service.getBanks()
    }


    @GetMapping("/{accountNumber}")
    fun getBank(@PathVariable accountNumber: String): BankDto {
        return service.getBank(accountNumber)
    }
}
