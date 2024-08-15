package me.greenworld.vpdmoney.ui.transaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import me.greenworld.vpdmoney.databinding.FragmentTransactionBinding

@AndroidEntryPoint
class TransactionFragment : Fragment() {
    private lateinit var binding:FragmentTransactionBinding
    private lateinit var adapter: TransactionAdapter
    private val viewModel: TransactionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentTransactionBinding.inflate(inflater, container, false).run {
            binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TransactionAdapter()
        binding.recyclerView.adapter = adapter
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        subscribe()
    }

    private fun subscribe(){
        viewModel.transactions.observe(viewLifecycleOwner){
            binding.display.isVisible = it.isEmpty()
            adapter.submitList(it)
        }
    }

}