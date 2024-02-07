package com.qifnav.moviediscovery.di

import android.content.Context
import com.qifnav.moviediscovery.data.api.MovieApi
import com.qifnav.moviediscovery.data.repository.MovieDiscoveryRepositoryImpl
import com.qifnav.moviediscovery.domain.repository.MovieDiscoveryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMovieApi(
        coreRetrofit: Retrofit
    ): MovieApi {
        return coreRetrofit.create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(
        movieApi: MovieApi,
        @ApplicationContext appContext: Context
    ): MovieDiscoveryRepository {
        return MovieDiscoveryRepositoryImpl(
            movieApi = movieApi,
            appContext = appContext
        )
    }
}