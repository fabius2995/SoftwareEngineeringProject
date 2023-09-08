package model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Model<T> {
	
	public T doRetrieveByKey(String key) throws SQLException;
	
	public ArrayList<T> doRetrieveAll (String key) throws SQLException;
	
	public int doSave (T obj) throws SQLException;
	
	public void doDelete(T obj) throws SQLException;
	
	public int doUpdate(T obj) throws SQLException;
	
}
