package org.cloudchallenge.codex.core.exception

class NotFoundException(message: String, code: String=ERROR_CODE) : FunctionalException(code, message){
    companion object {
        const val ERROR_CODE = "NOT_FOUND"
    }
}
