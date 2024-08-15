package me.greenworld.vpdmoney.domain.network

interface ConnectivityRepository {

    fun isDeviceConnectedToInternet(): Boolean
}