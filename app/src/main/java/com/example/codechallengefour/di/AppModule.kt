package com.example.codechallengefour.di

import com.example.codechallengefour.usecase.ConvertDigitsToWordImpl
import com.example.codechallengefour.usecase.ConvertDigitsToWordUseCase
import com.example.codechallengefour.usecase.CountDaysUseCase
import com.example.codechallengefour.usecase.CountDaysUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideCountDaysUseCase(): CountDaysUseCase = CountDaysUseCaseImpl()

    @Provides
    @Singleton
    fun provideConvertDigitsToWordUseCase(): ConvertDigitsToWordUseCase = ConvertDigitsToWordImpl()
}