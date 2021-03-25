package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.marzio.curso.boot.domain.Funcionario;

@Repository
public interface FuncionarioDao {
	
	void save (Funcionario funcionario);
	
	void update(Funcionario funcionario);
	
	void delete(Long id);
	
	Funcionario findById(Long id);
	
	List<Funcionario> findAll();
}
