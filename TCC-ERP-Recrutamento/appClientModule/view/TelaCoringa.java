package view;

import javax.swing.*;

public class TelaCoringa extends JFrame {

    public TelaCoringa() {

        setTitle("ERP - Recrutamento e Selecao");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(600, 500);

        setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane();

        abas.addTab("Cadastro de Vaga", new TelaCadastroVaga());
        abas.addTab("Cadastro de Candidato", new TelaCadastroCandidato());
        abas.addTab("Processo Seletivo", new TelaProcessoSeletivo());
        abas.addTab("Contratacao", new TelaContratacao());

        add(abas);
    }

    public static void main(String[] args) {
    }
}