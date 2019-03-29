package com.example.requestpermission

/**
 * 动态权限回调接口
 * Created by dway on 2018/1/10.
 * Update by haoxue on 2018/10/26.
 */
interface PermissionInterface {
    /**
     * 请求权限成功回调
     */
    fun requestPermissionsSuccess(callBackCode: Int)

    /**
     * 请求权限失败回调
     */
    fun requestPermissionsFail(callBackCode: Int)

}
