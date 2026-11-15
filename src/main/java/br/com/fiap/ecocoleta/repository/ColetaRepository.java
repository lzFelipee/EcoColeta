package br.com.fiap.ecocoleta.repository;

import br.com.fiap.ecocoleta.model.Coleta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColetaRepository
        extends JpaRepository<Coleta, Long> {
}