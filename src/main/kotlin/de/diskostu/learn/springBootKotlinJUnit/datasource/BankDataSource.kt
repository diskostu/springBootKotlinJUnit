package de.diskostu.learn.springBootKotlinJUnit.datasource

import de.diskostu.learn.springBootKotlinJUnit.model.BankDto

interface BankDataSource {

    fun retrieveBanks(): Collection<BankDto>
    fun retrieveBank(accountNumber: String): BankDto
}
