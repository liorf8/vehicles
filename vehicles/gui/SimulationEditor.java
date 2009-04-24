package vehicles.gui;

import vehicles.*;
import javax.swing.AbstractListModel;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;

/*
 * SimulationEditor.java
 *
 * Created on 28-Mar-2009, 14:51:37
 * @author Niall O'Hara
 */
public class SimulationEditor extends javax.swing.JFrame {

    /** Creates new form SimulationEditor */
    public SimulationEditor(java.awt.Frame parent) {
        initComponents();
    }

    @Action public void cancel() {
        dispose();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new JTabbedPane();
        jPanel1 = new JPanel();
        jPanel4 = new JPanel();
        jScrollPane1 = new JScrollPane();
        jList1 = new JList();
        jPanel5 = new JPanel();
        jScrollPane2 = new JScrollPane();
        jList2 = new JList();
        jPanel6 = new JPanel();
        jButton5 = new JButton();
        jButton6 = new JButton();
        jButton7 = new JButton();
        jButton8 = new JButton();
        jPanel12 = new JPanel();
        jPanel13 = new JPanel();
        jPanel2 = new JPanel();
        jPanel8 = new JPanel();
        jScrollPane3 = new JScrollPane();
        jList3 = new JList();
        jPanel9 = new JPanel();
        jScrollPane4 = new JScrollPane();
        jList4 = new JList();
        jPanel10 = new JPanel();
        jButton9 = new JButton();
        jPanel7 = new JPanel();
        jPanel11 = new JPanel();
        jPanel3 = new JPanel();
        jPanel14 = new JPanel();
        jTextField1 = new JTextField();
        jPanel15 = new JPanel();
        jLabel1 = new JLabel();
        jRadioButton1 = new JRadioButton();
        jRadioButton2 = new JRadioButton();
        jLabel2 = new JLabel();
        jRadioButton3 = new JRadioButton();
        jRadioButton4 = new JRadioButton();
        jPanel16 = new JPanel();
        jScrollPane5 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jPanel17 = new JPanel();
        jTextField4 = new JTextField();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jButton4 = new JButton();

        ResourceMap resourceMap = Application.getInstance(VehiclesApp.class).getContext().getResourceMap(SimulationEditor.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jPanel4.setBorder(BorderFactory.createTitledBorder(resourceMap.getString("jPanel4.border.title"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jList1.setModel(new AbstractListModel() {
            String[] strings = { "Vehicle 1", "Vehicle 2", "Vehicle 3", "Vehicle 4", "Vehicle 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setName("jList1"); // NOI18N
        jScrollPane1.setViewportView(jList1);

        GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
        );

        jPanel5.setBorder(BorderFactory.createTitledBorder(resourceMap.getString("jPanel5.border.title"))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jList2.setModel(new AbstractListModel() {
            String[] strings = { "Vehicle 1", "Vehicle 2", "Vehicle 3", "Vehicle 4", "Vehicle 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList2.setName("jList2"); // NOI18N
        jScrollPane2.setViewportView(jList2);

        GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
        );

        jPanel6.setName("jPanel6"); // NOI18N

        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N

        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setName("jButton6"); // NOI18N

        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setName("jButton7"); // NOI18N

        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setName("jButton8"); // NOI18N

        jPanel12.setBorder(BorderFactory.createTitledBorder(resourceMap.getString("jPanel12.border.title"))); // NOI18N
        jPanel12.setName("jPanel12"); // NOI18N

        jPanel13.setBackground(resourceMap.getColor("jPanel13.background")); // NOI18N
        jPanel13.setName("jPanel13"); // NOI18N

        GroupLayout jPanel13Layout = new GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 114, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        GroupLayout jPanel12Layout = new GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jPanel13, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jPanel13, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        GroupLayout jPanel6Layout = new GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(jButton5, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(jButton6, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(jButton8, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(jButton7, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(jPanel12, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(jButton7)
                .addContainerGap())
        );

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jPanel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jPanel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
                    .addComponent(jPanel5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jPanel8.setBorder(BorderFactory.createTitledBorder(resourceMap.getString("jPanel8.border.title"))); // NOI18N
        jPanel8.setName("jPanel8"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jList3.setModel(new AbstractListModel() {
            String[] strings = { "Enviro 1", "Enviro 2", "Enviro 3", "Enviro 4", "Enviro 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList3.setName("jList3"); // NOI18N
        jScrollPane3.setViewportView(jList3);

        GroupLayout jPanel8Layout = new GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jScrollPane3, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jScrollPane3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
        );

        jPanel9.setBorder(BorderFactory.createTitledBorder(resourceMap.getString("jPanel9.border.title"))); // NOI18N
        jPanel9.setName("jPanel9"); // NOI18N

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        jList4.setModel(new AbstractListModel() {
            String[] strings = { "Enviro 1" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList4.setEnabled(false);
        jList4.setName("jList4"); // NOI18N
        jScrollPane4.setViewportView(jList4);

        GroupLayout jPanel9Layout = new GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jScrollPane4, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jScrollPane4, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
        );

        jPanel10.setName("jPanel10"); // NOI18N

        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setName("jButton9"); // NOI18N

        GroupLayout jPanel10Layout = new GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton9, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton9)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(BorderFactory.createTitledBorder(resourceMap.getString("jPanel7.border.title"))); // NOI18N
        jPanel7.setName("jPanel7"); // NOI18N

        jPanel11.setBackground(resourceMap.getColor("jPanel11.background")); // NOI18N
        jPanel11.setName("jPanel11"); // NOI18N

        GroupLayout jPanel11Layout = new GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 289, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );

        GroupLayout jPanel7Layout = new GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jPanel11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jPanel11, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(jPanel9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
                    .addComponent(jPanel8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
                            .addComponent(jPanel10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(jPanel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        jPanel14.setBorder(BorderFactory.createTitledBorder(resourceMap.getString("jPanel14.border.title"))); // NOI18N
        jPanel14.setName("jPanel14"); // NOI18N

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        GroupLayout jPanel14Layout = new GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jTextField1, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        jPanel15.setBorder(BorderFactory.createTitledBorder(resourceMap.getString("jPanel15.border.title"))); // NOI18N
        jPanel15.setName("jPanel15"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jRadioButton1.setText(resourceMap.getString("jRadioButton1.text")); // NOI18N
        jRadioButton1.setName("jRadioButton1"); // NOI18N

        jRadioButton2.setSelected(true);
        jRadioButton2.setText(resourceMap.getString("jRadioButton2.text")); // NOI18N
        jRadioButton2.setName("jRadioButton2"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jRadioButton3.setSelected(true);
        jRadioButton3.setText(resourceMap.getString("jRadioButton3.text")); // NOI18N
        jRadioButton3.setName("jRadioButton3"); // NOI18N

        jRadioButton4.setText(resourceMap.getString("jRadioButton4.text")); // NOI18N
        jRadioButton4.setName("jRadioButton4"); // NOI18N

        GroupLayout jPanel15Layout = new GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(jRadioButton4)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(jRadioButton3))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)))
                .addContainerGap(262, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton3)))
        );

        jPanel16.setBorder(BorderFactory.createTitledBorder(resourceMap.getString("jPanel16.border.title"))); // NOI18N
        jPanel16.setName("jPanel16"); // NOI18N

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane5.setViewportView(jTextArea1);

        GroupLayout jPanel16Layout = new GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jScrollPane5, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jScrollPane5, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
        );

        jPanel17.setBorder(BorderFactory.createTitledBorder(resourceMap.getString("jPanel17.border.title"))); // NOI18N
        jPanel17.setName("jPanel17"); // NOI18N

        jTextField4.setText(resourceMap.getString("jTextField4.text")); // NOI18N
        jTextField4.setName("jTextField4"); // NOI18N

        GroupLayout jPanel17Layout = new GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jTextField4, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jTextField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(Alignment.TRAILING)
                    .addComponent(jPanel16, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel14, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(jPanel17, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(Alignment.CENTER)
                    .addComponent(jPanel14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jPanel15, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jPanel16, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N

        ActionMap actionMap = Application.getInstance(VehiclesApp.class).getContext().getActionMap(SimulationEditor.class, this);
        jButton4.setAction(actionMap.get("cancel")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap())
            .addComponent(jTabbedPane1, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2))
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(jButton3)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SimulationEditor().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JButton jButton6;
    private JButton jButton7;
    private JButton jButton8;
    private JButton jButton9;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JList jList1;
    private JList jList2;
    private JList jList3;
    private JList jList4;
    private JPanel jPanel1;
    private JPanel jPanel10;
    private JPanel jPanel11;
    private JPanel jPanel12;
    private JPanel jPanel13;
    private JPanel jPanel14;
    private JPanel jPanel15;
    private JPanel jPanel16;
    private JPanel jPanel17;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JPanel jPanel8;
    private JPanel jPanel9;
    private JRadioButton jRadioButton1;
    private JRadioButton jRadioButton2;
    private JRadioButton jRadioButton3;
    private JRadioButton jRadioButton4;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane4;
    private JScrollPane jScrollPane5;
    private JTabbedPane jTabbedPane1;
    private JTextArea jTextArea1;
    private JTextField jTextField1;
    private JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

}