package view;

import java.awt.*;
import javax.swing.*;

public class TelaCadastroVaga extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JTextField id = new JTextField(7);
    private final JTextField cargo = new JTextField(25);
    private final JTextField departamento = new JTextField(20);
    private final JTextField salario = new JTextField(15);

    private final JComboBox<String> tipoContrato = new JComboBox<>(
        new String[]{"Selecione o contrato","CLT", "Estagio", "Jovem Aprendiz", "Temporario"}
    );

    private final JComboBox<String> modalidade = new JComboBox<>(
        new String[]{"Selecione a modalidade","Presencial", "Hibrido", "Remoto"}
    );

    private final JComboBox<String> status = new JComboBox<>(
        new String[]{"Selecione o status","Aberta", "Em andamento", "Pausada", "Encerrada"}
    );

    private final JTextArea descricao = new JTextArea(4, 25);

    public TelaCadastroVaga() {
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
        componente(formulario, g, 1, "Cargo*:", cargo);
        componente(formulario, g, 2, "Departamento:", departamento);
        componente(formulario, g, 3, "Tipo de contrato:", tipoContrato);
        componente(formulario, g, 4, "Modalidade:", modalidade);
        componente(formulario, g, 5, "Salario:", salario);
        componente(formulario, g, 6, "Status:", status);
        componente(formulario, g, 7, "Descricao:", new JScrollPane(descricao));

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