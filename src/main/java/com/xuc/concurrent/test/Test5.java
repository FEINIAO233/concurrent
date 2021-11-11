package com.xuc.concurrent.test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuchang
 * @date 2021/11/11
 */
public class Test5 {
    public static void main(String[] args) {
        Account accountCas = new AccountCas(10000);
        Account.test(accountCas);
    }
}

class AccountCas implements Account {
    private AtomicInteger balance;

    public AccountCas(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public int get() {
        return this.balance.get();
    }

    @Override
    public void set(int amount) {
        while (true) {
            int prev = this.balance.get();
            int next = prev - amount;
            if (this.balance.compareAndSet(prev, next)) {
                break;
            }
        }
    }
}

interface Account {
    int get();

    void set(int amount);

    static void test(Account account) {
        long t1 = System.currentTimeMillis();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            threads.add(new Thread(() -> account.set(10)));
        }
        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(account.get() + ":" + (System.currentTimeMillis() - t1) + "ms");
    }
}
