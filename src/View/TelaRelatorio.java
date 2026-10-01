package view;

import javax.swing.*;
import javax.swing.JTabbedPane;

public class TelaRelatorio extends JPanel {
    private static final long serialVersionUID = 1L;

    public TelaRelatorio() {

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
