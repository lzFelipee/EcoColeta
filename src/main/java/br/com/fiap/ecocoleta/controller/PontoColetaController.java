
package br.com.fiap.ecocoleta.controller;

import br.com.fiap.ecocoleta.model.PontoColeta;
import br.com.fiap.ecocoleta.service.PontoColetaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pontos-coleta")
public class PontoColetaController {

    private final PontoColetaService service;

    public PontoColetaController(PontoColetaService service) {
        this.service = service;
    }

    @GetMapping
    public List<PontoColeta> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public PontoColeta buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PontoColeta criar(@RequestBody PontoColeta ponto) {
        return service.salvar(ponto);
    }

    @PutMapping("/{id}")
    public PontoColeta atualizar(
            @PathVariable Long id,
            @RequestBody PontoColeta ponto) {
        return service.atualizar(id, ponto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
