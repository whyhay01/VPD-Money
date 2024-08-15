package me.greenworld.vpdmoney.ui.transaction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.greenworld.vpdmoney.databinding.DisplayTransactionBinding
import me.greenworld.vpdmoney.domain.model.Transaction

class TransactionAdapter(): ListAdapter<Transaction, TransactionAdapter.TransactionViewHolder>(TransactionDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(DisplayTransactionBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TransactionViewHolder(private val binding:DisplayTransactionBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(transaction: Transaction){
            binding.transactionID.text = "Trans ID: ${transaction.transactionID}"
            binding.amountTransferred.text = "Amount transferred: ${transaction.amountTransferred}"
            binding.beneficiaryName.text = "Beneficiary name: ${transaction.beneficiaryName}"
            binding.destinationAccountNumber.text = "Dest account: ${transaction.destinationAccountNumber}"
            binding.date.text = "Date: ${transaction.date}"
            binding.sourceAccountNumber.text = "Src account: ${transaction.sourceAccountNumber}"
        }

    }
}

private object TransactionDiffCallback: DiffUtil.ItemCallback<Transaction>(){
    override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem.transactionID == newItem.transactionID
    }

    override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem == newItem
    }

}