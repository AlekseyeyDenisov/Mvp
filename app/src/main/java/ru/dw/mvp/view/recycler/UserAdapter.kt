package ru.dw.mvp.view.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.dw.mvp.R
import ru.dw.mvp.databinding.ItemUserBinding
import ru.dw.mvp.model.GithubUser

class UserAdapter(
    private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<UserAdapter.GithubUserViewHolder>() {

    var users: List<GithubUser> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context)
        )
            return GithubUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    inner class GithubUserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: GithubUser) = with(binding) {
            tvUserLogin.text = item.login
            ivUserAvatar.load(item.avatarUrl){
                placeholder(R.drawable.ic_user_placeholder)
            }
            root.setOnClickListener {
                onItemClickListener.onItemClick(item)
            }
        }


    }
}