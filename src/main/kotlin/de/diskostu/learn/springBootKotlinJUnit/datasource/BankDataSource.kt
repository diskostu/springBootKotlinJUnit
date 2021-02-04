package de.diskostu.learn.springBootKotlinJUnit.datasource

import de.diskostu.learn.springBootKotlinJUnit.model.BankDto

interface BankDataSource {

    fun retrieveBanks(): Collection<BankDto>
    fun retrieveBank(accountNumber: String): BankDto
    fun addBank(bank: BankDto): BankDto
    fun updateBank(bankForUpdate: BankDto): BankDto
    fun deleteBank(accountNumber: String)
}
