package com.meteor.meteorpuxiidea.common.network;

public interface TriConsumer<T, U, R> {
    void accept(T t, U u, R r);
}
