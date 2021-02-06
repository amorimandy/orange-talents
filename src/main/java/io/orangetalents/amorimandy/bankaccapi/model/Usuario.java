package io.orangetalents.amorimandy.bankaccapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.sun.istack.NotNull;

	@Entity
	@Table(name = "tb_usuario")
	public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private long id;

	@NotBlank
	@Column(name = "nome")
	private String nome;

	@NotEmpty(message = "O campo de email não pode estar vazio")
	@Email(message = "Utilize um email válido, por favor")
	@Column(unique = true)
	private String email;

	@NotBlank
	@Column(unique = true)
	@CPF
	private String cpf;

	@NotNull
	@Temporal(value = TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Date dataDeNascimento;

	@NotNull
	@Size(min = 8)
	private String senha;

	@NotNull
	@Size(min = 3, max = 100)
	private String usuario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
