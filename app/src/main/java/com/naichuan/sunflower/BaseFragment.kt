package com.naichuan.sunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<B: ViewDataBinding>(
    private val resId: Int
): Fragment(resId) {

    protected val binding by FragmentBinding<B>(resId)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initBinding(binding)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initData()
    }

    abstract fun initBinding(binding: B): View?

    protected open fun initData() {
        // init some common configs
    }
}