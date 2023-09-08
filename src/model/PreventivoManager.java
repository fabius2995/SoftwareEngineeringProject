package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PreventivoManager implements Model<Preventivo> {
	private static final String TABELLA = "Preventivo";
	@Override
	public Preventivo doRetrieveByKey(String key) throws SQLException {
		//per ogni utente cercheremo sempre i preventivi richiesti e mai un solo preventivo
		return null;
	}

	@Override
	public ArrayList<Preventivo> doRetrieveAll(String key) throws SQLException {
		Connection conn = null;
		PreparedStatement stat = null;
		String sql = "SELECT * FROM " + TABELLA + "WHERE username = ?";
		ArrayList<Preventivo> preventivi = new ArrayList<Preventivo>();
		
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stat = conn.prepareStatement(sql);
			stat.setString(1, key);
			ResultSet res = stat.executeQuery();
			
			while (res.next()) {
				Preventivo p  = new Preventivo();
				p.setData(res.getDate("dataRichiesta"));
				p.setId_auto(res.getInt("id_auto"));
				p.setUserClient(res.getString("username"));
				p.setPrezzo(res.getDouble("prezzo"));
				
				preventivi.add(p);
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
		
		return preventivi;
	} 

	@Override
	public int doSave(Preventivo obj) throws SQLException {

		Connection conn = null;
		PreparedStatement stat = null;
		String sql = "INSERT INTO " + TABELLA + "(id_auto, username, dataRichiesta, prezzo ) values (?, ?, ?, ?)";
		int res = 0;
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stat = conn.prepareStatement(sql);
			
			stat.setInt(1, obj.getId_auto());
			stat.setString(2, obj.getUsername());
			stat.setDate(3, new Date(obj.getData().getTime()));
			stat.setDouble(4, obj.getPrezzo());
			
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
	public void doDelete(Preventivo obj) throws SQLException {
		//questo metodo possiamo evitare di implementarlo poichè non avremo bisogno di eliminare un singolo preventivp
	}

	@Override
	public int doUpdate(Preventivo obj) throws SQLException {
		//questo metodo possiamo evitare di implementarlo poichè non avremo bisogno di aggiornare i preventivi già richiesti
		return 0;
	}

}
