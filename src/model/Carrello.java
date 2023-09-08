package model;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Carrello {

	private ArrayList<Ricambio> lista;
	private double totale;

	public Carrello() {
		this.lista = new ArrayList<>();
		this.totale = 0;		
	}
	
	public void addRicambio(Ricambio r) {
		if (this.lista.contains(r)) {
			for (Ricambio r2: lista) {
				if (r2.equals(r))
					r2.incrementQnt();
			}
		} else {
			r.setQuantità(1);
			lista.add(r);
		}
		this.totale += r.getPrezzo();
	}
	
	public void removeRicambio(Ricambio r) {
		if (lista.contains(r)) {
			totale -= r.getPrezzo();
			for (Ricambio r2: lista) {
				if (r2.equals(r)) {
					if (r2.getQuantità() <= 1) {
						lista.remove(r2);
					} else {
						r2.setQuantità(r2.getQuantità() - 1);
					}
				}
			}
		}
	}
	
	public double getTotale() {
		DecimalFormat df = new DecimalFormat("#.##");
		return Double.parseDouble(df.format(this.totale).replace("," , "."));
	}
	
	public ArrayList<Ricambio> getList() {
		return this.lista;
	}
	
	public boolean contains(Ricambio r) {
		return this.lista.contains(r);
	}
	
	public void clearCart() {
		this.lista.clear();
	}
	
	public boolean isEmpty() {
		return this.lista.size() == 0;
	}
}