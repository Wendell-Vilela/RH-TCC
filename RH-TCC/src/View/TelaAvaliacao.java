	package view;

import javax.swing.*;

public class TelaAvaliacao extends JPanel {
    private static final long serialVersionUID = 1L;

    public TelaAvaliacao() {

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
            "PDI",
            new TelaPDI()
        );


        // Adiciona as abas na janela
        add(abas);
    }
}
