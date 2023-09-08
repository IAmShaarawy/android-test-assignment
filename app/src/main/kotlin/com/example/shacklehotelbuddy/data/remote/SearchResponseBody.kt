package com.example.shacklehotelbuddy.data.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseBody(
    @SerialName("data")
    val `data`: Data = Data()
) {
    @Serializable
    data class Data(
        @SerialName("propertySearch")
        val propertySearch: PropertySearch = PropertySearch()
    ) {
        @Serializable
        data class PropertySearch(
            @SerialName("properties")
            val properties: List<Property> = listOf(),

            ) {
            @Serializable
            data class Property(
                @SerialName("id")
                val id: String = "",
                @SerialName("name")
                val name: String = "",
                @SerialName("neighborhood")
                val neighborhood: Neighborhood = Neighborhood(),
                @SerialName("price")
                val price: Price = Price(),
                @SerialName("propertyImage")
                val propertyImage: PropertyImage = PropertyImage(),
                @SerialName("reviews")
                val reviews: Reviews = Reviews(),

                ) {
                @Serializable
                data class Neighborhood(
                    @SerialName("name")
                    val name: String = "",
                    @SerialName("__typename")
                    val typename: String = ""
                )

                @Serializable
                data class Price(
                    @SerialName("lead")
                    val lead: Lead = Lead(),
                ) {
                    @Serializable
                    data class Lead(
                        @SerialName("formatted")
                        val formatted: String = "",
                    ) {
                        @Serializable
                        data class CurrencyInfo(
                            @SerialName("code")
                            val code: String = "",
                            @SerialName("symbol")
                            val symbol: String = "",
                            @SerialName("__typename")
                            val typename: String = ""
                        )
                    }
                }

                @Serializable
                data class PropertyImage(
                    @SerialName("image")
                    val image: Image = Image(),
                ) {
                    @Serializable
                    data class Image(
                        @SerialName("url")
                        val url: String = ""
                    )
                }

                @Serializable
                data class Reviews(
                    @SerialName("score")
                    val score: Double = 0.0,
                    @SerialName("total")
                    val total: Int = 0,
                )
            }
        }
    }
}