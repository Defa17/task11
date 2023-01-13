package ru.vsu.cs.course1;
import ru.vsu.cs.course1.logic.Logic;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import ru.vsu.cs.course1.utils.LoadFromFile;
import ru.vsu.cs.course1.utils.SaveToFile;
import ru.vsu.cs.util.*;


public class FrameMain extends JFrame {
    private JTextField textFieldA;
    private JPanel panelMain;
    private JTextField textFieldC;
    private JButton buttonCalc;
    private JButton buttonLoadInputFromFile;
    private JButton buttonSaveInputInfoFile;
    private JButton buttonRandomInput;
    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;
    private JMenuBar menuBarMain;
    private JMenu menuLookAndFeel;


    public FrameMain() {
        this.setTitle("FrameMain");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        buttonCalc.addActionListener(e -> {
            try {
                int number = Integer.parseInt(textFieldA.getText());
                String convertNumber = Logic.convert(number);
                textFieldC.setText(String.format("%s", convertNumber));
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Некорректные данные", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });


        buttonLoadInputFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int number = LoadFromFile.loadFromFile(fileChooserOpen.getSelectedFile().getPath());
                        String convertNumber = Logic.convert(number);
                        textFieldA.setText(String.format("%s", number));
                        textFieldC.setText(String.format("%s", convertNumber));
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonSaveInputInfoFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int number = Integer.parseInt(textFieldA.getText());
                        String convertNumber = Logic.convert(number);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        SaveToFile.saveToFile(file, convertNumber);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        buttonRandomInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Random random = new Random();
                    int number = random.nextInt(1000000000);
                    textFieldA.setText(String.format("%s", number));
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }

 }
