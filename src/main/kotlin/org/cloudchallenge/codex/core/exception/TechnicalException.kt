package org.cloudchallenge.codex.core.exception

open class TechnicalException(var code: String, message: String) : Exception(message)