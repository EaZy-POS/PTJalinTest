/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.lutfi.ptjalintestapplication.apitester;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.json.JSONObject;
import org.testcontainers.shaded.okhttp3.MediaType;
import org.testcontainers.shaded.okhttp3.OkHttpClient;
import org.testcontainers.shaded.okhttp3.Request;
import org.testcontainers.shaded.okhttp3.RequestBody;
import org.testcontainers.shaded.okhttp3.Response;

/**
 *
 * @author Lutfi
 */
public class Tester extends javax.swing.JFrame {

    private static String inquiry_request_holder;
    private static String inquiry_response_holder;
    private static String payment_request_holder;
    private static String payment_response_holder;
    private static ConfigUrl config;
    private static int request_state = 0;
    
    /**
     * Creates new form Tester
     */
    public Tester() {
        initComponents();
        txt_response.setEditable(false);
        txt_response.setLineWrap(true);
        txt_request.setWrapStyleWord(true);
        txt_request.setLineWrap(true);
    }

    private void initStart() {
        txt_request.setText("");
        txt_response.setText("");
        setTransactionType();
        setLocationRelativeTo(this);
        holdRequest("");
        holdResponse("");
        cekConfig();
    }
    
    private void cekConfig(){
        if(config == null){
            config_dialog.setLocationRelativeTo(this);
            config_dialog.setVisible(true);
        }
    }

    private void setTransactionType() {
        String[] trxType = new String[]{"-- Select --", "Inquiry", "Payment"};
        cmb_trx_type.removeAllItems();

        for (String item : trxType) {
            cmb_trx_type.addItem(item);
        }

        cmb_trx_type.setSelectedIndex(0);
    }

    private void performButonKirim() {
        cekConfig();
        if (cmb_trx_type.getSelectedIndex() == 0) {
            showMessage("Mohon pilih transaksi!", JOptionPane.WARNING_MESSAGE);
        } else if (txt_request.getText().equals("")) {
            showMessage("Mohon isi request!", JOptionPane.WARNING_MESSAGE);
        } else {
            sendRequest(txt_request.getText());
        }
    }

    private void showMessage(String message, int type) {
        String tTitle;
        switch (type) {
            case JOptionPane.INFORMATION_MESSAGE:
                tTitle = "Informasi";
                break;
            case JOptionPane.ERROR_MESSAGE:
                tTitle = "Error";
                break;
            case JOptionPane.WARNING_MESSAGE:
                tTitle = "Peringatan";
                break;
            default:
                tTitle = "Informasi";
                break;
        }

        JOptionPane.showMessageDialog(rootPane, message, tTitle, type);
    }
    
    private void holdRequest(String msg){
        switch(request_state){
            case 1:
                inquiry_request_holder = msg;
                break;
            case 2:
                payment_request_holder = msg;
                break;
            default:
                inquiry_request_holder = msg;
                payment_request_holder = msg;
                break;
        }
    }
    
    private void holdResponse(String msg){
        switch(request_state){
            case 1:
                inquiry_response_holder = msg;
                break;
            case 2:
                payment_response_holder = msg;
                break;
            default:
                inquiry_response_holder = msg;
                payment_response_holder = msg;
                break;
        }
    }

    private void sendRequest(String pRequest) {
        
        holdRequest(pRequest);
        String tResponse = sendHttpPOSTRequest(pRequest);
        
        if(tResponse != null && !tResponse.equals("")){
            JSONObject tJsonResp = new JSONObject(tResponse);
            holdResponse(tJsonResp.toString(1));
            txt_response.setText(tJsonResp.toString(1));
        }else{
            txt_response.append("--> Error Response from server ...................... \n");
        }
    }

    private String sendHttpPOSTRequest(String pParam) {
        OkHttpClient tCon;
        String tResponse = "";
        String tBaseUrl = config.getUrl(request_state);

        int timeout = 30000;
        try {
            tCon = new OkHttpClient().newBuilder().connectTimeout(timeout, TimeUnit.MILLISECONDS).build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, pParam);
            Request request = new Request.Builder()
                    .url(tBaseUrl)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();

            Response response = tCon.newCall(request).execute();
            tResponse = response.body().string();
        } catch (IOException e) {
            showMessage("ERROR on Sending Request To Biller: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
        return tResponse;
    }
    
    private void loadMessageHolder(){
        switch(request_state){
            case 1:
                txt_request.setText(inquiry_request_holder);
                break;
            case 2:
                payment_request_holder = inquiry_response_holder;
                txt_request.setText(payment_request_holder);
                break;
            default:
                break;
        }
    }
    
    private void saveConfig(){
        if(txt_baseurl.getText().equals("") || !txt_baseurl.getText().startsWith("http")){
            showMessage("Mohon isi base url dengan benar", JOptionPane.WARNING_MESSAGE);
        }else if(txt_inquiry_path.getText().equals("")){
            showMessage("Mohon path inquiry dengan benar", JOptionPane.WARNING_MESSAGE);
        }else if(txt_payment_path.getText().equals("")){
            showMessage("Mohon path payment dengan benar", JOptionPane.WARNING_MESSAGE);
        }else{
            config = new ConfigUrl();
            config.setBaseUrl(txt_baseurl.getText());
            config.setInquiryPath(txt_inquiry_path.getText());
            config.setPaymentPath(txt_payment_path.getText());
            config_dialog.dispose();
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

        config_dialog = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        txt_baseurl = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_inquiry_path = new javax.swing.JTextField();
        txt_payment_path = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        btn_simpan = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_request = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        cmb_trx_type = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btn_kirim = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_response = new javax.swing.JTextArea();

        config_dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        config_dialog.setTitle("Config");
        config_dialog.setBounds(new java.awt.Rectangle(0, 0, 450, 200));
        config_dialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        config_dialog.setResizable(false);
        config_dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                config_dialogWindowClosed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Config URL"));

        jLabel3.setText("Base URL");

        jLabel4.setText("Inquiry Path");

        jLabel5.setText("Payment Path");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_baseurl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_inquiry_path, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_payment_path, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_baseurl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_inquiry_path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_payment_path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_simpan)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_simpan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout config_dialogLayout = new javax.swing.GroupLayout(config_dialog.getContentPane());
        config_dialog.getContentPane().setLayout(config_dialogLayout);
        config_dialogLayout.setHorizontalGroup(
            config_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        config_dialogLayout.setVerticalGroup(
            config_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(config_dialogLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Request"));

        txt_request.setColumns(20);
        txt_request.setRows(5);
        txt_request.setPreferredSize(new java.awt.Dimension(250, 100));
        jScrollPane1.setViewportView(txt_request);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Transaction Type"));

        cmb_trx_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmb_trx_type.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_trx_typeItemStateChanged(evt);
            }
        });
        cmb_trx_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_trx_typeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmb_trx_type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmb_trx_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Response"));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Action"));

        btn_kirim.setText("Kirim");
        btn_kirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kirimActionPerformed(evt);
            }
        });

        btn_batal.setText("Batal");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_kirim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_batal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_kirim)
                    .addComponent(btn_batal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txt_response.setColumns(20);
        txt_response.setRows(5);
        jScrollPane2.setViewportView(txt_response);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_trx_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_trx_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_trx_typeActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        initStart();
    }//GEN-LAST:event_formComponentShown

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        initStart();
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_kirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kirimActionPerformed
        // TODO add your handling code here:
        performButonKirim();
    }//GEN-LAST:event_btn_kirimActionPerformed

    private void cmb_trx_typeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_trx_typeItemStateChanged
        // TODO add your handling code here:
        request_state = cmb_trx_type.getSelectedIndex();
        loadMessageHolder();
    }//GEN-LAST:event_cmb_trx_typeItemStateChanged

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        System.exit(1);
    }//GEN-LAST:event_formWindowClosed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        saveConfig();
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void config_dialogWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_config_dialogWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_config_dialogWindowClosed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
////            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
////                if ("Nimbus".equals(info.getName())) {
////                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
////                    break;
////                }
////            }
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Tester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(() -> {
//            new Tester().setVisible(true);
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_kirim;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JComboBox<String> cmb_trx_type;
    private javax.swing.JDialog config_dialog;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txt_baseurl;
    private javax.swing.JTextField txt_inquiry_path;
    private javax.swing.JTextField txt_payment_path;
    private javax.swing.JTextArea txt_request;
    private javax.swing.JTextArea txt_response;
    // End of variables declaration//GEN-END:variables
}
