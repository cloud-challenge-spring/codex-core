package org.cloudchallenge.codex.core.mongo

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import org.cloudchallenge.codex.core.context.InstanceContextHolder
import org.slf4j.LoggerFactory
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory
import java.security.InvalidParameterException

class MultiTenantMongoDbFactory internal constructor(
        mongo: MongoClient,
        private val prefix: String,
        val myDefaultDatabaseName: String?) : SimpleMongoClientDbFactory(mongo, prefix + myDefaultDatabaseName!!) {

    init {
        if (myDefaultDatabaseName == null || myDefaultDatabaseName.isEmpty())
            throw InvalidParameterException("defaultDatabaseName is null or empty")
    }

    override fun getDb(): MongoDatabase {
        var name = InstanceContextHolder.getInstanceId()

        if (name == null) {
            name = this.myDefaultDatabaseName
        }

        val dbToUse = prefix + name
        logger.trace("Acquiring database: {}", dbToUse)
        return super.getDb(dbToUse)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(MultiTenantMongoDbFactory::class.java)
    }
}