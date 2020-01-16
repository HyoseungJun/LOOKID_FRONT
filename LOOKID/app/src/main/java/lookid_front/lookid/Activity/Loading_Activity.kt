package lookid_front.lookid.Activity

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.util.Base64.NO_WRAP
import android.util.Log
import com.kakao.util.maps.helper.Utility.getPackageInfo
import lookid_front.lookid.R
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class Loading_Activity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        startActivity(Intent(applicationContext,SignIn_Activity::class.java))
        finish()
    }
}