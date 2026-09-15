package main;

import javax.swing.SwingUtilities;

import view.TelaCoringa;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            TelaCoringa tela = new TelaCoringa();

            tela.setVisible(true);
        });
    }
}