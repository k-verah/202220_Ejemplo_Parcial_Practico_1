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

public class MedicoEntity {
    private String nombre;
    private String apellido;
    private String registro;
    private Long id;

    
        @ManyToMany
        private List<EspecialidadEntity> especialidad = new ArrayList<>();


        public Object getIsbn() {
            return null;
        }


        public void setEspecialidad(EspecialidadEntity especialidadEntity) {
        }


        public static boolean isEmpty() {
            return false;
        }

    
}
