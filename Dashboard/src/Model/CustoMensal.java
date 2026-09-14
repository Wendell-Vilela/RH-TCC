package Model;

import java.math.BigDecimal;

public class CustoMensal {
    private BigDecimal investimentoTotal;
    private BigDecimal salarioTotal;
    private BigDecimal encargoTotal;
    private BigDecimal beneficioTotal;

    
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

    
}