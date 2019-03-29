package com.example.requestpermission

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_fourth.*
import android.widget.Toast


private const val REQUEST_CODE_ADD_PICTURE = 33

class FourthActivity : AppCompatActivity(), PermissionInterface {

    private var permissionHelper: PermissionHelper? = null
    // 拍照结果返回code
    private val REQ_CODE_PICK_PHOTO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        permissionHelper = PermissionHelper(this, this)
        initView()

    }

    private fun initView() {
        // 拍照监听
        bt_photo.setOnClickListener { // lambda 简写
            // 判断是否有权限
            permissionHelper?.requestPermissions(Manifest.permission.CAMERA, 1)
        }
    }


    /**
     * 重写Activity的权限请求返回结果方法
     *
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        permissionHelper?.requestPermissionsResult(requestCode, permissions, grantResults) // 接管结果判断
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun requestPermissionsSuccess(callBackCode: Int) { // 有权限处理
        if (callBackCode == REQ_CODE_PICK_PHOTO) {
            takePhoto()
        }
    }

    override fun requestPermissionsFail(callBackCode: Int) { // 无权限处理
        if (callBackCode == REQ_CODE_PICK_PHOTO) {
            Toast.makeText(this, "sd", Toast.LENGTH_LONG).show()
        }
    }

    // 处理有权限后的业务逻辑
    private fun takePhoto() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE_ADD_PICTURE)
    }


}
