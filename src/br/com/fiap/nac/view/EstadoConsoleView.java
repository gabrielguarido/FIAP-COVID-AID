package br.com.fiap.nac.view;

import java.util.List;
import br.com.fiap.nac.entity.Estado;
import br.com.fiap.nac.exception.CommitException;
import br.com.fiap.nac.exception.ResourceNotFoundException;
import br.com.fiap.nac.exception.UniqueConstraintViolationException;
import br.com.fiap.nac.singleton.EntityManagerFactorySingleton;
import br.com.fiap.nac.service.EstadoService;

/**
 * Classe main.
 *
 * @author Brazil Code - Gustavo Zotarelli
 * @since 10 de abr de 2020 15:51:03
 * @version 1.0
 */
public class EstadoConsoleView {

	/**
	 * Atributo estadoService
	 */
	private static EstadoService estadoService = new EstadoService();

	/**
	 * Método responsável por realizar o CRUD da entidade {@link Estado}.
	 *
	 * @author Brazil Code - Gustavo Zotarelli
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Criando e cadastrando novo Estado
			Estado estado = new Estado("BrRaszília", "DF");
			estadoService.save(estado);
			System.out.println("Estado cadastrado com sucesso!");

			// Atualizando Estado cadastro
			estado.setDescricao("Brasília");
			estadoService.update(estado);
			System.out.println("Estado atualizado: " + estado.getDescricao());

			// Removendo Estado
			estadoService.delete(estado.getId());
			System.out.println("Estado excluído com sucesso!");

			// Buscando todos os Estados existentes
			estadoService.findAll().forEach(es -> {
				System.out.println(es.toString());
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			estadoService.closeConnection();
		}
	}

}
