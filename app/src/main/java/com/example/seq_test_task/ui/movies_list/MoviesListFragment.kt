package com.example.seq_test_task.ui.movies_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import com.example.seq_test_task.ui.composable.ErrorSnackbar
import com.example.seq_test_task.ui.composable.ProgressIndicator
import com.example.seq_test_task.ui.theme.SeqTestTaskTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListFragment: Fragment() {

    private val viewModel: ListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                SeqTestTaskTheme {
                    val state by viewModel.uiState.collectAsStateWithLifecycle()

                    Box(modifier = Modifier.fillMaxSize()) {
                        if (state.isLoading) {
                            ProgressIndicator()
                        } else if (state.error == null) {
                            MoviesListScreen(
                                state = state,
                                onGenreClick = viewModel::onGenreSelected,
                                onMovieClick = { movieId ->
                                    val action =
                                        MoviesListFragmentDirections.actionListToDetails(movieId)
                                    findNavController().navigate(action)
                                }
                            )
                        } else {
                            ErrorSnackbar(
                                message = state.error ?: "Неизвестная ошибка",
                                onRetry = { viewModel.loadFilms() },
                                modifier = Modifier.align(Alignment.BottomCenter)
                            )
                        }
                    }
                }
            }
        }
    }
}