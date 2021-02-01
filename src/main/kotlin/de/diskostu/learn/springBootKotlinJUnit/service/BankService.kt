package de.diskostu.learn.springBootKotlinJUnit.service

import de.diskostu.learn.springBootKotlinJUnit.datasource.BankDataSource
import de.diskostu.learn.springBootKotlinJUnit.datasource.mock.MockBankDataSource
import de.diskostu.learn.springBootKotlinJUnit.model.BankDto
import org.springframework.stereotype.Service

@Service
class BankService(private val dataSource: BankDataSource) {
    fun getBanks() = dataSource.retrieveBanks()
    fun getBank(accountNumber: String) = dataSource.retrieveBank(accountNumber)
    fun addBank(bank: BankDto) = dataSource.addBank(bank)
}
