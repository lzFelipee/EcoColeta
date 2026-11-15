
package br.com.fiap.ecocoleta.controller;

import br.com.fiap.ecocoleta.model.Coleta;
import br.com.fiap.ecocoleta.service.ColetaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coletas")
public class ColetaController {

    private final ColetaService service;

    public ColetaController(ColetaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Coleta> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Coleta buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Coleta criar(@RequestBody Coleta coleta) {
        return service.salvar(coleta);
    }

    @PutMapping("/{id}")
    public Coleta atualizar(
            @PathVariable Long id,
            @RequestBody Coleta coleta) {
        return service.atualizar(id, coleta);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
