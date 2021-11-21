package com.test.spaceapp

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import com.test.spaceapp.domain.dagger.AppComponent
import com.test.spaceapp.domain.dagger.DaggerAppComponent
import com.test.spaceapp.domain.dagger.module.AppModule
import com.test.spacedemoapp.domain.net.networkconnection.NetWorkConnection
import io.reactivex.subjects.BehaviorSubject


class SpaceApp : Application() {

    lateinit var appComponent: AppComponent
    private var cicerone: Cicerone<Router>? = null

    @SuppressLint("StringFormatInvalid")
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        initCicerone()
        NetWorkConnection.isInternetAvailable(this)
        internetStateObservable.onNext(NetWorkConnection.isInternetAvailable(this))
        this.appComponent = this.initDagger()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkCallback = createNetworkCallback()
            cm.registerDefaultNetworkCallback(networkCallback)
        } else {
            val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            val broadcastReceiver = createBroadcastReceiver()
            registerReceiver(broadcastReceiver, intentFilter)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            val channelId = getString(R.string.default_notification_channel_id)
            val channelName = getString(R.string.default_notification_channel_name)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(
                NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_LOW)
            )
        }

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FirebaseMessaging", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = getString(R.string.msg_token_fmt, token)
            Log.d("FirebaseMessaging", msg)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })

        Firebase.messaging.subscribeToTopic("weather")
            .addOnCompleteListener { task ->
                var msg = getString(R.string.msg_subscribed)
                if (!task.isSuccessful) {
                    msg = getString(R.string.msg_subscribe_failed)
                }
                Log.d("FirebaseMessaging", msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }

    }

    private fun createMessagingReceiver() = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            val isNoConnectivity = intent?.extras?.getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY) ?: true
            internetStateObservable.onNext(!isNoConnectivity)
        }

    }

    private fun createBroadcastReceiver() = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            val isNoConnectivity = intent?.extras?.getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY) ?: true
            internetStateObservable.onNext(!isNoConnectivity)
        }

    }

    private fun createNetworkCallback() = object : ConnectivityManager.NetworkCallback() {

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            val isInternet = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            val isValidated = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            internetStateObservable.onNext(isInternet && isValidated)
        }

        override fun onLost(network: Network) {
            internetStateObservable.onNext(false)
        }
    }

    companion object {
        lateinit var INSTANCE: SpaceApp
        private val internetStateObservable: BehaviorSubject<Boolean> = BehaviorSubject.create()

        fun updateInternetState(isInternetAvailable: Boolean) {
            internetStateObservable.onNext(isInternetAvailable)
        }
    }

    private fun initDagger() = DaggerAppComponent.builder()
        .appModule(AppModule(this, internetStateObservable))
        .build()

    private fun initCicerone() {
        cicerone = Cicerone.create()
    }
}