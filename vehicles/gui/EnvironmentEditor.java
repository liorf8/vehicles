package vehicles.gui;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import vehicles.processing.Embedded;
import vehicles.*;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;
import processing.core.*;
import vehicles.util.*;
import vehicles.environment.*;

/*
 * EnvironmentEditor.java
 *
 * Created on 26-Mar-2009, 15:05:05
 * @author Niall O'Hara
 */
public class EnvironmentEditor extends javax.swing.JFrame {

    /** Creates new form EnvironmentEditor */
    public EnvironmentEditor(java.awt.Frame parent) {

        environmentArray = UtilMethods.getEnvironmentsFromFolder("xml/environments");
        environmentDropDown = new DefaultComboBoxModel(environmentArray);

        embed = new Embedded();
        initComponents();
        // important to call this whenever embedding a PApplet.
        // It ensures that the animation thread is started and
        // that other internal variables are properly set.
        //embed.init();
        populateFields(environmentArray[0]);
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

        tabContainer = new JTabbedPane();
        tab_Properties = new JPanel();
        panel_Preview = new JPanel();
        jPanel3 = new JPanel();
        panel_EnvironmentName = new JPanel();
        text_EnvironmentName = new JTextField();
        panel_Author = new JPanel();
        text_Author = new JTextField();
        panel_EnvironmentDescription = new JPanel();
        scrollpanel_VehicleDescription = new JScrollPane();
        text_EnvironmentDescription = new JTextArea();
        panel_LastModified = new JPanel();
        text_LastModified = new JTextField();
        tab_Design = new JPanel();
        panel_Layout = new JPanel();
        jPanel6 = new JPanel();
        panel_Tools = new JPanel();
        jPanel8 = new JPanel();
        jComboBox2 = new JComboBox();
        jPanel9 = new JPanel();
        jComboBox3 = new JComboBox();
        jPanel10 = new JPanel();
        panel_Red = new JPanel();
        slider_Radius = new JSlider();
        text_Radius = new JTextField();
        panel_Red1 = new JPanel();
        slider_Intensity = new JSlider();
        text_Intensity = new JTextField();
        jToggleButton1 = new JToggleButton();
        button_Save = new JButton();
        button_SaveAs = new JButton();
        button_Cancel = new JButton();
        text_Status = new JTextField();
        panel_SelectedEnvironment = new JPanel();
        dropdown_SelectedEnvironment = new JComboBox();

        ResourceMap resourceMap = Application.getInstance(VehiclesApp.class).getContext().getResourceMap(EnvironmentEditor.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setMinimumSize(new Dimension(620, 520));
        setName("Form"); // NOI18N
        addWindowFocusListener(new WindowFocusListener() {
            public void windowGainedFocus(WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(WindowEvent evt) {
            }
        });

        tabContainer.setName("tabContainer"); // NOI18N

        tab_Properties.setName("tab_Properties"); // NOI18N

        panel_Preview.setBorder(BorderFactory.createTitledBorder(resourceMap.getString("panel_Preview.border.title"))); // NOI18N
        panel_Preview.setName("panel_Preview"); // NOI18N

        jPanel3.setBackground(resourceMap.getColor("jPanel3.background")); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 342, Short.MAX_VALUE)
        );

        GroupLayout panel_PreviewLayout = new GroupLayout(panel_Preview);
        panel_Preview.setLayout(panel_PreviewLayout);
        panel_PreviewLayout.setHorizontalGroup(
            panel_PreviewLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_PreviewLayout.setVerticalGroup(
            panel_PreviewLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panel_EnvironmentName.setBorder(BorderFactory.createTitledBorder(null, resourceMap.getString("panel_EnvironmentName.border.title"), TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, resourceMap.getFont("panel_EnvironmentName.border.titleFont"))); // NOI18N
        panel_EnvironmentName.setName("panel_EnvironmentName"); // NOI18N
        panel_EnvironmentName.setPreferredSize(new Dimension(224, 46));

        text_EnvironmentName.setName("text_EnvironmentName"); // NOI18N

        GroupLayout panel_EnvironmentNameLayout = new GroupLayout(panel_EnvironmentName);
        panel_EnvironmentName.setLayout(panel_EnvironmentNameLayout);
        panel_EnvironmentNameLayout.setHorizontalGroup(
            panel_EnvironmentNameLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(text_EnvironmentName, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
        );
        panel_EnvironmentNameLayout.setVerticalGroup(
            panel_EnvironmentNameLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(text_EnvironmentName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        panel_Author.setBorder(BorderFactory.createTitledBorder(null, resourceMap.getString("panel_Author.border.title"), TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, resourceMap.getFont("panel_Author.border.titleFont"))); // NOI18N
        panel_Author.setName("panel_Author"); // NOI18N

        text_Author.setName("text_Author"); // NOI18N

        GroupLayout panel_AuthorLayout = new GroupLayout(panel_Author);
        panel_Author.setLayout(panel_AuthorLayout);
        panel_AuthorLayout.setHorizontalGroup(
            panel_AuthorLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(text_Author, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
        );
        panel_AuthorLayout.setVerticalGroup(
            panel_AuthorLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(text_Author, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        panel_EnvironmentDescription.setBorder(BorderFactory.createTitledBorder(null, resourceMap.getString("panel_EnvironmentDescription.border.title"), TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, resourceMap.getFont("panel_EnvironmentDescription.border.titleFont"))); // NOI18N
        panel_EnvironmentDescription.setName("panel_EnvironmentDescription"); // NOI18N
        panel_EnvironmentDescription.setPreferredSize(new Dimension(220, 311));

        scrollpanel_VehicleDescription.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpanel_VehicleDescription.setAutoscrolls(true);
        scrollpanel_VehicleDescription.setName("scrollpanel_VehicleDescription"); // NOI18N

        text_EnvironmentDescription.setColumns(20);
        text_EnvironmentDescription.setFont(resourceMap.getFont("text_EnvironmentDescription.font")); // NOI18N
        text_EnvironmentDescription.setLineWrap(true);
        text_EnvironmentDescription.setRows(5);
        text_EnvironmentDescription.setWrapStyleWord(true);
        text_EnvironmentDescription.setName("text_EnvironmentDescription"); // NOI18N
        scrollpanel_VehicleDescription.setViewportView(text_EnvironmentDescription);

        GroupLayout panel_EnvironmentDescriptionLayout = new GroupLayout(panel_EnvironmentDescription);
        panel_EnvironmentDescription.setLayout(panel_EnvironmentDescriptionLayout);
        panel_EnvironmentDescriptionLayout.setHorizontalGroup(
            panel_EnvironmentDescriptionLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(scrollpanel_VehicleDescription, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
        );
        panel_EnvironmentDescriptionLayout.setVerticalGroup(
            panel_EnvironmentDescriptionLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(scrollpanel_VehicleDescription, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
        );

        panel_LastModified.setBorder(BorderFactory.createTitledBorder(null, resourceMap.getString("panel_LastModified.border.title"), TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, resourceMap.getFont("panel_LastModified.border.titleFont"))); // NOI18N
        panel_LastModified.setName("panel_LastModified"); // NOI18N

        text_LastModified.setEditable(false);
        text_LastModified.setBorder(BorderFactory.createEmptyBorder(1, 4, 1, 1));
        text_LastModified.setName("text_LastModified"); // NOI18N

        GroupLayout panel_LastModifiedLayout = new GroupLayout(panel_LastModified);
        panel_LastModified.setLayout(panel_LastModifiedLayout);
        panel_LastModifiedLayout.setHorizontalGroup(
            panel_LastModifiedLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(text_LastModified, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
        );
        panel_LastModifiedLayout.setVerticalGroup(
            panel_LastModifiedLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(text_LastModified, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        GroupLayout tab_PropertiesLayout = new GroupLayout(tab_Properties);
        tab_Properties.setLayout(tab_PropertiesLayout);
        tab_PropertiesLayout.setHorizontalGroup(
            tab_PropertiesLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(tab_PropertiesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_PropertiesLayout.createParallelGroup(Alignment.TRAILING)
                    .addComponent(panel_EnvironmentDescription, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                    .addComponent(panel_EnvironmentName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                    .addComponent(panel_Author, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_LastModified, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addComponent(panel_Preview, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tab_PropertiesLayout.setVerticalGroup(
            tab_PropertiesLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, tab_PropertiesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_PropertiesLayout.createParallelGroup(Alignment.TRAILING)
                    .addComponent(panel_Preview, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tab_PropertiesLayout.createSequentialGroup()
                        .addComponent(panel_EnvironmentName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panel_Author, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panel_EnvironmentDescription, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(panel_LastModified, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tabContainer.addTab(resourceMap.getString("tab_Properties.TabConstraints.tabTitle"), tab_Properties); // NOI18N

        tab_Design.setName("tab_Design"); // NOI18N

        panel_Layout.setBorder(BorderFactory.createTitledBorder(null, resourceMap.getString("panel_Layout.border.title"), TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, resourceMap.getFont("panel_Layout.border.titleFont"))); // NOI18N
        panel_Layout.setName("panel_Layout"); // NOI18N

        jPanel6.setBackground(resourceMap.getColor("jPanel6.background")); // NOI18N
        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setPreferredSize(new Dimension(320, 240));

        GroupLayout jPanel6Layout = new GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 342, Short.MAX_VALUE)
        );

        GroupLayout panel_LayoutLayout = new GroupLayout(panel_Layout);
        panel_Layout.setLayout(panel_LayoutLayout);
        panel_LayoutLayout.setHorizontalGroup(
            panel_LayoutLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(jPanel6, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
        );
        panel_LayoutLayout.setVerticalGroup(
            panel_LayoutLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(jPanel6, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
        );

        panel_Tools.setBorder(BorderFactory.createTitledBorder(null, resourceMap.getString("panel_Tools.border.title"), TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, resourceMap.getFont("panel_Tools.border.titleFont"))); // NOI18N
        panel_Tools.setName("panel_Tools"); // NOI18N

        jPanel8.setBorder(BorderFactory.createTitledBorder(resourceMap.getString("jPanel8.border.title"))); // NOI18N
        jPanel8.setName("jPanel8"); // NOI18N

        jComboBox2.setModel(new DefaultComboBoxModel(new String[] { "320 x 240", "640 x 480", "800 x 600", "1024 x 768", "1280 x 960" }));
        jComboBox2.setName("jComboBox2"); // NOI18N

        GroupLayout jPanel8Layout = new GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jComboBox2, 0, 186, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        jPanel9.setBorder(BorderFactory.createTitledBorder(resourceMap.getString("jPanel9.border.title"))); // NOI18N
        jPanel9.setName("jPanel9"); // NOI18N

        jComboBox3.setModel(new DefaultComboBoxModel(new String[] { "Grass", "Sand", "Gravel", "Water", "Power Source" }));
        jComboBox3.setName("jComboBox3"); // NOI18N

        jPanel10.setBackground(resourceMap.getColor("jPanel10.background")); // NOI18N
        jPanel10.setName("jPanel10"); // NOI18N

        GroupLayout jPanel10Layout = new GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 186, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 126, Short.MAX_VALUE)
        );

        panel_Red.setBorder(BorderFactory.createTitledBorder(resourceMap.getString("panel_Red.border.title"))); // NOI18N
        panel_Red.setName("panel_Red"); // NOI18N

        slider_Radius.setMaximum(50);
        slider_Radius.setValue(10);
        slider_Radius.setName("slider_Radius"); // NOI18N
        slider_Radius.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                slider_Radius_StateChanged(evt);
            }
        });

        text_Radius.setEditable(false);
        text_Radius.setFont(resourceMap.getFont("text_Radius.font")); // NOI18N
        text_Radius.setText(resourceMap.getString("text_Radius.text")); // NOI18N
        text_Radius.setName("text_Radius"); // NOI18N

        GroupLayout panel_RedLayout = new GroupLayout(panel_Red);
        panel_Red.setLayout(panel_RedLayout);
        panel_RedLayout.setHorizontalGroup(
            panel_RedLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, panel_RedLayout.createSequentialGroup()
                .addComponent(slider_Radius, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(text_Radius, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        );
        panel_RedLayout.setVerticalGroup(
            panel_RedLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(text_Radius, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
            .addComponent(slider_Radius, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panel_Red1.setBorder(BorderFactory.createTitledBorder(resourceMap.getString("panel_Red1.border.title"))); // NOI18N
        panel_Red1.setName("panel_Red1"); // NOI18N

        slider_Intensity.setName("slider_Intensity"); // NOI18N
        slider_Intensity.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                slider_Intensity_StateChanged(evt);
            }
        });

        text_Intensity.setEditable(false);
        text_Intensity.setFont(resourceMap.getFont("text_Intensity.font")); // NOI18N
        text_Intensity.setText(resourceMap.getString("text_Intensity.text")); // NOI18N
        text_Intensity.setName("text_Intensity"); // NOI18N

        GroupLayout panel_Red1Layout = new GroupLayout(panel_Red1);
        panel_Red1.setLayout(panel_Red1Layout);
        panel_Red1Layout.setHorizontalGroup(
            panel_Red1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, panel_Red1Layout.createSequentialGroup()
                .addComponent(slider_Intensity, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(text_Intensity, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        );
        panel_Red1Layout.setVerticalGroup(
            panel_Red1Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(text_Intensity, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
            .addComponent(slider_Intensity, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jToggleButton1.setText(resourceMap.getString("jToggleButton1.text")); // NOI18N
        jToggleButton1.setName("jToggleButton1"); // NOI18N

        GroupLayout jPanel9Layout = new GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jToggleButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jComboBox3, 0, 75, Short.MAX_VALUE))
            .addComponent(jPanel10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_Red, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_Red1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(jToggleButton1)
                    .addComponent(jComboBox3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jPanel10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(panel_Red, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(panel_Red1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        GroupLayout panel_ToolsLayout = new GroupLayout(panel_Tools);
        panel_Tools.setLayout(panel_ToolsLayout);
        panel_ToolsLayout.setHorizontalGroup(
            panel_ToolsLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(jPanel8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_ToolsLayout.setVerticalGroup(
            panel_ToolsLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(panel_ToolsLayout.createSequentialGroup()
                .addComponent(jPanel8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jPanel9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout tab_DesignLayout = new GroupLayout(tab_Design);
        tab_Design.setLayout(tab_DesignLayout);
        tab_DesignLayout.setHorizontalGroup(
            tab_DesignLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, tab_DesignLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_Layout, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(panel_Tools, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        tab_DesignLayout.setVerticalGroup(
            tab_DesignLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, tab_DesignLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_DesignLayout.createParallelGroup(Alignment.TRAILING)
                    .addComponent(panel_Layout, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_Tools, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabContainer.addTab(resourceMap.getString("tab_Design.TabConstraints.tabTitle"), tab_Design); // NOI18N

        button_Save.setText(resourceMap.getString("button_Save.text")); // NOI18N
        button_Save.setName("button_Save"); // NOI18N

        button_SaveAs.setText(resourceMap.getString("button_SaveAs.text")); // NOI18N
        button_SaveAs.setName("button_SaveAs"); // NOI18N

        ActionMap actionMap = Application.getInstance(VehiclesApp.class).getContext().getActionMap(EnvironmentEditor.class, this);
        button_Cancel.setAction(actionMap.get("cancel")); // NOI18N
        button_Cancel.setText(resourceMap.getString("button_Cancel.text")); // NOI18N
        button_Cancel.setName("button_Cancel"); // NOI18N

        text_Status.setEditable(false);
        text_Status.setText(resourceMap.getString("text_Status.text")); // NOI18N
        text_Status.setName("text_Status"); // NOI18N

        panel_SelectedEnvironment.setBorder(BorderFactory.createTitledBorder(null, resourceMap.getString("panel_SelectedEnvironment.border.title"), TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, resourceMap.getFont("panel_SelectedEnvironment.border.titleFont"))); // NOI18N
        panel_SelectedEnvironment.setName("panel_SelectedEnvironment"); // NOI18N

        dropdown_SelectedEnvironment.setMaximumRowCount(4);
        dropdown_SelectedEnvironment.setModel(environmentDropDown);
        dropdown_SelectedEnvironment.setName("dropdown_SelectedEnvironment"); // NOI18N
        dropdown_SelectedEnvironment.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                dropdown_SelectedEnvironmentItemStateChanged(evt);
            }
        });

        GroupLayout panel_SelectedEnvironmentLayout = new GroupLayout(panel_SelectedEnvironment);
        panel_SelectedEnvironment.setLayout(panel_SelectedEnvironmentLayout);
        panel_SelectedEnvironmentLayout.setHorizontalGroup(
            panel_SelectedEnvironmentLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(dropdown_SelectedEnvironment, 0, 588, Short.MAX_VALUE)
        );
        panel_SelectedEnvironmentLayout.setVerticalGroup(
            panel_SelectedEnvironmentLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(dropdown_SelectedEnvironment, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(text_Status, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(button_Save)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(button_SaveAs)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(button_Cancel))
                    .addComponent(tabContainer, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addComponent(panel_SelectedEnvironment, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_SelectedEnvironment, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(tabContainer, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(button_Cancel)
                    .addComponent(button_SaveAs)
                    .addComponent(button_Save)
                    .addComponent(text_Status, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dropdown_SelectedEnvironmentItemStateChanged(ItemEvent evt) {//GEN-FIRST:event_dropdown_SelectedEnvironmentItemStateChanged
        JComboBox tempComboBox = (JComboBox) evt.getSource();
        Environment selected = (Environment) tempComboBox.getSelectedItem();
        populateFields(selected);
}//GEN-LAST:event_dropdown_SelectedEnvironmentItemStateChanged

    private void formWindowGainedFocus(WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        dropdown_SelectedEnvironment.requestFocus();
    }//GEN-LAST:event_formWindowGainedFocus

    private void slider_Radius_StateChanged(ChangeEvent evt) {//GEN-FIRST:event_slider_Radius_StateChanged
        JSlider tempSlider = (JSlider) evt.getSource();
        int value = tempSlider.getValue();
        text_Radius.setText(Integer.toString(value));
}//GEN-LAST:event_slider_Radius_StateChanged

    private void slider_Intensity_StateChanged(ChangeEvent evt) {//GEN-FIRST:event_slider_Intensity_StateChanged
        JSlider tempSlider = (JSlider) evt.getSource();
        int value = tempSlider.getValue();
        text_Intensity.setText(Integer.toString(value));
}//GEN-LAST:event_slider_Intensity_StateChanged

    private void populateFields(Environment p_environment) {
        Environment tempEnvironment = p_environment;

        text_Author.setText(tempEnvironment.getAuthor());
        text_EnvironmentDescription.setText(tempEnvironment.getDescription());
        text_EnvironmentName.setText(tempEnvironment.getName());
        text_LastModified.setText(tempEnvironment.getLastModified());

}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton button_Cancel;
    private JButton button_Save;
    private JButton button_SaveAs;
    private JComboBox dropdown_SelectedEnvironment;
    private JComboBox jComboBox2;
    private JComboBox jComboBox3;
    private JPanel jPanel10;
    private JPanel jPanel3;
    private JPanel jPanel6;
    private JPanel jPanel8;
    private JPanel jPanel9;
    private JToggleButton jToggleButton1;
    private JPanel panel_Author;
    private JPanel panel_EnvironmentDescription;
    private JPanel panel_EnvironmentName;
    private JPanel panel_LastModified;
    private JPanel panel_Layout;
    private JPanel panel_Preview;
    private JPanel panel_Red;
    private JPanel panel_Red1;
    private JPanel panel_SelectedEnvironment;
    private JPanel panel_Tools;
    private JScrollPane scrollpanel_VehicleDescription;
    private JSlider slider_Intensity;
    private JSlider slider_Radius;
    private JTabbedPane tabContainer;
    private JPanel tab_Design;
    private JPanel tab_Properties;
    private JTextField text_Author;
    private JTextArea text_EnvironmentDescription;
    private JTextField text_EnvironmentName;
    private JTextField text_Intensity;
    private JTextField text_LastModified;
    private JTextField text_Radius;
    private JTextField text_Status;
    // End of variables declaration//GEN-END:variables
    private PApplet embed;
    private Environment[] environmentArray;
    private DefaultComboBoxModel environmentDropDown;
}
