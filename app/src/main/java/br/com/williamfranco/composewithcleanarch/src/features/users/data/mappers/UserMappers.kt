package br.com.williamfranco.composewithcleanarch.src.features.users.data.mappers

import br.com.williamfranco.composewithcleanarch.src.features.users.data.models.AddressModel
import br.com.williamfranco.composewithcleanarch.src.features.users.data.models.CompanyModel
import br.com.williamfranco.composewithcleanarch.src.features.users.data.models.GeoModel
import br.com.williamfranco.composewithcleanarch.src.features.users.data.models.UserModel
import br.com.williamfranco.composewithcleanarch.src.features.users.domain.entities.AddressEntity
import br.com.williamfranco.composewithcleanarch.src.features.users.domain.entities.CompanyEntity
import br.com.williamfranco.composewithcleanarch.src.features.users.domain.entities.GeoEntity
import br.com.williamfranco.composewithcleanarch.src.features.users.domain.entities.UserEntity

fun UserModel.toEntity(): UserEntity = UserEntity(
    id = id,
    name = name,
    username = username,
    email = email,
    address = address?.toEntity(),
    phone = phone,
    website = website,
    company = company?.toEntity(),
)

fun List<UserModel>.toEntities(): List<UserEntity> = map { it.toEntity() }

private fun AddressModel.toEntity(): AddressEntity = AddressEntity(
    street = street,
    suite = suite,
    city = city,
    zipcode = zipcode,
    geo = geo?.toEntity(),
)

private fun GeoModel.toEntity(): GeoEntity = GeoEntity(
    lat = lat,
    lng = lng,
)

private fun CompanyModel.toEntity(): CompanyEntity = CompanyEntity(
    name = name,
    catchPhrase = catchPhrase,
    bs = bs,
)
