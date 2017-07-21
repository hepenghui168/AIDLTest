package aidl.admin.aidldemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import aidl.admin.aidldemo.diy.IRemote;
import aidl.admin.aidldemo.diy.Stub;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private IRemoteService remoteService;
    private IRemote remote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setClass(this, RemoteService.class);
        bindService(intent, connection, Service.BIND_AUTO_CREATE);//绑定服务
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            remote = Stub.asInterface(service);
            Log.i(TAG, "Client pid="+Process.myPid());
            try {
                remote.getPid();
                Log.i(TAG, "RemoteService pid="+remote.getPid());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            remote = null;

        }
    };
    /*private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            remoteService = IRemoteService.Stub.asInterface(service);//获取AIDL的接口实现引用
            try {
                Process.myPid();
                remoteService.getPid();
                Log.i(TAG, "Client pid="+Process.myPid());
                Log.i(TAG, "RemoteService pid="+remoteService.getPid());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            remoteService = null;

        }
    };*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
