package com.arpak.notlaruygulamasi

class ApiUtils {


    companion object{

        var BASE_URL = "http://kasimadalan.pe.hu"

        fun getNotlarDaoInterface(): NotlarDaoInterface {

            return RetrofitClient.getClient(BASE_URL).create(NotlarDaoInterface::class.java)
        }

    }
}