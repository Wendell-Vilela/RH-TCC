package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import view.Cores;
import view.funcaoFacilitar;

public class TelaCadastroCandidato extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JTextField id = new JTextField(7);
    private final JTextField nome = new JTextField(60);
    private final JTextField cpf = new JTextField(11);
    private final JTextField telefone = new JTextField(11);
    private final JTextField email = new JTextField(60);
    private final JTextField vaga = new JTextField(50);

    public TelaCadastroCandidato() {
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
        formulario.setLayout(new GridLayout(6, 1, 10, 10));

        // Criando e adicionando a borda com título
        TitledBorder bordaTitulo = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1, true),
            " Cadastro do Candidato "
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
        funcaoFacilitar.adicionarLinha(formulario, g, 0, "Código:", id);
        funcaoFacilitar.adicionarLinha(formulario, g, 1, "Nome Completo:", nome);
        funcaoFacilitar.adicionarLinha(formulario, g, 2, "CPF:", cpf);
        funcaoFacilitar.adicionarLinha(formulario, g, 3, "Telefone:", telefone);
        funcaoFacilitar.adicionarLinha(formulario, g, 4, "E-mail:", email);
        funcaoFacilitar.adicionarLinha(formulario, g, 5, "Vaga Desejada:", vaga);

        id.setEditable(false);

        // Criando e adicionando os botões
        JButton btnNovo = funcaoFacilitar.criarBotao("Novo", Cores.BOTAO_NOVO);
        JButton btnSalvar = funcaoFacilitar.criarBotao("Salvar", Cores.BOTAO_SALVAR);
        JButton btnExcluir = funcaoFacilitar.criarBotao("Excluir", Cores.BOTAO_EXCLUIR);
        JButton btnLimpar = funcaoFacilitar.criarBotao("Limpar", Cores.BOTAO_LIMPAR);

        botoes.add(btnNovo);
        botoes.add(btnSalvar);
        botoes.add(btnExcluir);
        botoes.add(btnLimpar);

        // Últimos detalhes (definição de backgrounds)
        Principal.setBackground(Cores.FUNDO);
        botoes.setBackground(Cores.FUNDO);
        formulario.setBackground(Cores.FUNDO);

        // Adicionando os JPanels
        Principal.add(formulario);
        Principal.add(botoes, BorderLayout.SOUTH);
        add(Principal);
    }
}