package com.example.mvvmcleanarchitectureex2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmcleanarchitectureex2.data.util.Resource
import com.example.mvvmcleanarchitectureex2.databinding.FragmentNewsBinding
import com.example.mvvmcleanarchitectureex2.presentation.adapter.NewsAdapter
import com.example.mvvmcleanarchitectureex2.presentation.viewmodel.NewsViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : Fragment() {


    lateinit var newsAdapter: NewsAdapter
    lateinit var dataBinding: FragmentNewsBinding
    private var country = "us"
    private var page = 1
    val viewModel: NewsViewModel by lazy {
        (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsAdapter = (activity as MainActivity).newsAdapter
        initRecyclerView()
        viewNewsList()
    }

    private fun viewNewsList() {
        viewModel.getNewsHeadLines(country, page)
        viewModel.newsHeadLines.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An Error Occurred : $it", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun initRecyclerView() {
//        newsAdapter = NewsAdapter(requireContext())
        dataBinding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showProgressBar() {
        dataBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        dataBinding.progressBar.visibility = View.INVISIBLE
    }

}