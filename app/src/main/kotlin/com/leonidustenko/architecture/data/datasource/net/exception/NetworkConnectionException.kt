package com.leonidustenko.architecture.data.datasource.net.exception

import java.io.IOException

/**
 * Created by leonid on 1/5/18.
 */
class NetworkConnectionException : IOException() {

    override val message: String?
        get() = "No network available, please check your WiFi or Data connection"
}