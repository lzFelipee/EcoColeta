
package br.com.fiap.ecocoleta.controller;

import br.com.fiap.ecocoleta.model.PontoColeta;
import br.com.fiap.ecocoleta.service.PontoColetaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PontoColetaController.class)
class PontoColetaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PontoColetaService service;

    @Test
    void deveListarPontosDeColeta() throws Exception {

        when(service.listar()).thenReturn(List.of());

        mockMvc.perform(get("/api/pontos-coleta"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void deveBuscarPontoPorId() throws Exception {

        PontoColeta ponto = new PontoColeta();
        ponto.setId(1L);
        ponto.setNome("Ecoponto Vila Mariana");

        when(service.buscarPorId(1L)).thenReturn(ponto);

        mockMvc.perform(get("/api/pontos-coleta/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome")
                        .value("Ecoponto Vila Mariana"));
    }

    @Test
    void deveCadastrarPontoDeColeta() throws Exception {

        PontoColeta ponto = new PontoColeta();
        ponto.setId(1L);
        ponto.setNome("Ecoponto Vila Mariana");

        when(service.salvar(any(PontoColeta.class)))
                .thenReturn(ponto);

        mockMvc.perform(post("/api/pontos-coleta")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "nome": "Ecoponto Vila Mariana",
                        "endereco": "Rua Exemplo, 100",
                        "bairro": "Vila Mariana",
                        "cidade": "São Paulo",
                        "cep": "04101-000",
                        "ativo": true
                    }
                    """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void deveExcluirPontoDeColeta() throws Exception {

        doNothing().when(service).excluir(1L);

        mockMvc.perform(delete("/api/pontos-coleta/1"))
                .andExpect(status().isNoContent());

        verify(service).excluir(1L);
    }
}
