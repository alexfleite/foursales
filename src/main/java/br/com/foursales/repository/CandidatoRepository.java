package br.com.foursales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.foursales.model.Candidato;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
}