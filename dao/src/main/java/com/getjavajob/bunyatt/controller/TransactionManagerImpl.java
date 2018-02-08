package com.getjavajob.bunyatt.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class TransactionManagerImpl {
    private static final String JDBC_URL = "someUrl";

    //todo Это обертка, для коллейблов, который юзают ДАОшки.
    //todo Здесь должно быть реализованно:
    //todo Драйверменеджер создает коннекшны, кладет их в пул коннешкнов
    //todo Из этого пула драйвер возвращает тот коннекшн, который юзается для данного треда
    //todo (Либо сделать так, что бы выдавался менее занятой коннешн)
    //todo И вообще я пилю велосипед блять :chtopodelat:
    //todo
    public <T> T doTransaction(Callable<T> unitOfWork) throws SQLException {
        Connection conn = DriverManager.getConnection(JDBC_URL);
        conn.setAutoCommit(false);
        try {
            T transactionResult = unitOfWork.call();
            conn.commit();
            return transactionResult;
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            conn.close();
            DriverManager.remove(conn);
        }
        return null; //wtf
    }
}
