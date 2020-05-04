package org.cloudchallenge.codex.core.config

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.cloudchallenge.codex.core.mongo.MultiTenantMongoDbFactory
import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories
@EnableConfigurationProperties(MongoProperties::class)
class MongoConfiguration() : AbstractMongoClientConfiguration() {

    override fun mongoClient(): MongoClient {
        return MongoClients.create()
    }

    override fun getDatabaseName(): String {
        return "springdata"
    }

    @Bean
    fun mongoTemplate(mongoDbFactory: MultiTenantMongoDbFactory): MongoTemplate {
        return MongoTemplate(mongoDbFactory)
    }

    @Bean
    fun mongoDbFactory(codexMongoProperties: CodexMongoProperties): MultiTenantMongoDbFactory {
        return MultiTenantMongoDbFactory(mongoClient(),
                codexMongoProperties.prefix, codexMongoProperties.defaultDatabaseName)
    }
}