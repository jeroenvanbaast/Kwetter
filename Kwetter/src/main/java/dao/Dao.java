/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Map;

/**
 *
 * @author Jeroen
 */
public interface Dao<T> {
      T create(T entity);
      T update(T entity);
      void remove(T entity);
      T getById(long id);
}
