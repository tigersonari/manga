package br.manga.model.converterjpa;

import br.manga.model.Classificacao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ClassificacaoConverter implements AttributeConverter<Classificacao, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Classificacao classificacao) {
        return classificacao == null ? null : classificacao.getId();
    }

    @Override
    public Classificacao convertToEntityAttribute(Integer id) {
        if (id == null) return null;
        for (Classificacao c : Classificacao.values()) {
            if (c.getId() == id) return c;
        }
        throw new IllegalArgumentException("ID de Classificacao inválido: " + id);
    }
}