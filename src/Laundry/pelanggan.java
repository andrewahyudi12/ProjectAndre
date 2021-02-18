package Laundry;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksiDB;
import net.proteanit.sql.DbUtils;

public class pelanggan extends javax.swing.JFrame {
    Connection connect;
    PreparedStatement pst;
    ResultSet rs;
    Statement st;
    int harga_pewangi = 0;
     
     
 
    public pelanggan() {
        initComponents();
       
        
       connect=koneksiDB.getkoneksi();
        get_antrian();
        tampildata();
        TANGGAL();
        Date date = new Date();
       
        tgl.setText(date.toString());
        
        
//        auto();
        /*Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(
                (screenSize.width - frameSize.width) / 2,
               (screenSize.height - frameSize.height) / 2);*/
    }
    
    public String hapus(String h){
         try {
            String sql="delete from tbl_pesan where no_antrian=?";
            pst=connect.prepareStatement(sql);
            pst.setString(1, h);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil");
        } 
         catch (SQLException ex) { 
            Logger.getLogger(pelanggan.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "pesan salah : " + ex);
        }
        return null;
    }
    //Membuat Method untuk menentukan secara otomatis no antrian  
    public void get_antrian() {
        try {
          String sql = "SELECT MAX(no_antrian) FROM tbl_pesan";
            pst=connect.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                if(rs.first() == false){
                    no_transaksi.setText("1");
                }else{
                    rs.last();
                    int auto_antrian = rs.getInt(1)+1;
                    String no = String.valueOf(auto_antrian);
                    int nomor = no.length();
                    for (int j =0; j < 3 ; j++){
                        no = no;
                    }
                    no_transaksi.setText(no);
                }
            }
            rs.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(pelanggan.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    //Membuat method untuk menentukan tanggal dan waktu
    public void TANGGAL(){
        Thread clok=new Thread(){
            public void run(){
                for(;;){
                    Calendar cal=Calendar.getInstance();
                    SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
                    SimpleDateFormat format2=new SimpleDateFormat("yyyy-MM-dd");
                    waktu.setText(format.format(cal.getTime()));
                    tgl.setText(format2.format(cal.getTime()));       
                try{ 
                    sleep(1000);
                }   catch (InterruptedException ex) {
                        Logger.getLogger(pelanggan.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            }
        };
          clok.start();      
    }
    //Membuat Method simpan
    private void simpan(){
        String tg=tgl.getText();
        try{
            String sql="insert into tbl_pesan (no_antrian, nama, no_telp, alamat, jpewangi, jumlah, harga, tanggal) value (?,?,?,?,?,?,?,?)";
            pst=connect.prepareStatement(sql);
            pst.setString(1,no_transaksi.getText());
            pst.setString(2,nama.getText());
            pst.setString(3,no_telp.getText());
            pst.setString(4,alamat.getText());
            pst.setString(5, (String) j_pewangi.getSelectedItem());
            pst.setString(6,jumlah.getText());
            pst.setString(7,tottal.getText());
            pst.setString(8,tg);
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
        } catch (SQLException ex) {
            Logger.getLogger(pelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
        tampildata();
    }
    
    //Membuat Method menampilkan data dari tabel pesan
    public void tampildata(){
     try{
         String sql = "SELECT * FROM tbl_pesan";
         pst=connect.prepareStatement(sql);
         rs=pst.executeQuery();
         tbl_beli.setModel(DbUtils.resultSetToTableModel(rs));
         
     }  catch (SQLException ex) {
            Logger.getLogger(pelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
  //membuat method reset  
  private void reset() {
        //no_transaksi.setText("");
        tgl.setText("");
        nama.setText("");
        jumlah.setText("");
        j_pewangi.setSelectedIndex(0);
        nama.setText("");
        no_telp.setText("");
        alamat.setText("");
        tottal.setText("");
       // no_transaksi.requestFocus();
        simpan.setEnabled(true);
        hapus.setEnabled(false);
  }
  //method filter Angka
  public void FilterAngka(KeyEvent b) {
        if (Character.isAlphabetic(b.getKeyChar())) {
            b.consume();
            JOptionPane.showMessageDialog(null, "Masukkan angka saja!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }
    //method Filter Huruf
    public void FilterHuruf(KeyEvent a) {
        if (Character.isDigit(a.getKeyChar())) {
            a.consume();
            JOptionPane.showMessageDialog(null, "Masukkan huruf saja!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
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
        waktu = new javax.swing.JLabel();
        tgl = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        nama = new javax.swing.JTextField();
        alamat = new javax.swing.JTextField();
        jumlah = new javax.swing.JTextField();
        no_telp = new javax.swing.JTextField();
        no_transaksi = new javax.swing.JTextField();
        j_pewangi = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_beli = new javax.swing.JTable();
        hitung = new javax.swing.JButton();
        tottal = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        simpan = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        selesai = new javax.swing.JButton();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(774, 508));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("L(A)UNDRY");

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("PESAN DI SINI");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Shop_48px.png"))); // NOI18N

        waktu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        waktu.setForeground(new java.awt.Color(255, 255, 255));
        waktu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        tgl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tgl.setForeground(new java.awt.Color(255, 255, 255));
        tgl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tglKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(tgl, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(waktu, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tgl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18))
                    .addComponent(jLabel2))
                .addGap(0, 11, Short.MAX_VALUE))
            .addComponent(waktu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, -1));

        jLabel5.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Jenis Pewangi");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel6.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Jumlah Pakaian");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel7.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nama");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, -1, -1));

        jLabel8.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("No.Telpon");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, -1, -1));

        jLabel9.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Harga");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 220, -1, -1));

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("/KG");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, -1, -1));

        nama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                namaKeyTyped(evt);
            }
        });
        getContentPane().add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 150, -1));
        getContentPane().add(alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, 150, -1));
        getContentPane().add(jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 110, -1));

        no_telp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no_telpActionPerformed(evt);
            }
        });
        getContentPane().add(no_telp, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 150, -1));

        no_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no_transaksiActionPerformed(evt);
            }
        });
        no_transaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                no_transaksiKeyTyped(evt);
            }
        });
        getContentPane().add(no_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 110, -1));

        j_pewangi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Cair", "Bubuk", "" }));
        getContentPane().add(j_pewangi, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 60, -1));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("No.Antrian");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        tbl_beli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Nama", "No.HP", "Alamat", "Jenis Pewangi", "Jumlah", "Harga", "Tanggal"
            }
        ));
        tbl_beli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_beliMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_beli);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 470, 110));

        hitung.setBackground(new java.awt.Color(51, 255, 255));
        hitung.setFont(new java.awt.Font("Berlin Sans FB", 1, 11)); // NOI18N
        hitung.setForeground(new java.awt.Color(255, 255, 255));
        hitung.setText("HITUNG");
        hitung.setPreferredSize(new java.awt.Dimension(73, 23));
        hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitungActionPerformed(evt);
            }
        });
        getContentPane().add(hitung, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 240, 80, 30));

        tottal.setPreferredSize(new java.awt.Dimension(73, 23));
        getContentPane().add(tottal, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 240, 100, 30));

        jLabel12.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Alamat");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, -1, -1));

        simpan.setBackground(new java.awt.Color(51, 255, 204));
        simpan.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 11)); // NOI18N
        simpan.setForeground(new java.awt.Color(255, 255, 255));
        simpan.setText("SIMPAN");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });
        getContentPane().add(simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, 80, 30));

        hapus.setBackground(new java.awt.Color(51, 255, 204));
        hapus.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 11)); // NOI18N
        hapus.setForeground(new java.awt.Color(255, 255, 255));
        hapus.setText("HAPUS");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        getContentPane().add(hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 390, 80, 30));

        reset.setBackground(new java.awt.Color(51, 255, 204));
        reset.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        reset.setForeground(new java.awt.Color(255, 255, 255));
        reset.setText("RESET");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        getContentPane().add(reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 440, 80, 30));

        selesai.setBackground(new java.awt.Color(51, 255, 204));
        selesai.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 11)); // NOI18N
        selesai.setForeground(new java.awt.Color(255, 255, 255));
        selesai.setText("KEMBALI");
        selesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selesaiActionPerformed(evt);
            }
        });
        getContentPane().add(selesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 440, 80, 30));

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        bg.setForeground(new java.awt.Color(255, 255, 255));
        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/20210130_103220.png"))); // NOI18N
        bg.setText("jLabel4");
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 510));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void no_telpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_no_telpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_no_telpActionPerformed

    private void hitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitungActionPerformed

        if (j_pewangi.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Isi data!");
        } else if (j_pewangi.getSelectedIndex() == 1) {
            harga_pewangi = 5000;
        } else if (j_pewangi.getSelectedIndex() == 2 ){
            harga_pewangi = 3000;
        }
        int totall = 0;
        totall = Integer.parseInt(String.valueOf(jumlah.getText()));
        int total = (int) ((totall * 10000) + harga_pewangi);
        tottal.setText(String.valueOf(total));

    }//GEN-LAST:event_hitungActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
       simpan();
       get_antrian();
       

    }//GEN-LAST:event_simpanActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
        String h=no_transaksi.getText();
       hapus(h);
        
            tampildata();
        
    }//GEN-LAST:event_hapusActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_resetActionPerformed

    private void selesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selesaiActionPerformed

        new home().setVisible(true);
        dispose();
    }//GEN-LAST:event_selesaiActionPerformed

    private void tglKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglKeyReleased
      
    }//GEN-LAST:event_tglKeyReleased

    private void tbl_beliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_beliMouseClicked
       
        simpan.setEnabled(false);
        hapus.setEnabled(true);
       try{
           int row=tbl_beli.getSelectedRow();
           String tabel_klik=(tbl_beli.getModel().getValueAt(row,0).toString());
           String sql="select * from tbl_pesan where no_antrian='"+tabel_klik+"'";
           pst=connect.prepareStatement(sql);
           rs=pst.executeQuery();
           if(rs.next()){
               String data1=rs.getString(("no_antrian"));
               no_transaksi.setText(data1);
               String data2=rs.getString(("nama"));
               nama.setText(data2);
               String data3=rs.getString(("no_telp"));
               no_telp.setText(data3);
               String data4=rs.getString(("alamat"));
               alamat.setText(data4);
               String data5=rs.getString(("jpewangi"));
               j_pewangi.setSelectedItem(data5);
               String data6=rs.getString(("jumlah"));
               jumlah.setText(data6);
               String data7=rs.getString(("harga"));
               tottal.setText(data7);
               String data8=rs.getString(("tanggal"));
               tgl.setText(data8);
              
               
               
               
           }
       } catch (SQLException ex) {
            Logger.getLogger(pelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbl_beliMouseClicked

    private void no_transaksiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_no_transaksiKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_no_transaksiKeyTyped

    private void namaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namaKeyTyped
        FilterHuruf(evt);
    }//GEN-LAST:event_namaKeyTyped

    private void no_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_no_transaksiActionPerformed
      get_antrian();
    }//GEN-LAST:event_no_transaksiActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
      
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        tampildata();
        no_transaksi.setEnabled(false);
    }//GEN-LAST:event_formWindowOpened

   
                                
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
            java.util.logging.Logger.getLogger(pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pelanggan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alamat;
    private javax.swing.JLabel bg;
    private javax.swing.JButton hapus;
    private javax.swing.JButton hitung;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> j_pewangi;
    private javax.swing.JTextField jumlah;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField no_telp;
    private javax.swing.JTextField no_transaksi;
    private javax.swing.JButton reset;
    private javax.swing.JButton selesai;
    private javax.swing.JButton simpan;
    private javax.swing.JTable tbl_beli;
    private javax.swing.JLabel tgl;
    private javax.swing.JTextField tottal;
    private javax.swing.JLabel waktu;
    // End of variables declaration//GEN-END:variables
private void jenis_pakaian() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
private void autokd_transaksi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

private void autokd_pelanggan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

private static class totall {

private static void setText(String string) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public totall() {
        }
    }
}

