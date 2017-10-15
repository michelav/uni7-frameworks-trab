package com.rocknrollers.distribuidora.biz;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.rocknrollers.distribuidora.biz.entity.Camisa;

/** 
 * Session Bean implementation class EstoqueBean
 */
@Stateless
@LocalBean
public class EstoqueBean implements EstoqueBeanLocal {
	
	@PersistenceContext
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public EstoqueBean() {}

	@Override
	public List<Camisa> listarCamisas() {
		return em.createNamedQuery("Camisa.findAll", Camisa.class).getResultList();
	}

	@Override
	public Camisa incluirCamisa(Camisa camisa) {
		TypedQuery<Camisa> query = em.createNamedQuery("Camisa.findByNameSize",Camisa.class);
		query.setParameter("nome", camisa.getNome()).setParameter("tamanho", camisa.getTamanho());
		if (query.getResultList().isEmpty()) {
			em.persist(camisa);
		} else {
			throw new RuntimeException(
				String.format(
				"Camisa (%s, %s) ja existe no estoque", 
				camisa.getNome(), camisa.getTamanho()));
		}
		return camisa;
	}
}
