package view;

import javax.swing.*;
import javax.swing.JTabbedPane;

public class TelaRecrutamento extends JPanel {
    private static final long serialVersionUID = 1L;

    public TelaRecrutamento() {

        // Criação das abas
        JTabbedPane abas = new JTabbedPane();

        // Adiciona cada tela do módulo
        abas.addTab(
            "Cadastro de Candidato",
            new TelaCadastroCandidato()
        );

        abas.addTab(
            "Cadastro de Vaga",
            new TelaCadastroVaga()
        );

        abas.addTab(
            "Contratação",
            new TelaContratacao()
        );

        abas.addTab(
            "Processo Seletivo",
            new TelaProcessoSeletivo()
        );


        // Adiciona as abas na janela
        add(abas);
    }
}
