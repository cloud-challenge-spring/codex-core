package org.cloudchallenge.codex.core.model

import org.springframework.data.annotation.Id

data class Instance(@Id var id: String? = null,
                    var name: String,
                    var instanceKey: String)