package TextEditor;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Chris Medina
 * @date Jan 16, 2015
 */
public final class TextEditor extends JFrame implements ActionListener, KeyListener, MouseListener,
                                                        UndoableEditListener {
    //CONSTANTS
    private static final String ICON_URL = "res/notepad.png";
    private static final String ICON_CUT = "res/cut.png";
    private static final String ICON_COPY = "res/copy.png";
    private static final String ICON_PASTE = "res/paste.png";
    private static final String ICON_UNDO = "res/undo.png";
    private static final String ICON_REDO = "res/redo.png";
    private static final String ICON_PRINT = "res/print.png";
    private static final String ICON_EXIT = "res/exit.png";
    private static final String ICON_NEW = "res/new.png";
    private static final String ICON_OPEN = "res/open.png";
    private static final String ICON_SAVE = "res/save.png";
    private static final String ICON_SAVEAS = "res/saveas.png";
    private static final String ICON_FIND = "res/find.png";
    private static final String ICON_REPLACE = "res/replace.png";
    private static final String ICON_INFO = "res/info.png";
    private static final String ICON_FONT = "res/font.png";

    private static final int FRAME_WIDTH = 640;
    private static final int FRAME_HEIGHT = 480;

    private final JMenuBar menuBar;
    private final JMenu file, edit, format, help;

    //FILE MENU
    private final JMenuItem newDoc, open, save, saveAs, print, exit;

    //EDIT MENU
    private final JMenuItem copy, cut, paste, undo, redo, find, replace;

    //FORMAT MENU
    private final JMenuItem setFont, wordwrap;

    //HELP MENU
    private final JMenuItem info;

    //GUI
    private final JPanel panel;
    private final JTextField timeInfo, counts;
    private final JScrollPane scrollPane;
    private final JTextArea textArea;
    private final JPopupMenu popupMenu;
    private final JFileChooser fileChooser;
    private final Highlighter highLighter;
    private final Document doc;
    private final UndoManager undoManager;
    private final Font[] fonts;

    private int lineNumber, letterCount, wordCount;
    private boolean isSaved, isChanged, isWordWrap;
    private String saveName, savePath;

    public TextEditor () throws HeadlessException {
        super("Text Editor");

        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon((ICON_URL)).getImage());

        //MONITOR PROPERTIES (center location on screen)
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode gfxConfig = gd.getDisplayMode();

        //GET ALL POSSIBLE FONTS IN THIS ENVIROMENT
        fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        int monitorWidth = gfxConfig.getWidth();
        int monitorHeight = gfxConfig.getHeight();

        setLocation((monitorWidth / 2) - (FRAME_WIDTH / 2), (monitorHeight / 2) - (FRAME_HEIGHT / 2));
        isSaved = false;
        isChanged = false;
        isWordWrap = true;
        savePath = "";

        //MEUN BAR, MENUS*/
        menuBar = new JMenuBar();
        menuBar.setBorder(new BevelBorder(BevelBorder.RAISED));

        file = new JMenu("File");
        edit = new JMenu("Edit");
        format = new JMenu("Format");
        help = new JMenu("Help");

        //MENU ITEMS*/
        newDoc = new JMenuItem("New");
        newDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newDoc.setToolTipText("Start a new document");

        open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.setToolTipText("Open a file...");

        save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.setToolTipText("Save the current document");

        saveAs = new JMenuItem("Save as...");
        saveAs.setToolTipText("Save the current file as...");

        exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        exit.setToolTipText("Exit the program");

        print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        print.setToolTipText("Print the current document");

        wordwrap = new JCheckBoxMenuItem("Word Wrap");
        wordwrap.setToolTipText("Toggle word wrap");
        wordwrap.setSelected(true);

        cut = new JMenuItem(new DefaultEditorKit.CutAction());
        cut.setText("Cut");
        cut.setToolTipText("Cut highlighted text to clipboard");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        copy = new JMenuItem(new DefaultEditorKit.CopyAction());
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.setText("Copy");
        copy.setToolTipText("Copy to clipboard");

        paste = new JMenuItem(new DefaultEditorKit.PasteAction());
        paste.setText("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.setToolTipText("Paste from clipboard");

        undo = new JMenuItem("Undo");
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        undo.setToolTipText("Undo most recent action");

        redo = new JMenuItem("Redo");
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        redo.setToolTipText("Redo most recent action");

        replace = new JMenuItem("Replace...");
        replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        replace.setToolTipText("Replace (a/an) character/word(s)...");

        find = new JMenuItem("Find...");
        find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        find.setToolTipText("Search document for a letter or word(s)...");

        setFont = new JMenuItem("Font...");
        setFont.setToolTipText("Change the current font...");

        info = new JMenuItem("About...");
        info.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        info.setToolTipText("Show information about the program...");

        panel = new JPanel(true);
        panel.setLayout(new BorderLayout(3, 3));

        fileChooser = new JFileChooser(System.getProperty("user.dir"));
        popupMenu = new JPopupMenu("Selector");
        addPopUpItems();

        //FILE MENU*/
        file.add(newDoc);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.add(print);
        file.add(exit);

        //EDIT MENU*/
        edit.add(undo);
        edit.add(redo);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(replace);
        edit.add(find);

        //FORMAT MENU*/
        format.add(setFont);
        format.add(wordwrap);

        //HELP MENU*/
        help.add(info);

        //TEXT AREA*/
        textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        textArea.addKeyListener(this);
        textArea.addMouseListener(this);
        textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));

        undoManager = new UndoManager();
        doc = textArea.getDocument();
        doc.addUndoableEditListener(this);
        highLighter = textArea.getHighlighter();
        highLighter.removeAllHighlights();

        counts = new JTextField(String.format("%25sLines: %-5d Letters: %-5d Words: %-5d", "", lineNumber, letterCount,
                wordCount));
        counts.setHighlighter(null);
        counts.setEditable(false);
        counts.setBorder(BorderFactory.createEmptyBorder());
        counts.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        textArea.getDocument().addDocumentListener(new TextListener(counts));

        timeInfo = new JTextField(new SimpleDateFormat("MM-dd-YYYY").format(new Date()));
        timeInfo.setMargin(new Insets(3, 5, 3, 5));
        timeInfo.setText("Date: " + timeInfo.getText());
        timeInfo.setHighlighter(null);
        timeInfo.setEditable(false);

        saveName = "Untitled";

        final JMenuItem[] listeners = {newDoc, open, save, saveAs, exit, print, redo, undo, replace, wordwrap,
                setFont, find,
                info};
        for (JMenuItem jc : listeners) {
            jc.addActionListener(this);
        }


        //FINALIZE
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(format);
        menuBar.add(help);
        loadIcons();

        this.add(panel, BorderLayout.SOUTH);
        this.setTitle(saveName + " - " + getTitle());
        this.add(menuBar, BorderLayout.NORTH);
        this.scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane
                .HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane, BorderLayout.CENTER);

        panel.add(timeInfo, BorderLayout.WEST);
        panel.add(counts, BorderLayout.EAST);

        setVisible(true);
        pack();
    }

    public static void main (String[] args) {
        System.out.println(System.getProperty("user.dir"));
        TextEditor textEditor = new TextEditor();
    }

    private void loadIcons(){
        cut.setIcon(new ImageIcon(ICON_CUT));
        copy.setIcon(new ImageIcon(ICON_COPY));
        paste.setIcon(new ImageIcon(ICON_PASTE));
        undo.setIcon(new ImageIcon(ICON_UNDO));
        redo.setIcon(new ImageIcon(ICON_REDO));
        print.setIcon(new ImageIcon(ICON_PRINT));
        newDoc.setIcon(new ImageIcon(ICON_NEW));
        open.setIcon(new ImageIcon(ICON_OPEN));
        exit.setIcon(new ImageIcon(ICON_EXIT));
        save.setIcon(new ImageIcon(ICON_SAVE));
        saveAs.setIcon(new ImageIcon(ICON_SAVEAS));
        find.setIcon(new ImageIcon(ICON_FIND));
        replace.setIcon(new ImageIcon(ICON_REPLACE));
        info.setIcon(new ImageIcon(ICON_INFO));
        setFont.setIcon(new ImageIcon(ICON_FONT));
    }

    private void addPopUpItems () {
        JMenuItem item = new JMenuItem(new DefaultEditorKit.CutAction());
        item.setText("Cut");
        item.setToolTipText(cut.getToolTipText());
        popupMenu.add(item);

        item = new JMenuItem(new DefaultEditorKit.CopyAction());
        item.setText("Copy");
        item.setToolTipText(copy.getToolTipText());
        popupMenu.add(item);

        item = new JMenuItem(new DefaultEditorKit.PasteAction());
        item.setText("Paste");
        item.setToolTipText(paste.getToolTipText());
        popupMenu.add(item);

        item = new JMenuItem(find.getText());
        item.setToolTipText(find.getToolTipText());
        item.setAction(new AbstractAction(find.getText()) {
            @Override
            public void actionPerformed (ActionEvent e) {
                handleFindOption();
            }
        });
        popupMenu.add(item);
    }

    //ACTION LISTENER*/
    @Override
    public void actionPerformed (ActionEvent e) {
        JMenuItem selected = (JMenuItem) e.getSource();
        if (selected == info) {
            handleInfo();
        } else if (selected == exit) {
            handleQuit();
        } else if (selected == wordwrap) {
            handleWordWrap();
        } else if (selected == find) {
            handleFindOption();
        } else if (selected == saveAs) {
            handleSaveAs();
        } else if (selected == save) {
            handleSave();
        } else if (selected == open) {
            handleOpen();
        } else if (selected == print) {
            handlePrint();
        } else if (selected == undo) {
            handleUndo();
        } else if (selected == redo) {
            handleRedo();
        } else if (selected == replace) {
            handleReplacement();
        } else if (selected == newDoc) {
            handleNew();
        } else if (selected == setFont) {
            handleFont();
        }
    }

    private void handleFont () {
        JDialog dialog = new FontDialog();
        dialog.setVisible(true);
    }

    private void handleInfo () {
        JOptionPane.showMessageDialog(this, "Produced by: Chris Medina\nDate: " + new SimpleDateFormat("M/DD/yyyy" +
                " @ hh:mm aa").format(new Date()));
    }

    private void handleQuit () {
        if (isChanged && !isSaved) {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you wish to quit (Y/N) ?", "Quit " +
                    "program?", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION)
                System.exit(0);
        } else {
            System.exit(0);
        }
    }

    private static int getLinesCount (JTextComponent txtComp) {
        int linecount = 0;
        Font f = txtComp.getFont();
        FontMetrics fontMetrics = txtComp.getFontMetrics(f);
        int fontHeight = fontMetrics.getHeight();

        try {
            int height = txtComp.modelToView(txtComp.getDocument().getEndPosition().getOffset() - 1).y;
            linecount = height / fontHeight + 1;
        } catch (BadLocationException e) {
            linecount = 0;
        }

        return linecount;
    }

    private void handleWordWrap () {
        isWordWrap = !isWordWrap;
        wordwrap.setSelected(isWordWrap);
        textArea.setLineWrap(isWordWrap);
        textArea.setWrapStyleWord(isWordWrap);
    }

    private void handleNew () {
        if (isChanged && !isSaved) {
            handleSaveAs();
        } else {
            this.saveName = "Untitled";
            this.textArea.setText("");
            this.isSaved = false;
            this.isChanged = false;
            this.setTitle(saveName + " - " + "Text Editor");
        }
    }

    private void handleReplacement () {
        JDialog dialog = new ReplaceDialog();
        dialog.setVisible(true);
    }

    private void replace (String replace, String with, boolean all) {
        String temp = textArea.getText();
        textArea.setText("");
        String filtered = temp.replaceAll("[-+.^:,]", "");

        if (all) {
            filtered = filtered.replaceAll(replace, with);
        } else {
            filtered = filtered.replace(replace, with);
        }

        this.textArea.setText(filtered);
    }

    private void handleUndo () {
        if (undoManager.canUndo()) {
            undoManager.undo();
        }
    }

    private void handleRedo () {
        if (undoManager.canRedo()) {
            undoManager.redo();
        }
    }

    private void handlePrint () {
        try {
            MessageFormat format = new MessageFormat("");
            this.textArea.print(new MessageFormat(this.saveName), new MessageFormat("Page - {0}"));
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

    private void handleSaveAs () {
        fileChooser.setDialogTitle("Save as...");
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            saveAs(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void handleSave () {
        if (!isSaved) {
            handleSaveAs();
        } else {
            save();
        }
    }

    private void save () {
        File f = new File(this.savePath);
        saveAs(f.getAbsolutePath());
    }

    private void saveAs (String filename) {
        try {
            File f = new File(filename);
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            bw.write(textArea.getText());
            bw.close();

            this.saveName = f.getName();
            this.savePath = f.getAbsolutePath();
            this.setTitle(saveName + " - Text Editor");
            this.isSaved = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleOpen () {
        if (!isSaved) {
            int selection = JOptionPane.showConfirmDialog(this, "This document hasn't been saved yet. Would you like " +
                    "to save?", "Save file?", JOptionPane.YES_OPTION);
            if (selection == JOptionPane.YES_OPTION) {
                handleSaveAs();
            }
        } else {
            fileChooser.setDialogTitle("Open file...");
            int selected = fileChooser.showOpenDialog(this);
            if (selected == JFileChooser.APPROVE_OPTION) {
                open(fileChooser.getSelectedFile().getAbsolutePath());
            }
        }
    }

    private void open (String filename) {
        try {
            File f = new File(filename);
            BufferedReader bw = new BufferedReader(new FileReader(filename));
            this.textArea.setText("");

            String s = "";
            while ((s = bw.readLine()) != null) {
                this.textArea.setText(s);
            }

            this.setTitle(f.getName() + " - Text Editor");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleFindOption () {
        JDialog findDialog = new FindDialog();
        findDialog.setVisible(true);
    }

    private void updateLineCount () {
        lineNumber = getLinesCount(textArea);
    }

    private void updateCharCount () {
        letterCount = doc.getLength();
    }

    private void updateWordCount () {
        wordCount = this.textArea.getText().split(" ").length;
    }

    private void updateAllTextCounts () {
        updateCharCount();
        updateLineCount();
        updateWordCount();
        counts.setText(String.format("%25sLines: %-5d Letters: %-5d Words: %-5d", "", lineNumber, letterCount,
                wordCount));
    }

    //KEY LISTENER*/
    @Override
    public void keyTyped (KeyEvent e) {
        updateAllTextCounts();
    }

    @Override
    public void keyPressed (KeyEvent e) {
        updateAllTextCounts();
    }

    @Override
    public void keyReleased (KeyEvent e) {
        updateAllTextCounts();
    }

    //MOUSE LISTENER*/
    private void handlePopUp (MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
        if (SwingUtilities.isLeftMouseButton(e)) {
            popupMenu.setVisible(false);
        }
    }

    @Override
    public void mouseClicked (MouseEvent e) {
        handlePopUp(e);
    }

    @Override
    public void mousePressed (MouseEvent e) {
        handlePopUp(e);
    }

    @Override
    public void mouseReleased (MouseEvent e) {
        handlePopUp(e);
    }

    @Override
    public void mouseEntered (MouseEvent e) {
        handlePopUp(e);
    }

    @Override
    public void mouseExited (MouseEvent e) {
        handlePopUp(e);
    }

    //UNDOABLEEDIT LISTENER
    @Override
    public void undoableEditHappened (UndoableEditEvent e) {
        undoManager.addEdit(e.getEdit());
    }

    //DOCUMENT LISTENER
    private class TextListener implements DocumentListener {

        private JTextField field;

        public TextListener (JTextField field) {
            this.field = field;
        }

        @Override
        public void insertUpdate (DocumentEvent e) {
            updateAllTextCounts();
            isChanged = true;
        }

        @Override
        public void removeUpdate (DocumentEvent e) {
            updateAllTextCounts();
            isChanged = true;
        }

        @Override
        public void changedUpdate (DocumentEvent e) {
            updateAllTextCounts();
            isChanged = true;
            isSaved = false;
        }
    }

    /**
     * A custom JDialog for finding text matches in current document based on user search request.
     */
    private class FindDialog extends JDialog implements ActionListener {
        private JLabel lbl_findWhat;
        private JTextField txt_find;
        private JButton btn_next, btn_cancel;
        private JPanel top, bottom;
        private int currIndex, currEnd;
        private boolean match;
        private int matches;

        public FindDialog () {
            this.setLocationRelativeTo(null);
            this.setLayout(new BorderLayout(3, 3));
            this.currIndex = 0;
            this.currEnd = 0;
            top = new JPanel();
            bottom = new JPanel();
            bottom.setAlignmentX(Component.CENTER_ALIGNMENT);
            matches = 0;

            lbl_findWhat = new JLabel("Find what: ");
            txt_find = new JTextField();
            txt_find.setEditable(true);
            txt_find.setColumns(25);
            btn_next = new JButton("Next");
            btn_cancel = new JButton("Cancel");

            top.add(lbl_findWhat);
            top.add(txt_find);
            bottom.add(btn_next);
            bottom.add(btn_cancel);

            this.add(top, BorderLayout.NORTH);
            this.add(bottom, BorderLayout.SOUTH);
            btn_cancel.addActionListener(this);
            btn_next.addActionListener(this);

            setVisible(true);
            pack();
        }

        @Override
        public void actionPerformed (ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if (button == btn_next) {
                findNext(txt_find.getText());
            } else {
                setVisible(false);
                highLighter.removeAllHighlights();
            }
        }

        private void findNext (String s) {
            int find = textArea.getText().indexOf(s, currEnd);
            if (find == -1) {
                if (!match)
                    JOptionPane.showMessageDialog(this, s + " not found!");
                else {
                    JOptionPane.showMessageDialog(this, "End of document reached!" + System.lineSeparator() +
                            "Found (\" + matches + \")\" + \" results.\"");
                }
            } else {
                matches++;
                match = true;
                currIndex = find;
                currEnd = currIndex + s.length();
                highLighter.removeAllHighlights();
                try {
                    highLighter.addHighlight(currIndex, currEnd, new DefaultHighlighter.DefaultHighlightPainter(Color
                            .YELLOW));
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * A custom JDialog for replacing text matches in current document based on user search request.
     */
    private class ReplaceDialog extends JDialog implements ActionListener, KeyListener {
        private JPanel left, right, bottom;
        private JLabel lbl_findWhat, lbl_replaceWith;
        private JTextField txt_find, txt_replace;
        private JButton btn_next, btn_replace, btn_replaceAll, btn_cancel;
        private int currIndex, endIndex, matches;
        private boolean found = false;

        public ReplaceDialog () {
            this.setLocationRelativeTo(null);
            this.setLayout(new BorderLayout(5, 5));
            this.setPreferredSize(new Dimension(400, 150));
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            this.left = new JPanel(new GridLayout(2, 1, 3, 5));
            this.right = new JPanel(new GridLayout(3, 1, 3, 3));
            this.bottom = new JPanel();
            this.bottom.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.currIndex = 0;
            this.endIndex = 0;
            this.matches = 0;

            //LABELS
            lbl_findWhat = new JLabel("Find what: ");
            lbl_replaceWith = new JLabel("Replace with: ");

            //TEXT FIELDS
            txt_find = new JTextField();
            txt_find.setEditable(true);
            txt_find.setColumns(25);
            txt_replace = new JTextField();
            txt_replace.setEditable(true);

            //BUTTONS
            btn_next = new JButton("Next");
            btn_replace = new JButton("Replace");
            btn_replaceAll = new JButton("Replace all");
            btn_cancel = new JButton("Cancel");

            btn_next.addActionListener(this);
            btn_replace.addActionListener(this);
            btn_replaceAll.addActionListener(this);
            btn_cancel.addActionListener(this);
            txt_find.addKeyListener(this);

            //LEFT PANEL
            left.add(lbl_findWhat);
            left.add(txt_find);
            left.add(lbl_replaceWith);
            left.add(txt_replace);

            //RIGHT PANEL
            right.add(btn_next);
            right.add(btn_replace);
            right.add(btn_replaceAll);

            //BOTTOM
            bottom.add(btn_cancel);

            btn_next.setEnabled(false);
            btn_replace.setEnabled(false);
            btn_replaceAll.setEnabled(false);

            this.add(left, BorderLayout.CENTER);
            this.add(right, BorderLayout.EAST);
            this.add(bottom, BorderLayout.SOUTH);

            this.setVisible(true);
            this.pack();
        }


        @Override
        public void actionPerformed (ActionEvent e) {
            JButton clicked = (JButton) e.getSource();

            String find = txt_find.getText();
            String replace = txt_replace.getText();

            if (clicked == btn_next) {
                next(find);
            } else if (clicked == btn_replace) {
                replace(find, replace);
            } else if (clicked == btn_replaceAll) {
                replaceAll(find, replace);
            } else if (clicked == btn_cancel) {
                this.setVisible(false);
                highLighter.removeAllHighlights();
            }
        }

        private void next (String entry) {
            int loc = textArea.getText().indexOf(entry, endIndex);
            if (loc != -1) {
                found = true;
                currIndex = loc;
                endIndex = currIndex + entry.length();
                highLighter.removeAllHighlights();
                try {
                    highLighter.addHighlight(loc, loc + entry.length(), new DefaultHighlighter
                            .DefaultHighlightPainter(Color.YELLOW));
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            } else {
                if (found)
                    JOptionPane.showMessageDialog(this, "End of document reached");
                else
                    JOptionPane.showMessageDialog(this, entry + " was not found");
            }
        }

        /**
         * Replace first found occurence
         */
        private void replace (String entry, String replace) {
            String out = "";
            found = false;
            for (int i = 0; i < textArea.getText().length(); i++) {
                if (i <= (textArea.getText().length() - entry.length())) {
                    if ((i == currIndex) && textArea.getText().substring(i, i + entry.length()).equals(entry)) {
                        found = true;
                        //replace
                        out += replace;
                        i += entry.length();
                        matches++;
                    } else
                        found = false;
                }

                if (i < textArea.getText().length())
                    out += textArea.getText().charAt(i);
            }

            textArea.setText(out);
        }

        private void replaceAll (String entry, String replace) {
            matches = 0;
            String out = "";
            found = false;
            for (int i = 0; i < textArea.getText().length(); i++) {
                if (i <= (textArea.getText().length() - entry.length())) {
                    if (textArea.getText().substring(i, i + entry.length()).equals(entry)) {
                        found = true;
                        //replace
                        out += replace;
                        i += entry.length();
                        matches++;
                    }
                }

                if (i < textArea.getText().length())
                    out += textArea.getText().charAt(i);
            }

            textArea.setText(out);
            if (found) {
                JOptionPane.showMessageDialog(this, "All occurences of " + entry + " have been replaced. Replaced " +
                        entry + " (" + matches + ") times.");
            } else {
                JOptionPane.showMessageDialog(this, "No more results found");
            }
        }

        @Override
        public void keyTyped (KeyEvent e) {
            handleKeyInput(e);
        }

        @Override
        public void keyPressed (KeyEvent e) {
            handleKeyInput(e);
        }

        @Override
        public void keyReleased (KeyEvent e) {
            handleKeyInput(e);
        }

        private void handleKeyInput (KeyEvent e) {
            if (txt_find.getText().isEmpty()) {
                btn_next.setEnabled(false);
                btn_replace.setEnabled(false);
                btn_replaceAll.setEnabled(false);
            } else {
                btn_next.setEnabled(true);
                btn_replace.setEnabled(true);
                btn_replaceAll.setEnabled(true);
            }
        }
    }


    /**
     * A custom JDialog Pane necessary for changing current font
     */
    private class FontDialog extends JDialog implements ActionListener {
        private String fontName;
        private int fontIndex, sizeIndex, colorIndex;
        private Font currFont;
        private static final int DIALOG_WIDTH = 680;
        private static final int DIALOG_HEIGHT = 340;

        //Preview Label properties
        private String currFontName = textArea.getFont().getFontName();
        private int currSize = textArea.getFont().getSize();
        private int currStyle = textArea.getFont().getStyle();
        private Color currColor = textArea.getForeground();
        private String currColorString = "Black";

        //GUI Elements
        private JLabel preview;
        private JComboBox<String> font, colors;
        private JComboBox<Integer> size;
        private JCheckBox bold, italic;
        private JButton confirm, cancel;
        private JPanel top, middle, bottom;

        //MONITOR PROPERTIES (center location on screen)
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode gfxConfig = gd.getDisplayMode();
        int monitorWidth = gfxConfig.getWidth();
        int monitorHeight = gfxConfig.getHeight();

        public FontDialog () {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
            setLocation(new Point((monitorWidth / 2) - DIALOG_WIDTH / 2, (monitorHeight / 2) - DIALOG_HEIGHT / 2));
            setTitle("Choose Font");
            setLayout(new BorderLayout(5, 20));

            top = new JPanel();
            bottom = new JPanel();
            middle = new JPanel();
            middle.setAlignmentX(Component.CENTER_ALIGNMENT);
            confirm = new JButton("Confirm");
            confirm.addActionListener(this);
            cancel = new JButton("Cancel");
            cancel.addActionListener(this);

            String[] tmpFonts = new String[fonts.length];
            Integer[] tmpSizes = {8, 10, 12, 14, 16, 18, 20, 24, 28, 32, 36, 40, 48, 52, 56, 60, 64, 68, 72, 80, 86,
                    92, 100};

            int index = 0;
            for (Font f : fonts)
                tmpFonts[index++] = f.getFontName();

            String[] tmpColors = {"White", "Black", "Blue", "Orange", "Green", "Red", "Gray"};

            currFont = textArea.getFont();
            font = new JComboBox<>(tmpFonts);
            fontIndex = Arrays.asList(tmpFonts).indexOf(currFont.getFontName());
            sizeIndex = Arrays.asList(tmpSizes).indexOf(currFont.getSize());

            colorIndex = Arrays.asList(tmpColors).indexOf(currColorString);

            font.setSelectedIndex(fontIndex);
            size = new JComboBox<>(tmpSizes);
            size.setSelectedIndex(sizeIndex);
            colors = new JComboBox<>(tmpColors);
            colors.setSelectedIndex(colorIndex);

            preview = new JLabel("Sample text");
            preview.setFont(this.getFont());
            bold = new JCheckBox("Bold");
            italic = new JCheckBox("Italic");
            bold.setSelected(false);
            italic.setSelected(false);

            font.addActionListener(this);
            size.addActionListener(this);
            colors.addActionListener(this);
            bold.addActionListener(this);
            italic.addActionListener(this);

            top.add(new JLabel("Font: "));
            top.add(font);
            top.add(new JLabel("Size: "));
            top.add(size);
            top.add(new JLabel("Color: "));
            top.add(colors);
            top.add(bold);
            top.add(italic);
            middle.add(preview);
            bottom.add(confirm);
            bottom.add(cancel);

            this.add(top, BorderLayout.NORTH);
            this.add(middle, BorderLayout.CENTER);
            this.add(bottom, BorderLayout.SOUTH);

            setVisible(true);
            pack();
        }

        @Override
        public void actionPerformed (ActionEvent e) {
            currFontName = (String) font.getSelectedItem();
            currSize = (int) size.getSelectedItem();
            fontIndex = font.getSelectedIndex();
            sizeIndex = size.getSelectedIndex();
            colorIndex = colors.getSelectedIndex();

            //BOLD AND ITALICS STYLE CHANGES
            int style;
            if (bold.isSelected() && italic.isSelected())
                style = Font.BOLD | Font.ITALIC;
            else if (bold.isSelected())
                style = Font.BOLD;
            else if (italic.isSelected())
                style = Font.ITALIC;
            else
                style = Font.PLAIN;

            preview.setFont(new Font(currFontName, style, currSize));

            //COLOR CHANGE
            switch (colorIndex) {
                case 0:
                    currColor = Color.WHITE;
                    currColorString = "White";
                    break;
                case 1:
                    currColor = Color.BLACK;
                    currColorString = "Black";
                    break;
                case 2:
                    currColor = Color.BLUE;
                    currColorString = "Blue";
                    break;
                case 3:
                    currColor = Color.ORANGE;
                    currColorString = "Orange";
                    break;
                case 4:
                    currColor = Color.GREEN;
                    currColorString = "Green";
                    break;
                case 5:
                    currColor = Color.RED;
                    currColorString = "Red";
                    break;
                case 6:
                    currColor = Color.GRAY;
                    currColorString = "Gray";
                    break;
            }

            preview.setForeground(currColor);

            JComponent component = (JComponent) e.getSource();
            if (component == confirm) {
                textArea.setFont(new Font(currFontName, style, currSize));
                textArea.setForeground(currColor);
                setVisible(false);
            } else if (component == cancel)
                setVisible(false);
        }

    }

}
