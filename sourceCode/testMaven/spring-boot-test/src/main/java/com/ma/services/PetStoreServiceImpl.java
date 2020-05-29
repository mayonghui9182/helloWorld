package com.ma.services;

import com.ma.dao.jpa.JpaAccountDao;
import com.ma.dao.jpa.JpaItemDao;

public class PetStoreServiceImpl {
    private JpaAccountDao accountDao;
    private JpaItemDao itemDao;

    public void setAccountDao(JpaAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setItemDao(JpaItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public PetStoreServiceImpl() {
    }
}
