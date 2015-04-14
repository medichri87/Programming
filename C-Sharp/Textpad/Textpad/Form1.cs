using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.Drawing.Printing;
using System.IO;
using System.Linq;
using System.Runtime.Versioning;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Documents;
using System.Windows.Forms;
using Textpad.Properties;
using Clipboard = System.Windows.Forms.Clipboard;
using FontStyle = System.Drawing.FontStyle;
using MessageBox = System.Windows.Forms.MessageBox;

namespace Textpad
{
    public partial class FormTextpad : Form
    {
        private bool saved, edited;
        private String fileName, fileLocation;

        public FormTextpad()
        {
            InitializeComponent();
            saved = false;
            edited = false;
            fileName = "";
            fileLocation = Convert.ToString(Environment.SpecialFolder.MyDocuments);
            lbl_date.Text = String.Format(DateTime.Now.ToString("F"));
        }

        /// <summary>
        /// Determines what happens when user closes the program
        /// </summary>
        private void menuItem_quit_Click(object sender, EventArgs e)
        {
            FormTextpad_FormClosing(sender, e as FormClosingEventArgs);
        }

        /// <summary>
        /// Determines what happens when user attempts to alter the current Font
        /// </summary>
        private void fontToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                DialogResult result = fontDialog.ShowDialog();
                if (result == DialogResult.OK)
                {
                    Font chosenFont = fontDialog.Font;
                    txt_input.SelectionFont = chosenFont;
                    txt_input.SelectionColor = fontDialog.Color;
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, @"Font not a TrueType Font!", MessageBoxButtons.OK,
                    MessageBoxIcon.Exclamation);
            }
        }

        /// <summary>
        /// Upon changing font style, update the current 
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void fontDialog_Apply(object sender, EventArgs e)
        {
            //WARNING: Only TrueType fonts are supported.
            try
            {
                Font chosenFont = fontDialog.Font;
                txt_input.SelectionFont = chosenFont;
                txt_input.SelectionColor = fontDialog.Color;
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, @"Font not a TrueType Font!", MessageBoxButtons.OK,
                    MessageBoxIcon.Exclamation);
            }
        }

        /// <summary>
        /// When user desires to save the file, show dialog and allow user to save wherever requested
        /// </summary>
        private void saveDialog_FileOk(object sender, CancelEventArgs e)
        {
            fileName = saveDialog.FileName;
            String fileExtension = fileName.Substring(saveDialog.FileName.LastIndexOf(".", StringComparison.CurrentCulture) + 1);
            txt_input.SaveFile(fileName,
                fileExtension.Equals("txt", StringComparison.CurrentCultureIgnoreCase)
                    ? RichTextBoxStreamType.PlainText
                    : RichTextBoxStreamType.RichText);
            fileName = saveDialog.FileName.Substring(saveDialog.FileName.LastIndexOf("\\", StringComparison.CurrentCulture) + 1);
            this.Text = fileName + @" - TextEditor";
            saved = true;
            fileLocation = saveDialog.FileName;
        }

        /// <summary>
        /// Show a Save dialog when user wishes to save current document as a file
        /// </summary>
        private void menuItem_saveAs_Click(object sender, EventArgs e)
        {
            saveDialog.ShowDialog();
        }

        /// <summary>
        /// Determines what happens when user selects OK after choosing a file to open from the file dialog
        /// </summary>
        private void openDialog_FileOk(object sender, CancelEventArgs e)
        {
            txt_input.Clear();
            fileName = openDialog.FileName.Substring(saveDialog.FileName.LastIndexOf("\\",
                StringComparison.CurrentCulture) + 1);
            Text = fileName + @" - TextEditor";
            fileLocation = openDialog.FileName;
            saved = true;
            txt_input.Rtf = File.ReadAllText(fileName, Encoding.UTF8);
        }

        /// <summary>
        /// Determines what happens when user creates a New document
        /// </summary>
        private void newToolStripMenuItem_Click(object sender, EventArgs e)
        {
            saved = false;
            txt_input.Clear();
            fileName = "Untitled";
            fileLocation = Convert.ToString(Environment.SpecialFolder.MyDocuments);
            Text = fileName + @" - TextEditor";
            txt_input.ResetFont();
            txt_input.ResetBackColor();
            txt_input.ResetText();
            txt_input.ResetFont();
            txt_input.ResetForeColor();
        }

        /// <summary>
        /// Determines what happens when user clicks on Open menu item
        /// </summary>
        private void menuItem_open_Click(object sender, EventArgs e)
        {
            openDialog.ShowDialog();
        }

        /// <summary>
        /// Determines what happens when user Saves the current file. If the file has not yet been saved, open the save dialog, otherwise overwrite the current contents of the file.
        /// </summary>
        private void menuItem_save_Click(object sender, EventArgs e)
        {
            if (saved)
                txt_input.SaveFile(fileLocation);
            else
            {
                menuItem_saveAs_Click(sender, e);
            }
        }

        /// <summary>
        /// Determines what happens when the user copys the selected text. The text will be transferred(not removed) to the system clipboard.
        /// </summary>
        private void copyToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Clipboard.SetText(txt_input.Text);
        }

        /// <summary>
        /// Determines what happens when the user pastes the text from the system clipboard. The text will be transferred to the current document, i.e. appends the text.
        /// </summary>
        private void pasteToolStripMenuItem_Click(object sender, EventArgs e)
        {
            txt_input.AppendText(Clipboard.GetText());
        }

        /// <summary>
        /// Determines what happens when the user changes the word wrap condition. If word wrap is selected the text will proceed no further than the boundary of the window. Otherwise, the text will proceed to the right off-screen.
        /// </summary>
        private void wordWrapToolStripMenuItem_Click(object sender, EventArgs e)
        {
            bool isWrapped = txt_input.WordWrap;
            txt_input.WordWrap = !isWrapped;
        }

        /// <summary>
        /// Determines what happens when the user wishes to find a String within the current docuemnt.
        /// This method will open a dialog to allow the user to input a String to search for.
        /// </summary>
        private void findToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            txt_input.Focus();
            FindForm form = new FindForm(this);
            form.ShowDialog();
        }

        /// <summary>
        /// Determines what happens when the user wishes to find and then replace a String within the current docuemnt.
        /// This method will open a dialog to allow the user to input a String to search/replace for.
        /// </summary>
        private void replaceToolStripMenuItem_Click(object sender, EventArgs e)
        {
            txt_input.Focus();
            ReplaceForm form = new ReplaceForm(this);
            form.ShowDialog();
        }

        /// <summary>
        /// Determines what happens when the text in the current document is altered in any way.
        /// </summary>
        private void txt_input_TextChanged(object sender, EventArgs e)
        {
            edited = true;
            saved = false;
            int letterCount = txt_input.Text.Length;
            int wordCount = txt_input.Text.TrimStart().Count(o => (o == ' ')) + (txt_input.Text.Length == 0 ? 0 : 1);
            int lineCount = txt_input.Lines.Count();
            int currentLine = txt_input.GetLineFromCharIndex(txt_input.SelectionStart) + 1;

            lbl_count.Text = Convert.ToString(wordCount);
            lbl_count2.Text = Convert.ToString(letterCount);
            lbl_lineCounter.Text = Convert.ToString(lineCount);
            lbl_currentLine.Text = Convert.ToString(currentLine);
        }

        /// <summary>
        /// Determines what happens when the user cuts the selected text to the system clipboard. The cut text is actually removed(transferred) from the document to the system clipboard.
        /// </summary>
        private void cutToolStripMenuItem_Click(object sender, EventArgs e)
        {
            txt_input.Cut();
        }

        /// <summary>
        /// Determines what happens when the user seeks to undo the most the recent action.
        /// </summary>
        private void undoToolStripMenuItem_Click(object sender, EventArgs e)
        {
            txt_input.Undo();
        }

        /// <summary>
        /// Determines what happens when the user seeks to redo the most the recent action.
        /// </summary>
        private void redoToolStripMenuItem_Click(object sender, EventArgs e)
        {
            txt_input.Redo();
        }

        private void ts_print_Click(object sender, EventArgs e)
        {
            printDialog.Document = printDocument;
            DialogResult result = printDialog.ShowDialog();
            if (result == DialogResult.OK)
            {
                printDocument.Print();
            }
        }

        /// <summary>
        /// Determines what happens when the main form being closed by the user(Prior to exiting).
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void FormTextpad_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (!saved && edited)
            {
                DialogResult result =
                    MessageBox.Show(@"You haven't saved since your last edit. Do you wish to save now(Y/N) ?", @"Warning", MessageBoxButtons.YesNoCancel, MessageBoxIcon.Warning);
                switch (result)
                {
                    case DialogResult.Yes:
                        menuItem_saveAs_Click(sender, e);
                        break;
                    case DialogResult.No:
                        Dispose();
                        Close();
                        break;
                }
            }
            else
            {
                Dispose();
                Close();
            }
        }

        private void PrintPage(object sender, PrintPageEventArgs e)
        {
            e.Graphics.DrawString(txt_input.Text, txt_input.Font, new SolidBrush(txt_input.ForeColor), 10, 10);
        }

        /// <summary>
        /// Shows a print preview of the current document
        /// </summary>
        private void ts_printPreview_Click(object sender, EventArgs e)
        {
            printPreviewDialog.Size = new System.Drawing.Size(printPreviewDialog.Size.Width * 3, printPreviewDialog.Size.Height * 3);
            printPreviewDialog.ShowDialog();
        }

        /// <summary>
        /// Shows a page setup dialog to the user for choosing printing options
        /// </summary>
        private void pageSetupToolStripMenuItem_Click(object sender, EventArgs e)
        {
            pageSetupDialog.ShowDialog();
        }



    }
}
