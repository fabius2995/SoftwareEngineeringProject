package model;

public class Automobile {

	private int id;
	private String marca;
	private String modello;
	private double prezzo;
	private String img;

	public Automobile() {
		super();
		this.marca = "";
		this.modello = "";
		this.img = "";
		this.prezzo = 0.0;
		
	}

	public Automobile(int id, String marca, String modello, double prezzo, String img) {
		this.id = id;
		this.marca = marca;
		this.modello = modello;
		this.prezzo = prezzo;
		this.img = img;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getImg() {
		return this.img;
	}
	
	public void setImg(String img) {
		this.img = "Immagini/Auto Nuove/" + this.marca + "/" + img;
	}

	public boolean isValid() {
		return isValidMarca() && isValidModello() && isValidPrezzo();
	}
	
	private boolean isValidMarca() {
		if (marca.length() > 0 && marca.length() < 20)
			return true;
		return false;
	}
	
	private boolean isValidModello() {
		if (this.modello.length() > 0 && this.modello.length() < 20)
			return true;
		return false;
	}
	
	private boolean isValidPrezzo() {
		if (prezzo < 1000 || prezzo > 9999999)
			return false;
		
		return true;
	}	

}
