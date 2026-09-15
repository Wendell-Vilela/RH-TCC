package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaAvaliacoesFeedbacks extends JPanel {

private static final long serialVersionUID = 1L;

private final JComboBox<String> area = new JComboBox<>(
    new String[]{"Selecione a area", "Tecnologia", "Recursos Humanos", "Financeiro", "Marketing"}
);

private final JComboBox<String> funcionario = new JComboBox<>(
    new String[]{"Selecione o funcionario", "Josimar dos Santos", "Roberto Minelo", "Gabrielly Siqueira", "Mathias Fonseca"}
);

private final JButton selecionar = new JButton("Selecionar");

private final JTextField autoavaliacao = new JTextField(5);
private final JTextField avaliacaoGestor = new JTextField(5);
private final JTextField avaliacaoPares = new JTextField(5);

private final JTable tabelaFeedbacks = new JTable();


public TelaAvaliacoesFeedbacks() {

    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    montar();
}


private void montar() {

    // Selecao do funcionario

    JPanel selecao = new JPanel(new GridBagLayout());

    selecao.setBorder(
        BorderFactory.createTitledBorder("Selecionar Funcionario")
    );

    GridBagConstraints g = new GridBagConstraints();

    g.insets = new Insets(5, 5, 5, 5);

    componente(selecao, g, 0, "Area:", area);
    componente(selecao, g, 1, "Funcionario:", funcionario);

    g.gridx = 2;
    g.gridy = 1;
    g.weightx = 0;
    g.fill = GridBagConstraints.NONE;

    selecao.add(selecionar, g);


    // Avaliacoes

    JPanel avaliacoes = new JPanel(new GridBagLayout());

    avaliacoes.setBorder(
        BorderFactory.createTitledBorder("Avaliacoes")
    );

    GridBagConstraints a = new GridBagConstraints();

    a.insets = new Insets(5, 5, 5, 5);

    componente(avaliacoes, a, 0, "Autoavaliacao:", autoavaliacao);
    componente(avaliacoes, a, 1, "Avaliacao do Gestor:", avaliacaoGestor);
    componente(avaliacoes, a, 2, "Avaliacao dos Pares:", avaliacaoPares);

    autoavaliacao.setEditable(false);
    avaliacaoGestor.setEditable(false);
    avaliacaoPares.setEditable(false);


    // Tabela de feedbacks

    String[] colunas = {
        "Competencia",
        "Avaliador",
        "Data",
        "Feedback"
    };

    DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

    tabelaFeedbacks.setModel(modelo);

    tabelaFeedbacks.setRowHeight(40);

    tabelaFeedbacks.getColumnModel().getColumn(0).setPreferredWidth(120);
    tabelaFeedbacks.getColumnModel().getColumn(1).setPreferredWidth(100);
    tabelaFeedbacks.getColumnModel().getColumn(2).setPreferredWidth(100);
    tabelaFeedbacks.getColumnModel().getColumn(3).setPreferredWidth(450);

    JScrollPane scrollTabela = new JScrollPane(tabelaFeedbacks);

    JPanel painelFeedbacks = new JPanel(new BorderLayout());

    painelFeedbacks.setBorder(
        BorderFactory.createTitledBorder("Feedbacks Recentes")
    );

    painelFeedbacks.add(scrollTabela, BorderLayout.CENTER);


    // Conteudo

    JPanel parteSuperior = new JPanel(new BorderLayout());

    parteSuperior.add(selecao, BorderLayout.NORTH);
    parteSuperior.add(avaliacoes, BorderLayout.CENTER);

    JPanel conteudo = new JPanel(new BorderLayout());

    conteudo.add(parteSuperior, BorderLayout.NORTH);
    conteudo.add(painelFeedbacks, BorderLayout.CENTER);

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


// Getters

public JComboBox<String> getArea() {
    return area;
}

public JComboBox<String> getFuncionario() {
    return funcionario;
}

public JButton getSelecionar() {
    return selecionar;
}

public JTextField getAutoavaliacao() {
    return autoavaliacao;
}

public JTextField getAvaliacaoGestor() {
    return avaliacaoGestor;
}

public JTextField getAvaliacaoPares() {
    return avaliacaoPares;
}

public JTable getTabelaFeedbacks() {
    return tabelaFeedbacks;
}

}