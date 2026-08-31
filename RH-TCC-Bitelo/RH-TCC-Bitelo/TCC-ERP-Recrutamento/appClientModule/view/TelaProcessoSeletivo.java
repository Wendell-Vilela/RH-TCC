package view;

import java.awt.*;
import javax.swing.*;

public class TelaProcessoSeletivo extends JPanel {

    /**
	 * 
	 */
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

    public TelaProcessoSeletivo() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        montar();
    }

    private void montar() {
        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBorder(
            BorderFactory.createTitledBorder("Cadastro de Vaga")
        );

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);

        componente(formulario, g, 0, "Codigo:", id);
        componente(formulario, g, 1, "Candidato:", candidato);
        componente(formulario, g, 2, "Vaga:", vaga);
        componente(formulario, g, 3, "Recrutador:", recrutador);
        componente(formulario, g, 4, "Etapa Atual:", etapaAtual);
        componente(formulario, g, 5, "Status:", status);

        id.setEditable(false);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botoes.add(new JButton("Novo"));
        botoes.add(new JButton("Salvar"));
        botoes.add(new JButton("Excluir"));
        botoes.add(new JButton("Limpar"));

        JPanel conteudo = new JPanel(new BorderLayout());
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
        g.gridx = 0;
        g.gridy = linha;
        g.weightx = 0;
        g.fill = GridBagConstraints.NONE;
        painel.add(new JLabel(texto), g);

        g.gridx = 1;
        g.weightx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painel.add(campo, g);
    }
}