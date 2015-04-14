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
    public partial class FindForm : Form
    {
        private int start; //starting index of search
        private int end; //ending index of search
        private bool match; //determine if match has been found from search
        private int index; //search index
        private int count; //count of matches
        private readonly RichTextBox text; //document(RichTextBox) to search through

        public FindForm(FormTextpad form)
        {
            InitializeComponent();
            text = form.Text_Input;
            start = 0;
            end = 0;
            index = 0;
            count = 0;
            match = false;
        }

        /// <summary>
        /// Find and highlight the next occurence of the given String within the TextBox
        /// </summary>
        private void btn_find_Click(object sender, EventArgs e)
        {
            index = text.Find(txt_find.Text, start, cb_exact.Checked ? RichTextBoxFinds.MatchCase : RichTextBoxFinds.None);
            text.SelectAll();
            if (index != -1)
            {
                match = true;
                text.SelectionStart = 0;
                text.SelectionLength = text.Text.Length;
                text.SelectionBackColor = Color.White;
                end = index + txt_find.Text.Length;

                text.SelectionStart = index;
                text.SelectionLength = txt_find.Text.Length;
                text.SelectionBackColor = Color.Yellow;
                start = end;
            }
            else
            {
                MessageBox.Show(match ? @"No more matches can be found!" : @"No matches found!", @"Finished Searching", MessageBoxButtons.OK, MessageBoxIcon.Information);
                btn_reset_Click(sender, e);
            }

        }

        /// <summary>
        /// Determines what happens when the user cancels the form
        /// </summary>
        private void btn_cancel_Click(object sender, EventArgs e)
        {
            ResetProperties();
            Close();
        }

        /// <summary>
        /// Reset the current word search (Sets start index to 0)
        /// </summary>
        private void btn_reset_Click(object sender, EventArgs e)
        {
            ResetProperties();
        }

        /// <summary>
        /// Reset the properties of the current search form
        /// </summary>
        private void ResetProperties()
        {
            match = false;
            start = 0;
            end = 0;
            index = 0;
            count = 0;
            text.SelectionStart = 0;
            text.SelectionLength = text.Text.Length;
            text.BackColor = Color.White;
            text.SelectionBackColor = Color.White;
            text.DeselectAll();
        }

        /// <summary>
        /// Ensure that buttons are inaccessible unless there is text input first
        /// </summary>
        private void OnTextChanged(object sender, EventArgs e)
        {
            if (txt_find.Text.Length > 0)
            {
                btn_reset.Enabled = true;
                btn_find.Enabled = true;
            }
            else
            {
                btn_reset.Enabled = false;
                btn_find.Enabled = false;
            }

        }

        private void OnClosing(object sender, FormClosingEventArgs e)
        {
            ResetProperties();
        }

        /// <summary>
        /// Determines what happens when the user seeks to find all matches of the input String in the current document
        /// </summary>
        private void btn_findAll_Click(object sender, EventArgs e)
        {
            ResetProperties();
            index = text.Find(txt_find.Text, start, cb_exact.Checked ? RichTextBoxFinds.MatchCase : RichTextBoxFinds.None);
            while (index != -1)
            {
                match = true;
                count++;
                end = index + txt_find.Text.Length;
                text.SelectionStart = index;
                text.SelectionLength = txt_find.Text.Length;
                text.SelectionBackColor = Color.Yellow;
                start = end;
                index = text.Find(txt_find.Text, start, cb_exact.Checked ? RichTextBoxFinds.MatchCase : RichTextBoxFinds.None);
            }

            MessageBox.Show(String.Format("Finished searching. {0} matches have been found!", count),
                @"Finished Searching", MessageBoxButtons.OK, MessageBoxIcon.Information);

        }

        /// <summary>
        /// Determines what happens on closing the form
        /// </summary>
        private void OnClose(object sender, FormClosedEventArgs e)
        {
            ResetProperties();
        }

    }
}
