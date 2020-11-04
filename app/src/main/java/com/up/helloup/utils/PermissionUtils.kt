package com.up.helloup.utils

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.ArrayList

object PermissionUtils {
    @Suppress("UNUSED_PARAMETER")
// Solicita as permissões
    fun validate(activity: Activity, requestCode: Int, vararg permissions: String): Boolean {
        val list = ArrayList<String>()
        for (permission in permissions) {
            // Valida permissão
            val ok = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
            if (!ok) {
                list.add(permission)
            }
        }
        if (list.isEmpty()) {
            // Tudo ok, retorna true
            return true
        }
        // Lista de permissões que falta acesso.
        val newPermissions = arrayOfNulls<String>(list.size)
        list.toArray(newPermissions)
        // Solicita permissão
        ActivityCompat.requestPermissions(activity, newPermissions, 1)
        return false
    }
}