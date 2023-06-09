package com.kandoka.juc.completablefuture;

import java.util.concurrent.*;

/**
 * @Description TODO
 * @Author kandoka
 * @Date 2023/6/7 11:04
 */
public class DemoCompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

//        CompletableFuture<Void> c1 = CompletableFuture.runAsync(() -> {
//            System.out.println(Thread.currentThread().getName() + " start");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + " end");
//        });
//
//        System.out.println(c1.get());

        CompletableFuture<Integer> c2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                Thread.sleep(3000);
//                int i = 1/0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end");
            return result;
        }, pool).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("has completed calculation, then update database");
            }
        }).exceptionally(e -> {
            System.out.println("error: " + e.getMessage() + "\nreason: " + e.getCause());
            return null;
        });
        System.out.println("main thread handle other tasks");
        System.out.println(c2.get());

        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "PlayerA";
        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "PlayerB";
        });

        CompletableFuture<String> result = f1.applyToEither(f2, f -> {
            return f + " is the winner";
        });

        System.out.println(result.join());

        pool.shutdown();
    }
}

class EShop {

    private String shopName;
    private Double price;

    public EShop(String shopName) {
        this.shopName = shopName;
    }

    public Double getPrice() {
        return ThreadLocalRandom.current().nextDouble(200);
    }

    public String getShopName() {
        return shopName;
    }
}
