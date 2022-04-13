package br.com.zup.edu.funcionariosmanager.repository;

import br.com.zup.edu.funcionariosmanager.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
}
