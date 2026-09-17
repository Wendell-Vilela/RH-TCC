package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import view.Cores;
import view.funcaoFacilitar;

public class TelaDadosPessoais extends JPanel {

    private static final long serialVersionUID = 1L;

    // Campos do formulário
    private final JTextField nome = new JTextField(40);
    private final JTextField nascimento = new JTextField(15);

    private final JComboBox<String> estadoCivil = new JComboBox<>(new String[]{
        "Selecione",
        "Solteiro(a)",
        "Casado(a)",
        "Divorciado(a)",
        "Viúvo(a)"
    });

    private final JTextField naturalidade = new JTextField(30);
    private final JTextField nacionalidade = new JTextField(30);
    private final JTextField endereco = new JTextField(40);
    private final JTextField cidade = new JTextField(30);

    public TelaDadosPessoais() {
        setLayout(new GridLayout(1, 1));
        setBackground(Cores.FUNDO);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Criando os JPanels que serão utilizados
        JPanel Principal = new JPanel();
        JPanel formulario = new JPanel();
        JPanel botoes = new JPanel();

        // Adicionando os layouts
        Principal.setLayout(new GridLayout(2, 1, 100, 10));
        botoes.setLayout(new FlowLayout(FlowLayout.LEFT));
        formulario.setLayout(new GridLayout(7, 1, 10, 10));

        // Criando e adicionando os inputs com borda de título
        TitledBorder bordaTitulo = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1, true), 
            " Dados Pessoais "
        );
        bordaTitulo.setTitleFont(new Font("SansSerif", Font.BOLD, 14));
        bordaTitulo.setTitleColor(Cores.TEXTO_TITULO);
        formulario.setBorder(BorderFactory.createCompoundBorder(
            bordaTitulo, 
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(8, 8, 8, 8);

        // Adicionando as linhas com funcaoFacilitar
        funcaoFacilitar.adicionarLinha(formulario, g, 0, "Nome Completo*:", nome);
        funcaoFacilitar.adicionarLinha(formulario, g, 1, "Data de Nascimento:", nascimento);
        funcaoFacilitar.adicionarLinha(formulario, g, 2, "Estado Civil:", estadoCivil);
        funcaoFacilitar.adicionarLinha(formulario, g, 3, "Naturalidade:", naturalidade);
        funcaoFacilitar.adicionarLinha(formulario, g, 4, "Nacionalidade:", nacionalidade);
        funcaoFacilitar.adicionarLinha(formulario, g, 5, "Endereço:", endereco);
        funcaoFacilitar.adicionarLinha(formulario, g, 6, "Cidade:", cidade);

        // Criando e adicionando os botões
        JButton btnNovo = funcaoFacilitar.criarBotao("Novo", Cores.BOTAO_NOVO);
        JButton btnSalvar = funcaoFacilitar.criarBotao("Salvar", Cores.BOTAO_SALVAR);
        JButton btnExcluir = funcaoFacilitar.criarBotao("Excluir", Cores.BOTAO_EXCLUIR);
        JButton btnLimpar = funcaoFacilitar.criarBotao("Limpar", Cores.BOTAO_LIMPAR);

        botoes.add(btnNovo);
        botoes.add(btnSalvar);
        botoes.add(btnExcluir);
        botoes.add(btnLimpar);

        // Últimos detalhes de background
        Principal.setBackground(Cores.FUNDO);
        botoes.setBackground(Cores.FUNDO);
        formulario.setBackground(Cores.FUNDO);

        // Adicionando os JPanels
        Principal.add(formulario);
        Principal.add(botoes, BorderLayout.SOUTH);
        add(Principal);
    }
}