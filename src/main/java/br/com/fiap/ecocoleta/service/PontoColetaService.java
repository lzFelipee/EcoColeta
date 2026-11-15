
package br.com.fiap.ecocoleta.service;

import br.com.fiap.ecocoleta.model.PontoColeta;
import br.com.fiap.ecocoleta.repository.PontoColetaRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PontoColetaService {

    private final PontoColetaRepository repository;

    public PontoColetaService(PontoColetaRepository repository) {
        this.repository = repository;
    }

    public List<PontoColeta> listar() {
        return repository.findAll();
    }

    public PontoColeta buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Ponto de coleta não encontrado"));
    }

    public PontoColeta salvar(PontoColeta ponto) {
        return repository.save(ponto);
    }

    public PontoColeta atualizar(Long id, PontoColeta dados) {
        PontoColeta ponto = buscarPorId(id);

        ponto.setNome(dados.getNome());
        ponto.setEndereco(dados.getEndereco());
        ponto.setBairro(dados.getBairro());
        ponto.setCidade(dados.getCidade());
        ponto.setCep(dados.getCep());
        ponto.setAtivo(dados.getAtivo());

        return repository.save(ponto);
    }

    public void excluir(Long id) {
        PontoColeta ponto = buscarPorId(id);
        repository.delete(ponto);
    }
}
