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
public class MedicoService {

        @Autowired
        MedicoRepository medicoRepository;

        @Autowired
        EspecialidadRepository especialidadRepository;


@Transactional
public MedicoEntity createMedico(MedicoEntity medicoEntity) throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicia proceso de creación del medico");
                
        if (medicoEntity.getEspecialidad() == null)
                throw new IllegalOperationException("Especialidad  is not valid");
                
        Optional<EspecialidadEntity> especialidadEntity = especialidadRepository.findById(((EspecialidadEntity) medicoEntity.getEspecialidad()).getId());
        if (especialidadEntity.isEmpty())
                throw new IllegalOperationException("Editorial is not valid");

        if (!validateISBN(medicoEntity.getIsbn()))
                throw new IllegalOperationException("ISBN is not valid");

        if (!medicoRepository.findByIsbn(medicoEntity.getIsbn()).isEmpty())
                throw new IllegalOperationException("ISBN already exists");

        medicoEntity.setEspecialidad(especialidadEntity.get());
        log.info("Termina proceso de creación del medico");
        return medicoRepository.save(medicoEntity);
}


private boolean validateISBN(Object isbn) {
    return false;
}
            
}
