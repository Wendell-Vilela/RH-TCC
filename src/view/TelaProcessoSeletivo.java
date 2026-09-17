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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import view.Cores;
import view.funcaoFacilitar;

public class TelaProcessoSeletivo extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JTextField id = new JTextField(7);
    private final JTextField candidato = new JTextField(60);
    private final JTextField vaga = new JTextField(30);
    private final JTextField recrutador = new JTextField(60);

    private final JComboBox<String> etapaAtual = new JComboBox<>(
        new String[]{"Selecione a etapa", "Triagem", "Entrevista", "Teste", "Entrevista Final"}
    );

    private final JComboBox<String> status = new JComboBox<>(
        new String[]{"Selecione o status", "Em andamento", "Aprovado", "Reprovado", "Cancelado"}
    );

    // Botões
    private final JButton novo = funcaoFacilitar.criarBotao("Novo", Cores.BOTAO_NOVO);
    private final JButton salvar = funcaoFacilitar.criarBotao("Salvar", Cores.BOTAO_SALVAR);
    private final JButton excluir = funcaoFacilitar.criarBotao("Excluir", Cores.BOTAO_EXCLUIR);
    private final JButton limpar = funcaoFacilitar.criarBotao("Limpar", Cores.BOTAO_LIMPAR);

    public TelaProcessoSeletivo() {
        setLayout(new BorderLayout());
        setBackground(Cores.FUNDO);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        montar();
    }

    private void montar() {
        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(Cores.FUNDO);

        TitledBorder bordaFormulario = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1, true),
            " Processo Seletivo "
        );
        bordaFormulario.setTitleFont(new Font("SansSerif", Font.BOLD, 14));
        bordaFormulario.setTitleColor(Cores.TEXTO_TITULO);
        formulario.setBorder(BorderFactory.createCompoundBorder(
            bordaFormulario,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);

        id.setEditable(false);

        etapaAtual.setFont(new Font("SansSerif", Font.PLAIN, 12));
        etapaAtual.setBackground(Cores.FUNDO);

        status.setFont(new Font("SansSerif", Font.PLAIN, 12));
        status.setBackground(Cores.FUNDO);

        componente(formulario, g, 0, "Código:", id);
        componente(formulario, g, 1, "Candidato:", candidato);
        componente(formulario, g, 2, "Vaga:", vaga);
        componente(formulario, g, 3, "Recrutador:", recrutador);
        componente(formulario, g, 4, "Etapa Atual:", etapaAtual);
        componente(formulario, g, 5, "Status:", status);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        botoes.setBackground(Cores.FUNDO);

        botoes.add(novo);
        botoes.add(salvar);
        botoes.add(excluir);
        botoes.add(limpar);

        JPanel conteudo = new JPanel(new BorderLayout(0, 15));
        conteudo.setBackground(Cores.FUNDO);

        conteudo.add(formulario, BorderLayout.NORTH);
        conteudo.add(botoes, BorderLayout.SOUTH);

        add(conteudo, BorderLayout.NORTH);
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

    // Getters dos campos

    public JTextField getId() {
        return id;
    }

    public JTextField getCandidato() {
        return candidato;
    }

    public JTextField getVaga() {
        return vaga;
    }

    public JTextField getRecrutador() {
        return recrutador;
    }

    public JComboBox<String> getEtapaAtual() {
        return etapaAtual;
    }

    public JComboBox<String> getStatus() {
        return status;
    }

    // Getters dos botões

    public JButton getNovo() {
        return novo;
    }

    public JButton getSalvar() {
        return salvar;
    }

    public JButton getExcluir() {
        return excluir;
    }

    public JButton getLimpar() {
        return limpar;
    }
}