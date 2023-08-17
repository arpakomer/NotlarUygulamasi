package com.arpak.notlaruygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
//import com.android.volley.Response
//import com.android.volley.toolbox.StringRequest
//import com.android.volley.toolbox.Volley
import com.arpak.notlaruygulamasi.databinding.ActivityNotBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotKayitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotBinding
    private lateinit var notDaoInterface: NotlarDaoInterface

    //    private lateinit var vt: VeritabaniYardimcisi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        vt = VeritabaniYardimcisi(this@NotKayitActivity)

        binding.toolbarNotKayit.title = "Not Kayıt"
        setSupportActionBar(binding.toolbarNotKayit)

        notDaoInterface = ApiUtils.getNotlarDaoInterface()

        binding.buttonKaydet.setOnClickListener {

            val ders_adi = binding.editTextDers.text.toString().trim()
            val not1 = binding.editTextNot1.text.toString().trim()
            val not2 = binding.editTextNot2.text.toString().trim()

            if (TextUtils.isEmpty(ders_adi)) {
                Snackbar.make(
                    binding.toolbarNotKayit,
                    "Ders Alanı Boş Bırakılamaz",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(not1)) {
                Snackbar.make(
                    binding.toolbarNotKayit,
                    "Not1 Alanı Boş Bırakılamaz",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(not2)) {
                Snackbar.make(
                    binding.toolbarNotKayit,
                    "Not2 Alanı Boş Bırakılamaz",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener

            }

            notDaoInterface.notEkle(ders_adi, not1.toInt(), not2.toInt())
                .enqueue(object : Callback<CRUDCevap> {
                    override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {
                    }

                    override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {

                    }
                })


//            notKayit(ders_adi, not1.toInt(),not2.toInt())


            startActivity(Intent(this@NotKayitActivity, MainActivity::class.java))
            finish()
        }
    }

//    private fun notKayit(ders_adi: String, not1: Int, not2: Int) {
//
//        val url = "http://kasimadalan.pe.hu/notlar/insert_not.php"
//
//        val istekNotEkleme = object :
//            StringRequest(Method.POST, url, Response.Listener {
//
//            }, Response.ErrorListener {
//
//            }) {
//
//            override fun getParams(): MutableMap<String, String> {
//                val params = HashMap<String, String>()
//
//                params["ders_adi"] = ders_adi
//                params["not1"] = not1.toString()
//                params["not2"] = not2.toString()
//                return params
//            }
//        }
//
//        Volley.newRequestQueue(this@NotKayitActivity).add(istekNotEkleme)
//
//    }
}