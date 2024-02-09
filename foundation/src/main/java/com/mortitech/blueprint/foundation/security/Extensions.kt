package com.mortitech.blueprint.foundation.security

import android.security.keystore.KeyProperties
import android.util.Base64
import org.bouncycastle.util.BigIntegers
import java.security.MessageDigest
import java.security.PublicKey
import java.security.interfaces.ECPublicKey
import java.util.Arrays

fun ByteArray.toBase64(): String = Base64.encodeToString(this, Base64.NO_WRAP)

fun ByteArray.convertCipherBytesToIntArray() = this.map { it.toUByte().toInt() }.toIntArray()

fun ByteArray.convertFromCipherToString() = this.convertCipherBytesToIntArray().toPlainString()

fun IntArray.toByteArray() = this.map(Int::toByte).toByteArray()

fun IntArray.toPlainString() = this.joinToString("") { Char(it).toString() }

fun String.decodeFromBase64(): String = String(Base64.decode(this, Base64.NO_WRAP))

fun String.toBase64(): String = this.toByteArray(Charsets.UTF_8).toBase64()

fun String.toIntArray() = this.map { it.code }.toIntArray()

fun String.toCipherByteArray() = this.toIntArray().toByteArray()

fun PublicKey.toRaw(): String {
    //Custom RAW Public Key Conversion based on Ernst's instructions
    val ecPubKey = this as ECPublicKey
    val resultByteArray = ByteArray(65)
    Arrays.fill(resultByteArray, 0.toByte())
    resultByteArray[0] = 4
    val xByteArrayAuth = BigIntegers.asUnsignedByteArray(ecPubKey.w.affineX)
    System.arraycopy(xByteArrayAuth,
        0,
        resultByteArray,
        33 - xByteArrayAuth.size,
        xByteArrayAuth.size)
    val yByteArrayAuth = BigIntegers.asUnsignedByteArray(ecPubKey.w.affineY)
    System.arraycopy(yByteArrayAuth,
        0,
        resultByteArray,
        65 - yByteArrayAuth.size,
        yByteArrayAuth.size)
    return java.util.Base64.getEncoder().encodeToString(resultByteArray)
}

fun String.toSHA512(): ByteArray {
    val md: MessageDigest = MessageDigest.getInstance(KeyProperties.DIGEST_SHA512)
    return md.digest(this.toByteArray())
}

fun ByteArray.toSHA512(): String {
    val md: MessageDigest = MessageDigest.getInstance(KeyProperties.DIGEST_SHA512)
    md.update(this)
    return Base64.encodeToString(md.digest(), Base64.NO_WRAP)
}