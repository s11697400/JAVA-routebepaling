import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.browser.BrowserSettings;
import com.teamdev.jxbrowser.browser.CloseOptions;
import com.teamdev.jxbrowser.browser.SavePageType;
import com.teamdev.jxbrowser.browser.callback.BrowserCallback;
import com.teamdev.jxbrowser.browser.event.BrowserEvent;
import com.teamdev.jxbrowser.capture.CaptureSession;
import com.teamdev.jxbrowser.devtools.DevTools;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.event.Observer;
import com.teamdev.jxbrowser.event.Subscription;
import com.teamdev.jxbrowser.frame.Frame;
import com.teamdev.jxbrowser.media.Audio;
import com.teamdev.jxbrowser.navigation.Navigation;
import com.teamdev.jxbrowser.os.Display;
import com.teamdev.jxbrowser.profile.Profile;
import com.teamdev.jxbrowser.search.TextFinder;
import com.teamdev.jxbrowser.ui.Bitmap;
import com.teamdev.jxbrowser.ui.Size;
import com.teamdev.jxbrowser.ui.event.*;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import com.teamdev.jxbrowser.zoom.Zoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

//Gemaakt door Michiel van Dooren s1166988 en Thijs Soepenberg s1169740
public class RouteGUI extends JFrame {
    DatabaseConnectie db = new DatabaseConnectie();
    int RouteID = db.getRouteID();
    double AantalKm = db.getAantalKm();

    public RouteGUI() {
        initComponents();
        open_site();

    }


    private void initComponents() {

        setTitle("Route");

        jPanel2 = new javax.swing.JPanel(new BorderLayout());
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Route: " + RouteID);

        jLabel2.setText("Aantal Km: " + (double) Math.round(Math.round(AantalKm*100.0)/100.0) * 111.0);


        jButton1.setText("Klaar met route");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(133, 133, 133)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(jButton1))
                                .addContainerGap(45, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 293, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );





        pack();
        setVisible(true);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if(evt.getSource() == jButton1){
            System.out.println("hallo");
        }
    }


    public static void main(String args[]) {






        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RouteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RouteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RouteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RouteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RouteGUI().setVisible(true);
            }
        });
    }


    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    //private javax.swing.JPanel jPanel2;
    private static final String setMarkerScript =
            "var myLatlng = new google.maps.LatLng(53.0143458, 6.6129259);\n" +
                    "var marker = new google.maps.Marker({\n" +
                    "    position: myLatlng,\n" +
                    "    map: map,\n" +
                    "    title: 'Hello World!'\n" +
                    "});";




    private void open_site() {

        System.setProperty("jxbrowser.license.key", "6P830J66YBMGYQKUX5L1TUIEHJP07JEIR0VZMXPZB08P6802UFJLPSN115BXZZITID1S");

        Engine engine = Engine.newInstance(RenderingMode.HARDWARE_ACCELERATED);
        Browser browser = engine.newBrowser();



            JTextField addressbar = new JTextField("http://localhost/googlemaps/simple_maps.html");
            addressbar.addActionListener(e -> browser.navigation().loadUrl(addressbar.getText()));
            BrowserView view = BrowserView.newInstance(browser);
            jPanel2.add(addressbar, BorderLayout.SOUTH);
            jPanel2.add(view, BorderLayout.CENTER);
            view.setSize(600,293);
            add(jPanel2);

           // JButton setMarkerButton = new JButton("Set Marker");
            jButton1.addActionListener(e ->
                browser.mainFrame().ifPresent(frame ->
                        frame.executeJavaScript(setMarkerScript)));



            browser.navigation().loadUrl(addressbar.getText());

    }


}
