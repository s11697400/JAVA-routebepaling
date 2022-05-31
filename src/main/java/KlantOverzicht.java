import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KlantOverzicht extends JFrame {
    public KlantOverzicht(int RetourID) {
        JFrame klantOverzicht = new JFrame();
        JPanel klantinfo = new JPanel();

        for (int i = 0; i < 1; i++) {
            System.out.println(RetourID);
            JLabel customerName = new JLabel("Naam: " + DatabaseConnectie.getCustomerName(RetourID).get(i));
            JLabel customerID = new JLabel("CustomerID: " + DatabaseConnectie.getCustomerID(RetourID).get(i));
            JLabel customerAdres = new JLabel("Adres: " + DatabaseConnectie.getCustomerAdres(RetourID).get(i));
            JButton sluiten = new JButton("Sluiten");

            klantinfo.add(customerName);
            klantinfo.add(customerID);
            klantinfo.add(customerAdres);
            klantinfo.add(sluiten);

            klantOverzicht.add(klantinfo);
            klantinfo.setLayout(new BoxLayout(klantinfo, BoxLayout.PAGE_AXIS));

            sluiten.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent sluitenActionPerformed) {
                    if (sluitenActionPerformed.getSource() == sluiten) {
                        klantOverzicht.setVisible(false);
                    }
                }
            });
        }

        klantOverzicht.setLayout(new FlowLayout());
        klantOverzicht.setSize(400, 200);
        klantOverzicht.setVisible(true);
    }
}
