package com.getjavajob.bunyatt.hibernate.dao;

import com.getjavajob.bunyatt.entities.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.util.Date;

//todo Не должно быть коммит транзакшн.
//todo Транзакшны должны быть ограничены контроллером
//todo
//todo
public class AccountDao {
    public static void main(String[] args) throws ParseException {
        Account account = new Account();
        account.setAge(5);
        account.setBirthDate(new Date());
//        account.setBirthDate(new SimpleDateFormat("yyyy.MM.dd").parse("1111.11.11"));
        account.setName("Name");

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(account);
        manager.getTransaction().commit();
        manager.close();
        factory.close();
    }
}
