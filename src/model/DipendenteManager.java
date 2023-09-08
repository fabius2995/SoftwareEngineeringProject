package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DipendenteManager implements Model<Dipendente> {
	
	private static final String TABELLA = "Dipendenti";
	@Override
	public Dipendente doRetrieveByKey(String key) throws SQLException {
		Connection connection = null;
		PreparedStatement stat = null;
		Dipendente d = null;
		String sql = "SELECT * FROM " + TABELLA + " d WHERE d.uname = ? OR d.email = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			stat = connection.prepareStatement(sql);
			stat.setString(1, key);
			stat.setString(2, key);
			ResultSet res = stat.executeQuery();
		
			if (res.next()) {
				d = new Dipendente();
				
				d.setNome(res.getString("nome"));
				d.setCognome(res.getString("cognome"));
				d.setPassword(res.getString("pass"));
				d.setRuolo(res.getString("ruolo"));
				d.setUsername(res.getString("uname"));
				d.setEmail(res.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stat != null)
					stat.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return d;
	}

	@Override
	public ArrayList<Dipendente> doRetrieveAll(String key) throws SQLException {
		ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();
		Connection conn = null;
		PreparedStatement stat = null;
		String sql = "SELECT * FROM " + TABELLA;
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stat = conn.prepareStatement(sql);
			ResultSet res = stat.executeQuery();
			
			while (res.next()) {
				Dipendente d = new Dipendente();
				
				d.setNome(res.getString("nome"));
				d.setCognome(res.getString("cognome"));
				d.setPassword(res.getString("pass"));
				d.setRuolo(res.getString("ruolo"));
				d.setUsername(res.getString("uname"));
				
				dipendenti.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stat != null)
					stat.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		
		return dipendenti;
	
	}

	@Override
	public int doSave(Dipendente obj) throws SQLException {
		Connection conn = null;
		PreparedStatement stat = null;
		int res = 0;
		if (!obj.isValid()) {
			return 0;
		}
		String sql = "insert into " + TABELLA + "  (uname, email, pass, nome, cognome, stipendio, ruolo) values (? ,? ,? ,? ,? , ?, ?)";
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stat = conn.prepareStatement(sql);
			stat.setString(1, obj.getUsername());
			stat.setString(2, obj.getEmail());
			stat.setString(3, obj.getPassword());
			stat.setString(4, obj.getNome());
			stat.setString(5, obj.getCognome());
			stat.setDouble(6, obj.getStipendio());
			stat.setString(7, obj.getRuolo());
			res = stat.executeUpdate();
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stat != null)
					stat.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
			
		}
		return res;
	}

	@Override
	public void doDelete(Dipendente obj) throws SQLException {
		Connection conn = null;
		PreparedStatement stat = null;
		String sql = "DELETE FROM " + TABELLA + "WHERE uname = ? ";

		try {
			conn = DriverManagerConnectionPool.getConnection();
			stat = conn.prepareStatement(sql);
			stat.setString(1, obj.getUsername());
			
			stat.execute();
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally  {
			try {
				if (stat != null)
					stat.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		
	}

	@Override
	public int doUpdate(Dipendente obj) throws SQLException {
		/*
		 * questo metodo aggiorna la colonna stipendio e la colonna pass di Dipendente
		 */
		Connection conn = null;
		PreparedStatement stat = null;
		int res = 0;
		String sql = "UPDATE " + TABELLA + " d SET d.stipendio = ? , d.pass = ? WHERE d.uname = ?";
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stat = conn.prepareStatement(sql);
			stat.setDouble(1, obj.getStipendio());
			stat.setString(2, obj.getPassword());
			stat.setString(3, obj.getUsername());
			
			res = stat.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stat != null)
					stat.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return res;
	}

	public int setAdministrator(String key) throws SQLException {
		/*
		 * questo metodo riceve la chiave primaria e rende amministratore l'utente selezionato
		 */
		Connection conn = null;
		PreparedStatement stat = null;
		int res = 0;
		String sql = "UPDATE " + TABELLA + " d SET d.img = ? WHERE d.uname = ?";
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stat = conn.prepareStatement(sql);
			stat.setString(1, "admin");
			stat.setString(2, key);
			
			res = stat.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stat != null)
					stat.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return res;
	}

}
