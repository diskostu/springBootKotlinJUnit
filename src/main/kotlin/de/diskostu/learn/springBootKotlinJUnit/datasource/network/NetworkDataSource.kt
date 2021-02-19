package de.diskostu.learn.springBootKotlinJUnit.datasource.network

import de.diskostu.learn.springBootKotlinJUnit.datasource.BankDataSource
import de.diskostu.learn.springBootKotlinJUnit.model.BankDto
import org.springframework.stereotype.Repository

@Repository("network")
class NetworkDataSource : BankDataSource {
    override fun retrieveBanks(): Collection<BankDto> {
        TODO("Not yet implemented")
    }

    override fun retrieveBank(accountNumber: String): BankDto {
        TODO("Not yet implemented")
    }

    override fun addBank(bank: BankDto): BankDto {
        TODO("Not yet implemented")
    }

    override fun updateBank(bankForUpdate: BankDto): BankDto {
        TODO("Not yet implemented")
    }

    override fun deleteBank(accountNumber: String) {
        TODO("Not yet implemented")
    }
}
