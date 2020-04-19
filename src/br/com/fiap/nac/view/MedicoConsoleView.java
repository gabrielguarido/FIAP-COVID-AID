package br.com.fiap.nac.view;

import java.util.Calendar;

import br.com.fiap.nac.entity.Endereco;
import br.com.fiap.nac.entity.Medico;
import br.com.fiap.nac.entity.Usuario;
import br.com.fiap.nac.enumerator.AreaMedicinaEnum;
import br.com.fiap.nac.service.EnderecoService;
import br.com.fiap.nac.service.MedicoService;

/**
 * Classe main.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 18 de abr de 2020 18:26:04
 * @version 1.0
 */
public class MedicoConsoleView {

	/**
	 * Atributo medicoService
	 */
	private static MedicoService medicoService = new MedicoService();

	/**
	 * Atributo enderecoService
	 */
	private static EnderecoService enderecoService = new EnderecoService();

	/**
	 * Atributo ID_TESTE
	 */
	private static final Long ID_TESTE = 1L;

	/**
	 * Método responsável por chamar cada método de CRUD para a entidade {@link Medico}. 
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

			// Criando e cadastrando novo Medico
			Medico medico = new Medico(usuario, "745869", AreaMedicinaEnum.CLINICA);
			medicoService.save(medico);

			// Buscando Medico criado
			Medico medicoBD = medicoService.findOne(medico.getId());
			System.out.println(medicoBD.toString());

			// Atualizando o CRM do Medico criado
			medicoBD.setCrm("4713596");
			medicoService.update(medicoBD);
			System.out.println("CRM atualizado: " + medicoBD.getCrm());

			// Removendo Medico criado
			medicoService.delete(medicoBD.getId());

			// Buscando todos os Medicos existentes
			medicoService.findAll().forEach(med -> {
				System.out.println(med.toString());
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fechando conexões
			enderecoService.closeConnection();
			medicoService.closeConnection();
		}
	}

}
