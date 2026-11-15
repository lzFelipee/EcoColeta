
package br.com.fiap.ecocoleta.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "coletas")
public class Coleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ponto_coleta_id", nullable = false)
    private PontoColeta pontoColeta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "residuo_id", nullable = false)
    private Residuo residuo;

    @Column(name = "quantidade_kg", nullable = false,
            precision = 10, scale = 2)
    private BigDecimal quantidadeKg;

    @Column(name = "data_coleta", nullable = false)
    private LocalDateTime dataColeta;

    @Column(nullable = false, length = 30)
    private String status;

    public Coleta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PontoColeta getPontoColeta() {
        return pontoColeta;
    }

    public void setPontoColeta(PontoColeta pontoColeta) {
        this.pontoColeta = pontoColeta;
    }

    public Residuo getResiduo() {
        return residuo;
    }

    public void setResiduo(Residuo residuo) {
        this.residuo = residuo;
    }

    public BigDecimal getQuantidadeKg() {
        return quantidadeKg;
    }

    public void setQuantidadeKg(BigDecimal quantidadeKg) {
        this.quantidadeKg = quantidadeKg;
    }

    public LocalDateTime getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDateTime dataColeta) {
        this.dataColeta = dataColeta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
