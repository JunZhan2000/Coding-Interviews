package Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public static void main(String[] args){
        Object a1 = new A();
        Object a2 = new B();
        B b = new B();
        if(a1 instanceof B){ }  //false
        if(b instanceof A){ }  //true
        if(b instanceof C){ }  //true

        System.out.println(a1.getClass());  // class A
        System.out.println(a2.getClass());  // class B
        if(a1.getClass() == a2.getClass())
            System.out.println("a1和a2的类型相同！");
        else
            System.out.println("不相同");

    }
}

class A{ }
interface C{ }
class B extends A implements C{ }