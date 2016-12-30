import java.io.IOException;

/**
 * Model of the currency converter
 * Created by Laszlo on 2016-12-28.
 */
 class ConvModel {

    //constants
    private static final double EUR_TO_USD = 1.04;
    private static final double EUR_TO_RON = 4.54;
    private static final double USD_TO_RON = 4.37;
    private static final double INITIAL_VALUE = 0;

    private double m_conversionRate;
    private double m_converted;

    ConvModel(){reset();}

    /**
     * Reset the converter to the initial value
     */
    private void reset(){m_converted = INITIAL_VALUE;}
    

    /**
     * Set the current exchange rate
     * @param from type of currency to convert from
     * @param to type of currency to convert to
     * @throws IOException if currency is not recognised
     * @throws NumberFormatException if input value is not correct
     */

     void setConversionRate(String from, String to) throws IOException, NumberFormatException{
        switch (from) {
            case "EUR":
                switch (to) {
                    case "RON":
                        m_conversionRate = EUR_TO_RON;
                        break;
                    case "USD":
                        m_conversionRate = EUR_TO_USD;
                        break;
                    case "EUR":
                        m_conversionRate = 1.0;
                        break;
                    default:
                        throw new IOException();
                }
                break;
            case "RON":
                switch (to) {
                    case "EUR":
                        m_conversionRate = 1 / EUR_TO_RON;
                        break;
                    case "USD":
                        m_conversionRate = 1 / USD_TO_RON;
                        break;
                    case "RON":
                        m_conversionRate = 1.0;
                        break;
                    default:
                        throw new IOException();
                }
                break;
            case "USD":
                switch (to) {
                    case "EUR":
                        m_conversionRate = 1 / EUR_TO_USD;
                        break;
                    case "RON":
                        m_conversionRate = USD_TO_RON;
                        break;
                    case "USD":
                        m_conversionRate = 1.0;
                        break;
                    default:
                        throw new IOException();
                }
                break;
            default:
                throw new IOException();
        }
    }

     double convert(double input){
        m_converted = input * m_conversionRate;
        return m_converted;
    }



    void setOutput(double value){m_converted = value;}

    double getOutput() {return m_converted;}
    
    double getConversionRate(){
        return m_conversionRate;
    }
}
