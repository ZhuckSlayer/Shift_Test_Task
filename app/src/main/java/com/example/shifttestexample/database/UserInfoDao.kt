package com.example.shifttestexample.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shifttestexample.pojo.PersonInfo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UserInfoDao {

    //Получаем всех пользователей из бд
    @Query("SELECT * FROM user_info")
    fun getUserInfo(): LiveData<List<PersonInfo>>
    @Query("SELECT COUNT(*) FROM user_info")
    fun getUsersCount():Int
    //Функция на вставку нового пользователя
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserUser(userList:List<PersonInfo>)
    //Функция на получение детальной информации по пользователю
    @Query("SELECT * FROM user_info WHERE email=:email")
    fun getUserDetailInfo(email:String):LiveData<PersonInfo>

}