package com.munki.paging.util.common

/**
 * RequestCode Util
 * @author 나비이쁜이
 * @since 2021.03.09
 */
class RequestCodeUtil {

    /**
     * 권한
     */
    object PermissionReqCode {
        const val REQ_PERMISSION_LOCATION = 1
        const val REQ_PERMISSION_CAMERA = 2
    }

    /**
     * Firebase Message
     */
    object FireBaseMessageReqCode {
        const val REQ_SEND_MESSAGE = 0
    }

    /**
     * Activity Result Setting
     */
    object ActivityResultReqCode {
        const val REQ_CHECK_SETTINGS = 0
    }
}