package com.example.seq_test_task.ui.movies_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.seq_test_task.databinding.FragmentMoviesDetailsBinding
import com.example.seq_test_task.ui.composable.MoviePic
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn
import com.example.seq_test_task.ui.theme.SeqTestTaskTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesDetailsFragment: Fragment() {
    private lateinit var binding: FragmentMoviesDetailsBinding
    private val viewModel: DetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe()
    }

    private fun subscribe() {
        viewModel.movieState.onEach { movie ->
            if (movie.id == -1L) return@onEach

            binding.movieTitle.text = movie.localizedName
            binding.movieSubtitle.text = "${movie.genres.joinToString(", ")}, ${movie.year} год"
            binding.movieRating.text = movie.rating?.toString() ?: ""
            binding.movieDescription.text = movie.description

            binding.moviePosterComposeView.setContent {
                SeqTestTaskTheme {
                    MoviePic(movie = movie)
                }
            }
        }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
}