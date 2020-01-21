package com.nshinn.marvellimited.net.netsec

import java.math.BigInteger
import java.security.MessageDigest

// Kotlin extension to add md5 hashing
fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}

class CryptoUtil {
    companion object {
        // Guarantees the timestamp used in the hash is
        // the same value used in the parameter
        var currentTimeStamp: String? = System.currentTimeMillis().toString()

        fun setRequestTimeStamp() {
            currentTimeStamp = System.currentTimeMillis().toString()
        }
    }
}