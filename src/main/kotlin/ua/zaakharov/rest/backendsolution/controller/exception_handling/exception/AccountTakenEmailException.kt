package ua.zaakharov.rest.backendsolution.controller.exception_handling.exception

class AccountTakenEmailException(
    email: String?,
    message: String = "Account by email: $email is already in database"
) : RuntimeException(message)