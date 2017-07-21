package aidl.admin.aidldemo.diy;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

/**
 * Created by admin on 2017/7/21.
 */

public interface IRemote extends IInterface {
    public static final String DESCRIPTOR = "aidl.admin.aidldemo.diy.IRemote";

    public static final int TRANSACTION_getPid = (IBinder.FIRST_CALL_TRANSACTION + 0);

    public int getPid() throws RemoteException;
}
