package aidl.admin.aidldemo.diy;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by admin on 2017/7/21.
 */

public class Proxy implements IRemote {
    private IBinder mRemote;


    Proxy(IBinder remote) {
        mRemote = remote;
    }

    public String getInterfaceDescriptor() {
        return DESCRIPTOR;
    }

    @Override
    public int getPid() throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _replay = Parcel.obtain();
        int _result;
        try {
            _data.writeInterfaceToken(DESCRIPTOR);
            mRemote.transact(TRANSACTION_getPid, _data, _replay, 0);
            _replay.readException();
            _result = _replay.readInt();
        } finally {
            _data.recycle();
            _replay.recycle();
        }
        return _result;
    }

    @Override
    public IBinder asBinder() {
        return mRemote;
    }
}
