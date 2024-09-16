package com.intrasfun.notificaconesporaviso.controlador;

import com.intrasfun.notificaconesporaviso.dto.NotificacionesRespuestaDTO;
import com.intrasfun.notificaconesporaviso.servicio.ServicioNotificaciones;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notificacionesPorAviso")
public class ControladorNotificaciones {
    private ServicioNotificaciones servicioNotificaciones;

    public ControladorNotificaciones(ServicioNotificaciones servicioNotificaciones) {
        this.servicioNotificaciones = servicioNotificaciones;
    }

    @GetMapping("/{id-usuario}")
    public ResponseEntity<List<NotificacionesRespuestaDTO>> buscarNotificacionesPorAviso(@PathVariable("id-usuario")String idUsuario){
        return ResponseEntity.ok().body(servicioNotificaciones.buscarNotificacionesPorIdUsuario(idUsuario));
    }

    // Nuevo endpoint para descargar PDF
    @GetMapping("/descargarPDF/{numeroComparendo}")
    public ResponseEntity<byte[]> descargarPDF(@PathVariable String numeroComparendo) {
        try {
            // Generar el HTML procesado
            String htmlProcesado = servicioNotificaciones.crearPlantillaHtml(numeroComparendo);

            // Generar el PDF a partir del HTML procesado
            byte[] pdfGenerado = servicioNotificaciones.generarPDF(htmlProcesado);

            // Definir los headers para la respuesta
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition", "attachment; filename=notificacion.pdf");

            // Devolver el PDF como respuesta
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                    .body(pdfGenerado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("Error al generar el PDF: " + e.getMessage()).getBytes());
        }
    }

}
