package br.manga.model.converterjpa;

import br.manga.model.Genero;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GeneroConverter implements AttributeConverter<Genero, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Genero genero) {
        return genero == null ? null : genero.getId();
    }

    @Override
    public Genero convertToEntityAttribute(Integer id) {
        if (id == null) return null;
        for (Genero g : Genero.values()) {
            if (g.getId() == id) return g;
        }
        throw new IllegalArgumentException("ID de Genero inválido: " + id);
    }
}