package com.wangzhe.springboot.jdbc;

public interface JDBCService {

    public void queryById(int id);
    public void updateNameById(int id,String name);
}