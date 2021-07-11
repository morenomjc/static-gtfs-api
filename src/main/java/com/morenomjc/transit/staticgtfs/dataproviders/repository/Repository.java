package com.morenomjc.transit.staticgtfs.dataproviders.repository;

public interface Repository<T> {
    void save(T data);
}
