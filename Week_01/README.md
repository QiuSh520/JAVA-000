学习笔记

### 1.第一次课第一题

通过javap反编译后的代码如下：

构造函数public com.example.demo.Hello():

```java
public com.example.demo.Hello();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=1, args_size=1
         0: aload_0                           //将this引用压入到操作数栈
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V 调用对象的<init>方法
         4: aload_0	                          //将this引用压入到操作数栈
         5: dconst_0						  //将double类型的0值压入到操作数栈
         6: putfield      #2                  // Field oddSum:D 给属性oddSum赋值
         9: return
      LineNumberTable:
        line 3: 0
        line 6: 4
```



普通函数public double calcOddAvg(int[] arr)：

```java
  public double calcOddAvg(int[]);
    descriptor: ([I)D
    flags: ACC_PUBLIC
    Code:
      stack=5, locals=6, args_size=2
         0: aload_1							 //0-1是把局部变量表1号槽的引用复制给2号槽吗？即复制数组引用，为什么要这么做呢？
         1: astore_2
         2: aload_2							  
         3: arraylength						  
         4: istore_3						 
         5: iconst_0						 
         6: istore        4					 //0-6局部变量i和数组长度保存在局部变量表的4和3号槽
         8: iload         4					 
        10: iload_3							 
        11: if_icmpge     56				 //8-11比较局部变量i和数组长度大小，大于或等于则跳56
        14: aload_2							
        15: iload         4					
        17: iaload							//14-17将int型数组指定索引的值推送至栈顶
        18: istore        5					//18-25，获取当前循环的数组元素值，判断是否对2求余为1，不等于则跳转50
        20: iload         5
        22: iconst_2
        23: irem
        24: iconst_1
        25: if_icmpne     50
        28: aload_0								//28-35对oddCount的值进行加一操作
        29: dup
        30: getfield      #3                  // Field oddCount:I
        33: iconst_1
        34: iadd
        35: putfield      #3                  // Field oddCount:I
        38: aload_0								//38-47将当前循环的数组元素值累加到oddSum字段
        39: dup
        40: getfield      #2                  // Field oddSum:D
        43: iload         5
        45: i2d
        46: dadd
        47: putfield      #2                  // Field oddSum:D
        50: iinc          4, 1					//局部变量i自增1
        53: goto          8						//跳转到for循环开头，获取局部变量i的值
        56: aload_0
        57: getfield      #3                  // Field oddCount:I
        60: ifne          68					//56-60判断oddCount的值是否为0，不为0则跳转68
        63: aload_0
        64: getfield      #2                  // Field oddSum:D
        67: dreturn								//63-67从当前方法返回oddSum的值
        68: aload_0
        69: getfield      #2                  // Field oddSum:D
        72: aload_0
        73: getfield      #3                  // Field oddCount:I
        76: i2d
        77: ddiv
        78: dreturn								//68-78获取oddSum和oddCount的值并相除返回值
```

### 第一次课第二题

在1_2目录下执行如下命令即可：

```
java -cp .;  HelloClassLoader
```

### 第一次课第三题

![内存参数关系](.\1_3\内存参数关系.png)