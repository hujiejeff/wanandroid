package com.hujiejeff.wanadnroid.module.base.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

fun <V : ViewBinding> setActivityContentView(
    activity: Activity,
    layoutInflater: LayoutInflater
): V? {
    try {
        val bindingClazz = getSuperClassGenericClass<V>(activity)
        val binding: V
        val inflateMethod = bindingClazz.getDeclaredMethod("inflate", LayoutInflater::class.java)
        binding = inflateMethod.invoke(null, layoutInflater) as V
        activity.setContentView(binding.root)
        return binding
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

fun <V : ViewBinding> setFragmentContentView(
    fragment: Fragment,
    layoutInflater: LayoutInflater,
    container: ViewGroup?
): V? {
    try {
        val bindingClazz = getSuperClassGenericClass<V>(fragment)
        val binding: V
        val inflateMethod = bindingClazz.getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        binding = inflateMethod.invoke(null, layoutInflater, container, false) as V
        return binding
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

/**
 * 获取父类泛型参数class 比如 ClassA: ClassB<T,V> 获取T,V的class对象
 */
fun <T> getSuperClassGenericClass(subClassObj: Any, index: Int = 0): Class<T> {
    val type = subClassObj.javaClass.genericSuperclass as ParameterizedType
    val genericClass = type.actualTypeArguments[index] as Class<*>
    return genericClass as Class<T>
}