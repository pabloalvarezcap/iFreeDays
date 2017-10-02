package es.monguerapps.ireferendum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.monguerapps.ireferendum.beans.Consulta;
import es.monguerapps.ireferendum.dao.IConsultaDAO;
import es.monguerapps.ireferendum.enums.EstadoConsulta;

@Service
public class ConsultaService implements IConsultaService {

	@Autowired
	private IConsultaDAO consultaDAO;

	@Override
	public List<Consulta> getAllConsultas() {
		return consultaDAO.getAllConsultas();
	}

	public synchronized boolean addConsulta(Consulta consulta) {
		consultaDAO.createConsulta(consulta);
		return true;
	}

	@Override
	public Consulta avanzarConsulta(Integer id) {
		final Consulta consulta = getConsultaById(id);
		EstadoConsulta estado = EstadoConsulta.fromString(consulta.getEstado());
		consulta.setEstado(estado.getEstadoSiguiente().getNombreEstado());
		return consultaDAO.updateConsulta(consulta);
	}

	@Override
	public Consulta getConsultaById(Integer id) {
		return consultaDAO.getConsultaById(id);
	}

}
