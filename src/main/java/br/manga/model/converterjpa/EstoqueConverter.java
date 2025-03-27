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
        return Estoque.valueOf(id);
    }
    
}
