package com.intrasfun.notificaconesporaviso.dto;

public class RespuestaExcepcionDTO {
    private String mensaje;

    public RespuestaExcepcionDTO(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
