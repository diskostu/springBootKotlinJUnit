package de.diskostu.learn.springBootKotlinJUnit.service

import de.diskostu.learn.springBootKotlinJUnit.datasource.BankDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
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
}
