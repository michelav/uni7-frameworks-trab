package com.rocknrollers.distribuidora.biz;

import java.util.List;

import javax.ejb.Local;

import com.rocknrollers.distribuidora.biz.entity.Camisa;

@Local
public interface EstoqueBeanLocal {
	
	public List<Camisa> listarCamisas();
	
	public Camisa incluirCamisa(Camisa camisa);

}
