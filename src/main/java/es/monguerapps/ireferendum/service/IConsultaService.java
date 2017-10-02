package es.monguerapps.ireferendum.service;

import java.util.List;

import es.monguerapps.ireferendum.beans.Consulta;

public interface IConsultaService {

	List<Consulta> getAllConsultas();

    boolean addConsulta(Consulta consulta);
    
    Consulta avanzarConsulta(Integer id);

    Consulta getConsultaById(Integer id);
}
