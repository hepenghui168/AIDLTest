package aidl.admin.aidldemo;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import aidl.admin.aidldemo.diy.Stub;

/**
 * Created by admin on 2017/7/21.
 */

public class RemoteService extends Service {

    public RemoteService() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;//暴露给客户端
    }

    //实现AIDL接口
    /*private IRemoteService.Stub binder = new IRemoteService.Stub() {

        @Override
        public int getPid() throws RemoteException {
            return Process.myPid();
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };*/
    private final Stub binder = new Stub() {
        @Override
        public int getPid() throws RemoteException {
            return Process.myPid();
        }
    };

    public static String getCurrentProcessName(Context context) {
        int pid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : activityManager.getRunningAppProcesses()) {
            if (appProcessInfo.pid == pid) {
                return appProcessInfo.processName;
            }
        }
        return null;
    }
}
