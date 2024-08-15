package me.greenworld.vpdmoney.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import me.greenworld.vpdmoney.databinding.FragmentAccountBinding
import me.greenworld.vpdmoney.domain.model.UserAccount
@AndroidEntryPoint
class AccountFragment : Fragment(), AccountAdapter.ItemClickedListener {

    private lateinit var binding: FragmentAccountBinding
    private val viewModel: AccountViewModel by viewModels()
    private lateinit var accountAdapter: AccountAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentAccountBinding.inflate(inflater, container, false).run {
            binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         accountAdapter = AccountAdapter(this)
        binding.recyclerView.adapter = accountAdapter
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        subscribe()
    }

    private fun subscribe(){
        viewModel.account.observe(viewLifecycleOwner){
            accountAdapter.submitList(it)
        }
    }

    override fun clickedItem(userAccount: UserAccount) {
        val userAccountString = Gson().toJson(userAccount)
        findNavController().navigate(AccountFragmentDirections.toTransferFragment(userAccountString))
    }

}