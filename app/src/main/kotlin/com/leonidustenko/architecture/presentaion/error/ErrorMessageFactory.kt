package com.leonidustenko.architecture.presentaion.error

import android.content.Context
import com.leonidustenko.architecture.R
import com.leonidustenko.architecture.presentaion.error.ErrorMessage.*

/**
 * Error messages factory.
 * Used to construct localized error message according to error code.
 */
object ErrorMessageFactory {

    /**
     * Creates error message by error code.
     *
     * @param context Context needed to retrieve String resources.
     * @param code Error code
     */
    fun create(context: Context, code: ErrorMessage): String {
        return context.getString(
                when (code) {
                    ERROR_GENERIC -> R.string.error_message_generic
                    ERROR_INTERNET_CONNECTION -> R.string.error_message_internet_connection
                    ERROR_GET_USERS -> R.string.error_message_get_users
                })
    }
}