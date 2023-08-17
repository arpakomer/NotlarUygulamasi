package com.arpak.notlaruygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
//import com.android.volley.Request.Method
//import com.android.volley.toolbox.StringRequest
//import com.android.volley.toolbox.Volley
import com.arpak.notlaruygulamasi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var notListesi: ArrayList<Notlar>
    private lateinit var adapter: NotlarAdapter
    private lateinit var notlarDaoInterface: NotlarDaoInterface
//    private lateinit var vt: VeritabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Not Uygulaması"
        setSupportActionBar(binding.toolbar)

        binding.recyclerViewMain.setHasFixedSize(true)
        binding.recyclerViewMain.layoutManager = LinearLayoutManager(this)


        notlarDaoInterface = ApiUtils.getNotlarDaoInterface()
        tumNotlarRetrofit()


//        tumNotlarVolleyKutuphanesi() - - > if we want to use volley library

//        vt = VeritabaniYardimcisi(this@MainActivity)
//        notListesi = Notlardao().tumNotlar(vt)


        binding.fabMain.setOnClickListener {
            startActivity(Intent(this@MainActivity, NotKayitActivity::class.java))
        }


    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)

    }


    private fun tumNotlarRetrofit() {

        notlarDaoInterface.tumNotlar().enqueue(object : Callback<NotlarCevap>{

            override fun onResponse(call: Call<NotlarCevap>, response: Response<NotlarCevap>) {
                val liste = response.body()?.notlar as ArrayList

                adapter = NotlarAdapter(this@MainActivity, liste)
                binding.recyclerViewMain.adapter = adapter

                var toplam = 0
                for (n in liste) {
                    toplam += (n.not1 + n.not2) / 2
                }

                if (toplam != 0){
                    binding.toolbar.subtitle = "Ortalama : ${toplam/liste.size}"

                }
            }

            override fun onFailure(call: Call<NotlarCevap>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }

    // if we want to use volley library
//    fun tumNotlarVolleyKutuphanesi() {
//
//        val url = "http://kasimadalan.pe.hu/notlar/tum_notlar.php"
//
//        val istek = StringRequest(Method.GET, url, { cevap ->
//
//            try {
//
//                var toplam = 0
//
//                notListesi = ArrayList()
//
//                val jsonObject = JSONObject(cevap)
//
//                val notlar = jsonObject.getJSONArray("notlar")
//
//                for (i in 0 until notlar.length()) {
//
//                    val n = notlar.getJSONObject(i)
//
//                    val not1 = n.getInt("not1")
//                    val not2 = n.getInt("not2")
//                    val not = Notlar(n.getInt("not_id"), n.getString("ders_adi"), not1, not2)
//
//                    notListesi.add(not)
//
//                    toplam = toplam +(not1+not2)/2
//                }
//                adapter = NotlarAdapter(this, notListesi)
//                binding.recyclerViewMain.adapter = adapter
//
//                if (toplam != 0){
//
//                    binding.toolbar.subtitle = "Ortalama : ${toplam/notListesi.size}"
//                }
//
//
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//
//
//        }, {
//
//        })
//
//        Volley.newRequestQueue(this@MainActivity).add(istek)
//    }


}