package com.intrasfun.notificaconesporaviso.servicio;

import com.intrasfun.notificaconesporaviso.dto.NotificacionesRespuestaDTO;
import com.intrasfun.notificaconesporaviso.entidad.Notificaciones;
import com.intrasfun.notificaconesporaviso.excepciones.PlantillaException;
import com.intrasfun.notificaconesporaviso.repositorio.RepositorioNotificaciones;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServicioNotificaciones {
    private RepositorioNotificaciones repositorioNotificaciones;
    private Configuration freeMarkerConfiguration;


    public ServicioNotificaciones(RepositorioNotificaciones repositorioNotificaciones, Configuration freeMarkerConfiguration) {
        this.repositorioNotificaciones = repositorioNotificaciones;
        this.freeMarkerConfiguration = freeMarkerConfiguration;
    }

    public List<NotificacionesRespuestaDTO> buscarNotificacionesPorIdUsuario(String idUsuario) {
        List<Notificaciones> notificacionesList = repositorioNotificaciones.findByIdUsuario(idUsuario);
        if (!notificacionesList.isEmpty()) {
            List<NotificacionesRespuestaDTO> notificacionesRespuestaDTOS = new ArrayList<>();
            for (Notificaciones notificaciones : notificacionesList) {
                notificacionesRespuestaDTOS.add(new NotificacionesRespuestaDTO(
                        notificaciones.getTipoIdentificacion(),
                        notificaciones.getIdUsuario(),
                        notificaciones.getNumeroComparendo(),
                        notificaciones.getFechaComparendo(),
                        notificaciones.getEstadoCartera(),
                        notificaciones.getPlaca(),
                        notificaciones.getResolucionAviso()));
            }
            return notificacionesRespuestaDTOS;
        } else {
            throw new IllegalArgumentException("Usuario no tiene notificaciones por aviso pendientes.");
        }
    }

    public String crearPlantillaHtml(String numeroComparendo){
        Notificaciones notificaciones = repositorioNotificaciones.findByNumeroComparendo(numeroComparendo);

        if (notificaciones == null) {
            throw new IllegalArgumentException("No se encontró la notificación con el número de comparendo: " + numeroComparendo);
        }

        // Crear un mapa de datos para pasar a la plantilla
        Map<String, Object> datos = new HashMap<>();
        datos.put("resolucionAviso", notificaciones.getResolucionAviso());
        datos.put("fechaResolucionAviso", notificaciones.getFechaResolucion());
        datos.put("tipoIdentificacion", notificaciones.getTipoIdentificacion());
        datos.put("idUsuario", notificaciones.getIdUsuario());
        datos.put("numeroComparendo", notificaciones.getNumeroComparendo());
        datos.put("fechaComparendo", notificaciones.getFechaComparendo());

        try {
            Template template = freeMarkerConfiguration.getTemplate("plantillaNotificacion.html");
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, datos);
        } catch (IOException | TemplateException e) {
            throw new PlantillaException("Error al generar la plantilla HTML", e); // Aquí lanzas la excepción personalizada
        }
    }

    public byte[] generarPDF(String htmlProcesado) throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlProcesado);
            renderer.layout();
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        }
    }

}
