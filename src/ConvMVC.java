/**
 * Main class for the currency converter
 * Created by Laszlo on 2016-12-30.
 */
public class ConvMVC {

    public static void main(String[] args){

        ConvModel model = new ConvModel();
        ConvView view = new ConvView(model);
        ConvController controller = new ConvController(model, view);

        view.setVisible(true);
    }

}
