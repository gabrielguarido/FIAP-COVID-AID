package br.com.fiap.nac.view;

import br.com.fiap.nac.entity.Estado;
import br.com.fiap.nac.service.EstadoService;

/**
 * Classe main
 *
 * @author Brazil Code - Andrew Pererira
 * @since Apr 19, 2020 7:49:42 PM
 * @version 1.0
 */
public class EstadoConsoleView {

	/**
	 * Atributo estadoService
	 */
	private static EstadoService estadoService = new EstadoService();

	/**
	 * Método responsável por realizar o CRUD da entidade {@link Estado}.
	 * OBS: Todos os métodos podem ser executados de uma só vez =D
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

			// Buscando Estado criado
			Estado estadoBD = estadoService.findOne(estado.getId());
			System.out.println(estadoBD.toString());

			// Atualizando Estado criado
			estadoBD.setDescricao("Brasília");
			estadoService.update(estadoBD);
			System.out.println("Estado atualizado: " + estadoBD.getDescricao());

			// Removendo Estado criado
			estadoService.delete(estadoBD.getId());

			// Buscando todos os Estados existentes
			estadoService.findAll().forEach(es -> {
				System.out.println(es.toString());
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fechando conexões
			estadoService.closeConnection();
		}

}
}
