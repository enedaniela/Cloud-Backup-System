

public class Utilizator {

	public String username;
	public String parola;
	public String nume;
	public String prenume;
	public String dataCreare;
	public String dataLogare;

	public Utilizator(String username, String parola, String nume, String prenume, String dataCreare, String dataLogare){
		this.username = username;
		this.parola = parola;
		this.nume = nume;
		this.prenume = prenume;
		this.dataCreare = dataCreare;
		this.dataLogare = dataLogare;
		
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public void setUsername(String username){
	this.username = username;
	}
	
	public String getParola(){
		return this.parola;
	}
	
	public void setParola(String parola){
		this.parola = parola;
	}
	
	public String getNume(){
		return this.nume;
	}
	
	public void setNume(String nume){
		this.nume = nume;
	}
	
	public String getPrenume(){
		return this.prenume;
	}
	
	public void setPrenume(String prenume){
		this.prenume = prenume;
	}
	
	public String getDataCreare(){
		return this.dataCreare;
	}
	
	public void setDataCreare(String dataCreare){
		this.dataCreare = dataCreare;
	}
	
	public String getDataLogare(){
		return this.dataLogare;
	}
	
	public void setDataLogare(String dataLogare){
		this.dataLogare = dataLogare;
	}
}
