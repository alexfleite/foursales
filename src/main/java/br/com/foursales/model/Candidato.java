package br.com.foursales.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data // lombok comentado para exibir a estrutura json no swagger
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ApiModel(value = "Candidato")
public class Candidato implements Serializable {

	private static final long serialVersionUID = 9020010295306053615L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Nome do Candidato é obrigatório")
	@ApiModelProperty(notes = "Nome do candidato")
	private String nome;

	@NotNull(message = "E-mail é obrigatório")
	@ApiModelProperty(notes = "E-mail do candidato")
	private String email;

	@NotNull(message = "Telefone é obrigatório")
	@ApiModelProperty(notes = "Telefone do candidato")
	private String telefone;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cartao", referencedColumnName = "id")
	private Set<CartaoCredito> cartaoCredito;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Set<CartaoCredito> getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(Set<CartaoCredito> cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

}
