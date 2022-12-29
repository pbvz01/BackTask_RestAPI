package ua.zaakharov.rest.backendsolution.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ua.zaakharov.rest.backendsolution.model.Account
import ua.zaakharov.rest.backendsolution.service.AccountService
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/accounts")
class AccountController(val service: AccountService) {

    @GetMapping("/param")
    fun getAccountWithParam(
        @RequestParam phone: String?,
        @RequestParam email: String?
    ): ResponseEntity<Account> {
        val correctPhone = "+$phone"
        val account = if(!phone.isNullOrEmpty()) {
                service.getAccountByPhone(correctPhone)
            } else {
            service.getAccountByEmail(email!!)
        }
        return ResponseEntity<Account>(account, HttpStatus.FOUND)
    }

    @GetMapping
    fun getAllAccounts(): ResponseEntity<Iterable<Account>> {
        val accounts = service.getAllAccounts()
        return ResponseEntity<Iterable<Account>>(accounts, HttpStatus.FOUND)
    }

    @GetMapping("/{id}")
    fun getAccount(@PathVariable("id") id: Long): ResponseEntity<Account> {
        val account = service.getAccountById(id)
        return ResponseEntity<Account>(account, HttpStatus.FOUND)
    }

    @PostMapping
    fun addAccount(@RequestBody @Valid account: Account): ResponseEntity<Account> {
        val newAccount = service.saveAccount(account)
        return ResponseEntity<Account>(newAccount, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateAccount(
        @RequestBody @Valid account: Account,
        @PathVariable("id") id: Long): ResponseEntity<Account> {
        val updateAccount = service.updateAccount(account, id)
        return ResponseEntity<Account>(updateAccount, HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun deleteAccount(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        service.deleteAccount(service.getAccountById(id))
        return ResponseEntity(HttpStatus.CREATED)
    }
}


