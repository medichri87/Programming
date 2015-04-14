namespace Textpad
{
    partial class ReplaceForm
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
            this.btn_replaceAll = new System.Windows.Forms.Button();
            this.btn_replace = new System.Windows.Forms.Button();
            this.btn_cancel = new System.Windows.Forms.Button();
            this.btn_reset = new System.Windows.Forms.Button();
            this.btn_next = new System.Windows.Forms.Button();
            this.cb_exact = new System.Windows.Forms.CheckBox();
            this.txt_replace = new System.Windows.Forms.TextBox();
            this.txt_find = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // btn_replaceAll
            // 
            this.btn_replaceAll.Enabled = false;
            this.btn_replaceAll.Location = new System.Drawing.Point(259, 70);
            this.btn_replaceAll.Name = "btn_replaceAll";
            this.btn_replaceAll.Size = new System.Drawing.Size(75, 23);
            this.btn_replaceAll.TabIndex = 19;
            this.btn_replaceAll.Text = "Replace All";
            this.btn_replaceAll.UseVisualStyleBackColor = true;
            this.btn_replaceAll.Click += new System.EventHandler(this.btn_replaceAll_Click);
            // 
            // btn_replace
            // 
            this.btn_replace.Enabled = false;
            this.btn_replace.Location = new System.Drawing.Point(259, 41);
            this.btn_replace.Name = "btn_replace";
            this.btn_replace.Size = new System.Drawing.Size(75, 23);
            this.btn_replace.TabIndex = 18;
            this.btn_replace.Text = "Replace";
            this.btn_replace.UseVisualStyleBackColor = true;
            this.btn_replace.Click += new System.EventHandler(this.btn_replace_Click);
            // 
            // btn_cancel
            // 
            this.btn_cancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.btn_cancel.Location = new System.Drawing.Point(259, 128);
            this.btn_cancel.Name = "btn_cancel";
            this.btn_cancel.Size = new System.Drawing.Size(75, 23);
            this.btn_cancel.TabIndex = 17;
            this.btn_cancel.Text = "Cancel";
            this.btn_cancel.UseVisualStyleBackColor = true;
            this.btn_cancel.Click += new System.EventHandler(this.btn_cancel_Click);
            // 
            // btn_reset
            // 
            this.btn_reset.Enabled = false;
            this.btn_reset.Location = new System.Drawing.Point(259, 99);
            this.btn_reset.Name = "btn_reset";
            this.btn_reset.Size = new System.Drawing.Size(75, 23);
            this.btn_reset.TabIndex = 16;
            this.btn_reset.Text = "Reset";
            this.btn_reset.UseVisualStyleBackColor = true;
            this.btn_reset.Click += new System.EventHandler(this.btn_reset_Click);
            // 
            // btn_next
            // 
            this.btn_next.Enabled = false;
            this.btn_next.Location = new System.Drawing.Point(259, 12);
            this.btn_next.Name = "btn_next";
            this.btn_next.Size = new System.Drawing.Size(75, 23);
            this.btn_next.TabIndex = 15;
            this.btn_next.Text = "Find Next";
            this.btn_next.UseVisualStyleBackColor = true;
            this.btn_next.Click += new System.EventHandler(this.btn_next_Click);
            // 
            // cb_exact
            // 
            this.cb_exact.AutoSize = true;
            this.cb_exact.Checked = true;
            this.cb_exact.CheckState = System.Windows.Forms.CheckState.Checked;
            this.cb_exact.FlatStyle = System.Windows.Forms.FlatStyle.Popup;
            this.cb_exact.Location = new System.Drawing.Point(92, 128);
            this.cb_exact.Name = "cb_exact";
            this.cb_exact.Size = new System.Drawing.Size(84, 17);
            this.cb_exact.TabIndex = 14;
            this.cb_exact.Text = "Exact Match";
            this.cb_exact.UseVisualStyleBackColor = true;
            // 
            // txt_replace
            // 
            this.txt_replace.Location = new System.Drawing.Point(92, 60);
            this.txt_replace.Multiline = true;
            this.txt_replace.Name = "txt_replace";
            this.txt_replace.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.txt_replace.Size = new System.Drawing.Size(151, 42);
            this.txt_replace.TabIndex = 13;
            // 
            // txt_find
            // 
            this.txt_find.Location = new System.Drawing.Point(93, 12);
            this.txt_find.Multiline = true;
            this.txt_find.Name = "txt_find";
            this.txt_find.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.txt_find.Size = new System.Drawing.Size(150, 42);
            this.txt_find.TabIndex = 12;
            this.txt_find.TextChanged += new System.EventHandler(this.txt_find_TextChanged);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(14, 63);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(72, 13);
            this.label2.TabIndex = 11;
            this.label2.Text = "Replace with:";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(30, 15);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(56, 13);
            this.label1.TabIndex = 10;
            this.label1.Text = "Find what:";
            // 
            // ReplaceForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.CancelButton = this.btn_cancel;
            this.ClientSize = new System.Drawing.Size(351, 162);
            this.Controls.Add(this.btn_replaceAll);
            this.Controls.Add(this.btn_replace);
            this.Controls.Add(this.btn_cancel);
            this.Controls.Add(this.btn_reset);
            this.Controls.Add(this.btn_next);
            this.Controls.Add(this.cb_exact);
            this.Controls.Add(this.txt_replace);
            this.Controls.Add(this.txt_find);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Name = "ReplaceForm";
            this.Text = "Replace";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.OnClosing);
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.OnClosed);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btn_replaceAll;
        private System.Windows.Forms.Button btn_replace;
        private System.Windows.Forms.Button btn_cancel;
        private System.Windows.Forms.Button btn_reset;
        private System.Windows.Forms.Button btn_next;
        private System.Windows.Forms.CheckBox cb_exact;
        private System.Windows.Forms.TextBox txt_replace;
        private System.Windows.Forms.TextBox txt_find;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;


    }
}