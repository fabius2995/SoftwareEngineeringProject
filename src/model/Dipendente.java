package model;

public class Dipendente {
	
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String ruolo;
	private String email;
	private double stipendio;
	public Dipendente(String nome, String cognome, String username, String password, String email, double stipendio) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.ruolo = "dipendente";
		this.email = email;
		this.stipendio = stipendio;
	}
	
	public Dipendente() {}
	
	private boolean isValidPwd() {
		if (this.password.length() < 6 || this.password.length() > 32 || this.password.equals(""))
			return false;
		return true;
	}
	
	private boolean isValidNome() {
		if (this.nome.matches("^[A-Za-z]{1,20}$"))
			return true;
		return false;
	}
	
	private boolean isValidCognome() { 
		if (this.cognome.matches("[A-Za-z]{1,20}"))
			return true;
		return false;
	}
	
	private boolean isValidUsername() {
		if (this.username.matches("[A-Za-z0-9-_.]{1,20}"))
			return true;
		return false;
	}
	
	private boolean isValidRuolo() {
		if (this.ruolo.toLowerCase().equals("dipendente") || this.ruolo.toLowerCase().equals("admin"))
			return true;
		return false;
	}
	
	private boolean isValidEmail() {
		if (this.email.matches("[a-zA-Z0-9._%-]{8,30}+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}"))
			return true;
		return false;
	}
	
	public boolean isValid() {
		return isValidEmail() &&
				isValidCognome() &&
				isValidNome() &&
				isValidPwd() &&
				isValidRuolo() &&
				isValidStipendio() &&
				isValidUsername();
				
	}
	
	private boolean isValidStipendio() {
		if (stipendio < 1000 || stipendio > 99999 )
			return false;
		return true;
	}
	
	public void setStipendio (double s) {
		this.stipendio = s;
	}
	
	public double getStipendio() {
		return this.stipendio;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRuolo(String ruolo) {
		 if (ruolo.toLowerCase().equals("admin")) {
			 this.ruolo = "admin";
		 } else {
			 this.ruolo = "dipendente";
		 }
	 }
	
	 public String getRuolo() {
		 return this.ruolo;
	 }
	 
	 public boolean isAdmin() {
		 return (this.ruolo.equals("admin")) ? true : false;
	 }
}
