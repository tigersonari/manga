package br.manga.model.converterjpa;

import br.manga.model.Formato;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FormatoConverter implements AttributeConverter<Formato, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Formato formato) {
        return formato == null ? null : formato.getId();
    }

    @Override
    public Formato convertToEntityAttribute(Integer id) {
        if (id == null) return null;
        for (Formato f : Formato.values()) {
            if (f.getId() == id) return f;
        }
        throw new IllegalArgumentException("ID de Formato inválido: " + id);
    }
}