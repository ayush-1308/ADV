package com.dao;

import java.util.Iterator;

public interface UserDao {
	public boolean createUser(User userOBJ);
	public Iterator<User> listAll();
}
