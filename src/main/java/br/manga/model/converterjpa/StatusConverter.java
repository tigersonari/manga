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
        return Status.valueOf(id);
    }
    
}
