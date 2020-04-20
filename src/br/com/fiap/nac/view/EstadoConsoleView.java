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

			// Buscando Estado criado
			Estado estadoBD = estadoService.findOne(estado.getId());
			System.out.println(estadoBD.toString());

			// Atualizando Estado cadastro
			estadoBD.setDescricao("Bras�lia");
			estadoService.update(estadoBD);
			System.out.println("Estado atualizado: " + estadoBD.getDescricao());

			// Removendo Estado
			estadoService.delete(estadoBD.getId());

			// Buscando todos os Estados existentes
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
