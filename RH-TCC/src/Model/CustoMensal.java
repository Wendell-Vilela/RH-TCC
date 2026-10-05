package model;

import java.math.BigDecimal;
import java.util.List;

public class CustoMensal {
    private BigDecimal investimentoTotal;
    private BigDecimal salarioTotal;
    private BigDecimal encargoTotal;
    private BigDecimal beneficioTotal;
    private List<Item> itens;

    public static class Item {
        private Long id;
        private String nome;
        private BigDecimal salario;
        private BigDecimal encargos;
        private BigDecimal beneficios;

        public Item(Long id, String nome, BigDecimal salario, BigDecimal encargos, BigDecimal beneficios) {
            this.id = id;
            this.nome = nome;
            this.salario = salario;
            this.encargos = encargos;
            this.beneficios = beneficios;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public BigDecimal getSalario() {
            return salario;
        }

        public void setSalario(BigDecimal salario) {
            this.salario = salario;
        }

        public BigDecimal getEncargos() {
            return encargos;
        }

        public void setEncargos(BigDecimal encargos) {
            this.encargos = encargos;
        }

        public BigDecimal getBeneficios() {
            return beneficios;
        }

        public void setBeneficios(BigDecimal beneficios) {
            this.beneficios = beneficios;
        }
    }

    public BigDecimal getInvestimentoTotal() {
        return investimentoTotal;
    }

    public void setInvestimentoTotal(BigDecimal investimentoTotal) {
        this.investimentoTotal = investimentoTotal;
    }

    public BigDecimal getSalarioTotal() {
        return salarioTotal;
    }

    public void setSalarioTotal(BigDecimal salarioTotal) {
        this.salarioTotal = salarioTotal;
    }

    public BigDecimal getEncargoTotal() {
        return encargoTotal;
    }

    public void setEncargoTotal(BigDecimal encargoTotal) {
        this.encargoTotal = encargoTotal;
    }

    public BigDecimal getBeneficioTotal() {
        return beneficioTotal;
    }

    public void setBeneficioTotal(BigDecimal beneficioTotal) {
        this.beneficioTotal = beneficioTotal;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}
