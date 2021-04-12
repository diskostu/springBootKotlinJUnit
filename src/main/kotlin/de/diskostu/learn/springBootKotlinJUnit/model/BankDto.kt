package de.diskostu.learn.springBootKotlinJUnit.model

import com.fasterxml.jackson.annotation.JsonProperty

data class BankDto(
        @JsonProperty("account_number")
        val accountNumber: String,
        @JsonProperty("trust")
        val trust: Double,
        @JsonProperty("default_transaction_fee")
        val transactionFee: Int,
)
