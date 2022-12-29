package ua.zaakharov.rest.backendsolution.controller.exception_handling

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ua.zaakharov.rest.backendsolution.controller.exception_handling.exception.AccountNotFoundException
import ua.zaakharov.rest.backendsolution.controller.exception_handling.exception.AccountTakenEmailException
import ua.zaakharov.rest.backendsolution.controller.exception_handling.exception.AccountTakenPhoneException
import ua.zaakharov.rest.backendsolution.controller.exception_handling.wrapper.AccountIncorrectData

@ControllerAdvice
class AccountGlobalExceptionHandler {
    @ExceptionHandler
    fun handlerException(ex: AccountNotFoundException): ResponseEntity<AccountIncorrectData> {
        val accountIncorrectData = AccountIncorrectData(ex.message)
        return ResponseEntity(accountIncorrectData, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handlerException(ex: AccountTakenEmailException): ResponseEntity<AccountIncorrectData> {
        val accountIncorrectData = AccountIncorrectData(ex.message)
        return ResponseEntity(accountIncorrectData, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handlerException(ex: AccountTakenPhoneException): ResponseEntity<AccountIncorrectData> {
        val accountIncorrectData = AccountIncorrectData(ex.message)
        return ResponseEntity(accountIncorrectData, HttpStatus.BAD_REQUEST)
    }
}