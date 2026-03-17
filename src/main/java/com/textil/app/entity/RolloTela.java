package com.textil.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
        name = "rollo_tela",
        indexes = {
                @Index(name = "idx_rollo_detalle", columnList = "detalle_orden_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolloTela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "detalle_orden_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_rollo_detalle")
    )
    private DetalleOrdenProduccion detalleOrdenProduccion;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal pesoKg;

    @Column(nullable = false)
    private LocalDate fechaProduccion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoRolloTela estado;

    @PrePersist
    protected void onCreate() {
        if (this.estado == null) {
            this.estado = EstadoRolloTela.PRODUCIDO;
        }
    }
}

