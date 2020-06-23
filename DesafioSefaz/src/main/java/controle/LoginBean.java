package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.TelefoneDAO;
import dao.TelefoneDAOImpl;
import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import entidade.Telefone;
import entidade.Usuario;

@ManagedBean(name = "LoginBean")
@SessionScoped
public class LoginBean {
	private String nome;
	private String email;
	private String senha;
	private int ddd;
	private String numero;
	private String tipo;
	private List<Usuario> listaUsuarios;
	private List<Telefone> listaTelefones;
	private Usuario usuario;
	private Telefone telefone;
	private String mensagemCadastro;
	private UsuarioDAO usuarioDAO;
	private TelefoneDAO telefoneDAO;
	
	public LoginBean() {
		this.listaTelefones = new ArrayList<Telefone>();
		this.telefone = new Telefone();
		this.listaUsuarios = new ArrayList<Usuario>();
		this.usuario = new Usuario();
		this.usuarioDAO = new UsuarioDAOImpl();
		this.telefoneDAO = new TelefoneDAOImpl();
	}
	
	public String login() { // método entrar + validação

		boolean achou = false;

		this.listaUsuarios = this.usuarioDAO.listarTodos();
		
		for (Usuario usuarioPesquisa : listaUsuarios) {

			if (usuarioPesquisa.getEmail().equals(this.email) && usuarioPesquisa.getSenha().equals(this.senha)) {

				achou = true;
			}
		}

		if (achou) {
			return "controle.xhtml";
		} else {
			return "index.xhtml";
		}

	}
	
	public void cadastrarUsuario() {
		Usuario newUser = new Usuario();
		Telefone newTelefone = new Telefone();
		
		newTelefone.setDdd(this.telefone.getDdd());
		newTelefone.setNumero(this.telefone.getNumero());
		newTelefone.setTipo(this.telefone.getTipo());
		
		List<Telefone> telefones = new ArrayList<Telefone>();
		telefones.add(newTelefone);
		
		newUser.setEmail(this.usuario.getEmail());
		newUser.setNome(this.usuario.getNome());
		newUser.setSenha(this.usuario.getSenha());

		newTelefone.setUsuario(newUser);
		
		boolean foundUser = false;
		
		this.listaUsuarios = this.usuarioDAO.listarTodos();
		
		for (Usuario usuarioPesquisa : listaUsuarios) {
			if (usuarioPesquisa.getEmail().equals(this.usuario.getEmail())) {
				foundUser = true;
			}
		}
		
		if(foundUser) {
			FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Erro ao cadastrar novo usuário"));
		} else {
			this.usuarioDAO.incluir(newUser);
			this.telefoneDAO.incluir(newTelefone);
			
			this.telefone = new Telefone();
			this.usuario = new Usuario();
		}
		
		
		
	}
	
	
	public void pesquisar() {
		this.listaUsuarios = this.usuarioDAO.listarTodos();
	}
	
	public void alterar() {
		
		Usuario u = new Usuario();
		Telefone t = new Telefone();
		
		t.setDdd(this.telefone.getDdd());
		t.setNumero(this.telefone.getNumero());
		t.setTipo(this.telefone.getTipo());
		
		List<Telefone> telefones = new ArrayList<Telefone>();
		telefones.add(t);
		
		u.setEmail(this.usuario.getEmail());
		u.setNome(this.usuario.getNome());
		u.setSenha(this.usuario.getSenha());

		t.setUsuario(u);
		
		this.usuarioDAO.alterar(u);
		this.telefoneDAO.alterar(t);
	}
	
	public void remover() {
		Usuario user = new Usuario();
		
		user.setEmail(this.usuario.getEmail());
		user.setNome(this.usuario.getNome());
		user.setSenha(this.usuario.getSenha());
		this.usuarioDAO.remover(user);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<Telefone> getListaTelefones() {
		return listaTelefones;
	}

	public void setListaTelefones(List<Telefone> listaTelefones) {
		this.listaTelefones = listaTelefones;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public String getMensagemCadastro() {
		return mensagemCadastro;
	}

	public void setMensagemCadastro(String mensagemCadastro) {
		this.mensagemCadastro = mensagemCadastro;
	}

}
