package com.salesianostriana.dam.ejercicio_evaluable.models.DTO;

import com.salesianostriana.dam.ejercicio_evaluable.models.MuseoLocal;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class MuseumResponseDto {

    private String nombre;
    private String ciudad;
    private Year anioApertura;
    private Integer salasExposicion;
    private String descripcionGeneral;
    private String urlOficial;
    private String urlImagen;

    public static MuseumResponseDto to(MuseoLocal museoLocal) {
        return MuseumResponseDto.builder()
                .nombre(museoLocal.getNombre())
                .ciudad(museoLocal.getCiudad())
                .anioApertura(museoLocal.getAnioApertura())
                .salasExposicion(museoLocal.getSalasExposicion())
                .descripcionGeneral(museoLocal.getDescripcionGeneral())
                .urlOficial(museoLocal.getUrlOficial())
                .urlImagen(museoLocal.getUrlImagen())
                .build();
    }
}
