
import javax.swing.*;

public class RetourOverzichtGUI extends javax.swing.JFrame {

    public RetourOverzichtGUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        statusComboBox = new javax.swing.JComboBox<>();
        VerwijderenButton = new javax.swing.JButton();
        customerIDLabel = new javax.swing.JLabel();
        productNummerLabel = new javax.swing.JLabel();
        resterendeDagenLabel = new javax.swing.JLabel();
        redenLabel = new javax.swing.JLabel();
        uitvoerenButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Retour aanvraag overzicht");

        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status", "placeholder" }));
        statusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusComboBoxActionPerformed(evt);
            }
        });

        VerwijderenButton.setText("Verwijderen");
        VerwijderenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerwijderenButtonActionPerformed(evt);
            }
        });

        customerIDLabel.setText("CustomerID: placeholder");

        productNummerLabel.setText("Productnummer: placeholder");

        resterendeDagenLabel.setText("Resterende dagen retourtermijn: placeholder");

        redenLabel.setText("Reden voor retour: placeholder");

        uitvoerenButton.setText("Uitvoeren");
        uitvoerenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uitvoerenButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(productNummerLabel)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(resterendeDagenLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                                                .addComponent(uitvoerenButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(VerwijderenButton)
                                                .addContainerGap(176, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(customerIDLabel)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(redenLabel)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(customerIDLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(productNummerLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(resterendeDagenLabel)
                                        .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(VerwijderenButton)
                                        .addComponent(uitvoerenButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(redenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(574, Short.MAX_VALUE))
        );

        pack();
    }

    private void VerwijderenButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() ==VerwijderenButton){
            int keuze = JOptionPane.showConfirmDialog(
                    this, "Weet u zeker dat u het request wilt verwijderen?", "Verwijderen?",
                    JOptionPane.YES_NO_OPTION);
        }
    }

    private void uitvoerenButtonActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void statusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RetourOverzichtGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RetourOverzichtGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RetourOverzichtGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RetourOverzichtGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RetourOverzichtGUI().setVisible(true);
            }
        });
    }

    private javax.swing.JButton VerwijderenButton;
    private javax.swing.JLabel customerIDLabel;
    private javax.swing.JLabel productNummerLabel;
    private javax.swing.JLabel redenLabel;
    private javax.swing.JLabel resterendeDagenLabel;
    private javax.swing.JComboBox<String> statusComboBox;
    private javax.swing.JButton uitvoerenButton;
}
