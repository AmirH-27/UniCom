package apj.unicom;
import apj.unicom.view.LoginView;
import javax.swing.*;
public class App {
    public static void main( String[] args ) {
        SwingUtilities.invokeLater(LoginView::new);
    }
}
