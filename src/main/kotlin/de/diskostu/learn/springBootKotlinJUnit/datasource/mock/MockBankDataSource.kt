package de.diskostu.learn.springBootKotlinJUnit.datasource.mock

import de.diskostu.learn.springBootKotlinJUnit.datasource.BankDataSource
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
}
