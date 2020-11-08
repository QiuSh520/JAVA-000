学习笔记

### 1、思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程？（目录7_1）

共实现了7种方式：1.提交任务到线程池(submit)，在主线程中通过Future.get()方法获取执行结果；2.通过FutureTask包装要执行的任务，将FutureTask作为任务传给单个线程/线程池，然后在主线程中调用FutureTask.get()获取执行结果；3.在主线程中构造permits为0的Semaphore并semaphore.acquire()，在子线程/线程池中则执行完成方法后，将执行结果放入共享对象中并semaphore.release()；4.在主线程中构造count为1的CountDownLatch并countDownLatch.await()，在子线程/线程池中则执行完成方法后，将执行结果放入共享对象中并countDownLatch.countDown()；5.通过synchronized+共享对象+wait()/notifyAll()，实现主线程和子线程之间结果的同步；6.通过Lock+Condition+共享对象，实现主线程和子线程之间结果的同步；7.在主线程中构造parties为2的CyclicBarrier并cyclicBarrier.await()，在子线程/线程池中则执行完成方法后，将执行结果放入共享对象中并cyclicBarrier.await()。

### 2、把多线程和并发相关知识带你梳理一遍，画一个脑图，截图上传到 Github 上。（目录8_1）

![](./8_1/Java多线程和并发.png)