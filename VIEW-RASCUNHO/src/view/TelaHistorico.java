package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import view.Cores;
import view.funcaoFacilitar;

public class TelaHistorico extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JTextField ano = new JTextField(10);
    private final JTextField evento = new JTextField(30);
    private final JTextField cargo = new JTextField(30);
    private final JTextArea observacao = new JTextArea(3, 30);

    public TelaHistorico() {
        setLayout(new BorderLayout());
        setBackground(Cores.FUNDO);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        montar();
    }

    private void montar() {
        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(Cores.FUNDO);

        TitledBorder borda = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1, true),
            " Histórico Profissional "
        );
        borda.setTitleFont(new Font("SansSerif", Font.BOLD, 14));
        borda.setTitleColor(Cores.TEXTO_TITULO);
        formulario.setBorder(BorderFactory.createCompoundBorder(
            borda,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);

        // Estiliza a área de texto de observação
        observacao.setFont(new Font("SansSerif", Font.PLAIN, 12));
        observacao.setLineWrap(true);
        observacao.setWrapStyleWord(true);

        JScrollPane scrollObservacao = new JScrollPane(observacao);
        scrollObservacao.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));

        componente(formulario, g, 0, "Ano:", ano);
        componente(formulario, g, 1, "Evento:", evento);
        componente(formulario, g, 2, "Cargo:", cargo);
        componente(formulario, g, 3, "Observação:", scrollObservacao);

        String[] colunas = {
            "Ano",
            "Evento",
            "Cargo",
            "Observação"
        };

        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(modelo);
        funcaoFacilitar.estilizarTabela(tabela);

        JScrollPane scrollTabela = new JScrollPane(tabela);
        scrollTabela.getViewport().setBackground(Cores.FUNDO);
        scrollTabela.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        botoes.setBackground(Cores.FUNDO);

        JButton btnNovo = funcaoFacilitar.criarBotao("Novo", Cores.BOTAO_NOVO);
        JButton btnSalvar = funcaoFacilitar.criarBotao("Salvar", Cores.BOTAO_SALVAR);
        JButton btnExcluir = funcaoFacilitar.criarBotao("Excluir", Cores.BOTAO_EXCLUIR);
        JButton btnLimpar = funcaoFacilitar.criarBotao("Limpar", Cores.BOTAO_LIMPAR);

        botoes.add(btnNovo);
        botoes.add(btnSalvar);
        botoes.add(btnExcluir);
        botoes.add(btnLimpar);

        JPanel conteudo = new JPanel(new BorderLayout(0, 15));
        conteudo.setBackground(Cores.FUNDO);

        conteudo.add(formulario, BorderLayout.NORTH);
        conteudo.add(scrollTabela, BorderLayout.CENTER);
        conteudo.add(botoes, BorderLayout.SOUTH);

        add(conteudo, BorderLayout.CENTER);
    }

    private void componente(
        JPanel painel,
        GridBagConstraints g,
        int linha,
        String texto,
        Component campo
    ) {
        JLabel rotulo = new JLabel(texto);
        rotulo.setFont(new Font("SansSerif", Font.BOLD, 12));
        rotulo.setForeground(Cores.TEXTO_TITULO);

        g.gridx = 0;
        g.gridy = linha;
        g.weightx = 0;
        g.fill = GridBagConstraints.NONE;
        g.anchor = GridBagConstraints.FIRST_LINE_END;

        painel.add(rotulo, g);

        if (campo instanceof JTextField) {
            funcaoFacilitar.estilizarCampoTexto((JTextField) campo);
        }

        g.gridx = 1;
        g.weightx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.LINE_START;

        painel.add(campo, g);
    }
}