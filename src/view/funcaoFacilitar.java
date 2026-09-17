package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

public class funcaoFacilitar {

    public static void adicionarLinha(
            JPanel painel,
            GridBagConstraints g,
            int linha,
            String texto,
            Component campo) {

        g.gridx = 0;
        g.gridy = linha;
        g.weightx = 0.0;
        g.fill = GridBagConstraints.NONE;
        g.anchor = GridBagConstraints.LINE_END;

        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));
        label.setForeground(Cores.TEXTO_PADRAO);

        painel.add(label, g);

        g.gridx = 1;
        g.weightx = 1.0;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.LINE_START;

        painel.add(campo, g);
    }

    public static void estilizarComboBox(JComboBox<String> combo) {
        combo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        combo.setPreferredSize(
            new Dimension(combo.getPreferredSize().width, 32)
        );
        combo.setBackground(Cores.BRANCO);
    }

    public static void estilizarCampoTexto(JTextField campo) {
        campo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        campo.setPreferredSize(new Dimension(campo.getPreferredSize().width, 32));
        campo.setBackground(Cores.BRANCO);
        campo.setForeground(Cores.TEXTO_PADRAO);
        campo.setCaretColor(Cores.TEXTO_PADRAO);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1, true),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
    }

    public static JButton criarBotao(String nome, Color corFundo) {
        JButton btn = new JButton(nome);

        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setForeground(Cores.BRANCO);
        btn.setBackground(corFundo);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(100, 35));

        return btn;
    }

    public static void estilizarTabela(JTable tabela) {
        // Configurações básicas de visual e linhas
        tabela.setBackground(Cores.FUNDO);
        tabela.setForeground(Cores.TEXTO_TITULO);
        tabela.setSelectionBackground(Cores.AZUL_MENU);
        tabela.setSelectionForeground(Cores.FUNDO);
        tabela.setGridColor(Cores.BORDA);
        tabela.setRowHeight(30);
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabela.setShowVerticalLines(false);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Estilização do cabeçalho
        if (tabela.getTableHeader() != null) {
            tabela.getTableHeader().setBackground(Cores.BORDA);
            tabela.getTableHeader().setForeground(Cores.TEXTO_TITULO);
            tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
            tabela.getTableHeader().setReorderingAllowed(false);

            ((DefaultTableCellRenderer) tabela.getTableHeader().getDefaultRenderer())
                    .setHorizontalAlignment(JLabel.CENTER);
        }

        // Centraliza as células de texto
        DefaultTableCellRenderer renderCentralizado = new DefaultTableCellRenderer();
        renderCentralizado.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(renderCentralizado);
        }
    }
}