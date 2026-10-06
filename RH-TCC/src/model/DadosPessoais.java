package model;

public class DadosPessoais {

    private String nome;
    private String nascimento;
    private String estadoCivil;
    private String naturalidade;
    private String nacionalidade;
    private String endereco;
    private String cidade;

    public DadosPessoais() {
    }
    
    public DadosPessoais(String nome, String nascimento, String estadoCivil,
                         String naturalidade, String nacionalidade,
                         String endereco, String cidade) {

        this.nome = nome;
        this.nascimento = nascimento;
        this.estadoCivil = estadoCivil;
        this.naturalidade = naturalidade;
        this.nacionalidade = nacionalidade;
        this.endereco = endereco;
        this.cidade = cidade;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}