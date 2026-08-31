package Main;

import View.ViewNovasAdmissoes;
import View.ViewTaxaTurnover;
import View.CustoMensal;
import View.GestaoFerias;
import View.TendenciaAbsenteismo;
import View.ViewFuncionariosAtivos;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {

        setTitle("Dashboard Executivo");
        setSize(1200, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JTabbedPane abas = new JTabbedPane();
        
        // Customização opcional para combinar com o tema escuro anterior
        abas.setBackground(Color.WHITE);
        abas.setForeground(Color.BLACK);
        abas.setFont(new Font("Arial", Font.BOLD, 11));

        // Adicionando as telas diretamente como abas
        abas.addTab("Funcionários Ativos", new ViewFuncionariosAtivos());
        abas.addTab("Novas Admissões", new ViewNovasAdmissoes());
        abas.addTab("Taxa de Turnover", new ViewTaxaTurnover());
        abas.addTab("Custo de Pessoal", new CustoMensal());
        abas.addTab("Taxa de Absenteismo", new TendenciaAbsenteismo());
        abas.addTab("Gestão de Férias", new GestaoFerias());

        // Adiciona o componente de abas no centro ocupando a tela toda
        add(abas, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main janela = new Main();
            janela.setVisible(true);
        });
    }
}
