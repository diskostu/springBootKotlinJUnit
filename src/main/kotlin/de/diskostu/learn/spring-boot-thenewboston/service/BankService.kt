package de.diskostu.learn.`spring-boot-thenewboston`.service

import de.diskostu.learn.`spring-boot-thenewboston`.datasource.BankDataSource
import de.diskostu.learn.`spring-boot-thenewboston`.model.BankDto
import org.springframework.stereotype.Service

@Service
class BankService(private val dataSource: BankDataSource) {
    fun getBanks() = dataSource.retrieveBanks()
    fun getBank(accountNumber: String) = dataSource.retrieveBank(accountNumber)
    fun addBank(bank: BankDto) = dataSource.addBank(bank)
    fun updateBank(bank: BankDto) = dataSource.updateBank(bank)
    fun deleteBank(accountNumber: String) = dataSource.deleteBank(accountNumber)
}
