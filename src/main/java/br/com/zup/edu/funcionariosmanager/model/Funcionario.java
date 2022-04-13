package br.com.zup.edu.funcionariosmanager.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private LocalDate dataAdmissao;

    @Column(nullable = false)
    private BigDecimal salario;

    public Funcionario(String nome, String cpf, String endereco, LocalDate dataAdmissao, BigDecimal salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.dataAdmissao = dataAdmissao;
        this.salario = salario;
    }

    /**
     * @deprecated construtor para uso exclusivo do Hibernate
     */
    @Deprecated
    public Funcionario() {
    }

    public Long getId() {
        return id;
    }

    public void atualizaSalario() {

        if (!this.salarioPodeSerAtualizado()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        BigDecimal aumento = this.salario.multiply(new BigDecimal("0.1"));

        this.salario = this.salario.add(aumento);
    }

    private boolean salarioPodeSerAtualizado() {
        return tempoDeCasa() >= 1;
    }

    private int tempoDeCasa() {
        LocalDate hoje = LocalDate.now();

        int tempoDeCasa = Period.between(this.dataAdmissao, hoje).getYears();

        return tempoDeCasa;
    }
}
