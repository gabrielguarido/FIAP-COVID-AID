package br.com.fiap.nac.view;

import java.util.Calendar;

import br.com.fiap.nac.entity.Endereco;
import br.com.fiap.nac.entity.Paciente;
import br.com.fiap.nac.entity.Usuario;
import br.com.fiap.nac.service.EnderecoService;
import br.com.fiap.nac.service.PacienteService;

/**
 * Classe main.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 19 de abr de 2020 10:54:35
 * @version 1.0
 */
public class PacienteConsoleView {

	/**
	 * Atributo pacienteService
	 */
	private static PacienteService pacienteService = new PacienteService();

	/**
	 * Atributo enderecoService
	 */
	private static EnderecoService enderecoService = new EnderecoService();

	/**
	 * Atributo ID_TESTE
	 */
	private static final Long ID_TESTE = 1L;

	/**
	 * Método responsável por chamar cada método de CRUD para a entidade {@link Paciente}. 
	 * OBS: Todos os métodos podem ser executados de uma só vez =D
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Buscando Endereco existente e criado Usuario
			Endereco endereco = enderecoService.findOne(ID_TESTE);
			Usuario usuario = new Usuario(endereco, "teste", "teste", "teste", "teste", Calendar.getInstance());

			// Criando e cadastrando novo Paciente
			Paciente paciente = new Paciente(usuario, "123456789", 19);
			pacienteService.save(paciente);

			// Buscando Paciente criado
			Paciente pacienteBD = pacienteService.findOne(paciente.getId());
			System.out.println(pacienteBD.toString());

			// Atualizando CPF do Paciente criado
			pacienteBD.setCpf("987654321");
			pacienteService.update(pacienteBD);
			System.out.println("CPF atualizado: " + pacienteBD.getCpf());

			// Removendo Paciente criado
			pacienteService.delete(pacienteBD.getId());

			// Buscando todos os Pacientes existentes
			pacienteService.findAll().forEach(pac -> {
				System.out.println(pac.toString());
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fechando conexões
			enderecoService.closeConnection();
			pacienteService.closeConnection();
		}
	}

}
