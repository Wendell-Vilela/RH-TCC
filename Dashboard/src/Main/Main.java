package Main;

import View.TelaCustoMensal;
import View.TelaGestaoFerias;
import View.TelaTendenciaAbsenteismo;
import View.ViewFuncionariosAtivos;
import View.ViewNovasAdmissoes;
import View.ViewTaxaTurnover;
import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {

    public Main() {

        setTitle("Dashboard Executivo");
        setSize(1200, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JTabbedPane abas = new JTabbedPane();
        
        
        abas.setBackground(Color.WHITE);
        abas.setForeground(Color.BLACK);
        abas.setFont(new Font("Arial", Font.BOLD, 11));


        abas.addTab("Funcionários Ativos", new ViewFuncionariosAtivos());
        abas.addTab("Novas Admissões", new ViewNovasAdmissoes());
        abas.addTab("Taxa de Turnover", new ViewTaxaTurnover());
        abas.addTab("Custo de Pessoal", new TelaCustoMensal());
        abas.addTab("Taxa de Absenteismo", new TelaTendenciaAbsenteismo());
        abas.addTab("Gestão de Férias", new TelaGestaoFerias());


        add(abas, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main janela = new Main();
            janela.setVisible(true);
        });
    }
}
