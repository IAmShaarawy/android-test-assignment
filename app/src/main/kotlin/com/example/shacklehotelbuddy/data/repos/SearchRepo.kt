package com.example.shacklehotelbuddy.data.repos

import com.example.shacklehotelbuddy.data.cache.daos.SearchHistoryDao
import com.example.shacklehotelbuddy.data.cache.entities.SearchHistoryEntity
import com.example.shacklehotelbuddy.data.remote.SearchRequestBody
import com.example.shacklehotelbuddy.data.remote.SearchService
import com.example.shacklehotelbuddy.foundation.DispatchersProvider
import com.example.shacklehotelbuddy.presentation.details.Property
import com.example.shacklehotelbuddy.presentation.lobby.CheckDate
import com.example.shacklehotelbuddy.presentation.lobby.LobbyDeskForm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.invoke
import javax.inject.Inject
import javax.inject.Singleton

interface SearchRepo {
    suspend fun searchFor(form: LobbyDeskForm):Long

    fun latestSearchLobbyDeskForm(): Flow<LobbyDeskForm?>

    fun search(form: LobbyDeskForm): Flow<List<Property>>
}

@Singleton
class SearchRepoImpl @Inject constructor(
    private val dispatchersProvider: DispatchersProvider,
    private val searchHistoryDao: SearchHistoryDao,
    private val searchService: SearchService
) : SearchRepo {
    override suspend fun searchFor(form: LobbyDeskForm) = dispatchersProvider.io {
        searchHistoryDao.insertSearchEntity(
            SearchHistoryEntity(
                checkIn = form.checkIn.toString(),
                checkOut = form.checkIn.toString(),
                adultsCount = form.adultsCount,
                childrenCount = form.children,
            )
        )
    }

    override fun latestSearchLobbyDeskForm(): Flow<LobbyDeskForm?> =
        searchHistoryDao.getLatestSearchEntity().map {
            it ?: return@map null
            LobbyDeskForm(
                checkIn = CheckDate.fromString(it.checkIn),
                checkOut = CheckDate.fromString(it.checkIn),
                adultsCount = it.adultsCount,
                children = it.childrenCount
            )
        }.flowOn(dispatchersProvider.io)

    override fun search(form: LobbyDeskForm): Flow<List<Property>> = flow {
        emit(
            searchService.getTendingRepos(
                SearchRequestBody(
                    checkInDate = SearchRequestBody.CheckInDate(
                        day = form.checkIn?.day?.toInt() ?: 0,
                        month = form.checkIn?.month?.toInt() ?: 0,
                        year = form.checkIn?.year?.toInt() ?: 0,
                    ),
                    checkOutDate = SearchRequestBody.CheckOutDate(
                        day = form.checkOut?.day?.toInt() ?: 0,
                        month = form.checkOut?.month?.toInt() ?: 0,
                        year = form.checkOut?.year?.toInt() ?: 0,
                    ),
                    rooms = listOf(
                        SearchRequestBody.Room(
                            adults = form.adultsCount,
                            children = List(form.children) { SearchRequestBody.Room.Children(age = it) })
                    )
                )
            )
        )
    }.flowOn(dispatchersProvider.io).map {
        it.data.propertySearch.properties.map {
            Property(
                name = it.name,
                neighborhoodName = it.neighborhood.name,
                formattedPrice = it.price.lead.formatted,
                rating = it.reviews.score,
                imgUrl = it.propertyImage.image.url
            )
        }
    }.flowOn(dispatchersProvider.default)

}
