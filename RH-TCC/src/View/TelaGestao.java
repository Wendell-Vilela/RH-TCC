package view;

import javax.swing.*;
import javax.swing.JTabbedPane;

public class TelaGestao extends JPanel {
    private static final long serialVersionUID = 1L;

    public TelaGestao() {

        // Criação das abas
        JTabbedPane abas = new JTabbedPane();

        // Adiciona cada tela do módulo
        abas.addTab(
            "Cadastro",
            new TelaCadastroFuncionario()
        );

        abas.addTab(
            "Dados Bancários",
            new TelaDadosBancarios()
        );

        abas.addTab(
            "Dados Pessoais",
            new TelaDadosPessoais()
        );

        abas.addTab(
            "Dependentes",
            new TelaDependentes()
        );
        
        abas.addTab(
                "Documentos",
                new TelaDocumentos()
            );

        abas.addTab(
                "Histórico",
                new TelaHistorico()
            );


        // Adiciona as abas na janela
        add(abas);
    }
}