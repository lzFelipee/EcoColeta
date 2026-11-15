package br.com.fiap.ecocoleta.repository;

import br.com.fiap.ecocoleta.model.Residuo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResiduoRepository
        extends JpaRepository<Residuo, Long> {
}