package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaCursosTrilhas extends JPanel {

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
        "Joao da Silva",
        "Maria Santos",
        "Pedro Oliveira"
    }
);

private final JButton selecionar = new JButton("Selecionar");

private final JLabel horasRealizadas =
    new JLabel("Horas realizadas: 48h");

private final JLabel cursosConcluidos =
    new JLabel("Cursos concluidos: 6");

private final JLabel certificados =
    new JLabel("Certificados: 5");

private final JTable tabelaCursosInternos = new JTable();
private final JTable tabelaCursosExternos = new JTable();


public TelaCursosTrilhas() {

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


    // Resumo

    JPanel resumo = new JPanel(new GridLayout(1, 3, 15, 5));

    resumo.setBorder(
        BorderFactory.createTitledBorder("Resumo de Cursos")
    );

    horasRealizadas.setHorizontalAlignment(SwingConstants.CENTER);
    cursosConcluidos.setHorizontalAlignment(SwingConstants.CENTER);
    certificados.setHorizontalAlignment(SwingConstants.CENTER);

    resumo.add(horasRealizadas);
    resumo.add(cursosConcluidos);
    resumo.add(certificados);


    // Tabela de cursos internos

    String[] colunas = {
        "Curso",
        "Carga Horaria",
        "Fornecedor",
        "Status"
    };

    DefaultTableModel modeloInternos =
        new DefaultTableModel(colunas, 0);

    modeloInternos.addRow(new Object[]{
        "Java Basico",
        "20h",
        "Empresa",
        "Concluido"
    });

    modeloInternos.addRow(new Object[]{
        "Comunicacao",
        "8h",
        "Empresa",
        "Em andamento"
    });

    modeloInternos.addRow(new Object[]{
        "Trabalho em Equipe",
        "10h",
        "Empresa",
        "Concluido"
    });

    tabelaCursosInternos.setModel(modeloInternos);

    tabelaCursosInternos.setRowHeight(30);

    tabelaCursosInternos.getColumnModel().getColumn(0)
        .setPreferredWidth(250);

    tabelaCursosInternos.getColumnModel().getColumn(1)
        .setPreferredWidth(120);

    tabelaCursosInternos.getColumnModel().getColumn(2)
        .setPreferredWidth(180);

    tabelaCursosInternos.getColumnModel().getColumn(3)
        .setPreferredWidth(150);

    JScrollPane scrollInternos =
        new JScrollPane(tabelaCursosInternos);


    // Painel cursos internos

    JPanel painelInternos =
        new JPanel(new BorderLayout());

    painelInternos.setBorder(
        BorderFactory.createTitledBorder("Cursos Internos")
    );

    painelInternos.add(
        scrollInternos,
        BorderLayout.CENTER
    );


    // Tabela de cursos externos

    DefaultTableModel modeloExternos =
        new DefaultTableModel(colunas, 0);

    modeloExternos.addRow(new Object[]{
        "Excel Avancado",
        "16h",
        "Alura",
        "Concluido"
    });

    modeloExternos.addRow(new Object[]{
        "Gestao de Projetos",
        "12h",
        "Udemy",
        "Concluido"
    });

    modeloExternos.addRow(new Object[]{
        "Power BI",
        "20h",
        "Coursera",
        "Em andamento"
    });

    tabelaCursosExternos.setModel(modeloExternos);

    tabelaCursosExternos.setRowHeight(30);

    tabelaCursosExternos.getColumnModel().getColumn(0)
        .setPreferredWidth(250);

    tabelaCursosExternos.getColumnModel().getColumn(1)
        .setPreferredWidth(120);

    tabelaCursosExternos.getColumnModel().getColumn(2)
        .setPreferredWidth(180);

    tabelaCursosExternos.getColumnModel().getColumn(3)
        .setPreferredWidth(150);

    JScrollPane scrollExternos =
        new JScrollPane(tabelaCursosExternos);


    // Painel cursos externos

    JPanel painelExternos =
        new JPanel(new BorderLayout());

    painelExternos.setBorder(
        BorderFactory.createTitledBorder("Cursos Externos")
    );

    painelExternos.add(
        scrollExternos,
        BorderLayout.CENTER
    );


    // Tabelas

    JPanel tabelas = new JPanel(new GridLayout(2, 1, 5, 10));

    tabelas.add(painelInternos);
    tabelas.add(painelExternos);


    // Conteudo

    JPanel parteSuperior =
        new JPanel(new BorderLayout());

    parteSuperior.add(
        selecao,
        BorderLayout.NORTH
    );

    parteSuperior.add(
        resumo,
        BorderLayout.CENTER
    );

    JPanel conteudo =
        new JPanel(new BorderLayout());

    conteudo.add(
        parteSuperior,
        BorderLayout.NORTH
    );

    conteudo.add(
        tabelas,
        BorderLayout.CENTER
    );

    add(
        conteudo,
        BorderLayout.CENTER
    );
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

    painel.add(
        new JLabel(texto),
        g
    );

    g.gridx = 1;
    g.weightx = 1;
    g.fill = GridBagConstraints.HORIZONTAL;

    painel.add(
        campo,
        g
    );
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

public JLabel getHorasRealizadas() {
    return horasRealizadas;
}

public JLabel getCursosConcluidos() {
    return cursosConcluidos;
}

public JLabel getCertificados() {
    return certificados;
}

public JTable getTabelaCursosInternos() {
    return tabelaCursosInternos;
}

public JTable getTabelaCursosExternos() {
    return tabelaCursosExternos;
}

}
