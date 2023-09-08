package com.example.shacklehotelbuddy.data.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchRequestBody(
    @SerialName("checkInDate")
    val checkInDate: CheckInDate = CheckInDate(),
    @SerialName("checkOutDate")
    val checkOutDate: CheckOutDate = CheckOutDate(),
    @SerialName("currency")
    val currency: String = "USD",
    @SerialName("destination")
    val destination: Destination = Destination(),
    @SerialName("eapid")
    val eapid: Int = 1,
    @SerialName("filters")
    val filters: Filters = Filters(),
    @SerialName("locale")
    val locale: String = "en_US",
    @SerialName("resultsSize")
    val resultsSize: Int = 200,
    @SerialName("resultsStartingIndex")
    val resultsStartingIndex: Int = 0,
    @SerialName("rooms")
    val rooms: List<Room> = listOf(),
    @SerialName("siteId")
    val siteId: Int = 300000001,
    @SerialName("sort")
    val sort: String = "PRICE_LOW_TO_HIGH"
) {
    @Serializable
    data class CheckInDate(
        @SerialName("day")
        val day: Int = 0,
        @SerialName("month")
        val month: Int = 0,
        @SerialName("year")
        val year: Int = 0
    )

    @Serializable
    data class CheckOutDate(
        @SerialName("day")
        val day: Int = 0,
        @SerialName("month")
        val month: Int = 0,
        @SerialName("year")
        val year: Int = 0
    )

    @Serializable
    data class Destination(
        @SerialName("regionId")
        val regionId: String = "6054439"
    )

    @Serializable
    data class Filters(
        @SerialName("price")
        val price: Price = Price()
    ) {
        @Serializable
        data class Price(
            @SerialName("max")
            val max: Int = 150,
            @SerialName("min")
            val min: Int = 100
        )
    }

    @Serializable
    data class Room(
        @SerialName("adults")
        val adults: Int = 0,
        @SerialName("children")
        val children: List<Children> = listOf()
    ) {
        @Serializable
        data class Children(
            @SerialName("age")
            val age: Int = 5
        )
    }
}