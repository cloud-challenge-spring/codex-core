package org.cloudchallenge.codex.core.lang

import java.security.SecureRandom

private const val characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"

fun randomString(length: Int) = (1..length)
        .map { SecureRandom().nextInt(characterSet.length) }
        .joinToString("") { characterSet.substring(it, it + 1) }
