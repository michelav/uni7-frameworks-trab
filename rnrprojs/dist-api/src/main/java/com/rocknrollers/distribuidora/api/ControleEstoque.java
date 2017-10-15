package com.rocknrollers.distribuidora.api;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.rocknrollers.distribuidora.biz.EstoqueBeanLocal;
import com.rocknrollers.distribuidora.biz.entity.Camisa;

@Path("/estoque")
public class ControleEstoque {
	
	@EJB EstoqueBeanLocal bean;
	
	@GET
	@Path("camisas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listar() {
		return Response.status(Status.OK).entity(bean.listarCamisas()).build();
	}
	
	@POST
	@Path("camisas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response incluirEstoque(Camisa camisa) {
		Camisa included = null;
		try {
			included = bean.incluirCamisa(camisa);
		} catch(Throwable e) {
			throw new WebApplicationException(e.getMessage(), Status.BAD_REQUEST);
		}
		return Response.status(Status.CREATED).entity(included).build();
	}
}
