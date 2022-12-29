package ua.zaakharov.rest.backendsolution.service

import org.springframework.stereotype.Service
import ua.zaakharov.rest.backendsolution.controller.exception_handling.exception.AccountKeyException
import ua.zaakharov.rest.backendsolution.controller.exception_handling.exception.AccountNotFoundException
import ua.zaakharov.rest.backendsolution.controller.exception_handling.exception.AccountTakenEmailException
import ua.zaakharov.rest.backendsolution.controller.exception_handling.exception.AccountTakenPhoneException
import ua.zaakharov.rest.backendsolution.model.Account
import ua.zaakharov.rest.backendsolution.repository.AccountRepository

@Service
class AccountService(val accountRepository: AccountRepository) {

    fun getAllAccounts(): Iterable<Account> = accountRepository.findAll()

    fun getAccountById(id: Long): Account =
        accountRepository.findById(id)
            .orElseThrow{AccountNotFoundException(AccountKeyException.ID, id.toString())}

    fun getAccountByPhone(phone: String): Account =
        accountRepository.findAccountByPhone(phone)
            .orElseThrow{AccountNotFoundException(AccountKeyException.PHONE, phone)}

    fun getAccountByEmail(email: String): Account =
        accountRepository.findAccountByEmail(email)
            .orElseThrow{AccountNotFoundException(AccountKeyException.EMAIL, email)}

    fun saveAccount(account: Account): Account {
        accountRepository.findAccountByEmailOrPhone(account.email, account.phone)
        .ifPresent {
            if (it.email.equals(account.email)) {
                throw AccountTakenEmailException(it.email)
            } else {
                throw AccountTakenPhoneException(it.phone)
            }
        }
        return accountRepository.save(account)
    }

    fun updateAccount(account: Account, id: Long) : Account {
        val currentAccount = getAccountById(id)
        val idCurrentAccount = currentAccount.id
        account.id = idCurrentAccount
        return saveAccount(account)
    }

    fun deleteAccount(account: Account) = accountRepository.delete(account)
}