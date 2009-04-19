/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vehicles.processing;

/**
 *
 * @author Niall O'Hara
 */
/*
 * ScrollTest.java
 *
 */

/**
 *
 * @author  zzynx
 */
public class ScrollTest extends javax.swing.JFrame {

    public ScrollTest() {
        embed = new ProcessingVehicle();
        initComponents();
        embed.init();
    }

    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("jButton1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(embed, gridBagConstraints);

        jScrollPane1.setViewportView(jPanel1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }

    private void exitForm(java.awt.event.WindowEvent evt) {
        System.exit(0);
    }

    public static void main(String args[]) {
        ScrollTest frame = new ScrollTest();
        frame.setSize(300,300);
        frame.setLocationRelativeTo(null);
        frame.show();
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private ProcessingVehicle embed;

}

