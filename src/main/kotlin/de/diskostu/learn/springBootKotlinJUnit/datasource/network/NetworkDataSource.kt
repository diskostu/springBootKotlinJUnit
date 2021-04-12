package de.diskostu.learn.springBootKotlinJUnit.datasource.network

import de.diskostu.learn.springBootKotlinJUnit.datasource.BankDataSource
import de.diskostu.learn.springBootKotlinJUnit.datasource.network.dto.BankList
import de.diskostu.learn.springBootKotlinJUnit.model.BankDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import java.io.IOException

@Repository("network")
class NetworkDataSource(@Autowired private val restTemplate: RestTemplate) : BankDataSource {

    private final val ip = "54.193.31.159"

    override fun retrieveBanks(): Collection<BankDto> {
        val response = restTemplate.getForEntity<BankList>("http://$ip/banks")

        return response.body?.results ?: throw IOException("Could not fetch banks from the network")
    }

    override fun retrieveBank(accountNumber: String): BankDto {
        TODO("Not yet implemented")
    }

    override fun addBank(bank: BankDto): BankDto {
        TODO("Not yet implemented")
    }

    override fun updateBank(bankForUpdate: BankDto): BankDto {
        TODO("Not yet implemented")
    }

    override fun deleteBank(accountNumber: String) {
        TODO("Not yet implemented")
    }
}
