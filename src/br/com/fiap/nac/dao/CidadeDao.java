package br.com.fiap.nac.dao;

import java.util.Optional;

import br.com.fiap.nac.entity.Cidade;
import br.com.fiap.nac.exception.CommitException;
import br.com.fiap.nac.exception.ResourceNotFoundException;

public interface CidadeDao extends GenericDAO<Cidade, Long> {


}
