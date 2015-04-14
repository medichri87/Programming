using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Textpad
{
    public partial class ReplaceForm : Form
    {
        private RichTextBox textBox;
        private int start;
        private int end;
        private int index;
        private bool found;
        private int count;

        public ReplaceForm(FormTextpad form)
        {
            InitializeComponent();
            this.textBox = form.Text_Input; //the document(RichTextBox) to search/replace in
            start = 0; //starting index of current search
            end = 0; //ending index of current search 
            index = 0; //occurence index
            found = false; //match found?
            count = 0; //match count
        }

        /// <summary>
        /// On 'Reset' button clicked
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btn_reset_Click(object sender, EventArgs e)
        {
            ResetProperties();
        }

        /// <summary>
        /// Determines what happens when the user moves the search forward by one when 'Next' button is clicked
        /// </summary>
        private void btn_next_Click(object sender, EventArgs e)
        {
            index = textBox.Find(txt_find.Text, start, cb_exact.Checked ? RichTextBoxFinds.MatchCase : RichTextBoxFinds.None);
            if (index != -1)
            {
                found = true;
                textBox.SelectionStart = 0;
                textBox.SelectionLength = textBox.Text.Length;
                textBox.SelectionBackColor = Color.White;
                end = index + txt_find.Text.Length;

                //Highlight next match
                textBox.SelectionStart = index;
                textBox.SelectionLength = txt_find.Text.Length;
                textBox.SelectionBackColor = Color.Yellow;
                start = end;
            }
            else
            {
                MessageBox.Show(found ? @"No more matches can be found!" : @"No matches found!",
                    @"Finished searching", MessageBoxButtons.OK, MessageBoxIcon.Information);
                btn_reset_Click(sender, e);
            }

        }

        /// <summary>
        /// Ensures the button to search/replace is not available until text is found(Length > 0)
        /// </summary>
        private void txt_find_TextChanged(object sender, EventArgs e)
        {
            if (txt_find.Text.Length > 0)
            {
                btn_next.Enabled = true;
                btn_replace.Enabled = true;
                btn_replaceAll.Enabled = true;
                btn_reset.Enabled = true;
            }
            else
            {
                btn_next.Enabled = false;
                btn_replace.Enabled = false;
                btn_replaceAll.Enabled = false;
                btn_reset.Enabled = false;
            }
        }

        /// <summary>
        /// Determines what happens when the user attempts to replace a single instance of the search text within the current document.
        /// </summary>
        private void btn_replace_Click(object sender, EventArgs e)
        {
            if (!found) return;
            textBox.SelectedText = txt_replace.Text;
            btn_next_Click(sender, e);
        }

        /// <summary>
        /// Determines what happens when the user attempts to replace ALL instances of the search text within the current document.
        /// </summary>
        private void btn_replaceAll_Click(object sender, EventArgs e)
        {
            btn_reset_Click(sender, e);
            index = textBox.Text.IndexOf(txt_find.Text, start, StringComparison.CurrentCulture);
            while (index != -1)
            {
                found = true;
                textBox.DeselectAll();
                end = index + txt_find.Text.Length;
                textBox.SelectionStart = index;
                textBox.SelectionLength = txt_find.Text.Length;
                textBox.SelectionBackColor = Color.Yellow;
                textBox.SelectedText = txt_replace.Text;
                start = end;
                index = textBox.Text.IndexOf(txt_find.Text, start, StringComparison.CurrentCulture);
                count++;
            }
            MessageBox.Show(found ? String.Format("All {0} matches have been replaced!", count) : @"No matches have been replaced", @"Finished Replacing", MessageBoxButtons.OK, MessageBoxIcon.Information);
            ResetProperties();
        }

        /// <summary>
        /// Determines what happens when the user cancels the search form
        /// </summary>
        private void btn_cancel_Click(object sender, EventArgs e)
        {
            ResetProperties();
            Close();
        }

        /// <summary>
        /// Determines what happens when the form is closing by user request (Just prior to exit)
        /// </summary>
        private void OnClosing(object sender, FormClosingEventArgs e)
        {
            ResetProperties();
        }

        /// <summary>
        /// Determines what happens when form has been closed
        /// </summary>
        private void OnClosed(object sender, FormClosedEventArgs e)
        {
            ResetProperties();
        }

        /// <summary>
        /// Reset the current search properties
        /// </summary>
        private void ResetProperties()
        {
            found = false;
            start = 0;
            end = 0;
            index = 0;
            textBox.SelectionStart = 0;
            textBox.SelectionLength = textBox.Text.Length;
            textBox.SelectionBackColor = Color.White;
            textBox.DeselectAll();
            count = 0;
        }

    }
}
