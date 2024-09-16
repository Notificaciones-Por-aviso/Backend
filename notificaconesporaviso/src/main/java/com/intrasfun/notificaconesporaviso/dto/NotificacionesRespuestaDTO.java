package com.intrasfun.notificaconesporaviso.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificacionesRespuestaDTO {
    private String tipoIdentificacion;
    private String idUsuario;
    private String numeroComparendo;
    private LocalDate fechaComparendo;
    private String estadoCartera;
    private String placa;
    private String resolusionAviso;

}
