package com.github.vincebrees.lolstats.data.repository.staticdata

import android.content.Context
import com.github.vincebrees.lolstats.data.database.RoomDataSource
import com.github.vincebrees.lolstats.data.entity.Champion
import com.github.vincebrees.lolstats.data.entity.ChampionMasteries
import com.github.vincebrees.lolstats.data.entity.Summoner
import com.github.vincebrees.lolstats.data.remote.RestApiDataSource
import com.github.vincebrees.lolstats.data.remote.dto.RestChampion
import com.github.vincebrees.lolstats.data.remote.dto.RestChampionMasteries
import com.github.vincebrees.lolstats.data.remote.dto.RestStaticChampionData
import com.github.vincebrees.lolstats.domain.response.DataResponse
import com.github.vincebrees.lolstats.domain.response.ErrorResponse
import com.github.vincebrees.lolstats.domain.response.SummonerIdErrorCode
import com.github.vincebrees.lolstats.domain.response.TypeResponse
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StaticDataRepositoryImpl @Inject constructor(
    private val roomDataSource: RoomDataSource,
    private val restApiDataSource: RestApiDataSource,
    private val mapper : StaticDataMapper,
    private val context: Context
) : StaticDataRepository {

    val allCompositeDisposable: MutableList<Disposable> = arrayListOf()

//    override fun getListChampion(): Single<TypeResponse<List<Champion>>> {
//        return restApiDataSource.getListStaticChampion()
//            .map { response -> if(response.isSuccessful && response.body() != null){
//                    val list = mapper.mapAsListChampion(response.body()!!)
//                    saveListChampionMasteries(list)
//                    DataResponse(list)
//                }else {
//                    ErrorResponse<List<Champion>>()
//                }
//            }
//    }

    override fun getListChampion(): Single<TypeResponse<List<Champion>>> {
        return Single.create<TypeResponse<List<Champion>>> {
            emitter ->
            val json: String?
            try {
                val inputStream = context.assets.open("champions.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer)
                val parser = JsonParser()
                val element = parser.parse(json)
                val obj = element.asJsonObject
                val gson = Gson()
                val restReturn : RestStaticChampionData = gson.fromJson(obj, RestStaticChampionData::class.java)
                emitter.onSuccess(DataResponse(mapper.mapAsListChampion(restReturn)))
//                val entries = obj.entrySet()
//                for (entry in entries) {
//                    val listType = object : TypeToken<List<RestChampion>>() {
//                    }.type
//
//                    val list: List<RestChampion> = gson.fromJson(entry.value, listType)
//                }
            } catch (ex: IOException) {
                ex.printStackTrace()
                emitter.onError(Throwable(message = "Failed Parsing"))
            }

        }
    }

    private fun saveListChampionMasteries(body: List<Champion>) {
        roomDataSource.roomDao().deleteListChampion()
        roomDataSource.roomDao().insertListChampion(body)
    }
}
