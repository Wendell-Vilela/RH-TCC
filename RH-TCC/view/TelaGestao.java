package view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class TelaGestao extends JFrame {
    private static final long serialVersionUID = 1L;

    public TelaGestao() {

        setTitle("ERP - Gestão de Funcionários");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(750, 600);

        setLocationRelativeTo(null);

        // Criação das abas
        JTabbedPane abas = new JTabbedPane();

        // Adiciona cada tela do módulo
        abas.addTab(
            "Cadastro de Funcionários",
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