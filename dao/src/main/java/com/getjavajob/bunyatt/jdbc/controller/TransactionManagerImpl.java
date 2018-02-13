package com.getjavajob.bunyatt.jdbc.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class TransactionManagerImpl implements TransactionManager{
    private static final String JDBC_URL = "someUrl";
    private static ThreadLocal<Connection> connectionHolder; //todo WHAT IS THIS

    //todo Это обертка, для коллейблов, который юзают ДАОшки.
    //todo Здесь должно быть реализованно:
    //todo Драйверменеджер создает коннекшны, кладет их в пул коннешкнов
    //todo Из этого пула драйвер возвращает тот коннекшн, который юзается для данного треда
    //todo (Либо сделать так, что бы выдавался менее занятой коннешн)
    //todo И вообще я пилю велосипед блять :chtopodelat:
    //todo А даже если не велосипед, то по любому надо юзать какую-то либку
    //todo
    public <T> T doTransaction(Callable<T> unitOfWork) throws Exception {
        Connection conn = DriverManager.getConnection(JDBC_URL);

        conn.setAutoCommit(false);
        connectionHolder.set(conn);
        try {
            T transactionResult = unitOfWork.call();
            conn.commit();
            return transactionResult;
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
            connectionHolder.remove();
        }
    }
}
