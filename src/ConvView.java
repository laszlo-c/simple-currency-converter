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
     * Constructor for view
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

        //content panel
        JPanel content = new JPanel();

        //container panel for textLabel
        JPanel top = new JPanel();

        //input comboBox container
        JPanel left = new JPanel();

        //output comboBox container
        JPanel right = new JPanel();

        //container panel for input textField, convert button and output textField
        JPanel center = new JPanel();

        //panel that contains the interactive objects
        JPanel converter = new JPanel();

        //convert button container
        JPanel convertButtonPanel = new JPanel();

        //set view
        left.setLayout(new FlowLayout());
        right.setLayout(new FlowLayout());
        center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
        top.add(m_rateLb);
        left.add(m_inputCb);
        right.add(m_outputCb);
        center.add(m_userInputTf);
        convertButtonPanel.add(m_convertBt);
        center.add(convertButtonPanel);
        center.add(m_outputTf);
        content.add(top);
        converter.setLayout(new FlowLayout());
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        converter.add(left);
        converter.add(center);
        converter.add(right);
        content.add(converter);
        m_rateLb.setText(String.format("1.000 %s = 1.000 %s", getSelectedInput(), getSelectedOutput()));
        m_rateLb.setVerticalAlignment(SwingConstants.CENTER);

        this.setContentPane(content);
        this.pack();

        //title and close operation
        this.setTitle("Currency converter");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    /**
     * Gets the amount of currency to convert
     * @return the amount to be converted
     */
    String getUserInput(){return m_userInputTf.getText();}

    /**
     * Gets the currency to convert from
     * @return the input currency
     */
    String getSelectedInput(){return m_inputCb.getSelectedItem().toString();}

    /**
     * Gets the currency to convert to
     * @return the output currency
     */
    String getSelectedOutput(){return m_outputCb.getSelectedItem().toString();}

    /**
     * Sets the text of the output textfield to the new value
     * @param newOutput the output amount to display
     */
    void setOutput(double newOutput){ m_outputTf.setText(String.format("%.3f", newOutput));}

    /**
     * Sets the text of the topmost label to display the current exchange rate
     * @param input currency to convert from
     * @param output currency to convert to
     */
    void setConversionLabel(String input, String output){m_rateLb.setText(String.format("1.000 %s = %.3f %s", input, m_model.getConversionRate(), output));}

    /**
     * Adds action listener to the input comboBox
     * @param inAL input action listener
     */
    void addInputListener(ActionListener inAL){m_inputCb.addActionListener(inAL);}

    /**
     * Adds action listener to the output comboBox
     * @param outAL output action listener
     */
    void addOutputListener(ActionListener outAL){m_outputCb.addActionListener(outAL);}

    /**
     * Adds action listeners to the convert button and the input text field
     * @param cal convert action listener
     */
    void addConvertListener(ActionListener cal ){
        m_convertBt.addActionListener(cal);
        m_userInputTf.addActionListener(cal);
    }

    /**
     * Shows dialog box with error message when called
     * @param message message to display
     */
    void showError(String message){JOptionPane.showMessageDialog(this, message);}



}
