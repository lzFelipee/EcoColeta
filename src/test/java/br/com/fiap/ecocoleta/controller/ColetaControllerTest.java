
package br.com.fiap.ecocoleta.controller;

import br.com.fiap.ecocoleta.model.Coleta;
import br.com.fiap.ecocoleta.model.PontoColeta;
import br.com.fiap.ecocoleta.model.Residuo;
import br.com.fiap.ecocoleta.service.ColetaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ColetaController.class)
class ColetaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ColetaService service;

    private Coleta criarColeta() {
        PontoColeta ponto = new PontoColeta();
        ponto.setId(1L);
        ponto.setNome("Ecoponto Vila Mariana");

        Residuo residuo = new Residuo();
        residuo.setId(1L);
        residuo.setNome("Papelão");
        residuo.setCategoria("Papel");

        Coleta coleta = new Coleta();
        coleta.setId(1L);
        coleta.setPontoColeta(ponto);
        coleta.setResiduo(residuo);
        coleta.setQuantidadeKg(new BigDecimal("15.50"));
        coleta.setDataColeta(
            LocalDateTime.of(2026, 11, 15, 10, 0)
        );
        coleta.setStatus("CONCLUIDA");

        return coleta;
    }

    @Test
    void deveListarColetas() throws Exception {

        when(service.listar()).thenReturn(List.of());

        mockMvc.perform(get("/api/coletas"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void deveBuscarColetaPorId() throws Exception {

        when(service.buscarPorId(1L))
                .thenReturn(criarColeta());

        mockMvc.perform(get("/api/coletas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.pontoColeta.nome")
                        .value("Ecoponto Vila Mariana"))
                .andExpect(jsonPath("$.residuo.nome")
                        .value("Papelão"))
                .andExpect(jsonPath("$.status")
                        .value("CONCLUIDA"));
    }

    @Test
    void deveCadastrarColeta() throws Exception {

        when(service.salvar(any(Coleta.class)))
                .thenReturn(criarColeta());

        mockMvc.perform(post("/api/coletas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "pontoColeta": {
                            "id": 1
                        },
                        "residuo": {
                            "id": 1
                        },
                        "quantidadeKg": 15.50,
                        "dataColeta": "2026-11-15T10:00:00",
                        "status": "CONCLUIDA"
                    }
                    """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.pontoColeta.nome")
                        .value("Ecoponto Vila Mariana"))
                .andExpect(jsonPath("$.residuo.nome")
                        .value("Papelão"));
    }

    @Test
    void deveAtualizarColeta() throws Exception {

        when(service.atualizar(eq(1L), any(Coleta.class)))
                .thenReturn(criarColeta());

        mockMvc.perform(put("/api/coletas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "pontoColeta": {
                            "id": 1
                        },
                        "residuo": {
                            "id": 1
                        },
                        "quantidadeKg": 15.50,
                        "dataColeta": "2026-11-15T10:00:00",
                        "status": "CONCLUIDA"
                    }
                    """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status")
                        .value("CONCLUIDA"));
    }

    @Test
    void deveExcluirColeta() throws Exception {

        doNothing().when(service).excluir(1L);

        mockMvc.perform(delete("/api/coletas/1"))
                .andExpect(status().isNoContent());

        verify(service).excluir(1L);
    }
}
