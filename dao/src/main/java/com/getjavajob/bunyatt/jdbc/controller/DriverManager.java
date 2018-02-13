package com.getjavajob.bunyatt.jdbc.controller;

import java.sql.Connection;

public class DriverManager {
    //todo Менеджер который отдает определенный коннекшн определенному треду.
    //todo Он уже реализован в sql пакете. В @TransactionManagerImpl уже заюзан.

    public static Connection getConnection(String url){
        Long key = Thread.currentThread().getId();
        //todo отдавать определенный коннекшн определенному потоку.
        //todo Тобишь сделать так, что бы был пуш коннекшнов. Узнать как это запилить.
        //todo ConnectionHolder (пул коннешнов)
        //todo
        //todo В этом классе реализовать логику, что бы класть в коннекшнХолдер новый коннекшн, если их меньше
        //todo Какого-нибудь числа (например 100)
        //todo
        return DriverManager.getConnection(url);
    }

    public static void remove(Connection conn) {

    }
}
