package me.rocketwash.client.utils

import android.util.Log


fun Any.threadName() = Thread.currentThread().name

fun Any.tag(): String = javaClass.simpleName

fun Any.joinTag(tag: String): String = "$tag [${threadName()}]"

/**
 * Print log in the default level - 'info'
 * */
fun Any.log(message: String) {
    log_i(message)
}

/**
 * Print log in the default level - 'info'
 * */
fun Any.log_i(message: String) {
    log_i(tag(), message)
}

/**
 * Print log in the default level - 'debug'
 * */
fun Any.log_d(message: String) {
    log_d(tag(), message)
}

/**
 * Print log in the default level - 'error'
 * */
fun Any.log_e(message: String) {
    log_e(tag(), message)
}

fun Any.log_i(tag: String, message: String) {
    Log.i(joinTag(tag), message)
}

fun Any.log_d(tag: String, message: String) {
    Log.d(joinTag(tag), message)
}

fun Any.log_e(tag: String, message: String) {
    Log.e(joinTag(tag), message)
}
