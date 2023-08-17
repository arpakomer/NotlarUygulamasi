package com.arpak.notlaruygulamasi

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
//import com.android.volley.Response
//import com.android.volley.toolbox.StringRequest
//import com.android.volley.toolbox.Volley
import com.arpak.notlaruygulamasi.databinding.ActivityNotdetayBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NotDetayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotdetayBinding
    private lateinit var notDaoInterface: NotlarDaoInterface
    private lateinit var not: Notlar

    //    private lateinit var vt: VeritabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotdetayBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        vt = VeritabaniYardimcisi(this@NotDetayActivity)
        binding.toolbarNotDetay.title = "Not Detay"
        setSupportActionBar(binding.toolbarNotDetay)
        notDaoInterface = ApiUtils.getNotlarDaoInterface()

        not = intent.getSerializableExtra("nesne") as Notlar

        binding.editTextDetayDers.setText(not.ders_adi)
        binding.editTextDetayNot1.setText((not.not1).toString())
        binding.editTextDetayNot2.setText((not.not2).toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_delete -> {
                Snackbar.make(binding.toolbarNotDetay, "Silinsin Mi", Snackbar.LENGTH_SHORT)
                    .setAction("Evet") {

                        notDaoInterface.notSil(not.not_id).enqueue(object : Callback<CRUDCevap> {
                            override fun onResponse(
                                call: Call<CRUDCevap>, response: Response<CRUDCevap>
                            ) {
                            }

                            override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {
                            }
                        })
//                        notSil(not.not_id)
                        startActivity(Intent(this@NotDetayActivity, MainActivity::class.java))
                        finish()
                    }.show()

                return true
            }
            R.id.action_arrangement -> {
                val ders_adi = binding.editTextDetayDers.text.toString().trim()
                val not1 = binding.editTextDetayNot1.text.toString().trim()
                val not2 = binding.editTextDetayNot2.text.toString().trim()

                if (TextUtils.isEmpty(ders_adi)) {
                    Snackbar.make(
                        binding.toolbarNotDetay,
                        "Ders detay Boş Bırakılamaz",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    return false
                }
                if (TextUtils.isEmpty(not1)) {
                    Snackbar.make(
                        binding.toolbarNotDetay,
                        "Not1 detay Boş Bırakılamaz",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    return false
                }
                if (TextUtils.isEmpty(not2)) {
                    Snackbar.make(
                        binding.toolbarNotDetay,
                        "Not2 detay Boş Bırakılamaz",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    return false
                }

                notDaoInterface.notGuncelle(not.not_id, ders_adi, not1.toInt(), not2.toInt())
                    .enqueue(object : Callback<CRUDCevap> {
                        override fun onResponse(
                            call: Call<CRUDCevap>,
                            response: Response<CRUDCevap>
                        ) {

                        }

                        override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {

                        }
                    })

//                notGuncelle(not.not_id,ders_adi,not1.toInt(),not2.toInt())
                startActivity(Intent(this@NotDetayActivity, MainActivity::class.java))
                finish()
                return true
            }
            else -> {
                return false
            }
        }
    }

//
//    private fun notSil(not_id: Int) {
//        val url = "http://kasimadalan.pe.hu/notlar/delete_not.php"
//
//        val istekSilme = object :
//            StringRequest(Method.POST, url, Response.Listener { }, Response.ErrorListener { }) {
//
//            override fun getParams(): MutableMap<String, String> {
//
//                val params = HashMap<String, String>()
//                params["not_id"] = not_id.toString()
//
//                return params
//            }
//        }
//        Volley.newRequestQueue(this@NotDetayActivity).add(istekSilme)
//    }
//
//    private fun notGuncelle(not_id: Int, ders_adi: String, not1: Int, not2: Int) {
//        val url = "http://kasimadalan.pe.hu/notlar/update_not.php"
//
//        val istekGuncelle = object : StringRequest(Method.POST,url,Response.Listener {  },Response.ErrorListener {  }){
//            override fun getParams(): MutableMap<String, String>? {
//                val params = HashMap<String,String>()
//                params["not_id"] = not_id.toString()
//                params["ders_adi"] = ders_adi
//                params["not1"] = not1.toString()
//                params["not2"] = not2.toString()
//                return params
//            }
//
//        }
//        Volley.newRequestQueue(this@NotDetayActivity).add(istekGuncelle)
//    }

}