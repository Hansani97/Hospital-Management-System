/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final_hospital_management_system;

import java.awt.HeadlessException;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC PAL
 */
public final class System extends javax.swing.JFrame {

    static void exit(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Creates new form System
     */
    public System() {
               
        initComponents();
        MysqlConnection();
        fillTable();
    }
    public Connection MysqlConnection(){
        Connection conn=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/patient", "root","sql123456");
            //JOptionPane.showMessageDialog(null,"Mysql DB connection successfully...");
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Mysql connection Failed...");
            return null;
        }   
    }
    public ArrayList<patient> retrieveData(){
         ArrayList<patient> al= new ArrayList<>();
         try {
             Connection conn = MysqlConnection();
             String qry="select * from addform";
             Statement st=conn.createStatement();
             ResultSet rs=st.executeQuery(qry);
             patient contact;
             while(rs.next()){
                 contact =new patient(rs.getInt("patientid"),rs.getString("patientname"),rs.getString("address"),rs.getString("gender"),rs.getString("guardian"),rs.getInt("telephone"),rs.getString("ward"),rs.getString("admitdate"));
                 al.add(contact);
             }
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(rootPane, e);
         }
         return al;
     }
     public void fillTable(){
         ArrayList<patient> al=retrieveData();
         DefaultTableModel model=(DefaultTableModel) jTable_Display_patient.getModel();
         model.setRowCount(0);
         Object[] row=new Object[8];
         for(int i =0;i< al.size();i++){
             row[0] =al.get(i).getPatientId();
             row[1] =al.get(i).getName();
             row[2] =al.get(i).getAddress();
             row[3] =al.get(i).getGender();
             row[4] =al.get(i).getGuardian();
             row[5] =al.get(i).getTelephone();
             row[6] =al.get(i).getWard();
             row[7] =al.get(i).getAdmiteddate();
             model.addRow(row);
         }
     }
    public void showItemToFields(int index){
   
             jTextField9.setText(Integer.toString(retrieveData().get(index).getPatientId()));
             jTextField10.setText(retrieveData().get(index).getName());
             jTextField11.setText(retrieveData().get(index).getAddress());
             jTextField2.setText(retrieveData().get(index).getGender());
             jTextField12.setText(retrieveData().get(index).getGuardian());
             jTextField13.setText(Integer.toString(retrieveData().get(index).getTelephone()));
             jTextField14.setText(retrieveData().get(index).getWard());
             try {
                 java.util.Date admit=null;
                 admit=new SimpleDateFormat("dd-MM-yyyy").parse((String)retrieveData().get(index).getAdmiteddate());
                 jDateChooser1.setDate(admit);
             } catch (ParseException e) {
                 JOptionPane.showMessageDialog(rootPane, e);
             }
       
     }

     public void distablefill(){
            try {
                Connection conn=MysqlConnection();   //preparedstatement is excute the parameterized query
                PreparedStatement ps=conn.prepareStatement("insert into disform"
                + "(patientid,patientname,address,gender,guardian,telephone,ward,admitdate) values" + "(?,?,?,?,?,?,?,?)");
                
        
                        
                ps.setInt(1,Integer.parseInt(jTextField9.getText()));
                ps.setString(2,jTextField10.getText());
                ps.setString(3,jTextField11.getText());
                ps.setString(4,jTextField2.getText());
                ps.setString(5,jTextField12.getText());
                 ps.setInt(6,Integer.parseInt(jTextField13.getText()));
                ps.setString(7,jTextField14.getText());
                
                SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                String adddate = sdf.format(jDateChooser1.getDate());
                ps.setString(8,adddate);
                //fillTable();
                /*SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy");
                String disdate = sdf2.format(jDateChooser2.getDate());
                ps.setString(9,disdate);*/
                
                int res=ps.executeUpdate();   //executeupdate is integer values of rows
                if(res>=1){
                    JOptionPane.showConfirmDialog(rootPane, "Are You Sure ??");
                }else{
                    JOptionPane.showMessageDialog(null,"Contact Added Failed...");
                } 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
            }
     }   
    public void deleteadd(){
        try {
                String qry = "delete from addform where patientid = ? ";
                Connection conn = MysqlConnection();
                PreparedStatement ps=conn.prepareStatement(qry);
                ps.setInt(1,Integer.parseInt(jTextField9.getText()));
                //ps.setString(1,txt_name.getText().toString());
                int res= ps.executeUpdate();
                fillTable();
                if(res>=1){
                    JOptionPane.showMessageDialog(null, "Succefully Discharge Patient");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Contact Delete Failed..");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            jTextField9.setText("");
            jTextField2.setText("");
            jTextField10.setText("");
            jTextField11.setText("");
            jTextField12.setText("");
            jTextField13.setText("");
            jTextField14.setText("");
            jDateChooser1.setDate(null);
    }
    
        /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Display_patient = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton5_Home = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        jLabel1.setText("Hospital Management System");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("Patients Infomation");
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("PatientId:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Patient Name:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Address:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Gender:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Guardian:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Telephone:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("Ward:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("Admited date:");

        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setText("Search By Name:");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jTable_Display_patient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PatientId", "Patient name", "Address", "Gender", "Guardian", "Telephone", "Ward", "Admit date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_Display_patient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_patientMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Display_patient);

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Insert.png"))); // NOI18N
        jButton2.setText("Save");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Update.png"))); // NOI18N
        jButton3.setText("Update");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Delete.png"))); // NOI18N
        jButton4.setText("Discharge");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jDateChooser1.setDateFormatString("dd-MM-yyyy");

        jButton5_Home.setBackground(new java.awt.Color(51, 51, 51));
        jButton5_Home.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton5_Home.setText("Home");
        jButton5_Home.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5_Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5_HomeActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1.setText("New");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setText("Discharge List");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(346, 346, 346)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5_Home, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(32, 32, 32)
                                .addComponent(jButton4)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(74, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton5_Home, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel6))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(413, 413, 413)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)
                            .addComponent(jButton4)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(148, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed
//ADD FUNCTION WORK
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      
        if(jTextField9.getText()!=null && jTextField10.getText()!=null && jTextField11.getText()!=null && jTextField2.getText() != null &&
                jTextField12.getText()!=null && jTextField13.getText()!=null && jTextField14.getText()!=null &&
                jDateChooser1!=null ){
            try {
                Connection conn=MysqlConnection();   //preparedstatement is excute the parameterized query
                PreparedStatement ps=conn.prepareStatement("insert into addform"
                + "(patientid,patientname,address,gender,guardian,telephone,ward,admitdate) values" + "(?,?,?,?,?,?,?,?)");
                
        
                        
                ps.setInt(1,Integer.parseInt(jTextField9.getText()));
                ps.setString(2,jTextField10.getText());
                ps.setString(3,jTextField11.getText());
                ps.setString(4,jTextField2.getText());
                ps.setString(5,jTextField12.getText());
                 ps.setInt(6,Integer.parseInt(jTextField13.getText()));
                ps.setString(7,jTextField14.getText());
                
                SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                String adddate = sdf.format(jDateChooser1.getDate());
                ps.setString(8,adddate);
                /*SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy");
                String disdate = sdf2.format(jDateChooser2.getDate());
                ps.setString(9,disdate);*/
                
                int res=ps.executeUpdate();   //executeupdate is integer values of rows
                if(res>=1){
                    JOptionPane.showMessageDialog(null,res+" Patient Record Added Successfully...");
                    fillTable();
                }else{
                    JOptionPane.showMessageDialog(null,"Contact Added Failed...");
                } 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
            }
            jTextField9.setText("");
            jTextField2.setText("");
            jTextField10.setText("");
            jTextField11.setText("");
            jTextField12.setText("");
            jTextField13.setText("");
            jTextField14.setText("");
            jDateChooser1.setDate(null);
            //jDateChooser2.setDate(null);    
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    //UPDATE FUNCTION WORK
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        /*if(jTextField9!=null && jTextField10!=null && jTextField11!=null &&
                jTextField12!=null && jTextField13!=null && jTextField14!=null &&
                jDateChooser1!=null){*/
            String value1=jTextField9.getText();
            String value2=jTextField10.getText();
            String value3=jTextField11.getText();
            String value4=jTextField2.getText();
            String value5=jTextField12.getText();
            String value6=jTextField13.getText();
            String value7=jTextField14.getText();
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
            String value8 = sdf.format(jDateChooser1.getDate());
            String qry=null;
            PreparedStatement ps=null;
            Connection conn=MysqlConnection();
            try {
                qry ="update addform set patientid='"+value1+"',patientname='"+value2+"',address='"+value3+"',gender='"+value4+"',guardian='"+value5+"',telephone='"+value6+"',ward='"+value7+"',admitdate='"+value8+"' where patientname='"+value2+"' ";  
                ps=conn.prepareStatement(qry);
                /*ps.setInt(1,Integer.parseInt(jTextField9.getText()));
                ps.setString(2,jTextField10.getText());
                ps.setString(3,jTextField11.getText());
                ps.setString(4,jTextField2.getText());
                ps.setString(5,jTextField12.getText());
                ps.setInt(6,Integer.parseInt(jTextField13.getText()));
                ps.setString(7,jTextField14.getText());
                
                SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                String adddate = sdf.format(jDateChooser1.getDate());
                ps.setString(8,adddate);*/
               /* SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy");
                String disdate = sdf2.format(jDateChooser2.getDate());
                ps.setString(9,disdate);*/
                ps.executeUpdate();   //executeupdate is integer values of rows
                 JOptionPane.showMessageDialog(null,"Data Updated...");
                fillTable();           
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
            }
            jTextField9.setText("");
            jTextField2.setText("");
            jTextField10.setText("");
            jTextField11.setText("");
            jTextField12.setText("");
            jTextField13.setText("");
            jTextField14.setText("");
            jDateChooser1.setDate(null);
            //jDateChooser2.setDate(null);       
             
    }//GEN-LAST:event_jButton3ActionPerformed
    //DELETE FUNCTION
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        distablefill();
        deleteadd();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5_HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5_HomeActionPerformed
        Homepage hm=new Homepage();
        hm.setVisible(true);
        dispose(); 
    }//GEN-LAST:event_jButton5_HomeActionPerformed
//show table data in fields
    private void jTable_Display_patientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_Display_patientMouseClicked
        int show=jTable_Display_patient.getSelectedRow();
        showItemToFields(show);
    }//GEN-LAST:event_jTable_Display_patientMouseClicked
//New Function
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            jTextField9.setText("");
            jTextField2.setText("");
            jTextField10.setText("");
            jTextField11.setText("");
            jTextField12.setText("");
            jTextField13.setText("");
            jTextField14.setText("");
            jDateChooser1.setDate(null);
            //jDateChooser2.setDate(null);
    }//GEN-LAST:event_jButton1ActionPerformed
//search function
    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
         ArrayList<patient> al= new ArrayList<>();
         String val=jTextField1.getText().toString();
         try {
            Connection conn=MysqlConnection();
            String qry="select * from addform where patientname like '%"+val+"%' ";
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(qry);
            patient contacts;
            while(rs.next()){
                 contacts =new patient(rs.getInt("patientid"),rs.getString("patientname"),rs.getString("address"),rs.getString("gender"),rs.getString("guardian"),rs.getInt("telephone"),rs.getString("ward"),rs.getString("admitdate"));
                 al.add(contacts);
             }
            DefaultTableModel model=(DefaultTableModel) jTable_Display_patient.getModel();
            model.setRowCount(0);
            Object[] row=new Object[8];
           for(int i =0;i< al.size();i++){
             row[0] =al.get(i).getPatientId();
             row[1] =al.get(i).getName();
             row[2] =al.get(i).getAddress();
             row[3] =al.get(i).getGender();
             row[4] =al.get(i).getGuardian();
             row[5] =al.get(i).getTelephone();
             row[6] =al.get(i).getWard();
             row[7] =al.get(i).getAdmiteddate();
             //row[8] =al.get(i).getDischargedate();
             model.addRow(row);
         }
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        Discharge1 dis = new Discharge1();
        dis.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton5MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(System.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(System.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(System.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(System.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new System().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton5_Home;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Display_patient;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

}
