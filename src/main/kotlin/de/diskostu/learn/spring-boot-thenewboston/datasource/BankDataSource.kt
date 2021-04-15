package de.diskostu.learn.`spring-boot-thenewboston`.datasource

import de.diskostu.learn.`spring-boot-thenewboston`.model.BankDto

interface BankDataSource {

    fun retrieveBanks(): Collection<BankDto>
    fun retrieveBank(accountNumber: String): BankDto
    fun addBank(bank: BankDto): BankDto
    fun updateBank(bankForUpdate: BankDto): BankDto
    fun deleteBank(accountNumber: String)
}
