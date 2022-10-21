package com.example.mvvmcleanarchitectureex2.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmcleanarchitectureex2.R
import com.example.mvvmcleanarchitectureex2.databinding.NewsListItemBinding
import com.unik.yunews.models.Article

class NewsAdapter(val context: Context) :RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val callBack = object  : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,callBack)




    inner class NewsViewHolder(val binding:NewsListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article){
            binding.txtTitle.text = article.title
            binding.txtDescription.text = article.description
            binding.txtPublishAt.text = article.publishedAt

            Log.e("NewsAdapter","URL Adapter::::::::::: ${article.urlToImage}")
            Glide.with(context)
                .load(article.urlToImage)
                .into(binding.ivArticle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding : NewsListItemBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.news_list_item,parent,false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}