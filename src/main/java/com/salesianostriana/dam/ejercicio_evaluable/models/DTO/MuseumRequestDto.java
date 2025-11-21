package com.salesianostriana.dam.ejercicio_evaluable.models.DTO;

import com.salesianostriana.dam.ejercicio_evaluable.models.MuseoLocal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class MuseumRequestDto {

    private String nombre;
    private String ciudad;
    private Integer anioApertura;
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

    public MuseoLocal from() {
        return MuseoLocal.builder()
                .nombre(this.getNombre())
                .ciudad(this.getCiudad())
                .anioApertura(Year.of(this.getAnioApertura()))
                .salasExposicion(this.getSalasExposicion())
                .descripcionGeneral(this.getDescripcionGeneral())
                .urlOficial(this.getUrlOficial())
                .urlImagen(this.getUrlImagen())
                .build();

    }
}
