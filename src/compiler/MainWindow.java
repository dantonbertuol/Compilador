package compiler;

import util.OpenedFile;
import util.Token;
import util.Error;
import lexical.LexicalAnalyzer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import syntactic.SyntacticAnalyzer;

/**
 * @author Daniel, Danton e Adrian
 * @since 21/10/2017
 */
public class MainWindow extends javax.swing.JFrame {
    OpenedFile openedFile = new OpenedFile();
    List<Token> tokenList;
    
    public MainWindow() {
        initComponents();
        
        btSave.setEnabled(false);
    }
    
    public MainWindow(List<Token> tokenList) {
        initComponents();
        this.tokenList = tokenList;
        
        btSave.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fcFile = new javax.swing.JFileChooser();
        mainPanel = new javax.swing.JPanel();
        btSave = new javax.swing.JButton();
        btOpen = new javax.swing.JButton();
        btCompile = new javax.swing.JButton();
        btClean = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taInput = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        taOutput = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Compilador");
        setBackground(new java.awt.Color(4, 130, 255));

        mainPanel.setBackground(new java.awt.Color(196, 196, 196));
        mainPanel.setMinimumSize(new java.awt.Dimension(700, 500));

        btSave.setText("Salvar");
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });

        btOpen.setText("Abrir");
        btOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOpenActionPerformed(evt);
            }
        });

        btCompile.setText("Compilar");
        btCompile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCompileActionPerformed(evt);
            }
        });

        btClean.setText("Limpar Saída");
        btClean.setToolTipText("");
        btClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCleanActionPerformed(evt);
            }
        });

        taInput.setColumns(20);
        taInput.setRows(5);
        taInput.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                taInputInputMethodTextChanged(evt);
            }
        });
        taInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                taInputKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(taInput);

        jLabel1.setText("Entrada:");

        taOutput.setColumns(20);
        taOutput.setRows(5);
        taOutput.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        taOutput.setEnabled(false);
        jScrollPane2.setViewportView(taOutput);

        jLabel2.setText("Saída:");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(btOpen)
                                .addGap(18, 18, 18)
                                .addComponent(btSave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btCompile)
                                .addGap(18, 18, 18)
                                .addComponent(btClean)))
                        .addGap(27, 27, 27))))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSave)
                    .addComponent(btOpen)
                    .addComponent(btClean)
                    .addComponent(btCompile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        FileWriter fileWriter = null;
        
        try {
            taOutput.setText("Salvo em: " + openedFile.getPath().toString());
            fileWriter = new FileWriter(openedFile.getPath().toString());
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf(taInput.getText());
        
        try {
            fileWriter.close();
            btSave.setEnabled(false);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btSaveActionPerformed

    private void btOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOpenActionPerformed
        int returnValue = fcFile.showOpenDialog(this);
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            openedFile.setName(fcFile.getSelectedFile().getName());
            File file = fcFile.getSelectedFile();
            Path path = Paths.get(file.getAbsolutePath());
            try {
                openedFile.setFile(Files.readAllBytes(path));
                openedFile.setPath(path);
                
                String text = new String(openedFile.getFile());
                taInput.setText(text);
            } catch (IOException ex) {
                System.out.println("! ERRO | MainWindows(btOpen) : Não foi possível abrir o arquivo. " + ex);
                taOutput.setText(taOutput.getText() + "\n! ERRO | MainWindows(btOpen) : Não foi possível abrir o arquivo.");
            }
        }
    }//GEN-LAST:event_btOpenActionPerformed

    private void btCompileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCompileActionPerformed
        Object[] tokensAndErrors = LexicalAnalyzer.analyze(taInput.getText());
        
        taOutput.setText(" --- LISTA DE TOKENS IDENTIFICADOS --- ");
        for(Token token : (ArrayList<Token>) tokensAndErrors[0]){
            if(token.getType() != util.Types.Token.error){
                taOutput.setText(taOutput.getText() + "\n - " + token.toString());
            }
        }
        
        SyntacticAnalyzer syntacticAnalyzer = new SyntacticAnalyzer(((ArrayList<Token>) tokensAndErrors[0]), (ArrayList<Error>) tokensAndErrors[1]);
        syntacticAnalyzer.syntatic();
        taOutput.setText(taOutput.getText() + "\n\n !!! LISTA DE ERROS ENCONTRADOS !!! ");
        for(Error error : syntacticAnalyzer.getErrors()){
            taOutput.setText(taOutput.getText() + "\n ! " + error.toString());
        }
    }//GEN-LAST:event_btCompileActionPerformed

    private void btCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCleanActionPerformed
        taOutput.setText("");
    }//GEN-LAST:event_btCleanActionPerformed

    private void taInputInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_taInputInputMethodTextChanged
        
    }//GEN-LAST:event_taInputInputMethodTextChanged

    private void taInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taInputKeyPressed
        btSave.setEnabled(true);
    }//GEN-LAST:event_taInputKeyPressed

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btClean;
    private javax.swing.JButton btCompile;
    private javax.swing.JButton btOpen;
    private javax.swing.JButton btSave;
    private javax.swing.JFileChooser fcFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextArea taInput;
    private javax.swing.JTextArea taOutput;
    // End of variables declaration//GEN-END:variables
}
