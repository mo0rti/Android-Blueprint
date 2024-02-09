package com.mortitech.blueprint.foundation.security

import java.nio.charset.Charset
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

object CryptoUtil {

    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    private const val STRING_LENGTH = 10

    fun createRandomSalt(): String = (1..STRING_LENGTH)
            .map { kotlin.random.Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")

    fun encryptData(plaintext: String, cipher: Cipher): CipherTextWrapper {
        val ciphertext = cipher.doFinal(plaintext.toByteArray(Charset.forName("UTF-8")))
        return CipherTextWrapper(ciphertext, cipher.iv)
    }

    fun decryptData(ciphertext: ByteArray, cipher: Cipher): String {
        val plaintext = cipher.doFinal(ciphertext)
        return String(plaintext, Charset.forName("UTF-8"))
    }

    fun getInitializedCipherForEncryption(cipher: Cipher, secretKey: SecretKey): Cipher {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        return cipher
    }

    fun getInitializedCipherForDecryption(
        cipher: Cipher,
        secretKey: SecretKey,
        initializationVector: ByteArray
    ): Cipher {
        cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(initializationVector))
        return cipher
    }

}