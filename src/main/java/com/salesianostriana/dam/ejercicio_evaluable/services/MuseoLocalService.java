package com.salesianostriana.dam.ejercicio_evaluable.services;

import com.salesianostriana.dam.ejercicio_evaluable.errors.InvalidMuseumDataException;
import com.salesianostriana.dam.ejercicio_evaluable.errors.MuseumNotFoundException;
import com.salesianostriana.dam.ejercicio_evaluable.errors.NoItemsFound;
import com.salesianostriana.dam.ejercicio_evaluable.models.DTO.MuseumResponseDto;
import com.salesianostriana.dam.ejercicio_evaluable.models.MuseoLocal;
import com.salesianostriana.dam.ejercicio_evaluable.repositories.MuseoLocalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MuseoLocalService {

    private final MuseoLocalRepository repository;

    public List<MuseoLocal> getAll() {
        List<MuseoLocal> list = repository.findAll();
        if (list.isEmpty()) {
            throw new NoItemsFound("No se ha encontrado ningún museo.");
        }
        return list;
    }

    public MuseoLocal crearMuseo(MuseoLocal museo) {
        return repository.save(comprobarCampos(museo));
    }

    public MuseoLocal comprobarCampos(MuseoLocal museo) {

        if (museo.getNombre().isEmpty()) {
            throw new InvalidMuseumDataException("El nombre no debe ser vacio.");
        }

        return museo;
    }

    public MuseoLocal obtenerDetalles(long id) {
        return repository.findById(id).orElseThrow(() -> new MuseumNotFoundException("No se ha encontrado el museo de ID " + id));
    }
}
