package br.com.foursales.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
//@Data lombok comentado para exibir a estrutura json no swagger
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ApiModel(value = "CartaoCredito")
public class CartaoCredito implements Serializable {

	private static final long serialVersionUID = -2675576146954770051L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Bandeira do Cartão obrigatório")
	@ApiModelProperty(notes = "Bandeira do Cartão")
	private String bandeira;

	@NotNull(message = "Número do Cartão é obrigatório")
	@ApiModelProperty(notes = "Número do Cartão")
	private Long Numero;

	@NotNull(message = "Código de Verificação do Cartão é obrigatório")
	@ApiModelProperty(notes = "Código de Verificação do Cartão ")
	private Long CVV;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public Long getNumero() {
		return Numero;
	}

	public void setNumero(Long numero) {
		Numero = numero;
	}

	public Long getCVV() {
		return CVV;
	}

	public void setCVV(Long cVV) {
		CVV = cVV;
	}

}
