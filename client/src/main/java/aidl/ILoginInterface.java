/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package aidl;
// Declare any non-default types here with import statements


import com.example.client._base.LogUtil;

public interface ILoginInterface extends android.os.IInterface {

    /**
     * Local-side IPC implementation stub class.
     */
    public static abstract class Stub extends android.os.Binder implements ILoginInterface {
        private static final String DESCRIPTOR = "com.example.aidl.ILoginInterface";
        static final int TRANSACTION_login = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);

        /**
         * Construct the stub at attach it to the interface.
         */
        public Stub() {
            LogUtil.d("xxx", "Stub()");
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast an IBinder object into an com.example.aidl.ILoginInterface interface,
         * generating a proxy if needed.
         */
        public static ILoginInterface asInterface(android.os.IBinder obj) {
            LogUtil.d("xxx", "Stub:asInterface");
            if ((obj == null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof ILoginInterface))) {
                return ((ILoginInterface) iin);
            }
            LogUtil.d("xxx", "Stub.Proxy()");
            return new ILoginInterface.Stub.Proxy(obj);
        }

        @Override
        public android.os.IBinder asBinder() {
            LogUtil.d("xxx", "Stub:asBinder");
            return this;
        }

        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
            LogUtil.d("xxx", "onTransact()");
            String descriptor = DESCRIPTOR;
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(descriptor);
                    return true;
                }
                case TRANSACTION_login: {
                    LogUtil.d("xxx", "TRANSACTION_login");
                    data.enforceInterface(descriptor);
                    int _result = this.login();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                }
                default: {
                    return super.onTransact(code, data, reply, flags);
                }
            }
        }

        private static class Proxy implements ILoginInterface {
            private android.os.IBinder mRemote;

            Proxy(android.os.IBinder remote) {
                LogUtil.d("xxx", "Stub.Proxy()");
                mRemote = remote;
            }

            @Override
            public android.os.IBinder asBinder() {
                LogUtil.d("xxx", "Proxy:asBinder");
                return mRemote;
            }

            public String getInterfaceDescriptor() {
                LogUtil.d("xxx", "Proxy:getInterfaceDescriptor");
                return DESCRIPTOR;
            }

            @Override
            public int login() throws android.os.RemoteException {
                LogUtil.d("xxx", "Proxy:login()");
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                int _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    LogUtil.d("xxx", "Proxy:mRemote.transact()");
                    boolean _status = mRemote.transact(ILoginInterface.Stub.TRANSACTION_login, _data, _reply, 0);
                    LogUtil.d("xxx", "Proxy:mRemote.transact.result:");
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
        }


    }

    public int login() throws android.os.RemoteException;
}
