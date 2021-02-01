package de.diskostu.learn.springBootKotlinJUnit.datasource.mock

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest {

    private val mockDataSource = MockBankDataSource()

    @Test
    fun `should provide a collection of bank`() {
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
        assertThat(bank.trust).isEqualTo(1.0)
        assertThat(bank.transactionFee).isEqualTo(10)
    }
}
