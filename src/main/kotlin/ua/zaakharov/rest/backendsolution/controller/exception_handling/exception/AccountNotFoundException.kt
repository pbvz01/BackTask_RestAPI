package ua.zaakharov.rest.backendsolution.controller.exception_handling.exception

import java.lang.RuntimeException

class AccountNotFoundException (
    key: AccountKeyException,
    value: String,
    message: String = "Account by $key: $value was not found"
) : RuntimeException(message)

enum class AccountKeyException {
    ID {
        override fun toString(): String = "ID"
    },
    EMAIL {
        override fun toString(): String = "EMAIL"
    },
    PHONE {
        override fun toString(): String = "PHONE"
    }


}