package me.greenworld.vpdmoney.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import me.greenworld.vpdmoney.R
import me.greenworld.vpdmoney.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentHomeBinding.inflate(inflater, container, false).run {
            binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.accountCard.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.toAccountFragment())
        }

        binding.transactionCard.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.toTransactionFragment())
        }
    }

}