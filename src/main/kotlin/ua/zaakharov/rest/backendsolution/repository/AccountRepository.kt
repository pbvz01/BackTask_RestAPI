package ua.zaakharov.rest.backendsolution.repository;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ua.zaakharov.rest.backendsolution.model.Account
import java.util.Optional

@Repository
interface AccountRepository : JpaRepository<Account, Long> {
    fun findAccountByEmail(email: String?): Optional<Account>
    fun findAccountByPhone(phone: String?): Optional<Account>
    fun findAccountByEmailOrPhone(email: String?, phone: String?): Optional<Account>
}
