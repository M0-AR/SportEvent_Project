package com.example.sportevent.utilities;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class StopWatchWorker extends Worker {

    public static final String WORK_NUMBER_KEY = "number";

    public StopWatchWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }


    @NonNull
    @Override
    public Result doWork() {
        Data inputData = getInputData();
        int number = inputData.getInt(WORK_NUMBER_KEY, -1);
        if (number != -1) {
            for (int i = 0; i < number; i++) {
                SampleData.countOneByOneSecond+=10;
                Log.d("StopWatchWorker", "doWork: " + SampleData.countOneByOneSecond);
                try {
                    Thread.sleep(10000); // 10 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return Result.failure();
                }
            }
        }
        return Result.success();
    }
}
