package aidl.admin.aidldemo.diy;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by admin on 2017/7/21.
 */

public abstract class Stub extends Binder implements IRemote {

    public Stub() {
        this.attachInterface(this, DESCRIPTOR);
    }

    public static IRemote asInterface(IBinder obj) {
        if (obj == null)
            return null;
        IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (iin != null && iin instanceof IRemote) {
            return (IRemote) iin;
        }
        return new Proxy(obj);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION:
            {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_getPid:
            {
                data.enforceInterface(DESCRIPTOR);
                int _result = this.getPid();
                reply.writeNoException();
                reply.writeInt(_result);
                return true;
            }
        }
        return super.onTransact(code, data, reply, flags);

    }
}
