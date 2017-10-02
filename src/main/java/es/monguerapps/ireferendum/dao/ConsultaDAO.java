package es.monguerapps.ireferendum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.monguerapps.ireferendum.beans.Consulta;

@Transactional
@Repository
public class ConsultaDAO implements IConsultaDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Consulta> getAllConsultas() {
		final String hql = "from Consulta";
		return (List<Consulta>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Consulta getConsultaById(Integer id) {
		return entityManager.find(Consulta.class, id);
	}

	@Override
	public void createConsulta(Consulta consulta) {
		entityManager.persist(consulta);
	}

	@Override
	public Consulta updateConsulta(Consulta consulta) {
		Consulta cons = getConsultaById(consulta.getIdConsulta());
		cons.setEstado(consulta.getEstado());
		entityManager.flush();
		return cons;
	}

}
