package com.wd.spending.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wd.spending.R
import com.wd.spending.REQUEST_AUTHORIZATION
import com.wd.spending.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.observeOn
import javax.inject.Inject

class HomeFragment : Fragment() {

    private val homeViewModel by activityViewModels<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.userAuthIOException.observe(viewLifecycleOwner) {
            startActivityForResult(it.intent, REQUEST_AUTHORIZATION)
        }

        homeViewModel.text.observe(viewLifecycleOwner) {
            binding.textHome.text = it
        }
    }
}