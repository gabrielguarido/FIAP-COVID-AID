package br.com.fiap.nac.view;

import br.com.fiap.nac.entity.Cidade;
import br.com.fiap.nac.entity.Endereco;
import br.com.fiap.nac.service.CidadeService;
import br.com.fiap.nac.service.EnderecoService;

/**
 * Classe main.
 *
 * @author Brazil Code - Andrew Pereira
 * @since 10 de abr de 2020 16:25:41
 * @version 1.0
 */
public class EnderecoConsoleView {

	/**
	 * Atributo usuarioService
	 */
	private static EnderecoService enderecoService = new EnderecoService();

	/**
	 * Atributo cidadeService
	 */
	private static CidadeService cidadeService = new CidadeService();

	/**
	 * Atributo ID_TESTE
	 */
	private static final Long ID_TESTE = 1L;

	/**
	 * M�todo respons�vel por realizar o CRUD da entidade.
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			// Buscando cidade existente
			Cidade cidade = cidadeService.findOne(ID_TESTE);

			// Criando e cadastrando novo ednereço
			Endereco endereco = new Endereco("Capao", "05798100", "Casa2", "Rua gersom marques da silva", cidade);
			enderecoService.save(endereco);

			// Buscando endereco criado
			Endereco enderecoBD = enderecoService.findOne(endereco.getId());
			System.out.println(enderecoBD.toString());

			// Atualizando a endereco do Usuario criado
			enderecoBD.setBairro("bairro");
			enderecoBD.setCep("00000-000");
			enderecoBD.setComplemento("complemento");
			enderecoBD.setLogradouro("Logradouro");

			enderecoService.update(enderecoBD);
			System.out.println(enderecoBD.toString());

			// Removendo Endereços criado
			enderecoService.delete(enderecoBD.getId());

			// Buscando todos os Endereços existentes
			enderecoService.findAll().forEach(end -> {
				System.out.println(end.toString());
			});

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			enderecoService.closeConnection();
		}

		// Closing factory n entity manager

	}

}
