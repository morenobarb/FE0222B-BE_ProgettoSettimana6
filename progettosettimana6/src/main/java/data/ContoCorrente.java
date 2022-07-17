package data;

public class ContoCorrente {
	private int numero;
	private String intestatario;
	private float saldo;

/////////////////GETTERS E SETTERS///////////////////////

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getIntestatario() {
		return intestatario;
	}

	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

///////////////COSTRUTTORI///////////////////

	public ContoCorrente(int numero, String intestatario, float saldo) {
		this.numero = numero;
		this.intestatario = intestatario;
		this.saldo = saldo;
	}

	public ContoCorrente() {
		
	}
	
	
	
}
