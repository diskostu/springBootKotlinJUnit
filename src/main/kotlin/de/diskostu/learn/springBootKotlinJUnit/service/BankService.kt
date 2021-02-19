package de.diskostu.learn.springBootKotlinJUnit.service

import de.diskostu.learn.springBootKotlinJUnit.datasource.BankDataSource
import de.diskostu.learn.springBootKotlinJUnit.datasource.mock.MockBankDataSource
import de.diskostu.learn.springBootKotlinJUnit.model.BankDto
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class BankService(@Qualifier("network") private val dataSource: BankDataSource) {
    fun getBanks() = dataSource.retrieveBanks()
    fun getBank(accountNumber: String) = dataSource.retrieveBank(accountNumber)
    fun addBank(bank: BankDto) = dataSource.addBank(bank)
    fun updateBank(bank: BankDto) = dataSource.updateBank(bank)
    fun deleteBank(accountNumber: String) = dataSource.deleteBank(accountNumber)
}
