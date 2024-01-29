package com.example.shifttestexample.viewmodel

import android.app.Application
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.shifttestexample.api.ApiFactory
import com.example.shifttestexample.database.AppDataBase
import com.example.shifttestexample.pojo.PersonInfo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.internal.operators.single.SingleUnsubscribeOn
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Callable

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val compositeDisposable = CompositeDisposable()
    private val db = AppDataBase.getInstance(application)
    var userList = db.userInfoDao().getUserInfo()
    var isError = MutableLiveData<Boolean>()

    //Функция для загрузки пользователей из API
    fun loadUsers() {
        val disposable = ApiFactory.apiService.loadUsers()
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.userInfoDao().inserUser(it.results)
            }, {
                isError.postValue(true)
            }
            )
        compositeDisposable.add(disposable)
    }

    //Функция обновления пользователей принудительно
    fun refreshUsers() {
        val thread = Thread()
        {
            db.clearAllTables()
        }
        thread.start()
        loadUsers()
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}