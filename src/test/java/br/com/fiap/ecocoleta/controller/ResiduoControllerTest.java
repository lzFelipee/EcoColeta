
package br.com.fiap.ecocoleta.controller;

import br.com.fiap.ecocoleta.model.Residuo;
import br.com.fiap.ecocoleta.service.ResiduoService;

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

@WebMvcTest(ResiduoController.class)
class ResiduoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ResiduoService service;

    @Test
    void deveListarResiduos() throws Exception {

        when(service.listar()).thenReturn(List.of());

        mockMvc.perform(get("/api/residuos"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void deveBuscarResiduoPorId() throws Exception {

        Residuo residuo = new Residuo();
        residuo.setId(1L);
        residuo.setNome("Papelão");
        residuo.setCategoria("Papel");
        residuo.setDescricao("Caixas e embalagens");

        when(service.buscarPorId(1L)).thenReturn(residuo);

        mockMvc.perform(get("/api/residuos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Papelão"))
                .andExpect(jsonPath("$.categoria").value("Papel"));
    }

    @Test
    void deveCadastrarResiduo() throws Exception {

        Residuo residuo = new Residuo();
        residuo.setId(1L);
        residuo.setNome("Papelão");
        residuo.setCategoria("Papel");
        residuo.setDescricao("Caixas e embalagens");

        when(service.salvar(any(Residuo.class)))
                .thenReturn(residuo);

        mockMvc.perform(post("/api/residuos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "nome": "Papelão",
                        "categoria": "Papel",
                        "descricao": "Caixas e embalagens"
                    }
                    """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Papelão"));
    }

    @Test
    void deveExcluirResiduo() throws Exception {

        doNothing().when(service).excluir(1L);

        mockMvc.perform(delete("/api/residuos/1"))
                .andExpect(status().isNoContent());

        verify(service).excluir(1L);
    }
}
