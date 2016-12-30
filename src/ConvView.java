import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * View of the currency converter
 * Created by Laszlo on 2016-12-28.
 */
class ConvView extends JFrame{
    //Constants
    private static final String[] CURRENCY = new String[]{"EUR", "RON", "USD"};
    private static final double INITIAL_VALUE = 0.0;

    private ConvModel m_model;

    //Components
    private JTextField m_userInputTf = new JTextField(10);
    private JTextField m_outputTf    = new JTextField(10);
    private JComboBox<String> m_inputCb      = new JComboBox<>(CURRENCY);
    private JComboBox<String> m_outputCb     = new JComboBox<>(CURRENCY);
    private JButton m_convertBt      = new JButton(">>");
    private JLabel m_rateLb          = new JLabel();

    /**
     * Constructor
     * @param model model to base constructor on
     */
    ConvView(ConvModel model){

        //initialize logic
        m_model = model;
        m_model.setOutput(INITIAL_VALUE);

        //initialize output
        m_outputTf.setText(Double.toString(m_model.getOutput()));
        m_outputTf.setEditable(false);

        //initialize view
        JPanel content = new JPanel();
        JPanel left = new JPanel();
        JPanel right = new JPanel();
        JPanel center = new JPanel();
        JPanel converter = new JPanel();
        left.setLayout(new FlowLayout());
        right.setLayout(new FlowLayout());
        center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
        content.add(m_rateLb);
        left.add(m_inputCb);
        right.add(m_outputCb);
        center.add(m_userInputTf);
        center.add(m_convertBt);
        center.add(m_outputTf);
        converter.setLayout(new FlowLayout());
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        converter.add(left);
        converter.add(center);
        converter.add(right);
        content.add(converter);
        m_rateLb.setText(String.format("1 %s = 1 %s", getSelectedInput(), getSelectedOutput()));
        m_rateLb.setVerticalAlignment(SwingConstants.CENTER);

        this.setContentPane(content);
        this.pack();

        this.setTitle("Currency converter");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    String getUserInput(){return m_userInputTf.getText();}

    String getSelectedInput(){return m_inputCb.getSelectedItem().toString();}

    String getSelectedOutput(){return m_outputCb.getSelectedItem().toString();}

    void setOutput(double newOutput){ m_outputTf.setText(String.format("%.2f", newOutput));}

    void setConversionLabel(String input, String output){m_rateLb.setText(String.format("1 %s = %.3f %s", input, m_model.getConversionRate(), output));}

    void addInputListener(ActionListener inal){m_inputCb.addActionListener(inal);}

    void addOutputListener(ActionListener outal){m_outputCb.addActionListener(outal);}

    void addConvertListener(ActionListener cal ){m_convertBt.addActionListener(cal);}

    void showError(String message){JOptionPane.showMessageDialog(this, message);}



}
