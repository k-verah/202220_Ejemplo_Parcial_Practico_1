package co.edu.uniandes.dse.parcialejemplo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class EspecialidadEntity {

    private String nombre;
    private String descripcion;
    private Long id;

    @ManyToMany(mappedBy = "especialidad")
    private List<MedicoEntity> books = new ArrayList<>();

    public Object getMedico() {
        return null;
    }

}
