/*


package com.arpak.notlaruygulamasi

import android.content.ContentValues

class Notlardao {


    fun tumNotlar(vt:VeritabaniYardimcisi): ArrayList<Notlar> {

        val db = vt.writableDatabase
        val notlarListesi = ArrayList<Notlar>()
        val cursor = db.rawQuery("SELECT * FROM notlar", null)
        while (cursor.moveToNext()){
            val not = Notlar(cursor.getInt(cursor.getColumnIndexOrThrow("not_id"))
                ,cursor.getString(cursor.getColumnIndexOrThrow("ders_ad"))
                ,cursor.getInt(cursor.getColumnIndexOrThrow("not1"))
                ,cursor.getInt(cursor.getColumnIndexOrThrow("not2")))

            notlarListesi.add(not)
        }
            return notlarListesi
    }

    fun notSil(vt:VeritabaniYardimcisi,not_id: Int){
        val db = vt.writableDatabase
        db.delete("notlar","not_id", arrayOf(not_id.toString()))
        db.close()
    }

    fun notEkle(vt:VeritabaniYardimcisi,ders_ad: String, not1: Int, not2: Int){
        val db = vt.writableDatabase
        val values = ContentValues()
        values.put("ders_ad",ders_ad)
        values.put("not1",not1)
        values.put("not2",not2)

        db.insertOrThrow("notlar",null,values)
        db.close()
    }

    fun notGuncelle(vt:VeritabaniYardimcisi, not_id: Int,ders_ad: String,not1: Int,not2: Int){
        val db =vt.writableDatabase
        val values = ContentValues()
        values.put("ders_ad",ders_ad)
        values.put("not1",not1)
        values.put("not2",not2)
        db.update("notlar",values,"not_id=?", arrayOf(not_id.toString()))
        db.close()
    }


}

*/
