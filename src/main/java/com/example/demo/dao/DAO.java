package com.example.demo.dao;

import java.util.List;

public interface DAO<T> {
	// create
	void create(T data) throws DAOException;
	
	// read
	List<T> findAll() throws DAOException;
	List<T> findSome(Object key) throws DAOException;
	T findOne(Object key) throws DAOException;
	
	
	// update
	void update(T data)throws DAOException;
	
	// delete
	void delete(Object key) throws DAOException;
	
}
