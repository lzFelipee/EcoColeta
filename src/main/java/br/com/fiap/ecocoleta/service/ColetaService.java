
package br.com.fiap.ecocoleta.service;

import br.com.fiap.ecocoleta.model.Coleta;
import br.com.fiap.ecocoleta.model.PontoColeta;
import br.com.fiap.ecocoleta.model.Residuo;
import br.com.fiap.ecocoleta.repository.ColetaRepository;
import br.com.fiap.ecocoleta.repository.PontoColetaRepository;
import br.com.fiap.ecocoleta.repository.ResiduoRepository;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ColetaService {

    private final ColetaRepository repository;
    private final PontoColetaRepository pontoRepository;
    private final ResiduoRepository residuoRepository;

    public ColetaService(
            ColetaRepository repository,
            PontoColetaRepository pontoRepository,
            ResiduoRepository residuoRepository) {

        this.repository = repository;
        this.pontoRepository = pontoRepository;
        this.residuoRepository = residuoRepository;
    }

    public List<Coleta> listar() {
        return repository.findAll();
    }

    public Coleta buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Coleta não encontrada"
            ));
    }

    public Coleta salvar(Coleta coleta) {

        associarEntidades(coleta);

        return repository.save(coleta);
    }

    public Coleta atualizar(Long id, Coleta dados) {

        Coleta coleta = buscarPorId(id);

        associarEntidades(dados);

        coleta.setPontoColeta(dados.getPontoColeta());
        coleta.setResiduo(dados.getResiduo());
        coleta.setQuantidadeKg(dados.getQuantidadeKg());
        coleta.setDataColeta(dados.getDataColeta());
        coleta.setStatus(dados.getStatus());

        return repository.save(coleta);
    }

    public void excluir(Long id) {

        Coleta coleta = buscarPorId(id);

        repository.delete(coleta);
    }

    private void associarEntidades(Coleta coleta) {

        if (coleta.getPontoColeta() == null ||
            coleta.getPontoColeta().getId() == null) {

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Ponto de coleta inválido"
            );
        }

        if (coleta.getResiduo() == null ||
            coleta.getResiduo().getId() == null) {

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Resíduo inválido"
            );
        }

        PontoColeta ponto = pontoRepository.findById(
            coleta.getPontoColeta().getId()
        ).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Ponto de coleta não encontrado"
        ));

        Residuo residuo = residuoRepository.findById(
            coleta.getResiduo().getId()
        ).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "Resíduo não encontrado"
        ));

        coleta.setPontoColeta(ponto);
        coleta.setResiduo(residuo);
    }
}
