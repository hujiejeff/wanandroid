package com.hujiejeff.base.utils

import java.lang.reflect.ParameterizedType

/**
 * 获取父类泛型参数class 比如 ClassA: ClassB<T,V> 获取T,V的class对象
 */
fun <T> getSuperClassGenericClass(subClassObj: Any, index: Int = 0): Class<T> {
    val type = subClassObj.javaClass.genericSuperclass as ParameterizedType
    val genericClass = type.actualTypeArguments[index] as Class<*>
    return genericClass as Class<T>
}