package vehicles.gui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
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
import java.text.Format;
import java.util.Date;
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
public class Simulator extends FrameView implements ChangeListener, ItemListener, FocusListener {

    public Simulator(SingleFrameApplication app) {
        super(app);
        setSimulationArray();
        setVehicleArray();
        setEnvironmentArray();
        //engine = new SimulatonEngine(simulationArray[0]); //populate the engine with a simulation object
        enviroPreview = new EnvironmentPreview(simulationArray[0].getEnvironment());
        isPaused = false;
        
        simulationDropDown = new DefaultComboBoxModel(simulationArray);

        initComponents();

        //populateFields(simulationArray[0]);

        // important to call this whenever embedding a PApplet.
        // It ensures that the animation thread is started and
        // that other internal variables are properly set.
        // engine.init();
        // engine.stop();
        enviroPreview.init();
        //engine.adjustRunningSpeed((float)this.slider_Speed.getValue());
        //jPanel3.remove(engine);
        //jPanel3.add(enviroPreview);
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
        jTabbedPane1 = new JTabbedPane();
        jScrollPane1 = new JScrollPane();
        jPanel3 = new JPanel();
        jPanel2 = new JPanel();
        jButton3 = new JButton();
        scrollpane_SystemLog = new JScrollPane();
        textarea_SystemLog = new JTextArea();
        jSeparator7 = new JSeparator();
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
        jMenuItem5 = new JMenuItem();
        jMenuItem1 = new JMenuItem();
        jMenuItem8 = new JMenuItem();
        JMenu helpMenu = new JMenu();
        JMenuItem aboutMenuItem = new JMenuItem();
        toolBar = new JToolBar();
        jPanel1 = new JPanel();
        button_Start = new JButton();
        button_Stop = new JButton();
        dropdown_SelectedSimulation = new JComboBox();
        button_Pause = new JToggleButton();
        jSeparator1 = new Separator();
        slider_Speed = new JSlider();

        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new Dimension(820, 644));

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jPanel3.setAutoscrolls(true);
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new GridBagLayout());

        jPanel3.add(enviroPreview);

        jScrollPane1.setViewportView(jPanel3);

        ResourceMap resourceMap = Application.getInstance(VehiclesApp.class).getContext().getResourceMap(Simulator.class);
        jTabbedPane1.addTab(resourceMap.getString("jScrollPane1.TabConstraints.tabTitle"), jScrollPane1); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        ActionMap actionMap = Application.getInstance(VehiclesApp.class).getContext().getActionMap(Simulator.class, this);
        jButton3.setAction(actionMap.get("saveLog")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N

        scrollpane_SystemLog.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane_SystemLog.setName("scrollpane_SystemLog"); // NOI18N

        textarea_SystemLog.setColumns(20);
        textarea_SystemLog.setEditable(false);
        textarea_SystemLog.setFont(resourceMap.getFont("textarea_SystemLog.font")); // NOI18N
        textarea_SystemLog.setLineWrap(true);
        textarea_SystemLog.setRows(5);
        textarea_SystemLog.setText(resourceMap.getString("textarea_SystemLog.text")); // NOI18N
        textarea_SystemLog.setWrapStyleWord(true);
        textarea_SystemLog.setDoubleBuffered(true);
        textarea_SystemLog.setName("textarea_SystemLog"); // NOI18N
        scrollpane_SystemLog.setViewportView(textarea_SystemLog);

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jButton3, GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(scrollpane_SystemLog, GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(scrollpane_SystemLog, GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jButton3))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jSeparator7.setName("jSeparator7"); // NOI18N

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(jSeparator7, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jSeparator7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE))
        );

        menuBar.setMinimumSize(new Dimension(261, 21));
        menuBar.setName("menuBar"); // NOI18N

        simulationMenu.setText(resourceMap.getString("simulationMenu.text")); // NOI18N
        simulationMenu.setName("simulationMenu"); // NOI18N

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

        jMenuItem5.setAction(actionMap.get("reloadXML")); // NOI18N
        jMenuItem5.setText(resourceMap.getString("jMenuItem5.text")); // NOI18N
        jMenuItem5.setName("jMenuItem5"); // NOI18N
        optionsMenu.add(jMenuItem5);

        jMenuItem1.setAction(actionMap.get("resetXML")); // NOI18N
        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        optionsMenu.add(jMenuItem1);

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

        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        toolBar.setAlignmentY(0.5F);
        toolBar.setName("toolBar"); // NOI18N
        toolBar.setRequestFocusEnabled(false);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new Dimension(100, 71));

        button_Start.setAction(actionMap.get("startSim")); // NOI18N
        button_Start.setText(resourceMap.getString("button_Start.text")); // NOI18N
        button_Start.setFocusable(false);
        button_Start.setHorizontalTextPosition(SwingConstants.CENTER);
        button_Start.setIconTextGap(10);
        button_Start.setName("button_Start"); // NOI18N

        button_Stop.setAction(actionMap.get("stopSim")); // NOI18N
        button_Stop.setText(resourceMap.getString("button_Stop.text")); // NOI18N
        button_Stop.setFocusable(false);
        button_Stop.setHorizontalTextPosition(SwingConstants.CENTER);
        button_Stop.setName("button_Stop"); // NOI18N

        dropdown_SelectedSimulation.setMaximumRowCount(4);
        dropdown_SelectedSimulation.setModel(simulationDropDown);
        dropdown_SelectedSimulation.setName("dropdown_SelectedSimulation"); // NOI18N
        dropdown_SelectedSimulation.addItemListener(this);
        dropdown_SelectedSimulation.addFocusListener(this);

        button_Pause.setAction(actionMap.get("pauseSim")); // NOI18N
        button_Pause.setText(resourceMap.getString("button_Pause.text")); // NOI18N
        button_Pause.setName("button_Pause"); // NOI18N

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(button_Start)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(button_Stop)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(button_Pause))
                    .addComponent(dropdown_SelectedSimulation, 0, 317, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dropdown_SelectedSimulation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(button_Start, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_Stop, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_Pause, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        toolBar.add(jPanel1);

        jSeparator1.setMinimumSize(new Dimension(6, 4));
        jSeparator1.setName("jSeparator1"); // NOI18N
        jSeparator1.setPreferredSize(new Dimension(6, 4));
        toolBar.add(jSeparator1);

        slider_Speed.setMajorTickSpacing(10);
        slider_Speed.setMaximum(200);
        slider_Speed.setMinorTickSpacing(2);
        slider_Speed.setPaintLabels(true);
        slider_Speed.setPaintTicks(true);
        slider_Speed.setEnabled(false);
        slider_Speed.setFocusable(false);
        slider_Speed.setMaximumSize(new Dimension(32767, 30000));
        slider_Speed.setMinimumSize(new Dimension(36, 48));
        slider_Speed.setName("slider_Speed"); // NOI18N
        slider_Speed.addChangeListener(this);
        toolBar.add(slider_Speed);

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setToolBar(toolBar);
    }

    // Code for dispatching events from components to event handlers.

    public void focusGained(java.awt.event.FocusEvent evt) {
        if (evt.getSource() == dropdown_SelectedSimulation) {
            Simulator.this.dropdown_SelectedSimulationFocusGained(evt);
        }
    }

    public void focusLost(java.awt.event.FocusEvent evt) {
    }

    public void itemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getSource() == dropdown_SelectedSimulation) {
            Simulator.this.dropdown_SelectedSimulationItemStateChanged(evt);
        }
    }

    public void stateChanged(javax.swing.event.ChangeEvent evt) {
        if (evt.getSource() == slider_Speed) {
            Simulator.this.slider_SpeedStateChanged(evt);
        }
    }// </editor-fold>//GEN-END:initComponents

    private void slider_SpeedStateChanged(ChangeEvent evt) {//GEN-FIRST:event_slider_SpeedStateChanged
    	if(slider_Speed.getValue() == 0){
    		slider_Speed.setValue(1);
    	}
    	engine.adjustRunningSpeed((float)slider_Speed.getValue());
        jPanel3.validate();
}//GEN-LAST:event_slider_SpeedStateChanged

    private void dropdown_SelectedSimulationItemStateChanged(ItemEvent evt) {//GEN-FIRST:event_dropdown_SelectedSimulationItemStateChanged
        JComboBox tempComboBox = (JComboBox) evt.getSource();
        EditorSimulation selected = (EditorSimulation) tempComboBox.getSelectedItem();
        enviroPreview.setEnvironment(selected.getEnvironment());
        //engine = new SimulatonEngine(selected);
        //engine.init();
        //engine.stop();
        jPanel3.validate();
        //engine.setSim(selected);
        //engine.setup();
       // engine.startSim();
        //engine.pause();
        //populateFields(selected);
        //engine = new SimulatonEngine(selected);
        //engine.init();
}//GEN-LAST:event_dropdown_SelectedSimulationItemStateChanged

    private void dropdown_SelectedSimulationFocusGained(FocusEvent evt) {//GEN-FIRST:event_dropdown_SelectedSimulationFocusGained
        setSimulationArray();
        simulationDropDown = new DefaultComboBoxModel(simulationArray);
        dropdown_SelectedSimulation.setModel(simulationDropDown);
    }//GEN-LAST:event_dropdown_SelectedSimulationFocusGained

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

    @Action
    public void startSim() {
        enviroPreview.destroy();
        EditorSimulation selected = (EditorSimulation) dropdown_SelectedSimulation.getSelectedItem();
        engine = new SimulatonEngine(selected);
        engine.init();
        jPanel3.remove(enviroPreview);
        jPanel3.add(engine);

        
        button_Start.setEnabled(false);
        dropdown_SelectedSimulation.setEnabled(false);
        slider_Speed.setEnabled(true);
        //engine.setup();
        button_Stop.setEnabled(true);
        button_Pause.setEnabled(true);
        engine.adjustRunningSpeed((float)this.slider_Speed.getValue());
        engine.startSim();
        button_Pause.setSelected(true);
        pauseSim();
        jPanel3.validate();
    }

    @Action
    public void stopSim() {
        engine.destroy();
        jPanel3.remove(engine);
        enviroPreview.start();
        jPanel3.add(enviroPreview);
        jPanel3.validate();
        button_Start.setEnabled(true);
        dropdown_SelectedSimulation.setEnabled(true);
        button_Stop.setEnabled(false);
        button_Pause.setEnabled(false);
        slider_Speed.setEnabled(false);
        slider_Speed.setValue(50);
        isPaused = false;
        if(button_Pause.isSelected())
            button_Pause.setSelected(false);
    }

    @Action
    public void pauseSim() {
        if (isPaused) {
            isPaused = false;
            slider_Speed.setEnabled(true);
            engine.startSim();
        } else {
            isPaused = true;
            engine.pause();
            slider_Speed.setEnabled(false);
        }
    }

    @Action
    public void saveLog() {
    }

    public void writeToLog(String text) {
        formattedTime = formatter.format(now.getTime());
        textarea_SystemLog.append("\n" + formattedTime + " : " + text);
    }

    @Action
    public void resetXML() {
        UtilMethods.resetXML();
        reloadXML();
    }

    @Action
    public void reloadXML() {
        setSimulationArray();
        setVehicleArray();
        setEnvironmentArray();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JToggleButton button_Pause;
    private JButton button_Start;
    private JButton button_Stop;
    private JComboBox dropdown_SelectedSimulation;
    private JMenu environmentMenu;
    private JButton jButton3;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem10;
    private JMenuItem jMenuItem11;
    private JMenuItem jMenuItem12;
    private JMenuItem jMenuItem2;
    private JMenuItem jMenuItem3;
    private JMenuItem jMenuItem4;
    private JMenuItem jMenuItem5;
    private JMenuItem jMenuItem6;
    private JMenuItem jMenuItem7;
    private JMenuItem jMenuItem8;
    private JMenuItem jMenuItem9;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JScrollPane jScrollPane1;
    private Separator jSeparator1;
    private JSeparator jSeparator2;
    private JSeparator jSeparator3;
    private JSeparator jSeparator4;
    private JSeparator jSeparator5;
    private JSeparator jSeparator7;
    private JTabbedPane jTabbedPane1;
    private JPanel mainPanel;
    private JMenuBar menuBar;
    private JMenu optionsMenu;
    private JScrollPane scrollpane_SystemLog;
    private JSlider slider_Speed;
    private JTextArea textarea_SystemLog;
    private JToolBar toolBar;
    private JMenu vehicleMenu;
    // End of variables declaration//GEN-END:variables

    private JDialog aboutBox;
    private JFrame environmentEditor;
    private JFrame vehicleEditor;
    private JFrame simulationEditor;
    private SimulatonEngine engine;
    private EnvironmentPreview enviroPreview;

    private Environment[] environmentArray;
    private EditorVehicle[] vehicleArray;
    private EditorSimulation[] simulationArray;

    private DefaultComboBoxModel simulationDropDown;
    private Boolean isPaused;
    private String formattedTime;
    private Date now = new Date();
    private Format formatter;
}
