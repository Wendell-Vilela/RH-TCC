package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaPDI extends JPanel {

private static final long serialVersionUID = 1L;

private final JComboBox<String> area = new JComboBox<>(
    new String[]{
        "Selecione a area",
        "Tecnologia",
        "Recursos Humanos",
        "Financeiro",
        "Marketing"
    }
);

private final JComboBox<String> funcionario = new JComboBox<>(
    new String[]{
        "Selecione o funcionario",
        "João da Silva",
        "Maria Santos",
        "Pedro Oliveira"
    }
);

private final JButton selecionar = new JButton("Selecionar");

private final JProgressBar progresso = new JProgressBar(0, 100);

private final JTable tabela = new JTable();


public TelaPDI() {

    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    montar();
}


private void montar() {

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

    JPanel painelProgresso = new JPanel(new BorderLayout());

    painelProgresso.setBorder(
        BorderFactory.createTitledBorder("Progresso do Plano")
    );

    progresso.setValue(65);
    progresso.setStringPainted(true);

    painelProgresso.add(progresso, BorderLayout.CENTER);


    String[] colunas = {
        "Proxima Acao",
        "Descricao",
        "Prazo",
        "Status"
    };

    DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

    modelo.addRow(new Object[]{
        "Curso de Java",
        "Realizar curso de aperfeicoamento em Java",
        "15/09/2026",
        "Em andamento"
    });

    modelo.addRow(new Object[]{
        "Treinamento de Comunicacao",
        "Participar de treinamento para melhorar a comunicacao",
        "30/09/2026",
        "Pendente"
    });

    modelo.addRow(new Object[]{
        "Projeto em equipe",
        "Participar de um projeto para desenvolver trabalho em equipe",
        "15/10/2026",
        "Pendente"
    });

    modelo.addRow(new Object[]{
        "Avaliacao de desempenho",
        "Realizar nova avaliacao com o gestor",
        "30/10/2026",
        "Finalizada"
    });

    tabela.setModel(modelo);

    tabela.setRowHeight(35);

    tabela.getColumnModel().getColumn(0).setPreferredWidth(150);
    tabela.getColumnModel().getColumn(1).setPreferredWidth(400);
    tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
    tabela.getColumnModel().getColumn(3).setPreferredWidth(120);

    JScrollPane scrollTabela = new JScrollPane(tabela);


    JPanel painelTabela = new JPanel(new BorderLayout());

    painelTabela.setBorder(
        BorderFactory.createTitledBorder("Acoes do Plano de Desenvolvimento")
    );

    painelTabela.add(scrollTabela, BorderLayout.CENTER);


    JPanel parteSuperior = new JPanel(new BorderLayout());

    parteSuperior.add(selecao, BorderLayout.NORTH);
    parteSuperior.add(painelProgresso, BorderLayout.CENTER);

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

public JProgressBar getProgresso() {
    return progresso;
}

public JTable getTabela() {
    return tabela;
}

}
