package de.diskostu.learn.springBootKotlinJUnit.datasource.mock

import de.diskostu.learn.springBootKotlinJUnit.model.BankDto
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.assertFailsWith

internal class MockBankDataSourceTest {

    private val mockDataSource = MockBankDataSource()

    @Test
    fun `should provide a collection of banks`() {
        // when
        val banks = mockDataSource.retrieveBanks()

        // then
        assertThat(banks.size).isGreaterThanOrEqualTo(3)
    }


    @Test
    fun `should provide some mock data`() {
        // when
        val banks = mockDataSource.retrieveBanks()

        // then
        assertThat(banks).allMatch { it.accountNumber.isNotBlank() }
        assertThat(banks).anyMatch { it.trust != 0.0 }
        assertThat(banks).anyMatch { it.transactionFee != 0 }
    }


    @Test
    fun `should return a single bank`() {
        // given
        val accountNumber = "1234"

        // when
        val bank = mockDataSource.retrieveBank(accountNumber = accountNumber)

        // then
        assertThat(bank).isNotNull
        assertThat(bank.accountNumber).isEqualTo(accountNumber)
        assertThat(bank.trust).isEqualTo(3.0)
        assertThat(bank.transactionFee).isEqualTo(10)
    }


    @Test
    fun `should add a new bank`() {
        // given
        val accountNumber = "new_bank_account"
        val trust = 12.0
        val transactionFee = 5
        val bank = BankDto(accountNumber, trust, transactionFee)
        val currentBanksSize = mockDataSource.retrieveBanks().size

        // when
        val addedBank: BankDto = mockDataSource.addBank(bank)

        // then
        assertThat(mockDataSource.retrieveBanks().size == currentBanksSize + 1)
        assertThat(addedBank.accountNumber == accountNumber)
        assertThat(addedBank.trust == trust)
        assertThat(addedBank.transactionFee == transactionFee)
    }


    @Test
    fun `should throw an exception when an existing bank should be added`() {
        // given
        val bank = BankDto("1234", 1.0, 1)

        // when / then
        val exception = assertFailsWith<IllegalArgumentException> {
            mockDataSource.addBank(bank)
        }
        assertThat(exception.message).contains("already exists")
    }
}
