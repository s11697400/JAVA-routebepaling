import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RetourOverzicht extends JPanel{
    public RetourOverzicht() {
        JFrame retouroverzicht = new JFrame();

        int aantalRetours = 3;
        JPanel[] retouraanvraag = new JPanel[aantalRetours];
        JPanel[] infoRetour = new JPanel[aantalRetours];
        JPanel[] knoppenRetour = new JPanel[aantalRetours];
        for (int i = 0; i < aantalRetours; i++) {

            JLabel customerID = new JLabel("CustomerID: ");
            JLabel productnummer = new JLabel("Productnummer: ");
            JLabel restDagen = new JLabel("Resterende dagen: ");
            JLabel reden = new JLabel("Reden: ");
            JLabel spacer = new JLabel(" ");
            JButton uitvoeren = new JButton("Uitvoeren");
            JComboBox status = new JComboBox();
            JButton verwijderen = new JButton("Verwijderen");

            retouraanvraag[i] = new JPanel();
            infoRetour[i] = new JPanel();
            knoppenRetour[i] = new JPanel();

            infoRetour[i].add(customerID);
            infoRetour[i].add(productnummer);
            infoRetour[i].add(restDagen);
            infoRetour[i].add(reden);
            infoRetour[i].add(spacer);
            knoppenRetour[i].add(uitvoeren);
            knoppenRetour[i].add(status);
            knoppenRetour[i].add(verwijderen);

            verwijderen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent verwijderenActionPerformed) {
                    int keuze = 0;
                    if (verwijderenActionPerformed.getSource() == verwijderen) {
                        keuze = JOptionPane.showConfirmDialog(
                                verwijderen, "Weet u het zeker?", "Request",
                                JOptionPane.YES_NO_OPTION);
                    }
                    if (keuze == JOptionPane.YES_OPTION) {
                        System.out.println("Alle gegevens zijn gewist.");
                        setVisible(false);
                    } else {
                    }
                }

            });
            retouraanvraag[i].setLayout(new FlowLayout());
            infoRetour[i].setLayout(new BoxLayout(infoRetour[i], BoxLayout.PAGE_AXIS));
            knoppenRetour[i].setLayout(new FlowLayout());

            retouraanvraag[i].add(infoRetour[i]);
            retouraanvraag[i].add(knoppenRetour[i]);
            retouroverzicht.add(retouraanvraag[i]);
        }

        retouroverzicht.setLayout(new FlowLayout());
        retouroverzicht.setSize(700,700);
        retouroverzicht.setVisible(true);
    }
}

