package model;

import java.util.Date;

public class Preventivo {

	private int id_auto;
	private String username;
	private Date data;
	private double prezzo;
	
	public Preventivo(int id_auto,  String userClient , Date data, double prezzo) {
		super();
		this.prezzo = prezzo;
		this.id_auto = id_auto;
		this.username = userClient;
		this.data = data;
	}
	
	public Preventivo() {
		this.data = new Date();
	}
	
	public double getPrezzo() {
		return this.prezzo;
	}
	
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	public int getId_auto() {
		return id_auto;
	}
	
	public void setId_auto(int id_auto) {
		this.id_auto = id_auto;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUserClient(String userClient) {
		this.username = userClient;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	/*
	 * Serve davvero all'interno del DB?
	 */
}
