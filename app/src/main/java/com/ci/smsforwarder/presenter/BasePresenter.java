package com.ci.smsforwarder.presenter;

import java.lang.ref.WeakReference;

public class BasePresenter<V> {

    private WeakReference<V> view;

    public void setView(V view) {
        this.view = new WeakReference(view);
    }

    public V getView() {
        return view.get();
    }
}