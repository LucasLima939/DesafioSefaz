package entidade;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Telefone {

	@Id
	@Column(name="NUMERO", nullable = false)
	private String numero;
	
	@Column(name="DDD", nullable = false)
	private int ddd;
	
	@Column(name="TIPO", nullable = false)
	private String tipo;
	
	@ManyToOne
	private Usuario usuario;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
