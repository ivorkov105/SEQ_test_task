package com.example.seq_test_task.ui.movies_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.seq_test_task.databinding.FragmentMoviesDetailsBinding

class MoviesDetailsFragment: Fragment() {
    private lateinit var binding: FragmentMoviesDetailsBinding
    private val args: MoviesDetailsFragmentArgs by navArgs()

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
        val receiveMovieId = args.movieId
        binding.textMovieId.text = receiveMovieId.toString()
        initCallback()
        subscribe()
    }

    private fun subscribe() {
        //TODO("Not yet implemented")
    }

    private fun initCallback() {
        //TODO("Not yet implemented")
    }
}