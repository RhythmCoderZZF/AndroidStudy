package com.example.android_study._kotlin._study._1_Object._7_Generics;


import java.util.ArrayList;
import java.util.List;

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/2
 * Description:
 */
class A_java {

    public static List<? super Number> in(List<? super Number> in) {
        in.add(1);
        in.add(1.1);
        Object object = in.get(0);  //集合一定是Number及其父类，所以可以安全插入Number及其子类
                                    // ；不知道方法参数的父类型是谁，所以get只能默认为Object
        return in;
    }

    public static List<? extends Number> out(List<? extends Number> out) {
        Number number = out.get(0);
        out.get(1);
//        out.add(1);               //集合一定是Number及其子类，所以不能插入Number及其子类（防止取的时候类型转换异常）
//                                      ；知道方法参数的父类型是谁，所以get只能默认为Number
        return out;
    }

    {
        List<Integer> list1 = new ArrayList<>();
        List<Number> list2 = new ArrayList<>();
        List<Object> list3 = new ArrayList<>();

//        in(list1);    err:
        in(list2);
        in(list3);

        out(list1);
        out(list2);
//        out(list3);   err
    }

}
