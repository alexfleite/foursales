package br.com.foursales.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.foursales.model.Candidato;
import br.com.foursales.repository.CandidatoRepository;

@RestController
@RequestMapping({ "/candidatos" })
public class CandidatoController {

	Logger logger = LoggerFactory.getLogger(CandidatoController.class);

	@Autowired
	private CandidatoRepository repository;

	/**
	 * Instancia uma nova CandidatoController.
	 *
	 * @param candidatoRepository
	 */
	public CandidatoController(CandidatoRepository candidatoRepository) {
		this.repository = candidatoRepository;
	}

	/**
	 * Find all.
	 *
	 * @return Lista de candidatos
	 */
	@GetMapping
	public List<Candidato> findAll() {

		logger.info("***** findAll inicio ***** ");
		logger.info("findAll: realizando a busca por todos os candidatos");
		List<Candidato> findAll = repository.findAll();

		if (null != findAll && !findAll.isEmpty()) {
			logger.info("findAll: Lista de candidatos retornada: " + findAll.toString());
		} else {
			logger.info("findAll: Nenhum candidato cadastrado");
		}
		logger.info("***** findAll fim ***** ");
		return findAll;

	}

	/**
	 * Find by id. Recupera candidato pelo id
	 *
	 * @param id
	 * @return Medico pelo id
	 */
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Candidato> findById(@PathVariable long id) {

		logger.info("***** findById inicio ***** ");

		ResponseEntity<Candidato> findById = repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());

		if (null != findById) {
			logger.info("findById: Candidato encontrado: " + findById.toString());
		} else {
			logger.info("findById: Candidato nao encontrado");
		}

		logger.info("***** findById final ***** ");

		return findById;
	}

	/**
	 * Cria um novo registro de Candidato na base.
	 *
	 * @param candidato
	 * @return Candidato criado
	 */
	@PostMapping()
	public Candidato create(@RequestBody Candidato candidato) throws Exception {

		logger.info("***** create inicio ***** ");

		Candidato create = repository.save(candidato);

		if (null != create) {
			logger.info("create: Candidato incluido com sucesso: " + create.toString());
		} else {
			logger.info("findById: Nao foi possivel incluir Candidato");
		}
		logger.info("***** create final ***** ");

		return create;
	}

	/**
	 * Update. Ataliza os dados do candidato
	 *
	 * @param id
	 * @param candidato
	 * @return Response entity. OK(200, "OK"), NOT_FOUND(404, "Not Found")
	 */
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Candidato> update(@PathVariable("id") long id, @RequestBody Candidato candidato) {

		logger.info("update: Inicio");

		return repository.findById(id).map(record -> {
			record.setNome(candidato.getNome());
			record.setEmail(candidato.getEmail());
			record.setTelefone(candidato.getTelefone());
			Candidato updated = repository.save(record);

			logger.info("update: Fim");
			return ResponseEntity.ok().body(updated);

		}).orElse(ResponseEntity.notFound().build());

	}

	/**
	 * Apaga candidato da base.
	 *
	 * @param id the id
	 * @return the response entity. OK(200, "OK"), NOT_FOUND(404, "Not Found")
	 */
	@DeleteMapping(path = { "/delete/{id}" })
	public ResponseEntity<?> delete(@PathVariable long id) {

		logger.info("delete: Inicio");

		return repository.findById(id).map(record -> {
			repository.deleteById(id);

			logger.info("delete: fim");

			return ResponseEntity.ok().body("Removido com sucesso");
		}).orElse(ResponseEntity.notFound().build());
	}
}
