import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class texteditor extends JFrame implements ActionListener {

    JScrollPane scrollPane;

    JTextArea textArea;

    JSpinner fontsizespinner;

    JComboBox fontbox;

    JMenuBar menuBar;

    JMenu filemenu;

    JMenuItem save;

    JMenuItem exit;

    JLabel fontsizelabel;

    texteditor(){
        this.setSize(500,600); //to set size of frame
        this.setTitle("Text-Editor-MinorProject"); //to set title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //To exit on clicking close button
        this.setLayout(new FlowLayout());   //setting layout

        //text area
        textArea = new JTextArea();//Defining textarea
        textArea.setSelectedTextColor(Color.blue); //text color when it is selected
        textArea.setFont(new Font("Arial",Font.PLAIN,18)); //Setting font size of text area

        //scroll pane
        scrollPane = new JScrollPane(textArea); //Defining scroll pane
        scrollPane.setPreferredSize(new Dimension(450,450));//Setting size for pane

        //font size spinner
        fontsizespinner = new JSpinner(); //Defining font size spinner
        fontsizespinner.setPreferredSize(new Dimension(50,25)); //setting size of spinner
        fontsizespinner.setValue(18);//setting default value for spinner

        fontsizespinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println((int)fontsizespinner.getValue());
                textArea.setFont(new Font(textArea.getFont().getFamily(),Font.PLAIN,(int)fontsizespinner.getValue()));
            }
        });

//        H.W
//        Add color option on your own
//        add font style option on your own(Bold,Plain,Italic)
//        Can ask over whatsApp group if found difficult to implement
//        add autosave to the same file or just the text to be saved
//        can we make the frame and textarea responsive
//        Code is on whatsapp as well

        //font scroll bar
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontbox = new JComboBox(fonts);
        fontbox.addActionListener(this);


        //Creating menu box(save,exit)
        menuBar = new JMenuBar();
        filemenu = new JMenu("File");
        save = new JMenuItem("save");
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        save.addActionListener(this);

        filemenu.add(save);
        filemenu.add(exit);
        menuBar.add(filemenu);

        fontsizelabel = new JLabel("Font Size:");
        //add all elemnts in Jframe
        this.setJMenuBar(menuBar);
        this.add(fontsizelabel);
        this.add(fontsizespinner);
        this.add(fontbox);
        this.add(scrollPane);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == fontbox){
            textArea.setFont(new Font((String)fontbox.getSelectedItem(), Font.PLAIN, (int)fontsizespinner.getValue()));
        }

        if(e.getSource() == exit){
            System.exit(29);
        }

        if(e.getSource() == save){
            JFileChooser file_chooser = new JFileChooser();
            file_chooser.setCurrentDirectory(new File("."));

            int resposne = file_chooser.showSaveDialog(null);

            if(resposne == 0){
                //I need to save this file
                File file = new File(file_chooser.getSelectedFile().getAbsolutePath());
                PrintWriter text;
                try {
                    text = new PrintWriter(file);
                    text.println(textArea.getText());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                text.close();
            }
        }
    }
}
