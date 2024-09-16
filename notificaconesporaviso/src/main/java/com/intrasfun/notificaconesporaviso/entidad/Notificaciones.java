package com.intrasfun.notificaconesporaviso.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
@Entity
@Table(name = "notificaciones")
public class Notificaciones {
    @Id
    private long id;

    @NonNull
    @Column
    private String tipoIdentificacion;

    @NonNull
    @Column
    private String idUsuario;

    @NonNull
    @Column
    private String numeroComparendo;

    @NonNull
    @Column
    private LocalDate fechaComparendo;

    @NonNull
    @Column
    private LocalDate fechaValidacion;

    @NonNull
    @Column
    private LocalDate fechaNotificacion;

    @NonNull
    @Column
    private String estadoCartera;

    @NonNull
    @Column
    private String placa;

    @NonNull
    @Column
    private LocalDate fechaResolucion;

    @NonNull
    @Column
    private String resolucionAviso;

}
