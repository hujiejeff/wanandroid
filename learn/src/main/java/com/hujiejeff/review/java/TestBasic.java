package com.hujiejeff.review.java;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

public class TestBasic {
    public static void main(String[] args) {
//        testBufferPool();
//        testStringPool();
//        testObject();
        testReflect();
        int a = 16>>2;
        System.out.println(a);
    }

    private static void testBufferPool() {
        Integer x = new Integer(123);
        Integer y = new Integer(123);
        System.out.println(x == y);    // false
        Integer z = Integer.valueOf(123);
        Integer k = Integer.valueOf(123);
        System.out.println(z == k);   // true

        Integer a = 20;
        Integer b = 20;
        System.out.println(a == b);

        Boolean b1 = true;
        Boolean b2 = true;
        System.out.println(b1 == b2);
    }

    private static void testStringPool() {
        String a = "abc";
        String b = "abc";
        String c = new String("abc");
        String d = new String("abc");
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(c == d);
    }

    private static void testObject() {
        Object a = new Object();
        Object b = a;
        System.out.println(a);
        System.out.println(b);
    }

    private static void testReflect() {
        Class<TestBasic> testBasicClass = TestBasic.class;
        Method[] methods = testBasicClass.getDeclaredMethods();
        try {
            Method method = testBasicClass.getMethod("testType6", List.class);
            System.out.println(method.getName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        for (Method m : methods) {
            for (Type type: m.getGenericParameterTypes()) {
                System.out.print("方法" + m.getName()+ "():的参数" + type + "是");
                if (type instanceof WildcardType) {
                    System.out.print("WildcardType,");
                }

                if (type instanceof TypeVariable) {
                    System.out.print("TypeVariable,");
                }

                if (type instanceof ParameterizedType) {
                    System.out.print("ParameterizedType,");
                    Type a = ((ParameterizedType) type).getActualTypeArguments()[0];
                    if (a instanceof WildcardType) {
                        System.out.print("," + a.getTypeName() + " is " + "WildcardType,");
                    }
                }

                if (type instanceof Class<?>) {
                    System.out.print("class type,");
                }

                if (type instanceof GenericArrayType) {
                    System.out.print("数组类型,");
                }
            }
            System.out.println(m.getGenericParameterTypes());
            System.out.println("--------------------------");
        }
    }


    public static <T> T testType1(T t) {
        return t;
    }

    public void testType2(String s) {

    }

    public void testType3(List<String> list) {

    }

    public<T> void testType4(List<T> list) {
    }

    public void testType5(List<?> list) {
    }

    public void testType6(List<? extends Number> list) {
        try {
            Field field = this.getClass().getField("name");

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public<T> void testType7(List<? extends T> list) {
    }

    public<T> void testType8(T[] t) {
    }

    public<T> void testType9(String[] t) {
    }


}
