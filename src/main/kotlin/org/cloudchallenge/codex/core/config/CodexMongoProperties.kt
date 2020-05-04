package org.cloudchallenge.codex.core.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "codex.mongodb")
data class CodexMongoProperties(
    var prefix: String = "codex-",
    var defaultDatabaseName: String = "global",
    var globalDatabaseName: String = "global"
)