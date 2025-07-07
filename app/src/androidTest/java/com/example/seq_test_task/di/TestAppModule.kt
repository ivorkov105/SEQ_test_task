package com.example.seq_test_task.di

import com.example.seq_test_task.data.repositories.MovieRepository
import com.example.seq_test_task.ui.movies_details.DetailsViewModel
import com.example.seq_test_task.ui.movies_list.ListViewModel
import com.example.seq_test_task.util.FakeMovieRepository
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val testModule = module {
    singleOf(::FakeMovieRepository).bind<MovieRepository>()
    viewModelOf(::ListViewModel)
    viewModelOf(::DetailsViewModel)
}