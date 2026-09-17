package view;
import view.Cores;
import java.awt.color.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import view.funcaoFacilitar;
import java.awt.*;
public class TelaCadastroFuncionario extends JPanel {
	
	private static final long serialVersionUID = 1L;

    private final JTextField id = new JTextField(7);
    private final JTextField nome = new JTextField(40);
    private final JTextField matricula = new JTextField(15);
    private final JTextField cargo = new JTextField(30);
    private final JTextField departamento = new JTextField(30);
    private final JTextField email = new JTextField(40);
    private final JTextField telefone = new JTextField(15);

    private final JComboBox<String> status = new JComboBox<>(
        new String[]{
            "Selecione o status",
            "Ativo",
            "Afastado",
            "Ferias",
            "Desligado"
        }
    );
    
	public TelaCadastroFuncionario() {
		setLayout(new GridLayout(1, 1));
		setBackground(Cores.FUNDO);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		// Criando os Jpanels que serão utilizados
		JPanel Principal = new JPanel();
		JPanel formulario = new JPanel();	
		JPanel botoes = new JPanel();
		
		//adicionando os layouts
		Principal.setLayout(new GridLayout(2,1,100,10));
		botoes.setLayout(new FlowLayout(FlowLayout.LEFT));
		formulario.setLayout(new GridLayout(8,1,10,10));
		
		//Criando e adicionando os inputs
		TitledBorder bordaTitulo = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Cores.BORDA, 1, true)," Dados Pessoais ");
		bordaTitulo.setTitleFont(new Font("SansSerif", Font.BOLD, 14));
		bordaTitulo.setTitleColor(Cores.TEXTO_TITULO);
		formulario.setBorder(BorderFactory.createCompoundBorder(bordaTitulo,BorderFactory.createEmptyBorder(15, 15, 15, 15)));
		GridBagConstraints g = new GridBagConstraints();
		g.insets = new Insets(8, 8, 8, 8);
		        
		funcaoFacilitar.adicionarLinha(formulario, g, 0, "ID do Funcionario:", id);
		funcaoFacilitar.adicionarLinha(formulario, g, 1, "Nome Completo*:", nome);
		funcaoFacilitar.adicionarLinha(formulario, g, 2, "Matricula:", matricula);
		funcaoFacilitar.adicionarLinha(formulario, g, 3, "Cargo:", cargo);
		funcaoFacilitar.adicionarLinha(formulario, g, 4, "Departamento:", departamento);
		funcaoFacilitar.adicionarLinha(formulario, g, 5, "Email:", email);
		funcaoFacilitar.adicionarLinha(formulario, g, 6, "Telefone:", telefone);
		funcaoFacilitar.adicionarLinha(formulario, g, 7, "Status:", status);
        
		//criando e adicionando os botões
		JButton btnNovo = funcaoFacilitar.criarBotao("Novo", Cores.BOTAO_NOVO);
        JButton btnSalvar = funcaoFacilitar.criarBotao("Salvar", Cores.BOTAO_SALVAR);
        JButton btnExcluir = funcaoFacilitar.criarBotao("Excluir", Cores.BOTAO_EXCLUIR);
        JButton btnLimpar = funcaoFacilitar.criarBotao("Limpar", Cores.BOTAO_LIMPAR);
        
        botoes.add(btnNovo);       
        botoes.add(btnSalvar);
        botoes.add(btnExcluir);
        botoes.add(btnLimpar);
        
		//ultimos detalhes
        Principal.setBackground(Cores.FUNDO);
        botoes.setBackground(Cores.FUNDO);
        formulario.setBackground(Cores.FUNDO);
        
		//adicionando os JPAnels
		Principal.add(formulario);
		Principal.add(botoes, BorderLayout.SOUTH);
		add(Principal);
	}
	
}