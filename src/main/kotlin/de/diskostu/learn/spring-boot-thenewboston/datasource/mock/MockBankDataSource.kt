package de.diskostu.learn.`spring-boot-thenewboston`.datasource.mock

import de.diskostu.learn.`spring-boot-thenewboston`.datasource.BankAlreadyExistsException
import de.diskostu.learn.`spring-boot-thenewboston`.datasource.BankDataSource
import de.diskostu.learn.`spring-boot-thenewboston`.datasource.NoBankFoundException
import de.diskostu.learn.`spring-boot-thenewboston`.model.BankDto
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource {

    val banks = mutableListOf(
        BankDto("1234", 3.0, 10),
        BankDto("1010", 17.0, 0),
        BankDto("5462", 0.0, 100)
    )

    override fun retrieveBanks(): Collection<BankDto> = banks


    override fun retrieveBank(accountNumber: String): BankDto {
        return banks.firstOrNull { it.accountNumber == accountNumber } ?: throw NoBankFoundException(accountNumber)
    }


    override fun addBank(bank: BankDto): BankDto {
        if (banks.any { it.accountNumber == bank.accountNumber }) {
            throw BankAlreadyExistsException(bank.accountNumber)
        }

        banks.add(bank)
        return bank
    }

    override fun updateBank(bankForUpdate: BankDto): BankDto {
        val accountNumber = bankForUpdate.accountNumber
        deleteBank(accountNumber)

        banks.add(bankForUpdate)

        return bankForUpdate
    }

    override fun deleteBank(accountNumber: String) {
        val existingBank = banks.firstOrNull { it.accountNumber == accountNumber }
            ?: throw NoBankFoundException(accountNumber)

        banks.remove(existingBank)
    }
}
