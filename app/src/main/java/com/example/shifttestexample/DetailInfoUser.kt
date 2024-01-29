package com.example.shifttestexample

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shifttestexample.databinding.ActivityDetailInfoUserBinding
import com.example.shifttestexample.pojo.PersonInfo
import com.squareup.picasso.Picasso
import java.io.Serializable
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class DetailInfoUser : AppCompatActivity() {
    private lateinit var binding: ActivityDetailInfoUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_info_user)
        binding = ActivityDetailInfoUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Получаем объект пользователя с предыдущего экрана
        val personInfo: PersonInfo = intent.getSerializableExtra(EXTRA_USER) as PersonInfo
        //Форматируем дату в привычный вид
        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        //Записываем данные на экран из базы данных
        with(binding)
        {
            with(personInfo)
            {
                Picasso.get().load(picture.large).into(ivAvatarka)
                val dateOB = OffsetDateTime.parse(
                    dob.date,
                    DateTimeFormatter.ISO_DATE_TIME
                )
                tvDob.text = getString(R.string.Dob, dateOB.format(formatter))
                tvage.text = getString(R.string.Age, dob.age.toString())
                tvNameDetail.text = getString(
                    R.string.Full_name,
                    name.first + " " + name.last
                )
                tvgender.text = getString(R.string.Gender, gender)
                tvphone.text = getString(R.string.phone, phone)
                tvemail.text = getString(R.string.Email, email)
                tvCity.text = getString(R.string.City, location.city)
                tvCountry.text = getString(R.string.country, location.country)
                tvPostal.text = getString(R.string.postal, location.postcode)
                tvStreet.text = getString(
                    R.string.Street,
                    location.street.name + " " + location.street.number
                )
            }
        }
        //Слушатель клика на почту
        binding.tvemail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:${personInfo.email}")
            startActivity(intent)
        }
        //Слушатель клика на телефон
        binding.tvphone.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("tel:+${personInfo.phone}"))
            startActivity(intent)
        }
        //Слушатель клика на улицу и номер дома
        binding.tvStreet.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            val adressUri = Uri.parse("geo:0,0")
                .buildUpon()
                .appendQueryParameter(
                    "q", "${personInfo.location.street.name}" +
                            ",${personInfo.location.street.number}"
                ).build()
            intent.setData(adressUri)
            startActivity(intent)
        }

    }


    companion object {
        private val EXTRA_USER = "Person"
        fun newIntent(context: Context, personInfo: PersonInfo): Intent {
            val intent = Intent(context, DetailInfoUser::class.java)
            intent.putExtra(EXTRA_USER, personInfo as Serializable)
            return intent
        }
    }
}