package org.cloudchallenge.codex.core.exception

open class FunctionalException(var code: String, message: String) : Exception(message)