package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AutomobileManager implements Model<Automobile> {

  private static final String TABELLA = "auto_nuove";

	@Override
	public Automobile doRetrieveByKey(String key) throws SQLException {
		Connection conn = null;
		PreparedStatement stat = null;
		if (!key.equals("") || key == null || key.equals("0"))
			return null;
		String sql = "SELECT * FROM " + TABELLA + " a WHERE a.id = ?";
		Automobile a = null;
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stat = conn.prepareStatement(sql);
			stat.setInt(1, Integer.parseInt(key));
			ResultSet res = stat.executeQuery();
			if (res.next()) {
				a = new Automobile();
				a.setId(res.getInt("id"));
				a.setMarca(res.getString("Marca"));
				a.setModello(res.getString("Modello"));
				a.setPrezzo(res.getDouble("Prezzo"));
				a.setImg(res.getString("img"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally  {
			try {
				if (stat != null) 
					stat.close();
			} 
			finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
				
		return a;
	}
	
	@Override
	public ArrayList<Automobile> doRetrieveAll(String key) throws SQLException {
		//key sempre ugule null
		
		Connection conn = null;
		PreparedStatement stat = null;
		String sql = "SELECT * FROM " + TABELLA;
		ArrayList<Automobile> automobili = new ArrayList<Automobile>();
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stat = conn.prepareStatement(sql);
			ResultSet res = stat.executeQuery();
			while (res.next()) {
				Automobile a = new Automobile();
				a.setId(res.getInt("id"));
				a.setMarca(res.getString("marca"));
				a.setModello(res.getString("modello"));
				a.setPrezzo(res.getDouble("prezzo"));
				a.setImg(res.getString("img"));
				automobili.add(a);
			}
		} catch  (SQLException e) {
			e.printStackTrace();
			return null;
		} finally  {
			try {
				if (stat != null)
					stat.close();
			} finally  {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
	
		return automobili;
	}

	@Override
	public int doSave(Automobile obj) throws SQLException {
		Connection conn = null;
		PreparedStatement stat = null;
		int res = 0;
		if (!obj.isValid()) {
			return 0;
		}
		String sql = "insert into " + TABELLA + "  (marca, modello, img, prezzo) values (? ,? ,? ,? )";
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stat = conn.prepareStatement(sql);
			stat.setString(1, obj.getMarca());
			stat.setString(2, obj.getModello());
			stat.setString(3, obj.getImg());
			stat.setDouble(4, obj.getPrezzo());
			
			res = stat.executeUpdate();
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally  {
			try {
				if (stat != null)
					stat.close();
			} finally  {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
			
		}
		return res;
	}

	@Override
	public void doDelete(Automobile obj) throws SQLException {
		Connection conn = null;
		PreparedStatement stat = null;
		String sql = "DELETE FROM " + TABELLA + "WHERE id = ? ";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stat = conn.prepareStatement(sql);
			stat.setInt(1, obj.getId());
			
			stat.execute();
			
			conn.commit();		
		} catch ( SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				if (stat != null)
					stat.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
	}

	@Override
	public int doUpdate(Automobile obj) throws SQLException {
		/*
		 * Questo metodo aggiorna solo il prezzo ed il path dell'immagine dell'automobile
		 * gli altri dati non necessitano di esser aggiornati
		 */
		Connection conn = null;
		PreparedStatement stat = null;
		int res = 0;
		String sql = "UPDATE " + TABELLA + " a SET a.prezzo = ? , a.img = ? WHERE a.id = ?";
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stat = conn.prepareStatement(sql);
			stat.setDouble(1, obj.getPrezzo());
			stat.setString(2, obj.getImg());
			stat.setInt(3, obj.getId());
			
			res = stat.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stat != null)
					stat.close();
			} finally  {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return res;
	}

}
