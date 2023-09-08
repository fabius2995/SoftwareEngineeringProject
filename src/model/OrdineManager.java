package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrdineManager implements Model<Ordine> {
	private static final String TABELLA = "Ordini";
	@Override	
	//non serve implementare questo metodo visto che a noi servira mai trovare un singolo ordine
	public Ordine doRetrieveByKey(String email) throws SQLException {
		return null;
	}

	@Override
	public ArrayList<Ordine> doRetrieveAll(String email) throws SQLException {
		Connection conn = null;
		PreparedStatement stat = null;
		ArrayList<Ordine> ordini = new ArrayList<>();
		String sql = "SELECT * FROM " + TABELLA + " WHERE username = ? ORDER BY dataOrdine DESC ";
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stat = conn.prepareStatement(sql);
			stat.setString(1, email);
			ResultSet res = stat.executeQuery();
			
			while (res.next()) {
				Ordine o = new Ordine();
				
				o.setData(res.getDate("dataAcquisto"));
				o.setProdotti(formattaStringa(res.getString("dataAcquisto")));
				o.setUsername(res.getString("username"));
				o.setTot(res.getFloat("totale"));
				o.setIndirizzo(res.getString("indirizzo"));
				
				ordini.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (stat != null) 
					stat.close();
			} 
			finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return ordini;
	}
	
	@Override
	public int doSave(Ordine o) throws SQLException {
		Connection conn = null;
		PreparedStatement stat = null;		
		String sql = "insert into " + TABELLA + " (totale, username, prodotti, indirizzo, dataAcquisto) values (? ,? ,? ,? ,?)";
		int res = 0;
		if (!o.isValid())
			return 0;
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stat = conn.prepareStatement(sql);
			stat.setDouble(1, o.getTot());
			stat.setString(2, o.getUsername());
			stat.setString(3, o.getProdotti());
			stat.setString(4, o.getIndirizzo());
			stat.setDate(5, new Date(o.getData().getTime()));
			
			res = stat.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				if (stat != null) 
					stat.close();
			} 
			finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return res;
	}

	@Override
	//non serve implementare questo metodo
	public void doDelete(Ordine key) throws SQLException {		
	}
	
    private String formattaStringa(String ordine) {
    	String result = "";
    	if (ordine.length() > 0) {
    		result += ordine.charAt(0);
    	}
    	for (int i = 1; i < ordine.length(); i++) {
    		char c = ordine.charAt(i);
    		if (Character.isDigit(c)) {
    			result += "<br/>" + c;
    		} else {
    			result += c;
    		}
    	}
    	
    	return result;	
    }
    //implementiamo questo metodo per poter evadere gli ordini
    
	@Override
	public int doUpdate(Ordine obj) throws SQLException {
		Connection conn = null;
		PreparedStatement stat = null;
		int res = 0;
		if (!obj.isValid())
			return 0;
		String sql = "update " + TABELLA + " SET stato = ? WHERE id = ?";
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stat = conn.prepareStatement(sql);
			stat.setBoolean(1, true);
			stat.setInt(2, obj.getId());
			
			res = stat.executeUpdate();
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				if (stat != null) 
					stat.close();
			} 
			finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return res;
	}
	
}
