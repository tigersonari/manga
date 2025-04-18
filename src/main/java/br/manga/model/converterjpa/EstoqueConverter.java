package br.manga.model.converterjpa;

import br.manga.model.Estoque;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EstoqueConverter implements AttributeConverter<Estoque, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Estoque estoque) {
        return estoque == null ? null : estoque.getId();
    }

    @Override
    public Estoque convertToEntityAttribute(Integer id) {
        if (id == null) return null;
        for (Estoque e : Estoque.values()) {
            if (e.getId() == id) return e;
        }
        throw new IllegalArgumentException("ID de Estoque inválido: " + id);
    }
}