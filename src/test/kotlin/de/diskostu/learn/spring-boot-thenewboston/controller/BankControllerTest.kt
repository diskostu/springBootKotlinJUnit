package de.diskostu.learn.`spring-boot-thenewboston`.controller

import com.fasterxml.jackson.databind.ObjectMapper
import de.diskostu.learn.`spring-boot-thenewboston`.model.BankDto
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.*
import javax.print.DocFlavor

/**
 * Integration test, starts up the whole application (and with it the Spring context)
 *
 * We have to use DirtiesContext here, because we have some operations where we modify the data. After that,
 * the current context should be removed from the context cache and subsequent tests should get a new context.
 * This makes the tests independent from each other.
 */
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
internal class BankControllerTest @Autowired constructor(val mockMvc: MockMvc, val objectMapper: ObjectMapper) {
    private val baseUrl = "/api/banks"

    @Nested
    @DisplayName("GET /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBanks {
        @Test
        fun `should return all banks`() {
            // when / then
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber") { value("1234") }
                }
        }
    }

    @Nested
    @DisplayName("GET /api/banks/{accountNumber}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBank {
        @Test
        fun `should return the bank with the given account number`() {
            // given
            val accountNumber = "1234"

            // when / then
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.trust") { value("3.0") }
                    jsonPath("$.transactionFee") { value("10") }
                }
        }

        @Test
        fun `should return not found if the accountNumber does not exist`() {
            // given
            val accountNumber = "does_not_exist"

            // when / then
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }

    }

    @Nested
    @DisplayName("POST /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostNewBank {
        @Test
        fun `should add the new bank`() {
            // given
            val accountNumber = "new_bank_account"
            val trust = 123.45
            val transactionFee = 2
            val bank = BankDto(accountNumber, trust, transactionFee)

            // when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(bank)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.accountNumber") { value(accountNumber) }
                    jsonPath("$.trust") { value(trust) }
                    jsonPath("$.transactionFee") { value(transactionFee) }
                }
        }


        @Test
        fun `should return BAD REQUEST if bank with given account number already exists`() {
            // given
            val existingBank = BankDto("1234", 1.0, 1)

            // when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(existingBank)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                    content {
                        contentType(DocFlavor.URL.TEXT_PLAIN_UTF_8.mimeType)
                    }
                }
        }

    }

    @Nested
    @DisplayName("PATCH /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class UpdateBank {
        @Test
        fun `should update an existing bank`() {
            // given
            val accountNumber = "1234"
            val updatedTrust = 11.0
            val updatedTransactionFee = 3
            val bankForUpdate = BankDto(accountNumber, updatedTrust, updatedTransactionFee)

            // when
            val performPatch = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(bankForUpdate)
            }

            // then
            performPatch
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                }

            mockMvc.get("$baseUrl/${bankForUpdate.accountNumber}")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.trust") { value(updatedTrust) }
                    jsonPath("$.transactionFee") { value(updatedTransactionFee) }
                }
        }
    }

    @Nested
    @DisplayName("DELETE /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class DeleteBank {

        @Test
        fun `should delete an existing bank`() {
            // given
            val accountNumber = "1234"

            // when / then
            mockMvc.delete("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isNoContent() }
                }

            mockMvc.get("$baseUrl/$accountNumber")
                .andExpect { status { isNotFound() } }
        }


        @Test
        fun `should return not found if the accountNumber does not exist`() {
            // given
            val accountNumber = "does_not_exist"

            // when / then
            mockMvc.delete("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }
}
