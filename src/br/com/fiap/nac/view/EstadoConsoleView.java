package br.com.fiap.nac.view;

import java.util.List;

import br.com.fiap.nac.entity.Estado;
<<<<<<< Updated upstream
import br.com.fiap.nac.entity.Usuario;
import br.com.fiap.nac.exception.CommitException;
import br.com.fiap.nac.exception.ResourceNotFoundException;
import br.com.fiap.nac.exception.UniqueConstraintViolationException;
import br.com.fiap.nac.singleton.EntityManagerFactorySingleton;
=======
import br.com.fiap.nac.service.EstadoService;
>>>>>>> Stashed changes

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
	 * M�todo respons�vel por realizar o CRUD da entidade {@link Estado}.
	 *
	 * @author Brazil Code - Gustavo Zotarelli
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Criando e cadastrando novo Estado
			Estado estado = new Estado("BrRasz�lia", "DF");
			estadoService.save(estado);
			System.out.println("Estado cadastrado com sucesso!");

			// Atualizando Estado cadastro
			estado.setDescricao("Bras�lia");
			estadoService.update(estado);
			System.out.println("Estado atualizado: " + estado.getDescricao());

			// Removendo Estado
			estadoService.delete(estado.getId());
			System.out.println("Estado exclu�do com sucesso!");

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
