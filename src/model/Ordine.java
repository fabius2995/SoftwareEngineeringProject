package model;

import java.util.ArrayList;
import java.util.Date;

public class Ordine {
	private double tot;
	private String indirizzo;
	private Date data; 
	private String username;
	private String prodotti;
	private boolean stato;
	private int id;
	//ordine non deve contenere id poiche quest'ultimo dev'essere contenuto solo nel db per questioni di gestione
	public Ordine(double tot, String indirizzo, Date data, String username, ArrayList<Ricambio> prodotti, boolean stato, int id) {
		this.tot = tot;
		this.data = data;
		this.indirizzo = indirizzo;
		this.username = username;
		this.prodotti = convertiLista(prodotti);
		this.stato = stato;
		this.id = id; 
	}
	
	public Ordine() {
		super();
		this.id = 0;
		this.data = new Date();
	}
	
	private static String convertiLista(ArrayList<Ricambio> p) {
		String lista = "";
		for (Ricambio r: p) {
			lista.concat(r.toString());
			lista.concat(" ");
		}
		return lista;
	}

	public double getTot() {
		return tot;
	}

	public void setTot(double tot) {
		this.tot = tot;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProdotti() {
		return prodotti;
	}

	public void setProdotti(String prodotti) {
		this.prodotti = prodotti;
	}
	
	public void setProdotti(ArrayList<Ricambio> prodotti) {
		
		this.prodotti = convertiLista(prodotti);
	}
	
	public void evadiOrdine() {
		this.stato = true;
	}
	
	public boolean getStato() {
		return this.stato;
	}

	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isValid() {
		boolean res = true;
		
		if (!username.matches("[A-Za-z0-9-_.%$&;]{6,32}"))
			res = false;
		
		if (prodotti.equals("") || tot <= 0)
			res = false;
		
		return res;
	}
}
