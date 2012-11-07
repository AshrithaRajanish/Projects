/******************************************************************************************

   Sudoku.java - japanese number puzzle solver and generator
   Version 2.0
   www.geocities.com/rajanish_gj/program.htm
      
   Copyright (c) 2005 Rajanish G J
   rajanish_gj@yahoo.com
   All Rights Reserved.

   Permission is hereby granted, free of charge, to any person obtaining a copy 
   of this software and associated documentation files (the "Software"), to
   deal in the Software without restriction, including without limitation the
   rights to use, copy, modify, merge, publish, distribute, and/or sell copies
   of the Software, and to permit persons to whom the Software is furnished to
   do so, provided that the above copyright notice and this permission notice
   appear in all copies of the Software and that both the above copyright
   notice and this permission notice appear in supporting documentation.

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT OF THIRD PARTY RIGHTS.
   IN NO EVENT SHALL THE COPYRIGHT HOLDER INCLUDED IN THIS NOTICE BE LIABLE FOR
   ANY CLAIM, OR ANY SPECIAL INDIRECT OR CONSEQUENTIAL DAMAGES, OR ANY DAMAGES
   WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION
   OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
   CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.

   Except as contained in this notice, the name of the copyright holder shall
   not be used in advertising or otherwise to promote the sale, use or other
   dealings in this Software without prior written authorization of the
   copyright holder.

******************************************************************************************/


import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;


public class Sudoku extends Frame
    implements ActionListener
{
	public static boolean abab;
	   static MainFrame a;
	   static int wh;
	   MenuBar mb;
	   Menu file;
	   Menu help;
	   MenuItem new_p;
	   MenuItem exit;
	   MenuItem rules;
	   MenuItem report;
	   String ruls;
	   String reps;

    Sudoku()
    {
        super("Sudoku");
        ruls = "Fill in the grid so that every row,\nevery column, and every 3x3 box contains \n the digits 1 through 9. \nYou can also use this software to solve \n other Sudoku puzzles just enter the values \n in appropriate position and then click solve.\n \n NOTE: Original Sudoku puzzles will \nhave unique solution, You will get only \n Original sudoku puzzles using this software";
        reps = "The program uses the random function \n to  generate the new puzzles and hence \n there is no chance or very rare chance \nof repetation of puzzles.. \n Web version of this software is available in \n\nwww.geocities.com/rajanish_gj/program.htm \n\nFree to download \n Enjoy Sudoku.. \n\nAny Reports regarding this software  \nplease send to: rajanish_gj@yahoo.com";
        setBounds(300, 220, 305, 330);
        a = new MainFrame();
        mb = new MenuBar();
        file = new Menu("File");
        new_p = new MenuItem("Puzzle");
        exit = new MenuItem("Exit");
        file.add(new_p);
        file.addSeparator();
        file.add(exit);
        Menu menu = new Menu("Run");
        MenuItem menuitem = new MenuItem("Solve");
        menuitem.disable();
        menu.add(menuitem);
        help = new Menu("Help");
        rules = new MenuItem("Game Rules");
        report = new MenuItem("Bug Reports");
        help.add(rules);
        help.addSeparator();
        help.add(report);
        mb.add(file);
        mb.add(menu);
        mb.add(help);
        setMenuBar(mb);
        setResizable(false);
        WinAdapter w = new WinAdapter(this);
        addWindowListener(w);
        add(a);
        wh = 0;
        a.init();
        setVisible(true);
        new_p.disable();
        new_p.setName("new_p");
        exit.setName("exit");
        rules.setName("rules");
        report.setName("report");
        new_p.addActionListener(this);
        exit.addActionListener(this);
        rules.addActionListener(this);
        report.addActionListener(this);
    }

    public void actionPerformed(ActionEvent actionevent)
    {
        Object obj = actionevent.getSource();
        String s = ((MenuItem)obj).getName();
        if(s == "exit")
            System.exit(0);
        if(s == "rules")
            JOptionPane.showMessageDialog(this, ruls, "Rules", 1);
        if(s == "report")
            JOptionPane.showMessageDialog(this, reps, "Report", 1);
    }

   
    
	public static void main(String args[])
	   {
		   Sudoku sudokuWindow = new Sudoku();
	   }
}

class WinAdapter extends WindowAdapter
{

	public WinAdapter(Frame frame)
	{
		mw = frame;
	}

	public void windowClosing(WindowEvent windowevent)
	{
		System.exit(0);
	}

	Frame mw;
}
