package ru.studio.kompas

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import kotlinx.android.synthetic.main.activity_main.*

//добавление сенсора SensorEventListener (слушатель для получения данных  с сенсора)
class MainActivity : AppCompatActivity(),SensorEventListener {
    // регистрация сенсора
    var manager: SensorManager? = null
    // переменная для хранения градусов
    var currnet_gradus: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
// регистрация слушателя
    override fun onResume() {
        super.onResume()
    manager?.registerListener(this, manager?.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME)
    }
// уберание регистрации слушателя
    override fun onPause() {
        super.onPause()
    manager?.unregisterListener(this)
    }

// слушатель выдающий точность сенсора
    override fun onSensorChanged(event: SensorEvent?) {
        val gradus: Int = event?.values?.get(0)?.toInt()!!
        tvGradus.text = gradus.toString()
        val rotAnima = RotateAnimation(currnet_gradus.toFloat(),(-gradus).toFloat(),Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)
    rotAnima.duration = 210
    rotAnima.fillAfter = true
    currnet_gradus = -gradus
    imDimanic.startAnimation(rotAnima)
    }
// даннные приходящие с сенсора
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}