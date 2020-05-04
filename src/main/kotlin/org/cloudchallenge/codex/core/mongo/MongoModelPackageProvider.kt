package org.cloudchallenge.codex.core.mongo

interface MongoModelPackageProvider {
    fun getModelPackages(): List<String>
}