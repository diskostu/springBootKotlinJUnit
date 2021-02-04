package de.diskostu.learn.springBootKotlinJUnit.datasource.mock

import de.diskostu.learn.springBootKotlinJUnit.datasource.NoBankFoundException
import de.diskostu.learn.springBootKotlinJUnit.model.BankDto
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.*
import java.lang.IllegalArgumentException
import kotlin.test.assertFailsWith

internal class MockBankDataSourceTest {

    private val mockDataSource = MockBankDataSource()

    @Nested
    @DisplayName("getBanks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBanks {
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
    }


    @Nested
    @DisplayName("getBank")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBank {
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
    }


    @Nested
    @DisplayName("addBank")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class AddBank {
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


    @Nested
    @DisplayName("updateBank")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class UpdateBanks {
        @Test
        fun `should update an existing bank`() {
            // given
            val accountNumber = "1234"
            val trust = 11.0
            val transactionFee = 1
            val bankForUpdate = BankDto(accountNumber, trust, transactionFee)
            val banksSizeBeforeUpdate = mockDataSource.retrieveBanks().size

            // when
            val updatedBank: BankDto = mockDataSource.updateBank(bankForUpdate)

            // then
            // check that the returned object is correct
            assertThat(mockDataSource.retrieveBanks().size == banksSizeBeforeUpdate)
            assertThat(updatedBank.accountNumber == accountNumber)
            assertThat(updatedBank.trust == trust)
            assertThat(updatedBank.transactionFee == transactionFee)

            // read the bank again and check all properties
            val retrievedBank = mockDataSource.retrieveBank(accountNumber)
            assertThat(retrievedBank.accountNumber == accountNumber)
            assertThat(retrievedBank.trust == trust)
            assertThat(retrievedBank.transactionFee == transactionFee)
        }


        @Test
        fun `should throw an exception when a non-existing bank should be updated`() {
            // given
            val accountNumber = "non_existing_account"
            val bankForUpdate = BankDto(accountNumber, 1.0, 1)

            // when
            val exception = assertFailsWith<NoBankFoundException> {
                mockDataSource.updateBank(bankForUpdate)
            }
            assertThat(exception.message).contains(accountNumber)
        }
    }


    @Nested
    @DisplayName("deleteBank")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class DeleteBanks {

        @Test
        fun `should delete an existing bank`() {
            // given
            val accountNumber = "1234"

            // when
            mockDataSource.deleteBank(accountNumber)

            // then
            try {
                mockDataSource.retrieveBank(accountNumber)
                fail("We expect that no bank is found.")
            } catch (ex: NoBankFoundException) {
                // OK
            }
        }


        @Test
        fun `should throw an exception when a non-existing bank should be deleted`() {
            // given
            val accountNumber = "non_existing_account"

            // when
            val exception = assertFailsWith<NoBankFoundException> {
                mockDataSource.deleteBank(accountNumber)
            }
            assertThat(exception.message).contains(accountNumber)
        }
    }
}
