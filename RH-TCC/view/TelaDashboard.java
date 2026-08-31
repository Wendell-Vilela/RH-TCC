package view;

import javax.swing.*;
import javax.swing.JTabbedPane;

public class TelaDashboard extends JFrame {
    private static final long serialVersionUID = 1L;

    public TelaDashboard() {

        setTitle("ERP - Gestão de Funcionários");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(750, 600);

        setLocationRelativeTo(null);

        // Criação das abas
        JTabbedPane abas = new JTabbedPane();

        // Adiciona cada tela do módulo
        abas.addTab(
            "Custo Mensal",
            new CustoMensal()
        );

        abas.addTab(
            "Gestão de Férias",
            new GestaoFerias()
        );

        abas.addTab(
            "Tendência de Absenteísmo",
            new TendenciaAbsenteismo()
        );

        abas.addTab(
            "Funcionários Ativos",
            new ViewFuncionariosAtivos()
        );

        abas.addTab(
                "Novas Admissões",
                new ViewNovasAdmissoes()
            );

        abas.addTab(
                "Taxa de Turnover",
                new ViewTaxaTurnover()
            );


        // Adiciona as abas na janela
        add(abas);
    }
}
