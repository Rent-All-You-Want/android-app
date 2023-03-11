package com.pablojuice.core.presentation.base.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

private const val INFLATE_METHOD = "inflate"

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null

    val binding: VB
        get() = _binding!!


    abstract fun bindLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean = false
    ): VB

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
//        val time = System.currentTimeMillis()
//        bindingClass?.inflate(inflater, container, false)?.run {
//            _binding = this
//            Timber.e("time ref ${System.currentTimeMillis() - time}")
//            return root
//        }
        bindLayout(inflater, container).run {
            _binding = this
            return root
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    //TODO TEST SPEED
//    protected var bindingClass: Class<VB>? = null
//    private fun Class<VB>.inflate(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        attachToParent: Boolean,
//    ): VB {
//        return try {
//            val method = getMethod(
//                INFLATE_METHOD,
//                LayoutInflater::class.java,
//                ViewGroup::class.java,
//                Boolean::class.java
//            )
//            method(null, inflater, container, attachToParent) as VB
//        } catch (e: NoSuchMethodException) {
//            val method = getMethod(
//                INFLATE_METHOD, LayoutInflater::class.java, ViewGroup::class.java
//            )
//            method(null, inflater, container) as VB
//        }
//    }
}