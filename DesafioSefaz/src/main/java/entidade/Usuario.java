package entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Usuario {

	@Id
	@Column(name="EMAIL", nullable = false)
	private String email;
	
	@Column(name="NOME", nullable = false)
	private String nome;
	
	@Column(name="SENHA", nullable = false)
	private String senha;
	
	@OneToMany(mappedBy="usuario", cascade= CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private List<Telefone> telefones;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}


}
