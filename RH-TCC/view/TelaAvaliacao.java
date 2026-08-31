package view;

import javax.swing.*;

public class TelaAvaliacao extends JFrame {
    private static final long serialVersionUID = 1L;

    public TelaAvaliacao() {

        setTitle("ERP - Gestão de Funcionários");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(750, 600);

        setLocationRelativeTo(null);

        // Criação das abas
        JTabbedPane abas = new JTabbedPane();

        // Adiciona cada tela do módulo
        abas.addTab(
            "Avaliações e Feedbacks",
            new TelaAvaliacoesFeedbacks()
        );

        abas.addTab(
            "Metas e Competências",
            new TelaMetasCompetencias()
        );

        abas.addTab(
            "Cursos e Trilhas",
            new TelaCursosTrilhas()
        );

        abas.addTab(
            "Plano de Desenvolvimento Individual",
            new TelaPDI()
        );


        // Adiciona as abas na janela
        add(abas);
    }
}
