package ua.zaakharov.rest.backendsolution.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import java.sql.Date
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long?,

    @Column(name ="name")
    @field:NotEmpty(message = "The user has to have a name.")
    @field:Size(min = 3,
        message = "The user has to have at least 3 symbols in name")
    @field:NotNull(message = "The request has not have key ${"name"}")
    val name: String?,

    @Column(name ="lastname")
    @field:NotEmpty(message = "The user has to have a lastname.")
    @field:Size(min = 3,
        message = "The user has to have at least 3 symbols in lastname")
    @field:NotNull(message = "The request has not have key ${"lastname"}")
    val lastname: String?,

    @Column(name ="phone")
    @field:Pattern(regexp = "^([+])\\d{12}\$",
        message = "The phone has to start with ${"+"} and have 12 numbers")
    @field:NotNull(message = "The request has not have key ${"phone"}")
    val phone: String?,

    @Column(name ="email")
    @field:Email(message = "Incorrect email. The email ")
    @field:NotNull(message = "The request has not have key ${"email"}")
    val email: String?,

    @Column(name ="date")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    val date: Date? = Date(java.util.Date().time),

    @Column(name ="application")
    @field:NotEmpty(message = "The user has to have a name of application")
    @field:NotNull(message = "The request has not have key ${"app"}")
    val app: String?
    )