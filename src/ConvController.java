import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Controller class of the currency converter
 * Created by Laszlo on 2016-12-30.
 */
 class ConvController {

    private ConvModel m_model;
    private ConvView  m_view;

    /**
     * Constructor of the controller
     * @param model model to base view and constructor on
     * @param view view to base constructor on
     */
    ConvController(ConvModel model, ConvView view){
        m_model = model;
        m_view = view;

        view.addInputListener(new InputOutputListener());
        view.addOutputListener(new InputOutputListener());
        view.addConvertListener(new ConvertListener());
    }

    /**
     * Inner class of event listener for the combo boxes
     */
    private class InputOutputListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String input;
            String output;
            try {
                input = m_view.getSelectedInput();
                output = m_view.getSelectedOutput();
                m_model.setConversionRate(input, output);
                m_view.setConversionLabel(input, output);
            }
            catch (IOException ioe){
                m_view.showError("Bad input!");
            }
        }
    }

    /**
     * Inner class of event listener for the convert button
     */
    private class ConvertListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            double input;
            try {
                input = Double.parseDouble(m_view.getUserInput());
                m_model.convert(input);
                m_view.setOutput(m_model.getOutput());
            }
            catch (NumberFormatException nfe){
                m_view.showError("Bad input: "+ m_view.getUserInput());
            }
        }
    }
}
