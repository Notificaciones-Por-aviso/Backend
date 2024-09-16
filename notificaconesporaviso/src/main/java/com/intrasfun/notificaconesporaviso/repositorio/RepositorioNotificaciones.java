package com.intrasfun.notificaconesporaviso.repositorio;

import com.intrasfun.notificaconesporaviso.entidad.Notificaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioNotificaciones extends JpaRepository<Notificaciones, String> {
    List<Notificaciones> findByIdUsuario(String idUsuario);
    Notificaciones findByNumeroComparendo(String numeroComparendo);


}
