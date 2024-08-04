package com.github.crisacm.animefinder.di

import com.github.crisacm.animefinder.data.repository.ListRepo
import com.github.crisacm.animefinder.data.repository.ListRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

  @Binds
  fun bindListRepo(listRepoImpl: ListRepoImpl): ListRepo
}
