package com.naichuan.sunflower

import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentBinding<out B : ViewDataBinding>(
    @LayoutRes private val resId: Int
) : ReadOnlyProperty<Fragment, B> {

    private var binding: B? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): B
        = binding ?: createBinding(thisRef)

    private fun createBinding(activity: Fragment): B
        = DataBindingUtil.inflate(LayoutInflater.from(activity.context), resId, null, true)
}