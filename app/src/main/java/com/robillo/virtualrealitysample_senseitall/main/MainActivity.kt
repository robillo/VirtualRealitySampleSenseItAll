package com.robillo.virtualrealitysample_senseitall.main

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.robillo.virtualrealitysample_senseitall.R
import com.robillo.virtualrealitysample_senseitall.vr.VrActivity
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityInterface {

    val CONST_EXTRA = "SCENE"

    private val EXTRA_STORE_NEAR_SEA = "EXTRA_STORE_NEAR_SEA"
    private val EXTRA_SUNRISE_IN_OPEN = "EXTRA_SUNRISE_IN_OPEN"
    private val EXTRA_NIGHT_BEAUTY = "EXTRA_NIGHT_BEAUTY"
    private val EXTRA_GREENERY_SHORE = "EXTRA_GREENERY_SHORE"
    private val EXTRA_WAYS_TO_GO = "EXTRA_WAYS_TO_GO"

    lateinit var i: Intent

    override fun setClickListeners() {
        i = Intent(this, VrActivity::class.java)

        store_near_sea_card.setOnClickListener{
            i.putExtra(CONST_EXTRA, EXTRA_STORE_NEAR_SEA)
            startActivity(i)
        }
        sunrise_in_open_card.setOnClickListener {
            i.putExtra(CONST_EXTRA, EXTRA_SUNRISE_IN_OPEN)
            startActivity(i)
        }
        night_time_beauty_card.setOnClickListener {
            i.putExtra(CONST_EXTRA, EXTRA_NIGHT_BEAUTY)
            startActivity(i)
        }
        greenery_at_shore_card.setOnClickListener {
            i.putExtra(CONST_EXTRA, EXTRA_GREENERY_SHORE)
            startActivity(i)
        }
        ways_to_go_card.setOnClickListener {
            Log.e("tag", "click")
            i.putExtra(CONST_EXTRA, EXTRA_WAYS_TO_GO)
            startActivity(i)
        }
    }

    override fun initialize() {

        Glide.with(this).load(R.drawable.store_near_sea)
                .apply(RequestOptions().centerCrop()).into(store_near_sea)

        Glide.with(this).load(R.drawable.sunrise_in_open)
                .apply(RequestOptions().centerCrop()).into(sunrise_in_open)

        Glide.with(this).load(R.drawable.night_time_beauty)
                .apply(RequestOptions().centerCrop()).into(night_time_beauty)

        Glide.with(this).load(R.drawable.greenery_at_the_shores)
                .apply(RequestOptions().centerCrop()).into(greenery_at_shore)

        Glide.with(this).load(R.drawable.ways_to_go)
                .apply(RequestOptions().centerCrop()).into(ways_to_go)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeStatusBarColor()
        initialize()
        setClickListeners()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun changeStatusBarColor() {
        val window = window ?: return
        val view = window.decorView ?: return
        var flags = view.systemUiVisibility
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        view.systemUiVisibility = flags
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    }
}
