package de.diskostu.learn.springBootKotlinJUnit.datasource.network.dto

import de.diskostu.learn.springBootKotlinJUnit.model.BankDto

data class BankList(val results: Collection<BankDto>)
