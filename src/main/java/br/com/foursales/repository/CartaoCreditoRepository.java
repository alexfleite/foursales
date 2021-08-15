package br.com.foursales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.foursales.model.CartaoCredito;

@Repository
public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Long> {
}