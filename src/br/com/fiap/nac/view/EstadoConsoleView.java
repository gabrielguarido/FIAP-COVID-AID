package br.com.fiap.nac.view;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import br.com.fiap.nac.dao.EstadoDAO;
import br.com.fiap.nac.dao.impl.EstadoDAOImpl;
import br.com.fiap.nac.entity.Cidade;
import br.com.fiap.nac.entity.Estado;
import br.com.fiap.nac.exception.CommitException;
import br.com.fiap.nac.exception.ResourceNotFoundException;
import br.com.fiap.nac.exception.UniqueConstraintViolationException;
import br.com.fiap.nac.service.CidadeService;
import br.com.fiap.nac.service.EstadoService;
import br.com.fiap.nac.singleton.EntityManagerFactorySingleton;

/**
 * Classe main
 *
 * @author Brazil Code - Andrew Pererira
 * @since Apr 19, 2020 7:49:42 PM
 * @version 1.0
 */
public class EstadoConsoleView {

	/**
	 * Atributo usuarioService
	 */
	private static EstadoService estadoService = new EstadoService();

	public static void main(String[] args) {

		try {
			// Criando e cadastrando novo estado
			Estado estado = new Estado("Rio Grande do sul", "rs");
			estadoService.save(estado);

			// Buscando cidade criada
			Estado estadoBD = estadoService.findOne(estado.getId());
			System.out.println(estadoBD.toString());

			// Atualizando o estado criado
			estadoBD.setDescricao("Estado Alterado");
			estadoService.update(estadoBD);
			System.out.println("estado atualizado: " + estadoBD.getDescricao());

			// Removendo Cidade criado
			estadoService.delete(estadoBD.getId());

			// Buscando todos os Usuarios existentes
			estadoService.findAll().forEach(es -> {
				System.out.println(es.toString());
			});
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			estadoService.closeConnection();
		}

	}

}
