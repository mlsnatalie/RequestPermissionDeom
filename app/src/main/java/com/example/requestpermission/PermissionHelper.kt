package com.example.requestpermission

import android.content.pm.PackageManager
import android.support.annotation.NonNull
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.os.Build
import android.app.Activity
import android.content.Context


/**
 * 动态权限帮助类
 * Created by dway on 2018/1/10.
 * Update by haoxue on 2018/10/26.
 */
class PermissionHelper(private val mActivity: Activity, private val mPermissionInterface: PermissionInterface) {
    private var permission: String? = null
    private var callBackCode: Int = 0

    /**
     * 请求权限
     * @param permission 权限名字
     * @param callBackCode 回调code
     */
    fun requestPermissions(permission: String, callBackCode: Int) {
        this.permission = permission
        this.callBackCode = callBackCode
        if (hasPermission(mActivity, permission)) {
            mPermissionInterface.requestPermissionsSuccess(callBackCode)
        } else {
            ActivityCompat.requestPermissions(mActivity, arrayOf(permission), callBackCode)
        }

    }

    /**
     * 在Activity中的onRequestPermissionsResult中调用,用来接收结果判断
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    fun requestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == callBackCode) {
            for (result in grantResults) {
                if (result == PackageManager.PERMISSION_GRANTED) {
                    mPermissionInterface.requestPermissionsSuccess(callBackCode)
                } else {
                    mPermissionInterface.requestPermissionsFail(callBackCode)
                }
            }
        }
    }

    companion object {

        /**
         * 判断是否有某个权限
         *
         * @param context
         * @param permission
         * @return
         */
        fun hasPermission(context: Context, permission: String): Boolean {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Android 6.0判断，6.0以下跳过。在清单文件注册即可，不用动态请求，这里直接视为有权限
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
            return true
        }
    }
}
