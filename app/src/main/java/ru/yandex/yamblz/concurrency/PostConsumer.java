package ru.yandex.yamblz.concurrency;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.CountDownLatch;

/**
 * Simple result consumer thread; non-extensible
 *
 * @author archinamon on 13/07/16.
 */

public final class PostConsumer extends Thread {

    @NonNull private final Runnable onFinish;
    @NonNull private final CountDownLatch countDownLatch;

    public PostConsumer(@NonNull Runnable onFinish,  @NonNull CountDownLatch countDownLatch) {
        this.onFinish = onFinish;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        super.run();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Handler(Looper.getMainLooper()).post(onFinish);
    }
}
