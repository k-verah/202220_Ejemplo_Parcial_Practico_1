package co.edu.uniandes.dse.parcialejemplo.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialejemplo.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialejemplo.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service


public class EspecialidadService {

    @Autowired
    EspecialidadRepository especialidadRepository;

    @Autowired
    MedicoRepository medicoRepository;

@Transactional
public EspecialidadEntity createEspecialidad(EspecialidadEntity especialidadEntity) throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicia proceso de creación de la especialidad");
                
        if (especialidadEntity.getMedico() == null)
                throw new IllegalOperationException("Medico is not valid");
                
        Optional<MedicoEntity> medicoEntity = medicoRepository.findById(((EspecialidadEntity) especialidadEntity.getMedico()).getId());
        if (MedicoEntity.isEmpty())
                throw new IllegalOperationException("Medico is not valid");
 
        if (!validateISBN(especialidadEntity.getIsbn()))
                throw new IllegalOperationException("ISBN is not valid");

        if (!especialidadRepository.findByIsbn(especialidadEntity.getIsbn()).isEmpty())
                throw new IllegalOperationException("ISBN already exists");

        especialidadEntity.setMedico(MedicoEntity.get());
        log.info("Termina proceso de creación de la especialidad");
        return especialidadRepository.save(especialidadEntity);
}

}
