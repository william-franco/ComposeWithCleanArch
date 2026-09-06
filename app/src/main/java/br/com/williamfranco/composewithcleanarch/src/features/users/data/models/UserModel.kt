package br.com.williamfranco.composewithcleanarch.src.features.users.data.models

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: Int? = null,
    val name: String? = null,
    val username: String? = null,
    val email: String? = null,
    val address: AddressModel? = null,
    val phone: String? = null,
    val website: String? = null,
    val company: CompanyModel? = null,
)

@Serializable
data class AddressModel(
    val street: String? = null,
    val suite: String? = null,
    val city: String? = null,
    val zipcode: String? = null,
    val geo: GeoModel? = null,
)

@Serializable
data class GeoModel(
    val lat: String? = null,
    val lng: String? = null,
)

@Serializable
data class CompanyModel(
    val name: String? = null,
    val catchPhrase: String? = null,
    val bs: String? = null,
)
