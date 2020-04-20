package br.com.fiap.nac.view;

import java.util.Calendar;

import br.com.fiap.nac.entity.Endereco;
import br.com.fiap.nac.entity.Usuario;
import br.com.fiap.nac.service.EnderecoService;
import br.com.fiap.nac.service.UsuarioService;

/**
 * Classe main.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 9 de abr de 2020 22:13:20
 * @version 1.0
 */
public class UsuarioConsoleView {

	/**
	 * Atributo usuarioService
	 */
	private static UsuarioService usuarioService = new UsuarioService();

	/**
	 * Atributo enderecoService
	 */
	private static EnderecoService enderecoService = new EnderecoService();

	/**
	 * Atributo ID_TESTE
	 */
	private static final Long ID_TESTE = 1L;

	/**
	 * Método responsável por chamar cada método de CRUD para a entidade {@link Usuario}. OBS: Todos os métodos podem ser
	 * executados de uma só vez =D
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Buscando Endereco existente
			Endereco endereco = enderecoService.findOne(ID_TESTE);

			// Criando e cadastrando novo Usuario
			Usuario usuario = new Usuario(endereco, "Teste", "Teste", "teste", "123", Calendar.getInstance());
			usuarioService.save(usuario);

			// Buscando Usuario criado
			Usuario usuarioBD = usuarioService.findOne(usuario.getId());
			System.out.println(usuarioBD.toString());

			// Atualizando a senha do Usuario criado
			usuarioBD.setSenha("456");
			usuarioService.update(usuarioBD);
			System.out.println("Senha atualizada: " + usuarioBD.getSenha());

			// Removendo Usuario criado
			usuarioService.delete(usuarioBD.getId());

			// Buscando todos os Usuarios existentes
			usuarioService.findAll().forEach(us -> {
				System.out.println(us.toString());
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fechando conexões
			enderecoService.closeConnection();
			usuarioService.closeConnection();
		}
	}

}
