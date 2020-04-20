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
	 * Atributo enderecoService
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
	 * Método responsável por realizar o CRUD da entidade.
	 * OBS: Todos os métodos podem ser executados de uma só vez =D
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Buscando cidade existente
			Cidade cidade = cidadeService.findOne(ID_TESTE);

			// Criando e cadastrando novo Endereco
			Endereco endereco = new Endereco("Capao", "05798100", "Casa2", "Rua gersom marques da silva", cidade);
			enderecoService.save(endereco);

			// Buscando Endereco criado
			Endereco enderecoBD = enderecoService.findOne(endereco.getId());
			System.out.println(enderecoBD.toString());

			// Atualizando o Endereco criado
			enderecoBD.setBairro("bairro");
			enderecoBD.setCep("00000-000");
			enderecoBD.setComplemento("complemento");
			enderecoBD.setLogradouro("Logradouro");
			enderecoService.update(enderecoBD);
			System.out.println("Endereco atualizado: " + enderecoBD.toString());

			// Removendo Endereco criado
			enderecoService.delete(enderecoBD.getId());

			// Buscando todos os Enderecos existentes
			enderecoService.findAll().forEach(end -> {
				System.out.println(end.toString());
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fechando conexões
			cidadeService.closeConnection();
			enderecoService.closeConnection();
		}
	}

}
