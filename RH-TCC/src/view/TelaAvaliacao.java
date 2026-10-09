	package view;

import java.awt.BorderLayout;

import javax.swing.*;

public class TelaAvaliacao extends JPanel {
    private static final long serialVersionUID = 1L;

    public TelaAvaliacao() {
    	
        // Criação das abas
        JTabbedPane abas = new JTabbedPane();
        setLayout(new BorderLayout());
        // Adiciona cada tela do módulo
        abas.addTab(
            "Avaliações e Feedbacks",
            new TelaAvaliacoesFeedbacks()
        );
        abas.addTab(
        		"Cadastro de Feedbacks",
        		new TelaCadastroFeedback()
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
        		"Cadastro de Cursos",
        		new TelaCadastroCursos()
        		);

        abas.addTab(
            "PDI",
            new TelaPDI()
        );
        
        abas.addTab(
        		"Cadastro de Ações PDI",
        		new TelaCadastroProximasAcoes()
        		);

        


        // Adiciona as abas na janela
        add(abas);
    }
}
