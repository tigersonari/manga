package br.manga.model.converterjpa;

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
    public TipoUsuario convertToEntityAttribute(Integer id) {
        if (id == null) return null;
        for (TipoUsuario tipo : TipoUsuario.values()) {
            if (tipo.getID() == id) return tipo;
        }
        throw new IllegalArgumentException("ID de TipoUsuario inválido: " + id);
    }
}