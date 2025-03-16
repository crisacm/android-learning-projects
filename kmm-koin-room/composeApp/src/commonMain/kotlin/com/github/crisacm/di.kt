package com.github.crisacm

import com.github.crisacm.data.repository.TaskRepositoryImpl
import com.github.crisacm.database.TaskDao
import com.github.crisacm.database.getRoomDatabase
import com.github.crisacm.domain.repository.TaskRepository
import com.github.crisacm.domain.usecase.AddTaskUseCase
import com.github.crisacm.domain.usecase.DeleteTaskUseCase
import com.github.crisacm.domain.usecase.GetTasksUseCase
import com.github.crisacm.domain.usecase.UpdateTaskUseCase
import com.github.crisacm.ui.screens.home.HomeViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

expect fun platformModule(): Module

val dataModule = module {
  single<TaskRepository> { TaskRepositoryImpl(get()) }
    .bind(TaskRepository::class)
}

val provideUseCasesModule = module {
  single { GetTasksUseCase(get()) }
  single { AddTaskUseCase(get()) }
  single { UpdateTaskUseCase(get()) }
  single { DeleteTaskUseCase(get()) }
}

val databaseModule = module {
  single<TaskDao> { getRoomDatabase(get()).taskDao() }
}

val viewModelModule = module {
  viewModel { HomeViewModel(get(), get(), get(), get()) }
}

fun initKoin(config: KoinAppDeclaration? = null) =
  startKoin {
    config?.invoke(this)
    modules(
      platformModule(),
      databaseModule,
      dataModule,
      provideUseCasesModule,
      viewModelModule,
    )
  }
