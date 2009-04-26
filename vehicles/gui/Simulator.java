package vehicles.gui;

import java.awt.GridBagLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import vehicles.vehicle.*;
import vehicles.util.*;
import vehicles.environment.*;
import vehicles.simulation.*;
import vehicles.processing.*;
import vehicles.*;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.ActionMap;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.JToolBar.Separator;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import javax.swing.JDialog;
import javax.swing.JFrame;

/*
 * Simulator.java
 *
 * Created on 26-Mar-2009, 13:02:43
 * @author Niall O'Hara
 */
public class Simulator extends FrameView implements ChangeListener {

    public Simulator(SingleFrameApplication app) {
        super(app);
        embed = new ProcessingVehicle();
        initComponents();

        setSimulationArray();
        setVehicleArray();
        setEnvironmentArray();

        // important to call this whenever embedding a PApplet.
        // It ensures that the animation thread is started and
        // that other internal variables are properly set.
        embed.init();
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = VehiclesApp.getApplication().getMainFrame();
            aboutBox = new AboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        VehiclesApp.getApplication().show(aboutBox);
    }
    
    @Action
    public void showEnvironmentEditor() {
        if (environmentEditor == null) {
            JFrame mainFrame = VehiclesApp.getApplication().getMainFrame();
            environmentEditor = new EnvironmentEditor(mainFrame, this);
            environmentEditor.setLocationRelativeTo(mainFrame);
        }
        VehiclesApp.getApplication().show(environmentEditor);
    }

    @Action
    public void showVehicleEditor() {
        if (vehicleEditor == null) {
            JFrame mainFrame = VehiclesApp.getApplication().getMainFrame();
            vehicleEditor = new VehicleEditor(mainFrame, this);
            vehicleEditor.setLocationRelativeTo(mainFrame);
        }
        VehiclesApp.getApplication().show(vehicleEditor);
    }

    @Action
    public void showsSimulationEditor() {
        if (simulationEditor == null) {
            JFrame mainFrame = VehiclesApp.getApplication().getMainFrame();
            simulationEditor = new SimulationEditor(mainFrame, this);
            simulationEditor.setLocationRelativeTo(mainFrame);
        }
        VehiclesApp.getApplication().show(simulationEditor);
    }

    @Action
    public void open() {
        JFileChooser fc = createFileChooser("openFileChooser");
        int option = fc.showOpenDialog(getFrame());
        if (JFileChooser.APPROVE_OPTION == option) {
            fc.getSelectedFile();
        }
    }

    @Action
    public void resetWindow(){
        ((Window)mainPanel.getTopLevelAncestor()).pack();
    }

    private JFileChooser createFileChooser(String name) {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle(getResourceMap().getString(name + ".dialogTitle"));
        String textFilesDesc = getResourceMap().getString("txtFileExtensionDescription");
        return fc;
    }


    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new JPanel();
        jScrollPane1 = new JScrollPane();
        jPanel3 = new JPanel();
        menuBar = new JMenuBar();
        JMenu simulationMenu = new JMenu();
        jMenuItem4 = new JMenuItem();
        jSeparator2 = new JSeparator();
        jMenuItem10 = new JMenuItem();
        jMenuItem9 = new JMenuItem();
        jSeparator3 = new JSeparator();
        JMenuItem exitMenuItem = new JMenuItem();
        vehicleMenu = new JMenu();
        jMenuItem3 = new JMenuItem();
        jSeparator4 = new JSeparator();
        jMenuItem6 = new JMenuItem();
        jMenuItem11 = new JMenuItem();
        environmentMenu = new JMenu();
        jMenuItem2 = new JMenuItem();
        jSeparator5 = new JSeparator();
        jMenuItem12 = new JMenuItem();
        jMenuItem7 = new JMenuItem();
        optionsMenu = new JMenu();
        jMenuItem1 = new JMenuItem();
        jSeparator6 = new JSeparator();
        jMenuItem8 = new JMenuItem();
        JMenu helpMenu = new JMenu();
        JMenuItem aboutMenuItem = new JMenuItem();
        toolBar = new JToolBar();
        jPanel1 = new JPanel();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton4 = new JButton();
        jComboBox1 = new JComboBox();
        jSeparator1 = new Separator();
        jSlider1 = new JSlider();

        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new Dimension(820, 610));

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jPanel3.setAutoscrolls(true);
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new GridBagLayout());

        jPanel3.add(embed);

        jScrollPane1.setViewportView(jPanel3);

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
        );

        menuBar.setMinimumSize(new Dimension(261, 21));
        menuBar.setName("menuBar"); // NOI18N

        ResourceMap resourceMap = Application.getInstance(VehiclesApp.class).getContext().getResourceMap(Simulator.class);
        simulationMenu.setText(resourceMap.getString("simulationMenu.text")); // NOI18N
        simulationMenu.setName("simulationMenu"); // NOI18N

        ActionMap actionMap = Application.getInstance(VehiclesApp.class).getContext().getActionMap(Simulator.class, this);
        jMenuItem4.setAction(actionMap.get("showsSimulationEditor")); // NOI18N
        jMenuItem4.setText(resourceMap.getString("jMenuItem4.text")); // NOI18N
        jMenuItem4.setName("jMenuItem4"); // NOI18N
        simulationMenu.add(jMenuItem4);

        jSeparator2.setName("jSeparator2"); // NOI18N
        simulationMenu.add(jSeparator2);

        jMenuItem10.setText(resourceMap.getString("jMenuItem10.text")); // NOI18N
        jMenuItem10.setName("jMenuItem10"); // NOI18N
        simulationMenu.add(jMenuItem10);

        jMenuItem9.setText(resourceMap.getString("jMenuItem9.text")); // NOI18N
        jMenuItem9.setName("jMenuItem9"); // NOI18N
        simulationMenu.add(jMenuItem9);

        jSeparator3.setName("jSeparator3"); // NOI18N
        simulationMenu.add(jSeparator3);

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        simulationMenu.add(exitMenuItem);

        menuBar.add(simulationMenu);

        vehicleMenu.setText(resourceMap.getString("vehicleMenu.text")); // NOI18N
        vehicleMenu.setName("vehicleMenu"); // NOI18N

        jMenuItem3.setAction(actionMap.get("showVehicleEditor")); // NOI18N
        jMenuItem3.setText(resourceMap.getString("jMenuItem3.text")); // NOI18N
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        vehicleMenu.add(jMenuItem3);

        jSeparator4.setName("jSeparator4"); // NOI18N
        vehicleMenu.add(jSeparator4);

        jMenuItem6.setText(resourceMap.getString("jMenuItem6.text")); // NOI18N
        jMenuItem6.setName("jMenuItem6"); // NOI18N
        vehicleMenu.add(jMenuItem6);

        jMenuItem11.setText(resourceMap.getString("jMenuItem11.text")); // NOI18N
        jMenuItem11.setName("jMenuItem11"); // NOI18N
        vehicleMenu.add(jMenuItem11);

        menuBar.add(vehicleMenu);

        environmentMenu.setText(resourceMap.getString("environmentMenu.text")); // NOI18N
        environmentMenu.setName("environmentMenu"); // NOI18N

        jMenuItem2.setAction(actionMap.get("showEnvironmentEditor")); // NOI18N
        jMenuItem2.setText(resourceMap.getString("jMenuItem2.text")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        environmentMenu.add(jMenuItem2);

        jSeparator5.setName("jSeparator5"); // NOI18N
        environmentMenu.add(jSeparator5);

        jMenuItem12.setText(resourceMap.getString("jMenuItem12.text")); // NOI18N
        jMenuItem12.setName("jMenuItem12"); // NOI18N
        environmentMenu.add(jMenuItem12);

        jMenuItem7.setText(resourceMap.getString("jMenuItem7.text")); // NOI18N
        jMenuItem7.setName("jMenuItem7"); // NOI18N
        environmentMenu.add(jMenuItem7);

        menuBar.add(environmentMenu);

        optionsMenu.setText(resourceMap.getString("optionsMenu.text")); // NOI18N
        optionsMenu.setName("optionsMenu"); // NOI18N

        jMenuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        optionsMenu.add(jMenuItem1);

        jSeparator6.setName("jSeparator6"); // NOI18N
        optionsMenu.add(jSeparator6);

        jMenuItem8.setAction(actionMap.get("resetWindow")); // NOI18N
        jMenuItem8.setText(resourceMap.getString("jMenuItem8.text")); // NOI18N
        jMenuItem8.setName("jMenuItem8"); // NOI18N
        optionsMenu.add(jMenuItem8);

        menuBar.add(optionsMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        toolBar.setRollover(true);
        toolBar.setAlignmentY(0.5F);
        toolBar.setName("toolBar"); // NOI18N
        toolBar.setRequestFocusEnabled(false);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new Dimension(100, 71));

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton1.setIconTextGap(10);
        jButton1.setName("jButton1"); // NOI18N

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setEnabled(false);
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton2.setName("jButton2"); // NOI18N

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setEnabled(false);
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton4.setName("jButton4"); // NOI18N

        jComboBox1.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setName("jComboBox1"); // NOI18N

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addComponent(jComboBox1, 0, 313, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        toolBar.add(jPanel1);

        jSeparator1.setMinimumSize(new Dimension(6, 4));
        jSeparator1.setName("jSeparator1"); // NOI18N
        jSeparator1.setPreferredSize(new Dimension(6, 4));
        toolBar.add(jSeparator1);

        jSlider1.setMajorTickSpacing(10);
        jSlider1.setMinorTickSpacing(1);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.setSnapToTicks(true);
        jSlider1.setValue(10);
        jSlider1.setFocusable(false);
        jSlider1.setMaximumSize(new Dimension(32767, 30000));
        jSlider1.setMinimumSize(new Dimension(36, 48));
        jSlider1.setName("jSlider1"); // NOI18N
        jSlider1.addChangeListener(this);
        toolBar.add(jSlider1);

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setToolBar(toolBar);
    }

    // Code for dispatching events from components to event handlers.

    public void stateChanged(javax.swing.event.ChangeEvent evt) {
        if (evt.getSource() == jSlider1) {
            Simulator.this.jSlider1StateChanged(evt);
        }
    }// </editor-fold>//GEN-END:initComponents

    private void jSlider1StateChanged(ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        embed.setMove_speed((float)jSlider1.getValue()/5);
        jPanel3.validate();
    }//GEN-LAST:event_jSlider1StateChanged



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JMenu environmentMenu;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton4;
    private JComboBox jComboBox1;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem10;
    private JMenuItem jMenuItem11;
    private JMenuItem jMenuItem12;
    private JMenuItem jMenuItem2;
    private JMenuItem jMenuItem3;
    private JMenuItem jMenuItem4;
    private JMenuItem jMenuItem6;
    private JMenuItem jMenuItem7;
    private JMenuItem jMenuItem8;
    private JMenuItem jMenuItem9;
    private JPanel jPanel1;
    private JPanel jPanel3;
    private JScrollPane jScrollPane1;
    private Separator jSeparator1;
    private JSeparator jSeparator2;
    private JSeparator jSeparator3;
    private JSeparator jSeparator4;
    private JSeparator jSeparator5;
    private JSeparator jSeparator6;
    private JSlider jSlider1;
    private JPanel mainPanel;
    private JMenuBar menuBar;
    private JMenu optionsMenu;
    private JToolBar toolBar;
    private JMenu vehicleMenu;
    // End of variables declaration//GEN-END:variables

    private JDialog aboutBox;
    private JFrame environmentEditor;
    private JFrame vehicleEditor;
    private JFrame simulationEditor;
    private ProcessingVehicle embed;

    private Environment[] environmentArray;
    private EditorVehicle[] vehicleArray;
    private EditorSimulation[] simulationArray;

    public Environment[] getEnvironmentArray() {
        return environmentArray;
    }

    public void setEnvironmentArray() {
        this.environmentArray = UtilMethods.getEnvironmentsFromFolder("xml/environments");
    }

    public void setEnvironmentArray(Environment[] environmentArray) {
        this.environmentArray = environmentArray;
    }

    public EditorSimulation[] getSimulationArray() {
        return simulationArray;
    }

    public void setSimulationArray() {
        this.simulationArray = UtilMethods.getSimulationsFromFolder("xml/simulations");
    }

    public void setSimulationArray(EditorSimulation[] simulationArray) {
        this.simulationArray = simulationArray;
    }

    public EditorVehicle[] getVehicleArray() {
        return vehicleArray;
    }

    public void setVehicleArray() {
        this.vehicleArray = UtilMethods.getVehiclesFromFolder("xml/vehicles");
    }

    public void setVehicleArray(EditorVehicle[] vehicleArray) {
        this.vehicleArray = vehicleArray;
    }

}
