package br.manga.model.converterjpa;

import br.manga.model.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Status status) {
        return status == null ? null : status.getId();
    }

    @Override
    public Status convertToEntityAttribute(Integer id) {
        if (id == null) return null;
        for (Status s : Status.values()) {
            if (s.getId() == id) return s;
        }
        throw new IllegalArgumentException("ID de Status inválido: " + id);
    }
}