package org.cloudchallenge.codex.core.lang

import org.springframework.data.mongodb.repository.MongoRepository

fun <T, ID> MongoRepository<T, ID>.findOne(id: ID?): T? = id?.let { findById(it).orElse(null) }
