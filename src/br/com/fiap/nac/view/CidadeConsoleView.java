package br.com.fiap.nac.view;

import br.com.fiap.nac.entity.Cidade;
import br.com.fiap.nac.entity.Estado;
import br.com.fiap.nac.service.CidadeService;

/**
 * Classe main.
 *
 * @author Brazil Code -Andrew Pereira
 * @since 10 de abr de 2020 16:24:49
 * @version 1.0
 */
public class CidadeConsoleView {

	/**
	 * Atributo usuarioService
	 */
	private static CidadeService cidadeService = new CidadeService();

	/**
	 * Atributo ID_TESTE
	 */
	private static final Long ID_TESTE = 1L;

	/**
	 * Método responsável por ...
	 *
	 * @author Brazil Code - Andrew Pereira OBS: Todos os m�todos podem ser executados de uma s� vez =D
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Buscando estado existente
			Estado estado = estadoService.findOne(ID_TESTE);

			// Criando e cadastrando nova cidade
			Cidade cidade = new Cidade("Cidade da putaria", estado);
			cidadeService.save(cidade);

			// Buscando cidade criada
			Cidade cidadeBD = cidadeService.findOne(cidade.getId());
			System.out.println(cidadeBD.toString());

			// Atualizando a cidade criada
			cidadeBD.setDescricao("CIDADE DOS PUTEIROS");
			cidadeService.update(cidadeBD);
			System.out.println("Cidade atualizada: " + cidadeBD.getDescricao());

			// Removendo Cidade criado
			cidadeService.delete(cidadeBD.getId());

			// Buscando todos os Usuarios existentes
			cidadeService.findAll().forEach(cd -> {
				System.out.println(cd.toString());
			});

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			cidadeService.closeConnection();
		}

	}

}
