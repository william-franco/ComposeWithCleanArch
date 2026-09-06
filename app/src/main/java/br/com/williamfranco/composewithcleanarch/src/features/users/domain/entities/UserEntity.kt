package br.com.williamfranco.composewithcleanarch.src.features.users.domain.entities

data class UserEntity(
    val id: Int? = null,
    val name: String? = null,
    val username: String? = null,
    val email: String? = null,
    val address: AddressEntity? = null,
    val phone: String? = null,
    val website: String? = null,
    val company: CompanyEntity? = null,
)

data class AddressEntity(
    val street: String? = null,
    val suite: String? = null,
    val city: String? = null,
    val zipcode: String? = null,
    val geo: GeoEntity? = null,
)

data class GeoEntity(
    val lat: String? = null,
    val lng: String? = null,
)

data class CompanyEntity(
    val name: String? = null,
    val catchPhrase: String? = null,
    val bs: String? = null,
)
