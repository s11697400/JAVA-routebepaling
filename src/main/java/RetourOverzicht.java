import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static java.lang.Integer.parseInt;


public class RetourOverzicht extends JPanel {


    public RetourOverzicht() {
        JFrame retouroverzicht = new JFrame();
        int aantalRetours = DatabaseConnectie.getAantalRetours();
        String[] statusretour = {"Goedgekeurd", "Afgewezen"};
        JPanel[] retouraanvraag = new JPanel[aantalRetours];
        JPanel[] infoRetour = new JPanel[aantalRetours];
        JPanel[] knoppenRetour = new JPanel[aantalRetours];

        for (int i = 0; i < aantalRetours; i++) {

            JLabel retourID = new JLabel("RetourID: " + DatabaseConnectie.getRetourID().get(i));
            JLabel orderID = new JLabel("OrderID: " + DatabaseConnectie.getOrderID().get(i));
            JLabel productnummer = new JLabel("Productnummer: " + DatabaseConnectie.getProductnummer().get(i));
            JLabel conditie = new JLabel("Conditie: " + DatabaseConnectie.getConditie().get(i));
            JLabel status = new JLabel("Status: " + DatabaseConnectie.getStatus().get(i));
            JLabel reden = new JLabel("Reden: " + DatabaseConnectie.getReden().get(i));
            JLabel spacer = new JLabel(" ");
            JButton uitvoeren = new JButton("Uitvoeren");
            JComboBox statusbox = new JComboBox(statusretour);
            JButton verwijderen = new JButton("Verwijderen");
            JButton klant = new JButton("Klant info");

            retouraanvraag[i] = new JPanel();
            infoRetour[i] = new JPanel();
            knoppenRetour[i] = new JPanel();

            infoRetour[i].add(retourID);
            infoRetour[i].add(orderID);
            infoRetour[i].add(productnummer);
            infoRetour[i].add(conditie);
            infoRetour[i].add(status);
            infoRetour[i].add(reden);
            infoRetour[i].add(spacer);
            knoppenRetour[i].add(uitvoeren);
            knoppenRetour[i].add(statusbox);
            knoppenRetour[i].add(verwijderen);
            knoppenRetour[i].add(klant);

            klant.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent klantActionPerformed) {
                    if (klantActionPerformed.getSource() == klant) {
                        new KlantOverzicht(parseInt(retourID.getText().replace("RetourID: ", "")));

                    }
                }
            });

            uitvoeren.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent uitvoerenActionPerformed) {
                    if (uitvoerenActionPerformed.getSource() == uitvoeren) {

                        Connection con = DatabaseConnectie.dbConnect();
                        PreparedStatement stmt = null;

                        try {
                            stmt = con.prepareStatement("UPDATE retour SET Status =? WHERE idRetour=? ");

                            stmt.setString(1, statusbox.getSelectedItem().toString());
                            stmt.setInt(2, parseInt(retourID.getText().replace("RetourID: ", "")));

                            stmt.executeUpdate();
                            con.close();
                            for (int j = 0; j < aantalRetours; j++) {
                                status.setText("Status: " + DatabaseConnectie.getStatus().get(j));
                            }
                        } catch (Exception e) {
                            System.err.println("exception");
                            System.err.println(e.getMessage());
                        }
                    }
                }
            });


            verwijderen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent verwijderenActionPerformed) {
                    int keuze = 0;
                    if (verwijderenActionPerformed.getSource() == verwijderen) {
                        keuze = JOptionPane.showConfirmDialog(
                                verwijderen, "Weet u zeker dat u deze request wilt verwijderen?", "Verwijderen?",
                                JOptionPane.YES_NO_OPTION);
                    }
                    if (keuze == JOptionPane.YES_OPTION) {
                        Connection con = DatabaseConnectie.dbConnect();
                        PreparedStatement stmt = null;

                        try {
                            stmt = con.prepareStatement("DELETE FROM retour WHERE idRetour=? ");

                            stmt.setInt(1, parseInt(retourID.getText().replace("RetourID: ", "")));

                            stmt.executeUpdate();
                            con.close();
                            retouroverzicht.setVisible(false);
                            new RetourOverzicht();

                        } catch (Exception e) {
                            System.err.println("exception");
                            System.err.println(e.getMessage());
                        }
                        setVisible(false);
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
        retouroverzicht.setTitle("Retour aanvragen");
        retouroverzicht.setSize(900, 700);
        retouroverzicht.setVisible(true);
    }
}


