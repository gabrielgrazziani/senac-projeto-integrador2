package tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

public class BarraDeProcesso extends JFrame {

    private JPanel contentPane;
    private JProgressBar progressBar;
    private JPanel panel;
    private int tamanho;
    private int agora;
    private int porcentagem;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	BarraDeProcesso frame = new BarraDeProcesso(20);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public BarraDeProcesso(int tamanho) {
        setTitle("Inserindo Dados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 150);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        contentPane.add(getProgressBar(), BorderLayout.NORTH);
        contentPane.add(getPanel(), BorderLayout.SOUTH);
        porcentagem = 0;
        this.agora = 0;
        this.tamanho = tamanho;
        setVisible(true);
    }

    private JProgressBar getProgressBar() {
        if (progressBar == null) {
            progressBar = new JProgressBar();
            progressBar.setStringPainted(true);
        }
        return progressBar;
    }

    private JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel();
            JLabel tex = new JLabel("Inserindo Dados");
            panel.add(tex);
        }
        return panel;
    }

    public void prosseguir() {
    	agora++;
    	porcentagem = (agora*100)/tamanho;
        progressBar.setValue(porcentagem);
        if (porcentagem >= 100) {
            //timer.stop();
            //btnIniciar.setEnabled(true);
            setVisible(false);
        }
    }
}


