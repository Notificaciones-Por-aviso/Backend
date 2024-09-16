package com.intrasfun.notificaconesporaviso.excepciones;

public class PlantillaException extends RuntimeException{
    public PlantillaException(String mensaje) {
        super(mensaje);
    }

    public PlantillaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
