using System;
using System.IO;
using System.Windows;
using System.Windows.Forms;

namespace Textpad
{
    partial class FormTextpad
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(FormTextpad));
            this.txt_input = new System.Windows.Forms.RichTextBox();
            this.menuStrip = new System.Windows.Forms.MenuStrip();
            this.fileToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.newToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.menuItem_open = new System.Windows.Forms.ToolStripMenuItem();
            this.menuItem_save = new System.Windows.Forms.ToolStripMenuItem();
            this.menuItem_saveAs = new System.Windows.Forms.ToolStripMenuItem();
            this.menuItem_print = new System.Windows.Forms.ToolStripMenuItem();
            this.ts_printPreview = new System.Windows.Forms.ToolStripMenuItem();
            this.pageSetupToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.menuItem_quit = new System.Windows.Forms.ToolStripMenuItem();
            this.editToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.menuItem_font = new System.Windows.Forms.ToolStripMenuItem();
            this.menuItem_cut = new System.Windows.Forms.ToolStripMenuItem();
            this.menuItem_copy = new System.Windows.Forms.ToolStripMenuItem();
            this.menuItem_paste = new System.Windows.Forms.ToolStripMenuItem();
            this.menuItem_undo = new System.Windows.Forms.ToolStripMenuItem();
            this.menuItem_redo = new System.Windows.Forms.ToolStripMenuItem();
            this.menuItem_wrap = new System.Windows.Forms.ToolStripMenuItem();
            this.findToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.menuItem_find = new System.Windows.Forms.ToolStripMenuItem();
            this.menuItem_replace = new System.Windows.Forms.ToolStripMenuItem();
            this.fontDialog = new System.Windows.Forms.FontDialog();
            this.saveDialog = new System.Windows.Forms.SaveFileDialog();
            this.openDialog = new System.Windows.Forms.OpenFileDialog();
            this.toolStripMenu = new System.Windows.Forms.ToolStrip();
            this.ts_cut = new System.Windows.Forms.ToolStripButton();
            this.ts_copy = new System.Windows.Forms.ToolStripButton();
            this.ts_paste = new System.Windows.Forms.ToolStripButton();
            this.ts_undo = new System.Windows.Forms.ToolStripButton();
            this.ts_redo = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.ts_replace = new System.Windows.Forms.ToolStripButton();
            this.ts_find = new System.Windows.Forms.ToolStripButton();
            this.ts_print = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.printDialog = new System.Windows.Forms.PrintDialog();
            this.printDocument = new System.Drawing.Printing.PrintDocument();
            this.pageSetupDialog = new System.Windows.Forms.PageSetupDialog();
            this.printPreviewDialog = new System.Windows.Forms.PrintPreviewDialog();
            this.lbl_date = new System.Windows.Forms.Label();
            this.lbl_wordCount = new System.Windows.Forms.Label();
            this.lbl_currentLine = new System.Windows.Forms.Label();
            this.lbl_count = new System.Windows.Forms.Label();
            this.lbl_current = new System.Windows.Forms.Label();
            this.lbl_letterCount = new System.Windows.Forms.Label();
            this.lbl_lineCounter = new System.Windows.Forms.Label();
            this.lbl_count2 = new System.Windows.Forms.Label();
            this.lbl_lineCount = new System.Windows.Forms.Label();
            this.menuStrip.SuspendLayout();
            this.toolStripMenu.SuspendLayout();
            this.SuspendLayout();
            // 
            // txt_input
            // 
            this.txt_input.AcceptsTab = true;
            this.txt_input.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.txt_input.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txt_input.Location = new System.Drawing.Point(0, 68);
            this.txt_input.Name = "txt_input";
            this.txt_input.Size = new System.Drawing.Size(800, 478);
            this.txt_input.TabIndex = 0;
            this.txt_input.Text = "";
            this.txt_input.TextChanged += new System.EventHandler(this.txt_input_TextChanged);
            // 
            // menuStrip
            // 
            this.menuStrip.BackColor = System.Drawing.SystemColors.ActiveBorder;
            this.menuStrip.BackgroundImageLayout = System.Windows.Forms.ImageLayout.None;
            this.menuStrip.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.menuStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.fileToolStripMenuItem,
            this.editToolStripMenuItem,
            this.findToolStripMenuItem});
            this.menuStrip.Location = new System.Drawing.Point(0, 0);
            this.menuStrip.Name = "menuStrip";
            this.menuStrip.RenderMode = System.Windows.Forms.ToolStripRenderMode.Professional;
            this.menuStrip.Size = new System.Drawing.Size(800, 27);
            this.menuStrip.TabIndex = 1;
            this.menuStrip.Text = "menuStrip1";
            // 
            // fileToolStripMenuItem
            // 
            this.fileToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.newToolStripMenuItem,
            this.menuItem_open,
            this.menuItem_save,
            this.menuItem_saveAs,
            this.menuItem_print,
            this.menuItem_quit});
            this.fileToolStripMenuItem.Name = "fileToolStripMenuItem";
            this.fileToolStripMenuItem.Size = new System.Drawing.Size(41, 23);
            this.fileToolStripMenuItem.Text = "File";
            // 
            // newToolStripMenuItem
            // 
            this.newToolStripMenuItem.Image = global::Textpad.Properties.Resources._new;
            this.newToolStripMenuItem.Name = "newToolStripMenuItem";
            this.newToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.N)));
            this.newToolStripMenuItem.Size = new System.Drawing.Size(173, 24);
            this.newToolStripMenuItem.Text = "New";
            this.newToolStripMenuItem.ToolTipText = "Create a new file";
            this.newToolStripMenuItem.Click += new System.EventHandler(this.newToolStripMenuItem_Click);
            // 
            // menuItem_open
            // 
            this.menuItem_open.Image = global::Textpad.Properties.Resources.open;
            this.menuItem_open.Name = "menuItem_open";
            this.menuItem_open.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.O)));
            this.menuItem_open.Size = new System.Drawing.Size(173, 24);
            this.menuItem_open.Text = "Open...";
            this.menuItem_open.ToolTipText = "Open a file...";
            this.menuItem_open.Click += new System.EventHandler(this.menuItem_open_Click);
            // 
            // menuItem_save
            // 
            this.menuItem_save.Image = global::Textpad.Properties.Resources.save;
            this.menuItem_save.Name = "menuItem_save";
            this.menuItem_save.ShortcutKeyDisplayString = "";
            this.menuItem_save.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.S)));
            this.menuItem_save.Size = new System.Drawing.Size(173, 24);
            this.menuItem_save.Text = "Save";
            this.menuItem_save.ToolTipText = "Save the current document";
            this.menuItem_save.Click += new System.EventHandler(this.menuItem_save_Click);
            // 
            // menuItem_saveAs
            // 
            this.menuItem_saveAs.Image = global::Textpad.Properties.Resources.saveas;
            this.menuItem_saveAs.Name = "menuItem_saveAs";
            this.menuItem_saveAs.Size = new System.Drawing.Size(173, 24);
            this.menuItem_saveAs.Text = "Save As...";
            this.menuItem_saveAs.ToolTipText = "Save the current document (Specified filename and location)";
            this.menuItem_saveAs.Click += new System.EventHandler(this.menuItem_saveAs_Click);
            // 
            // menuItem_print
            // 
            this.menuItem_print.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ts_printPreview,
            this.pageSetupToolStripMenuItem});
            this.menuItem_print.Image = global::Textpad.Properties.Resources.print;
            this.menuItem_print.Name = "menuItem_print";
            this.menuItem_print.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.P)));
            this.menuItem_print.Size = new System.Drawing.Size(173, 24);
            this.menuItem_print.Text = "Print...";
            this.menuItem_print.ToolTipText = "Print the current document";
            this.menuItem_print.Click += new System.EventHandler(this.ts_print_Click);
            // 
            // ts_printPreview
            // 
            this.ts_printPreview.Image = global::Textpad.Properties.Resources.print_preview;
            this.ts_printPreview.Name = "ts_printPreview";
            this.ts_printPreview.Size = new System.Drawing.Size(158, 24);
            this.ts_printPreview.Text = "Print Preview";
            this.ts_printPreview.ToolTipText = "Preview the document for printing";
            this.ts_printPreview.Click += new System.EventHandler(this.ts_printPreview_Click);
            // 
            // pageSetupToolStripMenuItem
            // 
            this.pageSetupToolStripMenuItem.Image = global::Textpad.Properties.Resources.print;
            this.pageSetupToolStripMenuItem.Name = "pageSetupToolStripMenuItem";
            this.pageSetupToolStripMenuItem.Size = new System.Drawing.Size(158, 24);
            this.pageSetupToolStripMenuItem.Text = "Page Setup";
            this.pageSetupToolStripMenuItem.ToolTipText = "Set up the Printing settings";
            this.pageSetupToolStripMenuItem.Click += new System.EventHandler(this.pageSetupToolStripMenuItem_Click);
            // 
            // menuItem_quit
            // 
            this.menuItem_quit.Image = global::Textpad.Properties.Resources.exit;
            this.menuItem_quit.Name = "menuItem_quit";
            this.menuItem_quit.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.Q)));
            this.menuItem_quit.Size = new System.Drawing.Size(173, 24);
            this.menuItem_quit.Text = "Quit";
            this.menuItem_quit.ToolTipText = "Quit the current program";
            this.menuItem_quit.Click += new System.EventHandler(this.menuItem_quit_Click);
            // 
            // editToolStripMenuItem
            // 
            this.editToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.menuItem_font,
            this.menuItem_cut,
            this.menuItem_copy,
            this.menuItem_paste,
            this.menuItem_undo,
            this.menuItem_redo,
            this.menuItem_wrap});
            this.editToolStripMenuItem.Name = "editToolStripMenuItem";
            this.editToolStripMenuItem.Size = new System.Drawing.Size(44, 23);
            this.editToolStripMenuItem.Text = "Edit";
            // 
            // menuItem_font
            // 
            this.menuItem_font.Image = global::Textpad.Properties.Resources.font;
            this.menuItem_font.Name = "menuItem_font";
            this.menuItem_font.Size = new System.Drawing.Size(149, 24);
            this.menuItem_font.Text = "Font...";
            this.menuItem_font.ToolTipText = "Set the font...";
            this.menuItem_font.Click += new System.EventHandler(this.fontToolStripMenuItem_Click);
            // 
            // menuItem_cut
            // 
            this.menuItem_cut.Image = global::Textpad.Properties.Resources.cut;
            this.menuItem_cut.Name = "menuItem_cut";
            this.menuItem_cut.Size = new System.Drawing.Size(149, 24);
            this.menuItem_cut.Text = "Cut";
            this.menuItem_cut.Click += new System.EventHandler(this.cutToolStripMenuItem_Click);
            // 
            // menuItem_copy
            // 
            this.menuItem_copy.Image = global::Textpad.Properties.Resources.copy;
            this.menuItem_copy.Name = "menuItem_copy";
            this.menuItem_copy.Size = new System.Drawing.Size(149, 24);
            this.menuItem_copy.Text = "Copy";
            this.menuItem_copy.Click += new System.EventHandler(this.copyToolStripMenuItem_Click);
            // 
            // menuItem_paste
            // 
            this.menuItem_paste.Image = global::Textpad.Properties.Resources.paste;
            this.menuItem_paste.Name = "menuItem_paste";
            this.menuItem_paste.Size = new System.Drawing.Size(149, 24);
            this.menuItem_paste.Text = "Paste";
            this.menuItem_paste.Click += new System.EventHandler(this.pasteToolStripMenuItem_Click);
            // 
            // menuItem_undo
            // 
            this.menuItem_undo.Image = global::Textpad.Properties.Resources.undo;
            this.menuItem_undo.Name = "menuItem_undo";
            this.menuItem_undo.Size = new System.Drawing.Size(149, 24);
            this.menuItem_undo.Text = "Undo";
            this.menuItem_undo.Click += new System.EventHandler(this.undoToolStripMenuItem_Click);
            // 
            // menuItem_redo
            // 
            this.menuItem_redo.Image = global::Textpad.Properties.Resources.redo;
            this.menuItem_redo.Name = "menuItem_redo";
            this.menuItem_redo.Size = new System.Drawing.Size(149, 24);
            this.menuItem_redo.Text = "Redo";
            this.menuItem_redo.Click += new System.EventHandler(this.redoToolStripMenuItem_Click);
            // 
            // menuItem_wrap
            // 
            this.menuItem_wrap.Checked = true;
            this.menuItem_wrap.CheckOnClick = true;
            this.menuItem_wrap.CheckState = System.Windows.Forms.CheckState.Checked;
            this.menuItem_wrap.Name = "menuItem_wrap";
            this.menuItem_wrap.Size = new System.Drawing.Size(149, 24);
            this.menuItem_wrap.Text = "Word Wrap";
            this.menuItem_wrap.Click += new System.EventHandler(this.wordWrapToolStripMenuItem_Click);
            // 
            // findToolStripMenuItem
            // 
            this.findToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.menuItem_find,
            this.menuItem_replace});
            this.findToolStripMenuItem.Name = "findToolStripMenuItem";
            this.findToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.F)));
            this.findToolStripMenuItem.Size = new System.Drawing.Size(47, 23);
            this.findToolStripMenuItem.Text = "Find";
            // 
            // menuItem_find
            // 
            this.menuItem_find.Image = global::Textpad.Properties.Resources.find;
            this.menuItem_find.Name = "menuItem_find";
            this.menuItem_find.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.F)));
            this.menuItem_find.Size = new System.Drawing.Size(181, 24);
            this.menuItem_find.Text = "Find...";
            this.menuItem_find.Click += new System.EventHandler(this.findToolStripMenuItem1_Click);
            // 
            // menuItem_replace
            // 
            this.menuItem_replace.Image = global::Textpad.Properties.Resources.replace;
            this.menuItem_replace.Name = "menuItem_replace";
            this.menuItem_replace.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.S)));
            this.menuItem_replace.Size = new System.Drawing.Size(181, 24);
            this.menuItem_replace.Text = "Replace...";
            this.menuItem_replace.Click += new System.EventHandler(this.replaceToolStripMenuItem_Click);
            // 
            // fontDialog
            // 
            this.fontDialog.AllowScriptChange = false;
            this.fontDialog.AllowSimulations = false;
            this.fontDialog.FontMustExist = true;
            this.fontDialog.ShowColor = true;
            this.fontDialog.Apply += new System.EventHandler(this.fontDialog_Apply);
            // 
            // saveDialog
            // 
            this.saveDialog.DefaultExt = "rtf";
            this.saveDialog.Filter = "Rich Text Format (RTF) (*.rtf)|*.rtf|Text File(TXT) (*.txt)|*.txt|Document (DOC) " +
    "(*.doc)|*.doc|Document (DOCX) (*.docx)|*.docx|OpenDocument Text (*.odt)|*.odt";
            this.saveDialog.InitialDirectory = "5";
            this.saveDialog.Title = "Save As...";
            this.saveDialog.FileOk += new System.ComponentModel.CancelEventHandler(this.saveDialog_FileOk);
            // 
            // openDialog
            // 
            this.openDialog.Filter = "All files (.*)|*.*";
            this.openDialog.InitialDirectory = "5";
            this.openDialog.RestoreDirectory = true;
            this.openDialog.Title = "Open file...";
            this.openDialog.FileOk += new System.ComponentModel.CancelEventHandler(this.openDialog_FileOk);
            // 
            // toolStripMenu
            // 
            this.toolStripMenu.ImeMode = System.Windows.Forms.ImeMode.NoControl;
            this.toolStripMenu.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ts_cut,
            this.ts_copy,
            this.ts_paste,
            this.ts_undo,
            this.ts_redo,
            this.toolStripSeparator1,
            this.ts_replace,
            this.ts_find,
            this.ts_print,
            this.toolStripSeparator2});
            this.toolStripMenu.Location = new System.Drawing.Point(0, 27);
            this.toolStripMenu.Name = "toolStripMenu";
            this.toolStripMenu.Size = new System.Drawing.Size(800, 38);
            this.toolStripMenu.TabIndex = 6;
            // 
            // ts_cut
            // 
            this.ts_cut.Image = global::Textpad.Properties.Resources.cut;
            this.ts_cut.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ts_cut.Name = "ts_cut";
            this.ts_cut.Size = new System.Drawing.Size(30, 35);
            this.ts_cut.Text = "Cut";
            this.ts_cut.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageAboveText;
            this.ts_cut.ToolTipText = "Cut(Remove) the selected text to clipboard";
            this.ts_cut.Click += new System.EventHandler(this.cutToolStripMenuItem_Click);
            // 
            // ts_copy
            // 
            this.ts_copy.Image = global::Textpad.Properties.Resources.copy;
            this.ts_copy.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ts_copy.Name = "ts_copy";
            this.ts_copy.Size = new System.Drawing.Size(39, 35);
            this.ts_copy.Text = "Copy";
            this.ts_copy.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageAboveText;
            this.ts_copy.ToolTipText = "Copy selected text to clipboard";
            this.ts_copy.Click += new System.EventHandler(this.copyToolStripMenuItem_Click);
            // 
            // ts_paste
            // 
            this.ts_paste.Image = global::Textpad.Properties.Resources.paste;
            this.ts_paste.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ts_paste.Name = "ts_paste";
            this.ts_paste.Size = new System.Drawing.Size(39, 35);
            this.ts_paste.Text = "Paste";
            this.ts_paste.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageAboveText;
            this.ts_paste.ToolTipText = "Paste text from clipboard to the screen";
            this.ts_paste.Click += new System.EventHandler(this.pasteToolStripMenuItem_Click);
            // 
            // ts_undo
            // 
            this.ts_undo.Image = global::Textpad.Properties.Resources.undo;
            this.ts_undo.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ts_undo.Name = "ts_undo";
            this.ts_undo.Size = new System.Drawing.Size(40, 35);
            this.ts_undo.Text = "Undo";
            this.ts_undo.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageAboveText;
            this.ts_undo.ToolTipText = "Undo recent operation";
            this.ts_undo.Click += new System.EventHandler(this.undoToolStripMenuItem_Click);
            // 
            // ts_redo
            // 
            this.ts_redo.Image = global::Textpad.Properties.Resources.redo;
            this.ts_redo.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ts_redo.Name = "ts_redo";
            this.ts_redo.Size = new System.Drawing.Size(38, 35);
            this.ts_redo.Text = "Redo";
            this.ts_redo.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageAboveText;
            this.ts_redo.ToolTipText = "Redo recent operation";
            this.ts_redo.Click += new System.EventHandler(this.redoToolStripMenuItem_Click);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(6, 38);
            // 
            // ts_replace
            // 
            this.ts_replace.Image = global::Textpad.Properties.Resources.replace;
            this.ts_replace.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ts_replace.Name = "ts_replace";
            this.ts_replace.Size = new System.Drawing.Size(52, 35);
            this.ts_replace.Text = "Replace";
            this.ts_replace.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageAboveText;
            this.ts_replace.Click += new System.EventHandler(this.replaceToolStripMenuItem_Click);
            // 
            // ts_find
            // 
            this.ts_find.Image = global::Textpad.Properties.Resources.find;
            this.ts_find.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ts_find.Name = "ts_find";
            this.ts_find.Size = new System.Drawing.Size(34, 35);
            this.ts_find.Text = "Find";
            this.ts_find.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageAboveText;
            this.ts_find.ToolTipText = "Search for a certain String in current document";
            this.ts_find.Click += new System.EventHandler(this.findToolStripMenuItem1_Click);
            // 
            // ts_print
            // 
            this.ts_print.Image = global::Textpad.Properties.Resources.print;
            this.ts_print.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.ts_print.Name = "ts_print";
            this.ts_print.Size = new System.Drawing.Size(36, 35);
            this.ts_print.Text = "Print";
            this.ts_print.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageAboveText;
            this.ts_print.ToolTipText = "Print current document";
            this.ts_print.Click += new System.EventHandler(this.ts_print_Click);
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(6, 38);
            // 
            // printDialog
            // 
            this.printDialog.AllowCurrentPage = true;
            this.printDialog.AllowSomePages = true;
            this.printDialog.Document = this.printDocument;
            this.printDialog.UseEXDialog = true;
            // 
            // printDocument
            // 
            this.printDocument.PrintPage += new System.Drawing.Printing.PrintPageEventHandler(this.PrintPage);
            // 
            // pageSetupDialog
            // 
            this.pageSetupDialog.Document = this.printDocument;
            // 
            // printPreviewDialog
            // 
            this.printPreviewDialog.AutoScrollMargin = new System.Drawing.Size(0, 0);
            this.printPreviewDialog.AutoScrollMinSize = new System.Drawing.Size(0, 0);
            this.printPreviewDialog.ClientSize = new System.Drawing.Size(400, 300);
            this.printPreviewDialog.Document = this.printDocument;
            this.printPreviewDialog.Enabled = true;
            this.printPreviewDialog.Icon = ((System.Drawing.Icon)(resources.GetObject("printPreviewDialog.Icon")));
            this.printPreviewDialog.MainMenuStrip = this.menuStrip;
            this.printPreviewDialog.Name = "printPreviewDialog";
            this.printPreviewDialog.UseAntiAlias = true;
            this.printPreviewDialog.Visible = false;
            // 
            // lbl_date
            // 
            this.lbl_date.AutoSize = true;
            this.lbl_date.Location = new System.Drawing.Point(12, 550);
            this.lbl_date.Name = "lbl_date";
            this.lbl_date.Padding = new System.Windows.Forms.Padding(0, 0, 10, 0);
            this.lbl_date.Size = new System.Drawing.Size(41, 17);
            this.lbl_date.TabIndex = 20;
            this.lbl_date.Text = "Date:";
            this.lbl_date.UseCompatibleTextRendering = true;
            // 
            // lbl_wordCount
            // 
            this.lbl_wordCount.AutoSize = true;
            this.lbl_wordCount.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lbl_wordCount.Location = new System.Drawing.Point(256, 550);
            this.lbl_wordCount.Name = "lbl_wordCount";
            this.lbl_wordCount.Padding = new System.Windows.Forms.Padding(10, 0, 5, 0);
            this.lbl_wordCount.Size = new System.Drawing.Size(56, 13);
            this.lbl_wordCount.TabIndex = 12;
            this.lbl_wordCount.Text = "Words:";
            // 
            // lbl_currentLine
            // 
            this.lbl_currentLine.AutoSize = true;
            this.lbl_currentLine.Location = new System.Drawing.Point(683, 550);
            this.lbl_currentLine.Name = "lbl_currentLine";
            this.lbl_currentLine.Padding = new System.Windows.Forms.Padding(10, 0, 10, 0);
            this.lbl_currentLine.Size = new System.Drawing.Size(33, 13);
            this.lbl_currentLine.TabIndex = 19;
            this.lbl_currentLine.Text = "1";
            // 
            // lbl_count
            // 
            this.lbl_count.AutoSize = true;
            this.lbl_count.Location = new System.Drawing.Point(303, 550);
            this.lbl_count.Name = "lbl_count";
            this.lbl_count.Padding = new System.Windows.Forms.Padding(10, 0, 10, 0);
            this.lbl_count.Size = new System.Drawing.Size(33, 13);
            this.lbl_count.TabIndex = 13;
            this.lbl_count.Text = "0";
            // 
            // lbl_current
            // 
            this.lbl_current.AutoSize = true;
            this.lbl_current.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lbl_current.Location = new System.Drawing.Point(609, 550);
            this.lbl_current.Name = "lbl_current";
            this.lbl_current.Padding = new System.Windows.Forms.Padding(10, 0, 5, 0);
            this.lbl_current.Size = new System.Drawing.Size(82, 13);
            this.lbl_current.TabIndex = 18;
            this.lbl_current.Text = "Current Line:";
            // 
            // lbl_letterCount
            // 
            this.lbl_letterCount.AutoSize = true;
            this.lbl_letterCount.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lbl_letterCount.Location = new System.Drawing.Point(372, 550);
            this.lbl_letterCount.Name = "lbl_letterCount";
            this.lbl_letterCount.Padding = new System.Windows.Forms.Padding(10, 0, 5, 0);
            this.lbl_letterCount.Size = new System.Drawing.Size(57, 13);
            this.lbl_letterCount.TabIndex = 14;
            this.lbl_letterCount.Text = "Letters:";
            // 
            // lbl_lineCounter
            // 
            this.lbl_lineCounter.AutoSize = true;
            this.lbl_lineCounter.Location = new System.Drawing.Point(533, 550);
            this.lbl_lineCounter.Name = "lbl_lineCounter";
            this.lbl_lineCounter.Padding = new System.Windows.Forms.Padding(10, 0, 10, 0);
            this.lbl_lineCounter.Size = new System.Drawing.Size(33, 13);
            this.lbl_lineCounter.TabIndex = 17;
            this.lbl_lineCounter.Text = "0";
            // 
            // lbl_count2
            // 
            this.lbl_count2.AutoSize = true;
            this.lbl_count2.Location = new System.Drawing.Point(420, 550);
            this.lbl_count2.Name = "lbl_count2";
            this.lbl_count2.Padding = new System.Windows.Forms.Padding(10, 0, 10, 0);
            this.lbl_count2.Size = new System.Drawing.Size(33, 13);
            this.lbl_count2.TabIndex = 15;
            this.lbl_count2.Text = "0";
            // 
            // lbl_lineCount
            // 
            this.lbl_lineCount.AutoSize = true;
            this.lbl_lineCount.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lbl_lineCount.Location = new System.Drawing.Point(491, 550);
            this.lbl_lineCount.Name = "lbl_lineCount";
            this.lbl_lineCount.Padding = new System.Windows.Forms.Padding(10, 0, 5, 0);
            this.lbl_lineCount.Size = new System.Drawing.Size(50, 13);
            this.lbl_lineCount.TabIndex = 16;
            this.lbl_lineCount.Text = "Lines:";
            // 
            // FormTextpad
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 572);
            this.Controls.Add(this.lbl_date);
            this.Controls.Add(this.lbl_wordCount);
            this.Controls.Add(this.lbl_currentLine);
            this.Controls.Add(this.lbl_count);
            this.Controls.Add(this.lbl_current);
            this.Controls.Add(this.lbl_letterCount);
            this.Controls.Add(this.lbl_lineCounter);
            this.Controls.Add(this.lbl_count2);
            this.Controls.Add(this.lbl_lineCount);
            this.Controls.Add(this.toolStripMenu);
            this.Controls.Add(this.txt_input);
            this.Controls.Add(this.menuStrip);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MainMenuStrip = this.menuStrip;
            this.Name = "FormTextpad";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Untitled - TextEditor";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.FormTextpad_FormClosing);
            this.menuStrip.ResumeLayout(false);
            this.menuStrip.PerformLayout();
            this.toolStripMenu.ResumeLayout(false);
            this.toolStripMenu.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.RichTextBox txt_input;
        public System.Windows.Forms.RichTextBox Text_Input
        {
            get { return txt_input; }
            set { this.txt_input = value; }
        }

        private System.Windows.Forms.MenuStrip menuStrip;
        private System.Windows.Forms.ToolStripMenuItem fileToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem menuItem_quit;
        private System.Windows.Forms.ToolStripMenuItem editToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem menuItem_save;
        private System.Windows.Forms.ToolStripMenuItem menuItem_saveAs;
        private System.Windows.Forms.ToolStripMenuItem newToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem menuItem_open;
        private ToolStripMenuItem menuItem_font;
        private FontDialog fontDialog;
        private SaveFileDialog saveDialog;
        private OpenFileDialog openDialog;
        private ToolStripMenuItem menuItem_cut;
        private ToolStripMenuItem menuItem_copy;
        private ToolStripMenuItem menuItem_paste;
        private ToolStripMenuItem menuItem_undo;
        private ToolStripMenuItem menuItem_redo;
        private ToolStripMenuItem menuItem_print;
        private ToolStripMenuItem findToolStripMenuItem;
        private ToolStripMenuItem menuItem_find;
        private ToolStripMenuItem menuItem_replace;
        private ToolStripMenuItem menuItem_wrap;
        private ToolStrip toolStripMenu;
        private ToolStripButton ts_cut;
        private ToolStripButton ts_copy;
        private ToolStripButton ts_paste;
        private ToolStripButton ts_undo;
        private ToolStripButton ts_redo;
        private ToolStripSeparator toolStripSeparator1;
        private ToolStripButton ts_print;
        private PrintDialog printDialog;
        private System.Drawing.Printing.PrintDocument printDocument;
        private ToolStripButton ts_find;
        private ToolStripButton ts_replace;
        private ToolStripSeparator toolStripSeparator2;
        private PageSetupDialog pageSetupDialog;
        private ToolStripMenuItem ts_printPreview;
        private ToolStripMenuItem pageSetupToolStripMenuItem;
        private PrintPreviewDialog printPreviewDialog;
        private Label lbl_date;
        private Label lbl_wordCount;
        private Label lbl_currentLine;
        private Label lbl_count;
        private Label lbl_current;
        private Label lbl_letterCount;
        private Label lbl_lineCounter;
        private Label lbl_count2;
        private Label lbl_lineCount;

    }
}

