package com.rocknrollers.prd.biz.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;


/**
 * The persistent class for the producao database table.
 * 
 */
@Entity
@NamedQuery(name="Producao.findAll", query="SELECT p FROM Producao p")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Producao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUCAO_PID_GENERATOR", sequenceName="PRODUCAO_PID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCAO_PID_GENERATOR")
	@XmlElement
	private Integer pid;

	/** Id do pedido de distribuição */
	@XmlElement
	private Integer rid;

	//bi-directional many-to-one association to ItensProducao
	@OneToMany(mappedBy="producao")
	@XmlElement
	private List<ItensProducao> itensProducaos;

	public Producao() {
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getRid() {
		return this.rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public List<ItensProducao> getItensProducaos() {
		return this.itensProducaos;
	}

	public void setItensProducaos(List<ItensProducao> itensProducaos) {
		this.itensProducaos = itensProducaos;
	}

	public ItensProducao addItensProducao(ItensProducao itensProducao) {
		getItensProducaos().add(itensProducao);
		itensProducao.setProducao(this);

		return itensProducao;
	}

	public ItensProducao removeItensProducao(ItensProducao itensProducao) {
		getItensProducaos().remove(itensProducao);
		itensProducao.setProducao(null);

		return itensProducao;
	}

}