package com.robillo.virtualrealitysample_senseitall.vr

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import com.robillo.virtualrealitysample_senseitall.R
import com.robillo.virtualrealitysample_senseitall.main.MainActivity
import kotlinx.android.synthetic.main.activity_vr.*

class VrActivity : AppCompatActivity() {

    lateinit var SCENE: String
    val CONST_EXTRA = "SCENE"

    private val EXTRA_STORE_NEAR_SEA = "EXTRA_STORE_NEAR_SEA"
    private val EXTRA_SUNRISE_IN_OPEN = "EXTRA_SUNRISE_IN_OPEN"
    private val EXTRA_NIGHT_BEAUTY = "EXTRA_NIGHT_BEAUTY"
    private val EXTRA_GREENERY_SHORE = "EXTRA_GREENERY_SHORE"
    private val EXTRA_WAYS_TO_GO = "EXTRA_WAYS_TO_GO"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_vr)

        SCENE = intent.extras?.getString(CONST_EXTRA).toString()
        loadPhotoSphere()
    }

    private fun loadPhotoSphere() {

        var drawable: Int = R.drawable.store_near_sea

        when(SCENE) {
            EXTRA_STORE_NEAR_SEA -> drawable = R.drawable.store_near_sea
            EXTRA_SUNRISE_IN_OPEN -> drawable = R.drawable.sunrise_in_open
            EXTRA_NIGHT_BEAUTY -> drawable = R.drawable.night_time_beauty
            EXTRA_GREENERY_SHORE -> drawable = R.drawable.greenery_at_the_shores
            EXTRA_WAYS_TO_GO -> drawable = R.drawable.ways_to_go
        }

        Handler().postDelayed(Runnable { runOnUi(drawable) }, 500)
    }

    fun runOnUi(drawable: Int) {
        runOnUiThread {
            if (pano_view != null) {
                pano_view.loadImageFromBitmap(
                        BitmapFactory.decodeResource(resources, drawable),
                        VrPanoramaView.Options()
                )
            }
        }
    }

    override fun onPause() {
        pano_view.pauseRendering()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        pano_view.resumeRendering()
    }

    override fun onDestroy() {
        pano_view.shutdown()
        super.onDestroy()
    }
}
