package com.example.mvvmcleanarchitectureex2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mvvmcleanarchitectureex2.databinding.ActivityMainBinding
import com.example.mvvmcleanarchitectureex2.presentation.adapter.NewsAdapter
import com.example.mvvmcleanarchitectureex2.presentation.viewmodel.NewsViewModel
import com.example.mvvmcleanarchitectureex2.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var newsAdapter: NewsAdapter

    @Inject
    lateinit var factory: NewsViewModelFactory
    lateinit var binding: ActivityMainBinding
    val viewModel: NewsViewModel by lazy {
        ViewModelProvider(this, factory)[NewsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvNews.setupWithNavController(navController)
    }
}