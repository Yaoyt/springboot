package com.beta.sb.thread;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by yaoyt on 2019-03-04.
 * jdk 8 之后提供的 completableFuture.
 * @author yaoyt
 */
public class CompletableFutureTest {

    // completableFuture 调用 complete方法， 表示此异步任务已经完成，并将结果设置到 completableFuture对象中。
    // 其他地方的 completableFuture 即可 调用 get 方法，将结果拿到
    @Test
    public void test1() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        new Thread(()->{
            System.out.println("异步线程执行");
            completableFuture.complete(Thread.currentThread().getName());
            System.out.println("异步线程执行完成");
        }).start();
        //do something else;
        try {
            Thread.sleep(1000L);
            System.out.println(123123123);
            System.out.println(completableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    // 当 completableFuture 所在子线程发生异常时
    @Test
    public void test2() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        new Thread(()->{
            System.out.println("异步线程执行");
            // 如果completableFuture 的异步线程中出现了异常， 必须手动通知住线程，否则 下方的 completableFuture.get会被阻塞。
            try {
                System.out.println(1 / 0);
            } catch (Exception e) {
                completableFuture.completeExceptionally(new RuntimeException("error"));
            }
            System.out.println("异步线程执行完成");
        }).start();
        //do something else;
        try {
            Thread.sleep(1000L);
            System.out.println(123123123);
            System.out.println(completableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    // 使用JDK提供的工厂方法创建CompletableFuture
    @Test
    public void test3(){
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println("异步线程执行开始");
            return Thread.currentThread().getName();
        });
        // do something else
        try {
            Thread.sleep(1000L);
            System.out.println(12312312);
            System.out.println(completableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    //public CompletableFuture<T> 	whenComplete(BiConsumer<? super T,? super Throwable> action)
    //public CompletableFuture<T> 	whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
    //public CompletableFuture<T> 	whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
    //public CompletableFuture<T>   exceptionally(Function<Throwable,? extends T> fn)
    // 上面四种方法都返回了 CompletableFuture, 当我们的Action执行完毕的时候， future 返回的值和我们原始的CompletableFuture 的值是一样的
    // whenComplete 方法中不允许使用返回值， 即时在whenComplete中改变了返回值，也无效。主线程获取到的仍然是改变之前的值。
    // whenComplete 方法的意义是 声明了一个在线程执行结束时调用的回调方法， 不影响也不能影响原异步线程的返回值。
    // 带有 async 的 whenComplete 方法会在新的线程池中执行， 没有 async 的 whenComplete 方法会在主线程中执行。
    // 没看懂。 exceptionally方法返回一个新的CompletableFuture，当原始的CompletableFuture抛出异常的时候，就会触发这个CompletableFuture的计算，调用function计算值，否则如果原始的CompletableFuture正常计算完后，这个新的CompletableFuture也计算完成，它的值和原始的CompletableFuture的计算的值相同。也就是这个exceptionally方法用来处理异常的情况。
    @Test
    public void test4() throws Exception{
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return 1;
        });
        Future<Integer> future = completableFuture.whenComplete((v,e)->{
            System.out.println(Thread.currentThread().getName());
            v = v+1;
            System.out.println(v);
        });
        System.out.println("Main:" + Thread.currentThread().getName());
        System.out.println(future.get());

    }

    // 计算结果的转换
    //public <U> CompletableFuture<U> 	thenApply(Function<? super T,? extends U> fn)
    //public <U> CompletableFuture<U> 	thenApplyAsync(Function<? super T,? extends U> fn)
    //public <U> CompletableFuture<U> 	thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
    // 当异步线程执行完毕后，改变 执行结果
    @Test
    public void test5() throws Exception{
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return 10;
        });
        CompletableFuture<String> f = future.thenApply(i -> i + 1).thenApply(i -> String.valueOf(i));
        System.out.println(f.get());
    }

    // 结算结果完成时消费
    //public CompletableFuture<Void> 	thenAccept(Consumer<? super T> action)
    //public CompletableFuture<Void> 	thenAcceptAsync(Consumer<? super T> action)
    //public CompletableFuture<Void> 	thenAcceptAsync(Consumer<? super T> action, Executor executor)
    //对结果进行消费而不会返回任何结果的方法
    @Test
    public void test6() throws Exception{
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 10;
        });
        future.thenAccept(System.out::println);
        System.out.println(future.get());
    }

}
