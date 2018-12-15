package com.kowa.app.utils;

public class ChainShell<T> {

    private T insatance;

    public ChainShell(T insatance) {
        this.insatance = insatance;
    }

    public ChainShell<T> with(Chain<T> chain){
        chain.event(insatance);
        return this;
    }

    public void done(Chain<T> chain){
        chain.event(insatance);
    }

    public T get(){
        return insatance;
    }

    public interface Chain<T>{
        void event(T self);
    }
}
