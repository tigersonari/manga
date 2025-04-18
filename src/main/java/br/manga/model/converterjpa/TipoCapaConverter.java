package br.manga.model.converterjpa;

import br.manga.model.TipoCapa;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoCapaConverter implements AttributeConverter<TipoCapa, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoCapa tipoCapa) {
        return tipoCapa == null ? null : tipoCapa.getID();
    }

    @Override
    public TipoCapa convertToEntityAttribute(Integer id) {
        if (id == null) return null;
        for (TipoCapa t : TipoCapa.values()) {
            if (t.getID() == id) return t;
        }
        throw new IllegalArgumentException("ID de TipoCapa inválido: " + id);
    }
}