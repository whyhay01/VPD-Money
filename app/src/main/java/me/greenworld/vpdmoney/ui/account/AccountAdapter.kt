package me.greenworld.vpdmoney.ui.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.greenworld.vpdmoney.databinding.AccountDisplayBinding
import me.greenworld.vpdmoney.domain.model.UserAccount

class AccountAdapter(private val listener:ItemClickedListener): ListAdapter<UserAccount, AccountAdapter.AccountViewHolder>(AccountDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder(AccountDisplayBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val account = getItem(position)
        holder.bind(account)
        holder.binding.accountCard.setOnClickListener {
            listener.clickedItem(account)
        }
    }

    class AccountViewHolder(val binding: AccountDisplayBinding): RecyclerView.ViewHolder(binding.root){


        fun bind(account: UserAccount){
            binding.accountBalance.text = "₦ ${account.balance.toDouble()}"
            binding.accountName.text = account.fullName
            binding.accountNumber.text = "Account N0: ${account.accountNumber}"
        }

    }


    interface ItemClickedListener{
        fun clickedItem(userAccount: UserAccount)
    }
}

private object AccountDiffCallback : DiffUtil.ItemCallback<UserAccount>(){
    override fun areItemsTheSame(oldItem: UserAccount, newItem: UserAccount): Boolean {
        return oldItem.accountNumber == newItem.accountNumber
    }

    override fun areContentsTheSame(oldItem: UserAccount, newItem: UserAccount): Boolean {
        return oldItem == newItem
    }

}