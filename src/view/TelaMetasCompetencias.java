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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import view.Cores;
import view.funcaoFacilitar;

public class TelaMetasCompetencias extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JTextField id = new JTextField(7);
    private final JTextField funcionario = new JTextField(25);
    private final JTextField cargo = new JTextField(20);
    private final JTextField gestor = new JTextField(20);
    private final JTextField area = new JTextField(20);
    private final JTextField prazo = new JTextField(15);

    private final JComboBox<String> status = new JComboBox<>(
        new String[]{"Selecione o status", "Pendente", "Em andamento", "Concluida"}
    );

    private final JTextArea meta = new JTextArea(4, 25);

    private final JTable tabela = new JTable();

    // Botoes
    private final JButton novo = funcaoFacilitar.criarBotao("Novo", Cores.BOTAO_NOVO);
    private final JButton salvar = funcaoFacilitar.criarBotao("Salvar", Cores.BOTAO_SALVAR);
    private final JButton excluir = funcaoFacilitar.criarBotao("Excluir", Cores.BOTAO_EXCLUIR);
    private final JButton limpar = funcaoFacilitar.criarBotao("Limpar", Cores.BOTAO_LIMPAR);

    public TelaMetasCompetencias() {
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
            " Cadastro de Meta "
        );
        bordaFormulario.setTitleFont(new Font("SansSerif", Font.BOLD, 14));
        bordaFormulario.setTitleColor(Cores.TEXTO_TITULO);
        formulario.setBorder(BorderFactory.createCompoundBorder(
            bordaFormulario,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);

        // Estilização dos componentes específicos
        id.setEditable(false);
        meta.setFont(new Font("SansSerif", Font.PLAIN, 12));
        meta.setLineWrap(true);
        meta.setWrapStyleWord(true);
        
        status.setFont(new Font("SansSerif", Font.PLAIN, 12));
        status.setBackground(Cores.FUNDO);

        JScrollPane scrollMeta = new JScrollPane(meta);
        scrollMeta.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));

        componente(formulario, g, 0, "Código:", id);
        componente(formulario, g, 1, "Funcionário:", funcionario);
        componente(formulario, g, 2, "Cargo:", cargo);
        componente(formulario, g, 3, "Gestor:", gestor);
        componente(formulario, g, 4, "Área:", area);
        componente(formulario, g, 5, "Prazo:", prazo);
        componente(formulario, g, 6, "Status:", status);
        componente(formulario, g, 7, "Meta:", scrollMeta);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        botoes.setBackground(Cores.FUNDO);

        botoes.add(novo);
        botoes.add(salvar);
        botoes.add(excluir);
        botoes.add(limpar);

        String[] colunas = {
            "Código",
            "Funcionário",
            "Cargo",
            "Gestor",
            "Área",
            "Meta",
            "Prazo",
            "Status"
        };

        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        tabela.setModel(modelo);
        funcaoFacilitar.estilizarTabela(tabela);

        JScrollPane scrollTabela = new JScrollPane(tabela);
        scrollTabela.getViewport().setBackground(Cores.FUNDO);
        scrollTabela.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1));

        JPanel painelTabela = new JPanel(new BorderLayout());
        painelTabela.setBackground(Cores.FUNDO);

        TitledBorder bordaTabela = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1, true),
            " Metas Cadastradas "
        );
        bordaTabela.setTitleFont(new Font("SansSerif", Font.BOLD, 14));
        bordaTabela.setTitleColor(Cores.TEXTO_TITULO);
        painelTabela.setBorder(BorderFactory.createCompoundBorder(
            bordaTabela,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        painelTabela.add(scrollTabela, BorderLayout.CENTER);

        JPanel parteSuperior = new JPanel(new BorderLayout(0, 10));
        parteSuperior.setBackground(Cores.FUNDO);

        parteSuperior.add(formulario, BorderLayout.NORTH);
        parteSuperior.add(botoes, BorderLayout.SOUTH);

        JPanel conteudo = new JPanel(new BorderLayout(0, 15));
        conteudo.setBackground(Cores.FUNDO);

        conteudo.add(parteSuperior, BorderLayout.NORTH);
        conteudo.add(painelTabela, BorderLayout.CENTER);

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

    // Getters dos campos

    public JTextField getId() {
        return id;
    }

    public JTextField getFuncionario() {
        return funcionario;
    }

    public JTextField getCargo() {
        return cargo;
    }

    public JTextField getGestor() {
        return gestor;
    }

    public JTextField getArea() {
        return area;
    }

    public JTextField getPrazo() {
        return prazo;
    }

    public JComboBox<String> getStatus() {
        return status;
    }

    public JTextArea getMeta() {
        return meta;
    }

    public JTable getTabela() {
        return tabela;
    }

    // Getters dos botoes

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