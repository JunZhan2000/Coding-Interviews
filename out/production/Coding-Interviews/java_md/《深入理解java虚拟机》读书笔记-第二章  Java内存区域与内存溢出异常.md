### 第二章  Java内存区域与内存溢出异常

***



#### 2.1 概述

Java 和 C/C++的一个重要区别在于 **动态分配内存** 和 **垃圾回收机制**。



#### 2.2 运行时数据区域

Java虚拟机在执行Java程序时，会将其管理的内存划分为不同的数据区域。

它们分别有不同的用途、创建和销毁的时间



##### 2.2.1 程序记数器

* 较小的一个内存区域
* **用于存储当前线程执行的字节码行号**
* 线程私有
* 仅服务于java方法，执行Native方法时，计数器的值为空
* 唯一一个没有规定内存溢出情况的区域



##### 2.2.2 Java虚拟机栈

* 线程私有，与线程声明周期相同

* 每个方法都对应一个栈帧（Stack Frame），用于存储局部变量表、操作数栈、动态链接、方法出口等信息，方法的调用与执行完成即对应栈帧的入栈与出栈

* 人们常说内存分为堆与栈，其中的栈就是Java虚拟机栈中的局部变量表

* **局部变量表存放了各种基本数据类型、引用类型与returnAddress类型**

* 进入一个方法时，其栈帧的大小是固定的

* 两种异常情况

  * 虚拟机无法动态扩展虚拟机栈，当线程申请的栈深度超过所允许的深度，会抛出StackOverFlowError异常
  * 虚拟机可以动态扩展虚拟机栈（大部分），当扩展时无法申请到足够的内存，就会抛出OutOfMemoryError

  

##### 2.2.3 本地方法栈

* **与虚拟机栈非常相似**

* 区别在于虚拟机栈服务于Java方法，本地方法栈服务于Native方法

  

##### 2.2.4 Java堆

* 内存最大的区域
* 各个线程共享，在虚拟机启动时创建
* **唯一作用就是存储对象实例**
* **垃圾收集器管理的主要区域**，别称GC堆（Gabage collected Heap）
* 可以处于物理上不连续的内存空间中，一般可扩展
* 如果堆没有内存用于创建实例，也无法扩展堆时，就会抛出OutOfMemoryError异常



##### 2.2.5 方法区

* 用于存储被虚拟机加载的类信息、常量、静态变量等信息
* 垃圾回收行为出现的较少，主要针对于常量池的回收以及对类型的卸载
* 当方法区无法满足内存分配需求时，抛出OutOfMemoryError异常



##### 2.2.6 运行时常量区

* 方法区的一部分
* class文件中有一项信息是常量池，用于存放编译时产生的各种字面量以及符号引用，这部分内容将在类加载后在方法区的运行时常量池存放
* 相较于class文件中的常量池，具备动态性，也可以存放运行时产生的常亮



##### 2.2.7 直接内存

* 不是虚拟机运行时数据区的一部分，但被频繁使用
* NIO类的一种IO方式，可以直接调用Native方法分配堆外内存，在某些场景显著提高性能
* **下面的看不懂了。。看完后面回来补**





#### 2.3 HotSpot虚拟机对象探秘

##### 2.3.1 对象的创建

1. 虚拟机开始执行一条new 指令，检查是否能在常量池中定位到对应类的符号引用，并检查该类是否已被加载、解析和初始化过。若没有则先执行该类的类加载过程，若有则开始分配内存空间
2. 如何分配内存
   * 指针碰撞：java堆中的内存是规整的，已使用的和空闲的内存分开存放，中间由一个指针分隔。则分配内存时只需移动该分界线的指针即可
   * 空闲列表：java堆中内存是不规整的，只能通过一个列表记录内存的使用情况

3. 并发情况下如何保证线程安全
   * 进行同步处理
   * 划分空间给不同线程分配内存，即每个线程在java堆中预先分配一小块内存，称为本地线程分配缓冲（TLAB）。只有TLAB用完并分配新的TLAB时，才需要同步锁定。

4. 将分配到的内存空间初始化为零值，保证未初始化对象也能使用对象的实例
5. 设置对象的信息，如对象属于哪个类、对象的哈希码、对象的GC分代年龄等，存放在对象头中
6. 执行构造方法，初始化一个对象的状态。



##### 2.3.2 对象的内存布局

* 对象在内存中存储的布局可以分为3个区域：**对象头、实例数据和对其填充**

* 对象头

  * 对象自身的运行时数据，如哈希码、GC分代年龄、锁状态标注等，

    这部分数据的长度为32bit（32位虚拟机）或64bit(未开启压缩指针的64位虚拟机)，被称为Mark Word

    实际数据已超过该空间大小，Mark Word被设计为非固定的数据结构，存储空间可依据使用情况复用

  * 类型指针（非必要），指向对象的类元数据的指针

* 实例数据：对象各个字段的内容。

* 对齐填充（非必须）：作为占位符保证对象初始位置始终为8个字节的整数倍

##### 2.3.3 对象的访问定位

* 如何通过引用类型定位与访问对象的位置
  * 通过句柄：在Java堆中划出一块空间（句柄池）存储句柄，引用变量存储句柄的位置，句柄存储两个指针：到对象实例数据的指针和到对象类型数据的指针，然后通过这两个指针访问数据
  * 通过指针：引用变量直接指向对象实例数据的位置，然后通过对象实例数据中 存储的 到对象类型数据的指针，访问对象类型数据
* 两种方式各自的优势
  * 句柄：当需要移动对象实例数据时，不需要改变reference本身的值。
  * 指针：直接访问对象实例数据，少一次指针定位的时间开销，更快捷



#### 2.4 实战：OutOfMemoryError异常



##### 2.4.1 Java堆溢出

```java
package learnJVM;

import java.util.List;
import java.util.ArrayList;


/**
 * 代码清单2-3
 * 创建过多对象导致Java堆溢出
 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */

public class HeapOOM {
    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}

```

##### 2.4.2 虚拟机栈和本地方法栈溢出

* 同时调用过多方法导致Java虚拟机栈溢出

  ```java
  package learnJVM;
  
  /**
   * 代码清单2-4
   * 调用过多方法导致Java虚拟机栈溢出
   * VM Args：-Xss128k
   * @author zzm
   */
  
  public class JavaVMStackSOF {
  
      private int stackLength = 1;
  
      public void stackLeak() {
          stackLength++;
          stackLeak();
      }
  
      public static void main(String[] args) throws Throwable {
          JavaVMStackSOF oom = new JavaVMStackSOF();
          try {
              oom.stackLeak();
          } catch (Throwable e) {
              System.out.println("stack length:" + oom.stackLength);
              throw e;
          }
      }
  }
  ```

  



* 创建过多线程导致内存溢出

  在我下载的Oracle的JDK中，报错为**无法再创建新的本地方法**

  而书中使用的HotSpot虚拟机没有本地方法区和java虚拟机的区别

  ```java
  package learnJVM;
  
  /**
   * 代码清单2-5
   * 创建过多线程导致内存溢出
   * 在我下载的Oracle的JDK中，报错为本地方法栈溢出，而书中使用的HotSpot虚拟机没有本地方法区和java虚拟机的区别
   * VM Args：-Xss2M （这时候不妨设大些）
   * @author zzm
   */
  public class JavaVMStackOOM {
  
      private void dontStop() {
          while (true) {
          }
      }
  
      public void stackLeakByThread() {
          while (true) {
              Thread thread = new Thread(new Runnable() {
                  @Override
                  public void run() {
                      dontStop();
                  }
              });
              thread.start();
          }
      }
  
      public static void main(String[] args) throws Throwable {
          JavaVMStackOOM oom = new JavaVMStackOOM();
          oom.stackLeakByThread();
      }
  }
  ```

##### 2.4.3 方法区和运行时常量池溢出

* 方法区的内存溢出（由于创建了大量的动态类）

  ```java
  /**
   * VM Args： -XX:PermSize=10M -XX:MaxPermSize=10M
   * @author zzm
   */
  public class JavaMethodAreaOOM {
  
  	public static void main(String[] args) {
  		while (true) {
  			Enhancer enhancer = new Enhancer();
  			enhancer.setSuperclass(OOMObject.class);
  			enhancer.setUseCache(false);
  			enhancer.setCallback(new MethodInterceptor() {
  				public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
  					return proxy.invokeSuper(obj, args);
  				}
  			});
  			enhancer.create();
  		}
  	}
  
  	static class OOMObject {
  
  	}
  }
  ```

  

* 下面的两段代码体现了JDK6和JDK7中关于管理内存的一些区别：**永久代（方法区）的概念**

* String.intern()方法：

  * jdk1.6：若字符串常量池中已经包含该String对象，则返回常量池中对应的对象；否则将该字符串的深拷贝添加到常量池中，并返回常量池中该字符串对象的引用
  * jdk1.7：和jdk1.6的区别在于：若第一次遇到该String对象，不再拷贝，而是记录一下该引用

* 2-6的代码在jdk1.6中会抛出OOM异常，而在jdk1.7中不会

  ```java
  /**
   * 代码清单2-6
   * 创建过多字面量，导致运行时常量池内存溢出
   * VM Args：-XX:PermSize=10M -XX:MaxPermSize=10M
   * @author zzm
   */
  public class RuntimeConstantPoolOOM {
  
      public static void main(String[] args) {
          // 使用List保持着常量池引用，避免Full GC回收常量池行为
          List<String> list = new ArrayList<String>();
          // 10MB的PermSize在integer范围内足够产生OOM了
          int i = 0;
          while (true) {
              list.add(String.valueOf(i++).intern());
          }
      }
  }
  ```

* 在jdk1.6中的结果是false,false

  jdk1.7中是true,false（常量池中原先已有"java"的字面量）

  ```java
  public class RuntimeConstantPoolOOM2 {
  
      public static void main(String[] args) {
          String str1 = new StringBuilder("中国").append("钓鱼岛").toString();
          System.out.println(str1.intern() == str1);
  
          String str2 = new StringBuilder("ja").append("va").toString();
          System.out.println(str2.intern() == str2);
      }
  }
  ```

##### 2.4.4 本地直接内存溢出

```java
package learnJVM;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

/**
 * 程序清单2-9
 * 创建过多直接内存导致内存溢出
 * VM Args：-Xmx20M -XX:MaxDirectMemorySize=10M
 * @author zzm
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
```

#### 2.5 本章小结

本章主要内容为 **java虚拟机中内存的划分，以及在这些区域中出现内存溢出的原因**