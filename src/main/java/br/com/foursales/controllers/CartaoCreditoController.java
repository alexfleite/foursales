package br.com.foursales.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.foursales.exception.CandidatoNotfoundException;
import br.com.foursales.exception.CartaoCreditoNotfoundException;
import br.com.foursales.model.CartaoCredito;
import br.com.foursales.repository.CartaoCreditoRepository;

@RestController
@RequestMapping({ "/cartaoCredito" })
public class CartaoCreditoController {

	Logger logger = LoggerFactory.getLogger(CartaoCreditoController.class);

	@Autowired
	private CartaoCreditoRepository repository;

	/**
	 * Instancia uma nova CartaoCreditoController.
	 *
	 * @param CartaoCreditoRepository
	 */
	public CartaoCreditoController(CartaoCreditoRepository cartaoCreditoRepository) {
		this.repository = cartaoCreditoRepository;
	}

	/**
	 * Find all.
	 *
	 * @return Lista de CartaoCreditos
	 */
	@GetMapping
	public List<CartaoCredito> findAll() {

		logger.info("***** findAll inicio ***** ");
		logger.info("findAll: realizando a busca por todos os Cartoes de Credito");
		List<CartaoCredito> findAll = repository.findAll();

		if (null != findAll && !findAll.isEmpty()) {
			logger.info("findAll: Lista de Cartoes de Credito retornada: " + findAll.toString());
		} else {
			logger.info("findAll: Nenhum Cartao de Credito cadastrado");
		}
		logger.info("***** findAll fim ***** ");
		return findAll;

	}

	/**
	 * Find by id. Recupera candidato pelo id
	 *
	 * @param id
	 * @return CartaoCredito pelo id
	 */
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<CartaoCredito> findById(@PathVariable long id) {

		logger.info("***** findById inicio ***** ");

		ResponseEntity<CartaoCredito> findById = repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());

		if (null != findById) {
			logger.info("findById: CartaoCredito encontrado: " + findById.toString());
			
			if(findById.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				logger.info("findById: CartaoCredito nao encontrado");
				logger.info("***** findById final ***** ");
				throw new CartaoCreditoNotfoundException();
			}
		}

		logger.info("***** findById final ***** ");

		return findById;
	}

	/**
	 * Cria um novo registro de CartaoCredito na base.
	 *
	 * @param cartaoCredito
	 * @return CartaoCredito criado
	 */
	@PostMapping()
	public CartaoCredito create(@Valid @RequestBody CartaoCredito cartaoCredito) throws Exception {

		logger.info("***** create inicio ***** ");

		CartaoCredito create = repository.save(cartaoCredito);

		if (null != create) {
			logger.info("create: CartaoCredito incluido com sucesso: " + create.toString());
		} else {
			logger.info("findById: Nao foi possivel incluir CartaoCredito");
		}
		logger.info("***** create final ***** ");

		return create;
	}

	/**
	 * Update. Ataliza os dados do Cartao de Credito
	 *
	 * @param id
	 * @param cartaoCredito
	 * @return Response entity. OK(200, "OK"), NOT_FOUND(404, "Not Found")
	 */
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<CartaoCredito> update(@PathVariable("id") long id, @RequestBody CartaoCredito cartaoCredito) {

		logger.info("update: Inicio");

		ResponseEntity<CartaoCredito> responseEntity = repository.findById(id).map(record -> {
			record.setBandeira(cartaoCredito.getBandeira());
			record.setCVV(cartaoCredito.getCVV());
			record.setNumero(cartaoCredito.getNumero());
			CartaoCredito updated = repository.save(record);

			logger.info("update: Fim");
			return ResponseEntity.ok().body(updated);

		}).orElse(ResponseEntity.notFound().build());
		
		if (null != responseEntity) {
			logger.info("findById: CartaoCredito encontrado: " + responseEntity.toString());
			
			if(responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				logger.info("findById: CartaoCredito nao encontrado");
				logger.info("***** findById final ***** ");
				throw new CartaoCreditoNotfoundException();
			}
		}
		return responseEntity;
		
	}

	/**
	 * Apaga cartao de credito da base.
	 *
	 * @param id the id
	 * @return the response entity. OK(200, "OK"), NOT_FOUND(404, "Not Found")
	 */
	@DeleteMapping(path = { "/delete/{id}" })
	public ResponseEntity<?> delete(@PathVariable long id) {

		logger.info("delete: Inicio");

		ResponseEntity<String> responseEntity = repository.findById(id).map(record -> {
			repository.deleteById(id);

			logger.info("delete: fim");

			return ResponseEntity.ok().body("Cartao " + id + " removido com sucesso");
		}).orElse(ResponseEntity.notFound().build());
		
		if (null != responseEntity) {
			logger.info("findById: CartaoCredito encontrado: " + responseEntity.toString());
			
			if(responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				logger.info("findById: CartaoCredito nao encontrado");
				logger.info("***** findById final ***** ");
				throw new CartaoCreditoNotfoundException();
			}
		}
		return responseEntity;
		
		
		
		
	}
	
}
