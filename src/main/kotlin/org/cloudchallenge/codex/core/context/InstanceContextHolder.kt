package org.cloudchallenge.codex.core.context

import org.slf4j.LoggerFactory
import org.slf4j.MDC

object InstanceContextHolder {
    private val logger = LoggerFactory.getLogger(InstanceContextHolder::class.java)
    private val context = ThreadLocal<String>()

    fun setInstanceId(instanceId: String?) {
        logger.trace("Switching to instance: {}", instanceId)
        context.set(instanceId)
        MDC.put("instanceId", instanceId)
    }

    fun getInstanceId(): String? = context.get()

    fun clearInstanceId() {
        logger.trace("Removing instance context {}", context.get())
        context.remove()
        MDC.remove("instanceId")
    }
}

inline fun <T> withInstance(instanceId: String, action: (instanceId: String) -> T): T {
    val oldInstance = InstanceContextHolder.getInstanceId()
    try {
        InstanceContextHolder.setInstanceId(instanceId)
        return action(instanceId)
    } finally {
        InstanceContextHolder.setInstanceId(oldInstance)
    }
}