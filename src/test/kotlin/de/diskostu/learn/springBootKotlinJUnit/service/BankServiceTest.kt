package de.diskostu.learn.springBootKotlinJUnit.service

import de.diskostu.learn.springBootKotlinJUnit.datasource.BankDataSource
import de.diskostu.learn.springBootKotlinJUnit.model.BankDto
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.internal.matchers.Any

/**
 * Independent of the Spring application context.
 */
internal class BankServiceTest {

    private val dataSource: BankDataSource = mockk(relaxed = true)
    private val bankService = BankService(dataSource)


    @Test
    fun `should call its data source to retrieve banks`() {
        // when
        bankService.getBanks()

        // then
        verify(exactly = 1) { dataSource.retrieveBanks() }
    }


    @Test
    fun `should call its data source to retrieve a bank with the correct account number`() {
        // given
        val accountNumber = "666"

        // when
        bankService.getBank(accountNumber)

        // then
        verify(exactly = 1) { dataSource.retrieveBank(accountNumber) }
    }


    @Test
    fun `should call its data source to add a new bank`() {
        // given
        val bank = BankDto("new_bank_account", 1.0, 1)

        // when
        bankService.addBank(bank)

        // then
        verify(exactly = 1) { dataSource.addBank(bank) }
    }
}
