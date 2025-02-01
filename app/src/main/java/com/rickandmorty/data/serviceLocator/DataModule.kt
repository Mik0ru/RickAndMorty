package com.rickandmorty.data.serviceLocator

import com.rickandmorty.BuildConfig
import com.rickandmorty.data.remote.api.CharacterApiService
import com.rickandmorty.data.remote.api.EpisodeApiService
import com.rickandmorty.data.remote.api.LocationApiService
import com.rickandmorty.data.remote.repository.CharacterRepository
import com.rickandmorty.data.remote.repository.EpisodeRepository
import com.rickandmorty.data.remote.repository.LocationRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single { provideRetrofit() }

    single { get<Retrofit>().create(CharacterApiService::class.java) }
    single { CharacterRepository(get()) }

    single { get<Retrofit>().create(LocationApiService::class.java) }
    single { LocationRepository(get()) }

    single { get<Retrofit>().create(EpisodeApiService::class.java) }
    single { EpisodeRepository(get()) }

}
fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}