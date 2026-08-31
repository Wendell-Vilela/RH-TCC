package app;

import javax.swing.JFrame;

import view.TelaPDI;

public class Main {

    public static void main(String[] args) {

        JFrame janela = new JFrame("Metas e Competências");

        janela.setSize(900, 600);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);

        TelaPDI tela = new TelaPDI();

        janela.add(tela);

        janela.setVisible(true);
    }
}