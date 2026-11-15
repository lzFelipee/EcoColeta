package br.com.fiap.ecocoleta.repository;

import br.com.fiap.ecocoleta.model.PontoColeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PontoColetaRepository
        extends JpaRepository<PontoColeta, Long> {
}