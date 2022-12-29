package ua.zaakharov.rest.backendsolution.controller.exception_handling.exception

class AccountTakenPhoneException(
    phone: String?,
    message: String = "Account by phone: $phone is already in database"
) : RuntimeException(message)