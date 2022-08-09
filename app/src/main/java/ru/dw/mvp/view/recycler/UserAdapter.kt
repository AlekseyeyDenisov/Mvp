package ru.dw.mvp.view.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.dw.mvp.R
import ru.dw.mvp.model.GithubUser

class UserAdapter() :
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

    class GithubUserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvLogin by lazy { itemView.findViewById<TextView>(R.id.tvUserLogin) }

        fun bind(item:GithubUser) {
            tvLogin.text = item.login

        }
    }
}