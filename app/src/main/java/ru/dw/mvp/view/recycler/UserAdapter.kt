package ru.dw.mvp.view.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return GithubUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

   inner class GithubUserViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        fun bind(item:GithubUser) {
            ItemUserBinding.bind(itemView).apply {
                tvUserLogin.text = item.login
                root.setOnClickListener {
                    onItemClickListener.onItemClick(item)
                }
            }




        }
    }
}