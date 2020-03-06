package Test;

import java.util.Objects;

class Employee {

    public String name;
    public Integer salary;
    public Integer hireDay;

    public boolean equals(Object o){
        //判断是否是同一个引用
        if(this == o)
            return true;

        //如果o为null，直接返回false
        if(o == null)
            return false;

        //如果对象的实际类型必须均为Employee，返回false
        if(this.getClass() != o.getClass())
            return false;

        //假如o的引用类型是Employee的父类型，将o转成Employee类型
        Employee other = (Employee) o;

        //检查两个对象的状态（即各个属性）是否相同
        return Objects.equals(this.name, other.name)
                && Objects.equals(this.salary, other.salary)
                && Objects.equals(this.hireDay, other.hireDay);  //调用Objects.equals方法可以检查属性为空的情况
    }
}
