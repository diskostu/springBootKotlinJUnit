package de.diskostu.learn.`spring-boot-thenewboston`.service

import de.diskostu.learn.`spring-boot-thenewboston`.datasource.BankDataSource
import de.diskostu.learn.`spring-boot-thenewboston`.model.BankDto
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

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


    @Test
    fun `should call its data source to update a bank`() {
        // given
        val bank = BankDto("1234", 1.0, 1)

        // when
        bankService.updateBank(bank)

        // then
        verify(exactly = 1) { dataSource.updateBank(bank) }
    }


    @Test
    fun `should call its data source to delete a bank`() {
        // given
        val accountNumber = "1234"

        // when
        bankService.deleteBank(accountNumber)

        // then
        verify(exactly = 1) { dataSource.deleteBank(accountNumber) }
    }
}
