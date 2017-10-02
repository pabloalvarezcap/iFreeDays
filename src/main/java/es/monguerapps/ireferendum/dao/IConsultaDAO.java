package es.monguerapps.ireferendum.dao;

import java.util.List;

import es.monguerapps.ireferendum.beans.Consulta;

public interface IConsultaDAO {
	List<Consulta> getAllConsultas();
	Consulta getConsultaById(Integer id);
	void createConsulta(Consulta consulta);
	Consulta updateConsulta(Consulta consulta);
}
