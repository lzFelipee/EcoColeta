
package br.com.fiap.ecocoleta.controller;

import br.com.fiap.ecocoleta.model.Residuo;
import br.com.fiap.ecocoleta.service.ResiduoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residuos")
public class ResiduoController {

    private final ResiduoService service;

    public ResiduoController(ResiduoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Residuo> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Residuo buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Residuo criar(@RequestBody Residuo residuo) {
        return service.salvar(residuo);
    }

    @PutMapping("/{id}")
    public Residuo atualizar(
            @PathVariable Long id,
            @RequestBody Residuo residuo) {
        return service.atualizar(id, residuo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
