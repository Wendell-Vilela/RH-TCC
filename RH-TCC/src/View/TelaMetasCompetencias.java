package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaMetasCompetencias extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JTextField id = new JTextField(7);
    private final JTextField funcionario = new JTextField(25);
    private final JTextField cargo = new JTextField(20);
    private final JTextField gestor = new JTextField(20);
    private final JTextField area = new JTextField(20);
    private final JTextField prazo = new JTextField(15);

    private final JComboBox<String> status = new JComboBox<>(
        new String[]{"Selecione o status", "Pendente", "Em andamento", "Concluida"}
    );

    private final JTextArea meta = new JTextArea(4, 25);

    private final JTable tabela = new JTable();

    // Botoes
    private final JButton novo = new JButton("Novo");
    private final JButton salvar = new JButton("Salvar");
    private final JButton excluir = new JButton("Excluir");
    private final JButton limpar = new JButton("Limpar");

    public TelaMetasCompetencias() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        montar();
    }

    private void montar() {

        JPanel formulario = new JPanel(new GridBagLayout());

        formulario.setBorder(
            BorderFactory.createTitledBorder("Cadastro de Meta")
        );

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);

        componente(formulario, g, 0, "Codigo:", id);
        componente(formulario, g, 1, "Funcionario:", funcionario);
        componente(formulario, g, 2, "Cargo:", cargo);
        componente(formulario, g, 3, "Gestor:", gestor);
        componente(formulario, g, 4, "Area:", area);
        componente(formulario, g, 5, "Prazo:", prazo);
        componente(formulario, g, 6, "Status:", status);
        componente(formulario, g, 7, "Meta:", new JScrollPane(meta));

        id.setEditable(false);

        meta.setLineWrap(true);
        meta.setWrapStyleWord(true);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT));

        botoes.add(novo);
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
            BorderFactory.createTitledBorder("Metas Cadastradas")
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

    public JTextField getFuncionario() {
        return funcionario;
    }

    public JTextField getCargo() {
        return cargo;
    }

    public JTextField getGestor() {
        return gestor;
    }

    public JTextField getArea() {
        return area;
    }

    public JTextField getPrazo() {
        return prazo;
    }

    public JComboBox<String> getStatus() {
        return status;
    }

    public JTextArea getMeta() {
        return meta;
    }

    public JTable getTabela() {
        return tabela;
    }

    // Getters dos botoes

    public JButton getNovo() {
        return novo;
    }

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