package view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class TelaCoringa extends JFrame {

    private static final long serialVersionUID = 1L;

    public TelaCoringa() {

        setTitle("ERP - Gestão de Funcionários");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(750, 600);

        setLocationRelativeTo(null);

        // Criação das abas
        JTabbedPane abas = new JTabbedPane();

        // Adiciona cada tela do módulo
        abas.addTab(
            "Cadastro de Funcionário",
            new TelaCadastroFuncionario()
        );

        abas.addTab(
            "Dados Pessoais",
            new TelaDadosPessoais()
        );

        abas.addTab(
            "Documentos",
            new TelaDocumentos()
        );

        abas.addTab(
            "Dados Bancários",
            new TelaDadosBancarios()
        );

        abas.addTab(
            "Dependentes",
            new TelaDependentes()
        );

        abas.addTab(
            "Histórico",
            new TelaHistorico()
        );

        // Adiciona as abas na janela
        add(abas);
    }
}