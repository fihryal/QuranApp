package com.faqiy.quran.presentation.Adzan

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.faqiy.quran.core.data.Resource
import com.faqiy.quran.databinding.FragmentAdzanBinding
import com.faqiy.quran.presentation.ViewModelFactory


class AdzanFragment : Fragment() {

    private var _binding: FragmentAdzanBinding? = null
    private val binding get() = _binding as FragmentAdzanBinding

    private val adzanViewModel: AdzanViewModel by viewModels { ViewModelFactory(requireContext()) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdzanBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adzanViewModel.getDetailAdzanTime().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    binding.tvLocation.text = it.data?.listAddress?.get(1)
                    binding.tvDate.text = it.data?.currentDdate?.get(3)
                    when (val adzanTime = it.data?.adzanTime) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            binding.apply {
                                adzanTime.data?.let { time ->
                                    tvTimeSubuh.text = time.subuh
                                    tvImsak.text = time.imsak
                                    tvDzuhur.text = time.dzuhur
                                    tvAshar.text = time.ashar
                                    tvMaghrib.text = time.maghrib
                                    tvIsya.text = time.isya
                                }
                            }
                        }

                        is Resource.Error -> {
                            Log.e("AdzanFragment", "Getting id city: ${it.message}")
                        }
                        else -> {
                            Toast.makeText(context, "Something wrong.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                is Resource.Error -> {}
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}