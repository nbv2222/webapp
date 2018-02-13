package com.getjavajob.bunyatt.jdbc.controller;

import java.util.concurrent.Callable;

public interface TransactionManager {

    public <T> T doTransaction(Callable<T> unitOfWork) throws Exception;
}
