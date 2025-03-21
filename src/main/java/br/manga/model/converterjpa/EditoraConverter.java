package br.manga.model.converterjpa;

import br.manga.model.Editora;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EditoraConverter implements AttributeConverter<Editora, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Editora editora) {


            return editora == null ? null : editora.getId();

    }

    @Override
    public Editora convertToEntityAttribute(Integer id) {
        return Editora.valueOf(id);
    }
    
}
