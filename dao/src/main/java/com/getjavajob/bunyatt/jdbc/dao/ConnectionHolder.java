package com.getjavajob.bunyatt.jdbc.dao;

import java.sql.Connection;

/**
 * Created by Nat on 22.11.2015.
 */
public class ConnectionHolder {

    private Connection connection;
    private Integer counter;

    public ConnectionHolder(Connection connection) {
        this.connection = connection;
        this.counter = 0;
    }

    public Connection getConnection() {
        return connection;
    }

    public Integer getCounter() {
        return counter;
    }

    public void incrementCounter() {
        counter++;
    }

    public void decrementCounter() {
        counter--;
    }
}
