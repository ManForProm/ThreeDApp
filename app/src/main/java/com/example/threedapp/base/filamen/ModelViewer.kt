package com.example.threedapp.base.filamen

import android.animation.ValueAnimator
import android.view.Surface
import android.view.SurfaceView
import android.view.animation.LinearInterpolator
import com.google.android.filament.*
import com.google.android.filament.android.DisplayHelper
import com.google.android.filament.android.UiHelper
import java.time.Duration
import kotlin.math.*
import kotlin.random.Random

private const val kNearPlane = 0.5
private const val kFarPlane = 10000.0
private const val kFovDegrees = 45.0
private const val kAperture = 16f
private const val kShutterSpeed = 1f / 125f
private const val kSensitivity = 100f

class ModelViewer(
    val engine: Engine,
    val surfaceView: SurfaceView
) {
    val view: View = engine.createView()
    val camera: Camera =
        engine.createCamera(EntityManager.get().create())
            .apply { setExposure(kAperture, kShutterSpeed, kSensitivity) }
    var scene: Scene? = null
        set(value) {
            view.scene = value
            field = value
        }

    var scale = 4.0
    var rotation = 0.0
    var verticalRotation = 0.0
    var sliderValue = 0.0

    private val uiHelper: UiHelper = UiHelper(UiHelper.ContextErrorPolicy.DONT_CHECK)
    private var displayHelper: DisplayHelper
    private var swapChain: SwapChain? = null
    private val renderer: Renderer = engine.createRenderer()

    private var animationDuration = 60_000
    private var animationOnOff = false
    private var animator = ValueAnimator.ofFloat(0.0f, (2.0 * PI).toFloat())

    private val start = 0f

    init {
        view.camera = camera

        displayHelper = DisplayHelper(surfaceView.context)
        uiHelper.renderCallback = SurfaceCallback()
        uiHelper.attachTo(surfaceView)
        addDetachListener(surfaceView)

        animateModelView()

    }


    fun render(frameTimeNanos: Long) {
        if (!uiHelper.isReadyToRender) {
            return
        }

        // Render the scene, unless the renderer wants to skip the frame.
        if (renderer.beginFrame(swapChain!!, frameTimeNanos)) {
            renderer.render(view)
            renderer.endFrame()
        }
    }

    fun animationSettings(start: Boolean, duration: Long = 60_000) {
        animationDuration = duration.toInt()
        animationOnOff = start
    }

    private fun animateModelView() {
        animator.interpolator = LinearInterpolator()
        animator.duration = 60_000
        animator.repeatMode = ValueAnimator.RESTART
        animator.repeatCount = ValueAnimator.INFINITE
        animator.addUpdateListener { a ->
            val piDivideTwo = (PI / 2).toFloat()
            val v = if (animationOnOff) (a.animatedValue as Float) + start else 0f
            camera.lookAt(
                cos(v + rotation  ) * scale,
                (if(verticalRotation > piDivideTwo) piDivideTwo
                else if(verticalRotation < -piDivideTwo) -piDivideTwo
                else verticalRotation).toDouble() * scale + 0.5,
                sin(v + rotation )* scale,
                0.0,
                0.0,
                0.0,
                0.0,
                1.0,
                0.0
            )
        }
        animator.start()
    }

    private fun addDetachListener(view: android.view.View) {
        class AttachListener : android.view.View.OnAttachStateChangeListener {
            var detached = false

            override fun onViewAttachedToWindow(v: android.view.View) {
                detached = false
            }

            override fun onViewDetachedFromWindow(v: android.view.View) {
                if (!detached) {
                    animator.cancel()

                    uiHelper.detach()

                    engine.destroyRenderer(renderer)
                    engine.destroyView(this@ModelViewer.view)
                    engine.destroyCameraComponent(camera.entity)

                    detached = true
                }
            }

        }
        view.addOnAttachStateChangeListener(AttachListener())
    }

    inner class SurfaceCallback : UiHelper.RendererCallback {
        override fun onNativeWindowChanged(surface: Surface) {
            swapChain?.let { engine.destroySwapChain(it) }
            swapChain = engine.createSwapChain(surface)
            displayHelper.attach(renderer, surfaceView.display)
        }

        override fun onDetachedFromSurface() {
            displayHelper.detach()
            swapChain?.let {
                engine.destroySwapChain(it)
                engine.flushAndWait()
                swapChain = null
            }
        }

        override fun onResized(width: Int, height: Int) {
            view.viewport = Viewport(0, 0, width, height)
            val aspect = width.toDouble() / height.toDouble()
            camera.setProjection(kFovDegrees, aspect, kNearPlane, kFarPlane, Camera.Fov.VERTICAL)
        }
    }
}