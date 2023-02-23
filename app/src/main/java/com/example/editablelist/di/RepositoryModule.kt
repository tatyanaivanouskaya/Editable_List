package com.example.editablelist.di

import com.example.editablelist.domain.repository.ItemsRepository
import com.example.editablelist.domain.repository.ItemsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepositoryModule(repositoryImpl: ItemsRepositoryImpl): ItemsRepository
}