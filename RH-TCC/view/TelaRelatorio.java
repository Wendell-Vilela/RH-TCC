package view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class TelaRelatorio extends JFrame {
    private static final long serialVersionUID = 1L;

    public TelaRelatorio() {

        setTitle("ERP - Gestão de Funcionários");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(750, 600);

        setLocationRelativeTo(null);

        // Criação das abas
        JTabbedPane abas = new JTabbedPane();

        // Adiciona cada tela do módulo
        abas.addTab(
            "Custo e Projeções",
            new TelaCusto()
        );

        abas.addTab(
            "Desempenho",
            new TelaDesempenho()
        );

        abas.addTab(
            "Rotatividade",
            new TelaRotatividade()
        );

        abas.addTab(
            "Visão Geral de Indicadores",
            new TelaVGI()
        );


        // Adiciona as abas na janela
        add(abas);
    }
}
