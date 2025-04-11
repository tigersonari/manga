/*package br.manga.model.converterjpa;

import br.manga.model.TipoUsuario;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoUsuarioConverter implements AttributeConverter<TipoUsuario, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoUsuario tipoUsuario) {


            return tipoUsuario == null ? null : tipoUsuario.getID();

    }

    @Override
    public TipoUsuario convertToEntityAttribute(Integer ID) {
        return TipoUsuario.valueOf(id);
    }
    
}*/
