package com.palak.applemusicappdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.palak.applemusicappdemo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * This is single entry point. It containers entire view for the fragments that will be loaded
 * with the help of navigation component.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "TESTTTT"
    }

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("LogNotTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Log.w(TAG, "onCreate: build type : ${BuildConfig.BUILD_TYPE} " +
                ", applicationId : ${BuildConfig.APPLICATION_ID} " +
                ", debug : ${BuildConfig.DEBUG}" )

        Log.w(TAG, "onCreate: ${this.packageName}" )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}