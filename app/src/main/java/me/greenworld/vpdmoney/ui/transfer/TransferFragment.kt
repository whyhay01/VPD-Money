package me.greenworld.vpdmoney.ui.transfer

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import me.greenworld.vpdmoney.common.CustomProgressDialog
import me.greenworld.vpdmoney.databinding.FragmentTransferBinding
import me.greenworld.vpdmoney.domain.model.UserAccount

@AndroidEntryPoint
class TransferFragment : Fragment() {

    private lateinit var binding: FragmentTransferBinding
    private val arg : TransferFragmentArgs by navArgs()
    private val viewModel:TransferViewModel by viewModels()
    private val progressDialog by lazy { CustomProgressDialog(requireContext(), requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentTransferBinding.inflate(inflater, container, false).run {
            viewModel = this@TransferFragment.viewModel
            lifecycleOwner = this@TransferFragment
            binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.transferButton.setOnClickListener {
            viewModel.onTransferButtonClicked()
        }
        val account = createUserAccount()
        binding.accountName.text = account.fullName
        binding.accountBalance.text = "₦ ${account.balance}"
        binding.accountNumber.text = "Account No: ${account.accountNumber}"
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        subscribe()
    }


    private fun subscribe(){
        viewModel.loading.observe(viewLifecycleOwner){
            if (it){
                progressDialog.start("Loading...")
            }else{
                progressDialog.stop()
            }
        }
        viewModel.error.observe(viewLifecycleOwner){(showError, message, tryAgain)->
            if (showError){
                showTransferResponse("Transfer Error", message, tryAgain)
            }
        }
        viewModel.success.observe(viewLifecycleOwner){
            if (it.first){
                showTransferResponse("Transfer Success", it.second, false)
            }
        }

        viewModel.showConfirmDialog.observe(viewLifecycleOwner){
            if (it){
                showResponseDialog()
            }
        }
    }

    private fun createUserAccount():UserAccount{
        return Gson().fromJson(arg.userAccountString, UserAccount::class.java)
    }

    private fun showResponseDialog(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Action Required")
        builder.setMessage("Are you sure you want to transfer \n a sum of ₦ ${viewModel.form.amount} " +
                " to ${viewModel.form.accountNumber}")

        builder.setPositiveButton(Html.fromHtml("<font color='#ff0000'>Proceed</font>")){ dialogInterface, which ->
            viewModel.transfer(createUserAccount())
            dialogInterface.dismiss()
        }
        builder.setNegativeButton(Html.fromHtml("<font color='#0E60E2'>Cancel</font>")){ dialogInterface, which ->
            dialogInterface.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.show()
    }

    private fun showTransferResponse(title: String, message:String, tryAgain:Boolean) {
        val alertDialog = AlertDialog
            .Builder(requireContext())
            .setCancelable(false)
            .setTitle(title)
            .setMessage(message)
            .setNeutralButton(if (tryAgain)"Try again" else "Dismiss") { dialog, _ ->
                if (tryAgain){
                    dialog.dismiss()
                }else{
                    findNavController().popBackStack()
                }
            }
            .create()
        alertDialog.setTitle(title)
        alertDialog.setCancelable(false)
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.show()
    }


    override fun onPause() {
        super.onPause()
        progressDialog.stop()
    }

}