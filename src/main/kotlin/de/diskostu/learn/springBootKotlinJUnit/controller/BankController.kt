package de.diskostu.learn.springBootKotlinJUnit.controller

import de.diskostu.learn.springBootKotlinJUnit.datasource.BankAlreadyExistsException
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
    fun handleBadRequest(ex: NoBankFoundException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BankAlreadyExistsException::class)
    fun handleBadRequest(ex: BankAlreadyExistsException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }

    @GetMapping
    fun getBanks(): Collection<BankDto> {
        return service.getBanks()
    }


    @GetMapping("/{accountNumber}")
    fun getBank(@PathVariable accountNumber: String): BankDto {
        return service.getBank(accountNumber)
    }


    /**
     * with the RequestBody annotation, Jackson extracts the JSON data from the request and maps it into a BankDto object.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody bank: BankDto): BankDto {
        return service.addBank(bank)
    }
}
