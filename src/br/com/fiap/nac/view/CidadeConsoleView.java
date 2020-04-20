package br.com.fiap.nac.view;

import br.com.fiap.nac.entity.Cidade;
import br.com.fiap.nac.entity.Estado;
import br.com.fiap.nac.service.CidadeService;
import br.com.fiap.nac.service.EstadoService;

/**
 * Classe main.
 *
 * @author Brazil Code -Andrew Pereira
 * @since 10 de abr de 2020 16:24:49
 * @version 1.0
 */
public class CidadeConsoleView {

	/**
	 * Atributo cidadeService
	 */
	private static CidadeService cidadeService = new CidadeService();

	/**
	 * Atributo estadoService
	 */
	private static EstadoService estadoService = new EstadoService();

	/**
	 * Atributo ID_TESTE
	 */
	private static final Long ID_TESTE = 4L;

	/**
	 * Método responsável por realizar o CRUD da entidade. 
	 * OBS: Todos os métodos podem ser executados de uma só vez =D
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Buscando estado existente
			Estado estado = estadoService.findOne(ID_TESTE);

			// Criando e cadastrando nova Cidade
			Cidade cidade = new Cidade("São Paulo", estado);
			cidadeService.save(cidade);

			// Buscando Cidade criada
			Cidade cidadeBD = cidadeService.findOne(cidade.getId());
			System.out.println(cidadeBD.toString());

			// Atualizando a Cidade criada
			cidadeBD.setDescricao("Boituva");
			cidadeService.update(cidadeBD);
			System.out.println("Cidade atualizada: " + cidadeBD.getDescricao());

			// Removendo Cidade criado
			cidadeService.delete(cidadeBD.getId());

			// Buscando todos as Cidades existentes
			cidadeService.findAll().forEach(cid -> {
				System.out.println(cid.toString());
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fechando conexões
			estadoService.closeConnection();
			cidadeService.closeConnection();
		}
	}

}
