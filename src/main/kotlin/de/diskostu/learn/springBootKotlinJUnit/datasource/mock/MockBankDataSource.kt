package de.diskostu.learn.springBootKotlinJUnit.datasource.mock

import de.diskostu.learn.springBootKotlinJUnit.datasource.BankDataSource
import de.diskostu.learn.springBootKotlinJUnit.datasource.NoBankFoundException
import de.diskostu.learn.springBootKotlinJUnit.model.BankDto
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource {

    val banks = listOf(
        BankDto("1234", 3.0, 1),
        BankDto("1010", 17.0, 0),
        BankDto("5462", 0.0, 100)
    )

    override fun retrieveBanks(): Collection<BankDto> = banks


    override fun retrieveBank(accountNumber: String): BankDto {
        return banks.firstOrNull { it.accountNumber == accountNumber } ?: throw NoBankFoundException(accountNumber)
    }
}
