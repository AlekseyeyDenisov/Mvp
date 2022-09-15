package ru.dw.mvp.view.fragment.userDetails.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.dw.mvp.databinding.ItemForkBinding
import ru.dw.mvp.model.entity.GithubRepo

class ForkDetailsAdapter( private val onItemClickForkListener: OnItemClickForkListener) :
    RecyclerView.Adapter<ForkDetailsAdapter.GithubForkViewHolder>() {

    var forks: List<GithubRepo> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubForkViewHolder {
        val binding = ItemForkBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return GithubForkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GithubForkViewHolder, position: Int) {
        holder.bind(forks[position])
    }

    override fun getItemCount(): Int = forks.size

    inner class GithubForkViewHolder(private val binding: ItemForkBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: GithubRepo) = with(binding) {
            tvNameFork.text = item.name
            root.setOnClickListener {
                onItemClickForkListener.onItemClick(item)
            }
        }


    }
}