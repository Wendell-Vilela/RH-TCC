package view;

import javax.swing.*;

public class TelaCoringa extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TelaCoringa() {

        setTitle("ERP - Relatórios e KPI's");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(600, 500);

        setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane();

        abas.addTab("Visão Geral dos Indicadores", new TelaVGI());
        abas.addTab("Taxa de Rotatividade", new TelaRotatividade());
        abas.addTab("Desempenho de Grupos", new TelaDesempenho());
        abas.addTab("Custos e Projeções", new TelaCusto());

        add(abas);
    }

    public static void main(String[] args) {
    }
}