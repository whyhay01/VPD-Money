package me.greenworld.vpdmoney.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import me.greenworld.vpdmoney.R
import me.greenworld.vpdmoney.common.CustomProgressDialog
import me.greenworld.vpdmoney.databinding.FragmentLoginBinding

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private val progressDialog by lazy { CustomProgressDialog(requireContext(), requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentLoginBinding.inflate(inflater, container, false).run {
            viewModel = this@LoginFragment.viewModel
            lifecycleOwner = this@LoginFragment
            binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            viewModel.form.email = binding.emailEditText.text.toString()
            viewModel.form.password = binding.passwordEditText.text.toString()
            viewModel.onLoginClicked()
        }

        subscribe()
    }

    private fun subscribe(){
        viewModel.error.observe(viewLifecycleOwner){(showError, message) ->
            if (showError){
                showSnackError(message)
            }
        }
        viewModel.success.observe(viewLifecycleOwner){
            findNavController().navigate(LoginFragmentDirections.toHomeFragment())
        }

        viewModel.loading.observe(viewLifecycleOwner){
            if (it){
                progressDialog.start("Loading...")
            }else{
                progressDialog.stop()
            }
        }


    }

    private fun showSnackError(message:String){
        Snackbar.make(requireView(), message, Snackbar.ANIMATION_MODE_SLIDE).show()
    }

    override fun onPause() {
        super.onPause()
        progressDialog.stop()
    }

}