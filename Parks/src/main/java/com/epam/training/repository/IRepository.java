package com.epam.training.repository;

import java.util.List;

public interface IRepository <T> {
        boolean insert(T t);
        boolean delete(long id);
        boolean update(String name, long id);
        T read(long id);
        List <T> readAll();


    }

