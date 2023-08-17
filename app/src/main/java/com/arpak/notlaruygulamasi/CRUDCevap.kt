package com.arpak.notlaruygulamasi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CRUDCevap(
    @SerializedName("success")
    @Expose
    var success : Int,
    @SerializedName("message")
    @Expose
    var message: String
): Serializable
