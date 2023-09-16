package com.pablojuice.core.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

private const val INFLATE_METHOD = "inflate"

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    protected var safeBinding: VB? = null

    val binding: VB
        get() = safeBinding!!

    private val jobsToClear = mutableListOf<Job>()

    protected abstract val layoutClass: Class<VB>

    protected open val attachToParent: Boolean = false

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inflate(inflater, container).run {
            safeBinding = this
            return root
        }
    }

    @CallSuper
    override fun onDestroyView() {
        safeBinding = null
        jobsToClear.forEach { job -> job.cancel() }
        jobsToClear.clear()
        super.onDestroyView()
    }

    fun submitJob(jobToClear: Job) = jobsToClear.add(jobToClear).let { jobToClear }

    fun <T> Flow<T>.observe(block: (T) -> Unit) =
        submitJob(lifecycleScope.launchWhenCreated { collectLatest(block) })

//    submitJob(
//    lifecycleScope.launch {
//        lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) { collectLatest(block) }
//    }
//    )

    private fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VB {
        return try {
            val method = layoutClass.getMethod(
                INFLATE_METHOD,
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.java
            )
            method(null, inflater, container, attachToParent) as VB
        } catch (e: RuntimeException) {
            val method = layoutClass.getMethod(
                INFLATE_METHOD, LayoutInflater::class.java, ViewGroup::class.java
            )
            method(null, inflater, container) as VB
        }
    }
}