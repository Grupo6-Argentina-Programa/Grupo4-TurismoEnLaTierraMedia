package DAO;

import java.util.List;

public interface GenericDAO<T> {

	public int insert(T t);
	public int update(T t);
	public int delete(T t);
	
	public int findMaxID();
	public  List<T> findAll(); 
	public int countAll();
}
