
package br.com.fiap.ecocoleta.service;

import br.com.fiap.ecocoleta.model.Residuo;
import br.com.fiap.ecocoleta.repository.ResiduoRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ResiduoService {

    private final ResiduoRepository repository;

    public ResiduoService(ResiduoRepository repository) {
        this.repository = repository;
    }

    public List<Residuo> listar() {
        return repository.findAll();
    }

    public Residuo buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Resíduo não encontrado"));
    }

    public Residuo salvar(Residuo residuo) {
        return repository.save(residuo);
    }

    public Residuo atualizar(Long id, Residuo dados) {
        Residuo residuo = buscarPorId(id);

        residuo.setNome(dados.getNome());
        residuo.setCategoria(dados.getCategoria());
        residuo.setDescricao(dados.getDescricao());

        return repository.save(residuo);
    }

    public void excluir(Long id) {
        Residuo residuo = buscarPorId(id);
        repository.delete(residuo);
    }
}
