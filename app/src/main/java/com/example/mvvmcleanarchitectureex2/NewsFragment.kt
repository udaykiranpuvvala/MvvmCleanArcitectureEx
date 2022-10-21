package com.example.mvvmcleanarchitectureex2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmcleanarchitectureex2.data.util.Resource
import com.example.mvvmcleanarchitectureex2.databinding.FragmentNewsBinding
import com.example.mvvmcleanarchitectureex2.presentation.adapter.NewsAdapter
import com.example.mvvmcleanarchitectureex2.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0


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
        val navController = Navigation.findNavController(view)
        newsAdapter.setOnItemClickListener {
            Log.e("Article","Article :::::::::::::::::::: "+it.url)
            val bundle = Bundle().apply {
//                putSerializable("selected_article", it)
                putString("url",it.url)
            }
            Log.e("Article","Article :::::::::::::::::::: "+bundle.toString())
            navController.navigate(R.id.action_newsFragment_to_infoFragment,bundle)
        }
        initRecyclerView()
        viewNewsList()
        setSearchView()
    }

    private fun viewNewsList() {
        viewModel.getNewsHeadLines(country, page)
        viewModel.newsHeadLines.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())

                        if(it.totalResults % 20 == 0) {
                            pages = it.totalResults / 20
                        }else{
                            page = (it.totalResults / 20)+1
                        }

                        isLastPage = page == pages
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
            addOnScrollListener(this@NewsFragment.onScrollListener)
        }
    }

    private fun showProgressBar() {
        isLoading = true
        dataBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        dataBinding.progressBar.visibility = View.INVISIBLE
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = dataBinding.rvNews.layoutManager as LinearLayoutManager

            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition+visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling

            if(shouldPaginate){
                page++
                viewModel.getNewsHeadLines(country, page)
                isScrolling = false
            }

        }
    }

    private fun setSearchView(){
        dataBinding.svNews.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchNews("us",query.toString(),page)
                viewSearchNews()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                MainScope().launch {
                    delay(2000)
                    viewModel.searchNews("us",newText.toString(),page)
                    viewSearchNews()
                }
                return false
            }

        })

        dataBinding.svNews.setOnCloseListener(object : SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                initRecyclerView()
                viewNewsList()
                return false
            }

        })
    }

    fun viewSearchNews(){
        viewModel.searchedNews.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())

                        if(it.totalResults % 20 == 0) {
                            pages = it.totalResults / 20
                        }else{
                            page = (it.totalResults / 20)+1
                        }

                        isLastPage = page == pages
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
}