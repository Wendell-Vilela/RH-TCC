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

    JPanel painelPrincipal;
    CardLayout cardLayout;

 
    public Main() {

        setTitle("Dashboard Executivo");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // MENU

        JPanel menu = new JPanel(new GridBagLayout());
        menu.setBackground(new Color(30, 30, 30));
        menu.setPreferredSize(new Dimension(255, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 15, 10, 15);

        JButton btnCusto = criarBotao("Custo de Pessoal");
        JButton btnFuncionarios = criarBotao("Funcionários Ativos");
        JButton btnAdmissoes = criarBotao("Novas Admissões");
        JButton btnTurnover = criarBotao("Taxa de Turnover");
        JButton btnFerias = criarBotao("Gestão de Férias");
        JButton btnAbsenteismo = criarBotao("Taxa de Absenteismo");

        gbc.gridy = 0;
        menu.add(btnFuncionarios, gbc);

        gbc.gridy = 1;
        menu.add(btnAdmissoes, gbc);

        gbc.gridy = 2;
        menu.add(btnTurnover, gbc);

        gbc.gridy = 3;
        menu.add(btnCusto, gbc);

        gbc.gridy = 4;
        menu.add(btnFerias, gbc);

        gbc.gridy = 5;
        menu.add(btnAbsenteismo, gbc);

        add(menu, BorderLayout.WEST);

        


        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);
        painelPrincipal.setBackground(Color.BLACK);

        painelPrincipal.add(
                new ViewFuncionariosAtivos(),
                "FUNCIONARIOS"
        );

        painelPrincipal.add(
                new ViewNovasAdmissoes(),
                "ADMISSOES"
        );

        painelPrincipal.add(
                new ViewTaxaTurnover(),
                "TURNOVER"
        );

        painelPrincipal.add(
                new CustoMensal(),
                "CUSTO"
        );    

        painelPrincipal.add(
                new TendenciaAbsenteismo(),
                "ABSENTEISMO"
        );           

        painelPrincipal.add(
                new GestaoFerias(),
                "FERIAS"
        ); 

        add(painelPrincipal, BorderLayout.CENTER);

        // BOTÕES

        btnFuncionarios.addActionListener(e ->
                cardLayout.show(painelPrincipal, "FUNCIONARIOS")
        );

        btnAdmissoes.addActionListener(e ->
                cardLayout.show(painelPrincipal, "ADMISSOES")
        );

        btnTurnover.addActionListener(e ->
                cardLayout.show(painelPrincipal, "TURNOVER")
        );

        btnFerias.addActionListener(e ->
                cardLayout.show(painelPrincipal, "FERIAS")
        );

        btnAbsenteismo.addActionListener(e ->
                cardLayout.show(painelPrincipal, "ABSENTEISMO")
        );

         btnCusto.addActionListener(e ->
                cardLayout.show(painelPrincipal, "CUSTO")
        );

        cardLayout.show(painelPrincipal, "FUNCIONARIOS");
    }

    // BOTÃO

    private JButton criarBotao(String texto) {

        JButton botao = new JButton(texto);

        botao.setPreferredSize(new Dimension(220, 55));
        botao.setForeground(Color.WHITE);
        botao.setBackground(new Color(45, 45, 45));
        botao.setFont(new Font("Arial", Font.BOLD, 13));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return botao;
    }

    // MAIN

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Main janela = new Main();
            janela.setVisible(true);
        });
    }
}