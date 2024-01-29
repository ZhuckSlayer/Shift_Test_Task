package com.example.shifttestexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shifttestexample.adapter.UserRecyclerViewAdapter
import com.example.shifttestexample.databinding.ActivityMainBinding
import com.example.shifttestexample.pojo.PersonInfo
import com.example.shifttestexample.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val adapter = UserRecyclerViewAdapter()

        binding.rvUsers.adapter = adapter

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.loadUsers()

        binding.rvUsers.layoutManager = LinearLayoutManager(this)

        //Считываем изменения в Livedata, для выгрузки пользователей
        viewModel.userList.observe(this, Observer {
            adapter.persona = it
        })

        //Слушатель обновления пользователей
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refreshUsers()
            binding.refreshLayout.isRefreshing = false
        }

        //Слушатель клика для перехода на экран с подробной информацией о пользователе
        adapter.oncLickListener = object : UserRecyclerViewAdapter.onClickListener {
            override fun onClick(personInfo: PersonInfo) {
                val intent = DetailInfoUser.newIntent(this@MainActivity, personInfo)
                startActivity(intent)
            }
        }

        //Слушатель конца экрана, для постоянной подгрузки данных
        adapter.onreachEnd = object : UserRecyclerViewAdapter.onReachEnd {
            override fun onReachEnd() {
                viewModel.loadUsers()
            }
        }
        //Считываем изменения в Livedata, для выгрузки ошибки
        viewModel.isError.observe(this, Observer {
            if (it) {
                Toast.makeText(
                    this@MainActivity,
                    "Ошибка загрузки данных",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

}