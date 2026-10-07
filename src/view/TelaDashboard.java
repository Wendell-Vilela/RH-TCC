package view;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.JTabbedPane;

public class TelaDashboard extends JPanel {
    private static final long serialVersionUID = 1L;

    public TelaDashboard() {

        // Criação das abas
        JTabbedPane abas = new JTabbedPane();
        setLayout(new BorderLayout());

        // Adiciona cada tela do módulo
        abas.addTab(
            "Custo Mensal",
            new TelaCustoMensal()
        );

        abas.addTab(
            "Gestão de Férias",
            new TelaGestaoFerias()
        );

        abas.addTab(
            "Tendência de Absenteísmo",
            new TelaTendenciaAbsenteismo()
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
