package com.mortitech.blueprint.foundation.security

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class CipherTextWrapper(
    val cipherText: ByteArray,
    val initializeVector: ByteArray
) {
    fun toJson(gson: Gson): String = gson.toJson(toJsonClass())

    private fun toJsonClass() =
        CipherTextJson(
            ct = cipherText.convertFromCipherToString(),
            iv = initializeVector.convertFromCipherToString()
        )

    private data class CipherTextJson(
        @SerializedName("ct")
        val ct: String,
        @SerializedName("iv")
        val iv: String
    )

    companion object {
        fun fromJson(gson: Gson, jsonString: String): CipherTextWrapper {
            val cipherTextJson = gson.fromJson(jsonString, CipherTextJson::class.java)
            return CipherTextWrapper(
                cipherText = cipherTextJson.ct.toCipherByteArray(),
                initializeVector = cipherTextJson.iv.toCipherByteArray()
            )
        }
    }
}