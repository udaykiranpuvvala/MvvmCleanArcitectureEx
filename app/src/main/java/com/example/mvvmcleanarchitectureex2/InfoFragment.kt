package com.example.mvvmcleanarchitectureex2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.mvvmcleanarchitectureex2.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_info, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("Article","Article InfoFragment 1 :::::::::::::::::::: ")

        val args : InfoFragmentArgs by navArgs()
        val article = args.toBundle().getString("url")
        Log.e("Article","Article InfoFragment article:::::::::::::::::::: "+article)
        binding.webView.apply {
            webViewClient = WebViewClient()
            if(article != "") {
                loadUrl(article.toString())
            }
        }
    }
}