package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaCadastroCursos extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JTextField id = new JTextField(7);
    private final JTextField nomeCurso = new JTextField(25);
    private final JTextField cargaHoraria = new JTextField(20);
    private final JTextField fornecedor = new JTextField(20);
    private final JComboBox<String> tipoCurso = new JComboBox<>(
    		new String[] {"Selecione se o curso é interno ou externo", "Curso Interno", "Curso Externo"});

    private final JComboBox<String> status = new JComboBox<>(
        new String[]{"Selecione o status", "Em andamento", "Concluida"}
    );
    
    private final JTextField dataInicio = new JTextField(20);

    private final JTable tabela = new JTable();

    // Botoes
    private final JButton salvar = new JButton("Salvar");
    private final JButton excluir = new JButton("Excluir");
    private final JButton limpar = new JButton("Limpar");

    public TelaCadastroCursos() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        montar();
    }

    private void montar() {

        JPanel formulario = new JPanel(new GridBagLayout());

        formulario.setBorder(
            BorderFactory.createTitledBorder("Cadastro de Curso")
        );

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);

        componente(formulario, g, 0, "Codigo:", id);
        componente(formulario, g, 1, "Nome:", nomeCurso);
        componente(formulario, g, 2, "Carga horária:", cargaHoraria);
        componente(formulario, g, 4, "Fornecedor:", fornecedor);
        componente(formulario, g, 5, "Tipo:", tipoCurso);
        componente(formulario, g, 6, "Status:", status);
        componente(formulario, g, 7, "Data de Início:", dataInicio);

        id.setEditable(false);


        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT));

        botoes.add(salvar);
        botoes.add(excluir);
        botoes.add(limpar);

        String[] colunas = {
            "Codigo",
            "Funcionario",
            "Cargo",
            "Gestor",
            "Area",
            "Meta",
            "Prazo",
            "Status"
        };

        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

        tabela.setModel(modelo);

        JScrollPane scrollTabela = new JScrollPane(tabela);

        JPanel painelTabela = new JPanel(new BorderLayout());

        painelTabela.setBorder(
            BorderFactory.createTitledBorder("Cursos Cadastrados")
        );

        painelTabela.add(scrollTabela, BorderLayout.CENTER);

        JPanel parteSuperior = new JPanel(new BorderLayout());

        parteSuperior.add(formulario, BorderLayout.NORTH);
        parteSuperior.add(botoes, BorderLayout.SOUTH);

        JPanel conteudo = new JPanel(new BorderLayout());

        conteudo.add(parteSuperior, BorderLayout.NORTH);
        conteudo.add(painelTabela, BorderLayout.CENTER);

        add(conteudo, BorderLayout.CENTER);
    }

    private void componente(
        JPanel painel,
        GridBagConstraints g,
        int linha,
        String texto,
        Component campo
    ) {
        g.gridx = 0;
        g.gridy = linha;
        g.weightx = 0;
        g.fill = GridBagConstraints.NONE;

        painel.add(new JLabel(texto), g);

        g.gridx = 1;
        g.weightx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;

        painel.add(campo, g);
    }

    // Getters dos campos

    public JTextField getId() {
        return id;
    }

    public JTextField getNomeCurso() {
        return nomeCurso;
    }

    public JTextField getCargaHoraria() {
        return cargaHoraria;
    }

    public JTextField getFornecedor() {
        return fornecedor;
    }

    public JComboBox<String> getTipoCurso() {
        return tipoCurso;
    }


    public JComboBox<String> getStatus() {
        return status;
    }

    public JTextField getDataInicio() {
        return dataInicio;
    }

    public JTable getTabela() {
        return tabela;
    }

    // Getters dos botoes

    public JButton getSalvar() {
        return salvar;
    }

    public JButton getExcluir() {
        return excluir;
    }

    public JButton getLimpar() {
        return limpar;
    }
}
