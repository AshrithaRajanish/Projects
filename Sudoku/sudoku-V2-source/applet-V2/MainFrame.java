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

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class MainFrame extends Applet
    implements ActionListener, Runnable
{
		int ctSeconds,ctMinutes,ctHours;
		String time;
		Thread tp;
		static boolean error,te,haveSingleSolution,iscleared;
		public static String emg1,emg2;
		public static String title;
		static int co;
		Grid3x3 grid;
		Button solve,clear,reload,newPuzzle,check;
		public static int values[][] = new int[9][9];
		public int toReload[][];
		Solver solver;
		Generator generator;
		String tempValues[][];
		Panel buttonPanel;

    public MainFrame()
    {
        ctSeconds = -1;
        ctMinutes = 0;
        ctHours = 0;
        time = new String();
        toReload = new int[9][9];
        solver = new Solver();
        generator = new Generator();
        tempValues = new String[9][9];
        iscleared = false;
    }

    public void init()
    {
        te = true;
        haveSingleSolution = true;
        setBackground(Color.lightGray);
        buttonPanel = new Panel();
        emg1 = null;
        emg2 = null;
        title = "Error!!!!!!!";
        grid = new Grid3x3();
        clear = new Button("Clear");
        clear.setName("clear");
        solve = new Button("Solve");
        solve.setName("solve");
        reload = new Button("Reload");
        reload.setName("reload");
        newPuzzle = new Button("New Puzzle");
        newPuzzle.setName("newPuzzle");
        check = new Button("Check");
        check.setName("check");
        reload.addActionListener(this);
        newPuzzle.addActionListener(this);
        solve.addActionListener(this);
        clear.addActionListener(this);
        check.addActionListener(this);
        buttonPanel.add(newPuzzle);
        buttonPanel.add(solve);
        buttonPanel.add(check);
        buttonPanel.add(reload);
        buttonPanel.add(clear);
        Panel panel = new Panel();
        add(panel, "North");
        add(grid);
        add(buttonPanel);
    }

    public void actionPerformed(ActionEvent actionevent)
    {
        Object obj = actionevent.getSource();
        String buttonName = ((Button)obj).getName();
        co = 0;
        if(buttonName == "solve")
        {
            timerStop();
            if(!iscleared)
            {
                iscleared = false;
                for(int i = 0; i < 9; i++)
                {
                    for(int i1 = 0; i1 < 9; i1++)
                        values[i][i1] = toReload[i][i1];

                }

                postAssign2();
            }
            preAssign();
            if(!validation())
            {
                values = solver.solve(values);
                if(verify())
                {
                    postAssign1();
                    emg1 = "Solved it..";
                    if(haveSingleSolution)
                        emg2 = "This puzzle has a unique solution";
                    else
                        emg2 = "This puzzle can have more than one Solution";
                    title = "Sodoku :)";
                    errorMessage(emg1, emg2);
                } else
                {
                    if(co > 12)
                    {
                        emg1 = "Invalid Sudoku..or could not be solved";
                        emg2 = "without Guess . ";
                        title = "Sodoku :)";
                    }
                    errorMessage(emg1, emg2);
                    postAssign1();
                }
            } else
            {
                errorMessage(emg1, emg2);
            }
        }
        if(buttonName == "clear")
        {
            iscleared = true;
            for(int j = 0; j < 9; j++)
            {
                for(int j1 = 0; j1 < 9; j1++)
                    values[j][j1] = 0;

            }

            postAssign2();
            grid.a1();
            timerStop();
            time = "";
            repaint();
        }
        if(buttonName == "newPuzzle")
        {
            iscleared = false;
            haveSingleSolution = true;
            values = generator.f();
            for(int k = 0; k < 9; k++)
            {
                for(int k1 = 0; k1 < 9; k1++)
                    toReload[k][k1] = values[k][k1];

            }

            grid.a1();
            postAssign2();
            if(!validation());
            justDoIt(tempValues);
            timerStart();
        }
        if(buttonName == "reload")
        {
            iscleared = false;
            for(int l = 0; l < 9; l++)
            {
                for(int l1 = 0; l1 < 9; l1++)
                    values[l][l1] = toReload[l][l1];

            }

            grid.a1();
            postAssign2();
            justDoIt(tempValues);
            timerStart();
        }
        if(buttonName == "check")
        {
            te = false;
            preAssign();
            if(!validation())
            {
                if(verify())
                {
                    timerStop();
                    emg1 = "valid Solution";
                    emg2 = "Feel free to send bug reports to my id .\n rajanish_gj@yahoo.com ";
                    title = "Sodoku :)";
                    errorMessage(emg1, emg2);
                } else
                {
                    emg1 = "valid upto here";
                    emg2 = "Complete the puzzle.. ";
                    title = "Sodoku :)";
                    errorMessage(emg1, emg2);
                }
            } else
            {
                errorMessage(emg1, emg2);
            }
        }
    }

    public boolean verify()
    {
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
                if(values[i][j] == 0)
                    co++;

        }

        if(!te && co != 0)
        {
            emg1 = "Blocks are empty..";
            emg2 = "Fill and try check again..";
            te = true;
            return false;
        }
        if(co > 24 && te)
        {
            emg1 = "Insufficient values entered..";
            emg2 = "";
            return false;
        }
        if(co < 25 && co >= 4 && te)
        {
            emg1 = "Needs some guess work,";
            emg2 = "enter guess values then click se again.";
            haveSingleSolution = false;
            return false;
        } else
        {
            return true;
        }
    }

    public void errorMessage(String s, String s1)
    {
        JOptionPane.showMessageDialog(this, s + "\n" + s1, title, -1);
        title = "Error!!!!!!!";
    }

    public boolean validation()
    {
        error = false;
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                try
                {
                    if(tempValues[i][j].equals(""))
                        values[i][j] = 0;
                    else
                        values[i][j] = Integer.parseInt(tempValues[i][j]);
                }
                catch(Exception exception)
                {
                    emg1 = "Found incomaptible value in " + (i + 1) + "th row " + (j + 1) + "th column, ";
                    emg2 = "Please correct it before continuing";
                    error = true;
                }
                if(values[i][j] > 9)
                {
                    emg1 = "Found incomaptible value in " + (i + 1) + "th row " + (j + 1) + "th column,";
                    emg2 = "Please correct it before continuing";
                    error = true;
                }
            }

        }

        if(!error)
            error = chek();
        return error;
    }

    public boolean chek()
    {
        for(int i = 1; i <= 9; i++)
        {
            int counts1 = 0;
            int counts2 = 0;
            int counts3 = 0;
            int counts4 = 0;
            int counts5 = 0;
            int counts6 = 0;
            int counts7 = 0;
            int counts8 = 0;
            int counts9 = 0;
            for(int i3 = 0; i3 < 9; i3++)
            {
                int k2 = 0;
                int l2 = 0;
                for(int j3 = 0; j3 < 9; j3++)
                {
                    if(values[i3][j3] == i)
                        k2++;
                    if(values[j3][i3] == i)
                        l2++;
                    if(i3 <= 2 && j3 <= 2 && values[i3][j3] == i)
                        counts1++;
                    if(i3 <= 2 && j3 <= 5 && j3 >= 3 && values[i3][j3] == i)
                        counts2++;
                    if(i3 <= 2 && j3 >= 6 && values[i3][j3] == i)
                        counts3++;
                    if(i3 <= 5 && i3 >= 3 && j3 <= 2 && values[i3][j3] == i)
                        counts4++;
                    if(i3 <= 5 && i3 >= 3 && j3 <= 5 && j3 >= 3 && values[i3][j3] == i)
                        counts5++;
                    if(i3 <= 5 && i3 >= 3 && j3 >= 6 && values[i3][j3] == i)
                        counts6++;
                    if(i3 >= 6 && j3 <= 2 && values[i3][j3] == i)
                        counts7++;
                    if(i3 >= 6 && j3 <= 5 && j3 >= 3 && values[i3][j3] == i)
                        counts8++;
                    if(i3 >= 6 && j3 >= 6 && values[i3][j3] == i)
                        counts9++;
                }

                if(k2 > 1)
                {
                    emg1 = "You have duplicates of " + i + " in row " + (i3 + 1) + ",";
                    emg2 = "Please correct it before continuing";
                    return true;
                }
                if(l2 > 1)
                {
                    emg1 = "You have duplicates of " + i + " in column " + (i3 + 1) + ",";
                    emg2 = "Please correct it before continuing";
                    return true;
                }
                if(counts1 > 1)
                {
                    emg1 = "You have duplicates of " + i + " in Block 1";
                    emg2 = "Please correct it before continuing";
                    return true;
                }
                if(counts2 > 1)
                {
                    emg1 = "You have duplicates of " + i + " in Block 2";
                    emg2 = "Please correct it before continuing";
                    return true;
                }
                if(counts3 > 1)
                {
                    emg1 = "You have duplicates of " + i + " in Block 3";
                    emg2 = "Please correct it before continuing";
                    return true;
                }
                if(counts4 > 1)
                {
                    emg1 = "You have duplicates of " + i + " in Block 4";
                    emg2 = "Please correct it before continuing";
                    return true;
                }
                if(counts5 > 1)
                {
                    emg1 = "You have duplicates of " + i + " in Block 5";
                    emg2 = "Please correct it before continuing";
                    return true;
                }
                if(counts6 > 1)
                {
                    emg1 = "You have duplicates of " + i + " in Block 6";
                    emg2 = "Please correct it before continuing";
                    return true;
                }
                if(counts7 > 1)
                {
                    emg1 = "You have duplicates of " + i + " in Block 7";
                    emg2 = "Please correct it before continuing";
                    return true;
                }
                if(counts8 > 1)
                {
                    emg1 = "You have duplicates of " + i + " in Block 8";
                    emg2 = "Please correct it before continuing";
                    return true;
                }
                if(counts9 > 1)
                {
                    emg1 = "You have duplicates of " + i + " in Block 9";
                    emg2 = "Please correct it before continuing";
                    return true;
                }
            }

        }

        return false;
    }

    public void postAssign2()
    {
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
                if(values[i][j] == 0)
                    tempValues[i][j] = "";
                else
                    tempValues[i][j] = Integer.toString(values[i][j]);

        }

        grid.pl1.t1.setText(tempValues[0][0]);
        grid.pl1.t2.setText(tempValues[0][1]);
        grid.pl1.t3.setText(tempValues[0][2]);
        grid.pl1.t4.setText(tempValues[0][3]);
        grid.pl1.t5.setText(tempValues[0][4]);
        grid.pl1.t6.setText(tempValues[0][5]);
        grid.pl1.t7.setText(tempValues[0][6]);
        grid.pl1.t8.setText(tempValues[0][7]);
        grid.pl1.t9.setText(tempValues[0][8]);
        
        grid.pl2.t1.setText(tempValues[1][0]);
        grid.pl2.t2.setText(tempValues[1][1]);
        grid.pl2.t3.setText(tempValues[1][2]);
        grid.pl2.t4.setText(tempValues[1][3]);
        grid.pl2.t5.setText(tempValues[1][4]);
        grid.pl2.t6.setText(tempValues[1][5]);
        grid.pl2.t7.setText(tempValues[1][6]);
        grid.pl2.t8.setText(tempValues[1][7]);
        grid.pl2.t9.setText(tempValues[1][8]);
        
        grid.pl3.t1.setText(tempValues[2][0]);
        grid.pl3.t2.setText(tempValues[2][1]);
        grid.pl3.t3.setText(tempValues[2][2]);
        grid.pl3.t4.setText(tempValues[2][3]);
        grid.pl3.t5.setText(tempValues[2][4]);
        grid.pl3.t6.setText(tempValues[2][5]);
        grid.pl3.t7.setText(tempValues[2][6]);
        grid.pl3.t8.setText(tempValues[2][7]);
        grid.pl3.t9.setText(tempValues[2][8]);
        
        grid.pl4.t1.setText(tempValues[3][0]);
        grid.pl4.t2.setText(tempValues[3][1]);
        grid.pl4.t3.setText(tempValues[3][2]);
        grid.pl4.t4.setText(tempValues[3][3]);
        grid.pl4.t5.setText(tempValues[3][4]);
        grid.pl4.t6.setText(tempValues[3][5]);
        grid.pl4.t7.setText(tempValues[3][6]);
        grid.pl4.t8.setText(tempValues[3][7]);
        grid.pl4.t9.setText(tempValues[3][8]);
        
        grid.pl5.t1.setText(tempValues[4][0]);
        grid.pl5.t2.setText(tempValues[4][1]);
        grid.pl5.t3.setText(tempValues[4][2]);
        grid.pl5.t4.setText(tempValues[4][3]);
        grid.pl5.t5.setText(tempValues[4][4]);
        grid.pl5.t6.setText(tempValues[4][5]);
        grid.pl5.t7.setText(tempValues[4][6]);
        grid.pl5.t8.setText(tempValues[4][7]);
        grid.pl5.t9.setText(tempValues[4][8]);
        
        grid.pl6.t1.setText(tempValues[5][0]);
        grid.pl6.t2.setText(tempValues[5][1]);
        grid.pl6.t3.setText(tempValues[5][2]);
        grid.pl6.t4.setText(tempValues[5][3]);
        grid.pl6.t5.setText(tempValues[5][4]);
        grid.pl6.t6.setText(tempValues[5][5]);
        grid.pl6.t7.setText(tempValues[5][6]);
        grid.pl6.t8.setText(tempValues[5][7]);
        grid.pl6.t9.setText(tempValues[5][8]);
        
        grid.pl7.t1.setText(tempValues[6][0]);
        grid.pl7.t2.setText(tempValues[6][1]);
        grid.pl7.t3.setText(tempValues[6][2]);
        grid.pl7.t4.setText(tempValues[6][3]);
        grid.pl7.t5.setText(tempValues[6][4]);
        grid.pl7.t6.setText(tempValues[6][5]);
        grid.pl7.t7.setText(tempValues[6][6]);
        grid.pl7.t8.setText(tempValues[6][7]);
        grid.pl7.t9.setText(tempValues[6][8]);
        
        grid.pl8.t1.setText(tempValues[7][0]);
        grid.pl8.t2.setText(tempValues[7][1]);
        grid.pl8.t3.setText(tempValues[7][2]);
        grid.pl8.t4.setText(tempValues[7][3]);
        grid.pl8.t5.setText(tempValues[7][4]);
        grid.pl8.t6.setText(tempValues[7][5]);
        grid.pl8.t7.setText(tempValues[7][6]);
        grid.pl8.t8.setText(tempValues[7][7]);
        grid.pl8.t9.setText(tempValues[7][8]);
        
        grid.pl9.t1.setText(tempValues[8][0]);
        grid.pl9.t2.setText(tempValues[8][1]);
        grid.pl9.t3.setText(tempValues[8][2]);
        grid.pl9.t4.setText(tempValues[8][3]);
        grid.pl9.t5.setText(tempValues[8][4]);
        grid.pl9.t6.setText(tempValues[8][5]);
        grid.pl9.t7.setText(tempValues[8][6]);
        grid.pl9.t8.setText(tempValues[8][7]);
        grid.pl9.t9.setText(tempValues[8][8]);
    }

    public void postAssign1()
    {
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
                if(values[i][j] == 0)
                    tempValues[i][j] = "";
                else
                    tempValues[i][j] = Integer.toString(values[i][j]);

        }

        grid.pl1.t1.setText(tempValues[0][0]);
        grid.pl1.t2.setText(tempValues[0][1]);
        grid.pl1.t3.setText(tempValues[0][2]);
        grid.pl1.t4.setText(tempValues[1][0]);
        grid.pl1.t5.setText(tempValues[1][1]);
        grid.pl1.t6.setText(tempValues[1][2]);
        grid.pl1.t7.setText(tempValues[2][0]);
        grid.pl1.t8.setText(tempValues[2][1]);
        grid.pl1.t9.setText(tempValues[2][2]);
        
        grid.pl2.t1.setText(tempValues[0][3]);
        grid.pl2.t2.setText(tempValues[0][4]);
        grid.pl2.t3.setText(tempValues[0][5]);
        grid.pl2.t4.setText(tempValues[1][3]);
        grid.pl2.t5.setText(tempValues[1][4]);
        grid.pl2.t6.setText(tempValues[1][5]);
        grid.pl2.t7.setText(tempValues[2][3]);
        grid.pl2.t8.setText(tempValues[2][4]);
        grid.pl2.t9.setText(tempValues[2][5]);
        
        grid.pl3.t1.setText(tempValues[0][6]);
        grid.pl3.t2.setText(tempValues[0][7]);
        grid.pl3.t3.setText(tempValues[0][8]);
        grid.pl3.t4.setText(tempValues[1][6]);
        grid.pl3.t5.setText(tempValues[1][7]);
        grid.pl3.t6.setText(tempValues[1][8]);
        grid.pl3.t7.setText(tempValues[2][6]);
        grid.pl3.t8.setText(tempValues[2][7]);
        grid.pl3.t9.setText(tempValues[2][8]);
        
        grid.pl4.t1.setText(tempValues[3][0]);
        grid.pl4.t2.setText(tempValues[3][1]);
        grid.pl4.t3.setText(tempValues[3][2]);
        grid.pl4.t4.setText(tempValues[4][0]);
        grid.pl4.t5.setText(tempValues[4][1]);
        grid.pl4.t6.setText(tempValues[4][2]);
        grid.pl4.t7.setText(tempValues[5][0]);
        grid.pl4.t8.setText(tempValues[5][1]);
        grid.pl4.t9.setText(tempValues[5][2]);
        
        grid.pl5.t1.setText(tempValues[3][3]);
        grid.pl5.t2.setText(tempValues[3][4]);
        grid.pl5.t3.setText(tempValues[3][5]);
        grid.pl5.t4.setText(tempValues[4][3]);
        grid.pl5.t5.setText(tempValues[4][4]);
        grid.pl5.t6.setText(tempValues[4][5]);
        grid.pl5.t7.setText(tempValues[5][3]);
        grid.pl5.t8.setText(tempValues[5][4]);
        grid.pl5.t9.setText(tempValues[5][5]);
        
        grid.pl6.t1.setText(tempValues[3][6]);
        grid.pl6.t2.setText(tempValues[3][7]);
        grid.pl6.t3.setText(tempValues[3][8]);
        grid.pl6.t4.setText(tempValues[4][6]);
        grid.pl6.t5.setText(tempValues[4][7]);
        grid.pl6.t6.setText(tempValues[4][8]);
        grid.pl6.t7.setText(tempValues[5][6]);
        grid.pl6.t8.setText(tempValues[5][7]);
        grid.pl6.t9.setText(tempValues[5][8]);
        
        grid.pl7.t1.setText(tempValues[6][0]);
        grid.pl7.t2.setText(tempValues[6][1]);
        grid.pl7.t3.setText(tempValues[6][2]);
        grid.pl7.t4.setText(tempValues[7][0]);
        grid.pl7.t5.setText(tempValues[7][1]);
        grid.pl7.t6.setText(tempValues[7][2]);
        grid.pl7.t7.setText(tempValues[8][0]);
        grid.pl7.t8.setText(tempValues[8][1]);
        grid.pl7.t9.setText(tempValues[8][2]);
        
        grid.pl8.t1.setText(tempValues[6][3]);
        grid.pl8.t2.setText(tempValues[6][4]);
        grid.pl8.t3.setText(tempValues[6][5]);
        grid.pl8.t4.setText(tempValues[7][3]);
        grid.pl8.t5.setText(tempValues[7][4]);
        grid.pl8.t6.setText(tempValues[7][5]);
        grid.pl8.t7.setText(tempValues[8][3]);
        grid.pl8.t8.setText(tempValues[8][4]);
        grid.pl8.t9.setText(tempValues[8][5]);
        
        grid.pl9.t1.setText(tempValues[6][6]);
        grid.pl9.t2.setText(tempValues[6][7]);
        grid.pl9.t3.setText(tempValues[6][8]);
        grid.pl9.t4.setText(tempValues[7][6]);
        grid.pl9.t5.setText(tempValues[7][7]);
        grid.pl9.t6.setText(tempValues[7][8]);
        grid.pl9.t7.setText(tempValues[8][6]);
        grid.pl9.t8.setText(tempValues[8][7]);
        grid.pl9.t9.setText(tempValues[8][8]);
    }

    public void preAssign()
    {
        tempValues[0][0] = grid.pl1.t1.getText();
        tempValues[0][1] = grid.pl1.t2.getText();
        tempValues[0][2] = grid.pl1.t3.getText();
        tempValues[1][0] = grid.pl1.t4.getText();
        tempValues[1][1] = grid.pl1.t5.getText();
        tempValues[1][2] = grid.pl1.t6.getText();
        tempValues[2][0] = grid.pl1.t7.getText();
        tempValues[2][1] = grid.pl1.t8.getText();
        tempValues[2][2] = grid.pl1.t9.getText();
        
        tempValues[0][3] = grid.pl2.t1.getText();
        tempValues[0][4] = grid.pl2.t2.getText();
        tempValues[0][5] = grid.pl2.t3.getText();
        tempValues[1][3] = grid.pl2.t4.getText();
        tempValues[1][4] = grid.pl2.t5.getText();
        tempValues[1][5] = grid.pl2.t6.getText();
        tempValues[2][3] = grid.pl2.t7.getText();
        tempValues[2][4] = grid.pl2.t8.getText();
        tempValues[2][5] = grid.pl2.t9.getText();
        
        tempValues[0][6] = grid.pl3.t1.getText();
        tempValues[0][7] = grid.pl3.t2.getText();
        tempValues[0][8] = grid.pl3.t3.getText();
        tempValues[1][6] = grid.pl3.t4.getText();
        tempValues[1][7] = grid.pl3.t5.getText();
        tempValues[1][8] = grid.pl3.t6.getText();
        tempValues[2][6] = grid.pl3.t7.getText();
        tempValues[2][7] = grid.pl3.t8.getText();
        tempValues[2][8] = grid.pl3.t9.getText();
        
        tempValues[3][0] = grid.pl4.t1.getText();
        tempValues[3][1] = grid.pl4.t2.getText();
        tempValues[3][2] = grid.pl4.t3.getText();
        tempValues[4][0] = grid.pl4.t4.getText();
        tempValues[4][1] = grid.pl4.t5.getText();
        tempValues[4][2] = grid.pl4.t6.getText();
        tempValues[5][0] = grid.pl4.t7.getText();
        tempValues[5][1] = grid.pl4.t8.getText();
        tempValues[5][2] = grid.pl4.t9.getText();
        
        tempValues[3][3] = grid.pl5.t1.getText();
        tempValues[3][4] = grid.pl5.t2.getText();
        tempValues[3][5] = grid.pl5.t3.getText();
        tempValues[4][3] = grid.pl5.t4.getText();
        tempValues[4][4] = grid.pl5.t5.getText();
        tempValues[4][5] = grid.pl5.t6.getText();
        tempValues[5][3] = grid.pl5.t7.getText();
        tempValues[5][4] = grid.pl5.t8.getText();
        tempValues[5][5] = grid.pl5.t9.getText();
        
        tempValues[3][6] = grid.pl6.t1.getText();
        tempValues[3][7] = grid.pl6.t2.getText();
        tempValues[3][8] = grid.pl6.t3.getText();
        tempValues[4][6] = grid.pl6.t4.getText();
        tempValues[4][7] = grid.pl6.t5.getText();
        tempValues[4][8] = grid.pl6.t6.getText();
        tempValues[5][6] = grid.pl6.t7.getText();
        tempValues[5][7] = grid.pl6.t8.getText();
        tempValues[5][8] = grid.pl6.t9.getText();
        
        tempValues[6][0] = grid.pl7.t1.getText();
        tempValues[6][1] = grid.pl7.t2.getText();
        tempValues[6][2] = grid.pl7.t3.getText();
        tempValues[7][0] = grid.pl7.t4.getText();
        tempValues[7][1] = grid.pl7.t5.getText();
        tempValues[7][2] = grid.pl7.t6.getText();
        tempValues[8][0] = grid.pl7.t7.getText();
        tempValues[8][1] = grid.pl7.t8.getText();
        tempValues[8][2] = grid.pl7.t9.getText();
        
        tempValues[6][3] = grid.pl8.t1.getText();
        tempValues[6][4] = grid.pl8.t2.getText();
        tempValues[6][5] = grid.pl8.t3.getText();
        tempValues[7][3] = grid.pl8.t4.getText();
        tempValues[7][4] = grid.pl8.t5.getText();
        tempValues[7][5] = grid.pl8.t6.getText();
        tempValues[8][3] = grid.pl8.t7.getText();
        tempValues[8][4] = grid.pl8.t8.getText();
        tempValues[8][5] = grid.pl8.t9.getText();
        
        tempValues[6][6] = grid.pl9.t1.getText();
        tempValues[6][7] = grid.pl9.t2.getText();
        tempValues[6][8] = grid.pl9.t3.getText();
        tempValues[7][6] = grid.pl9.t4.getText();
        tempValues[7][7] = grid.pl9.t5.getText();
        tempValues[7][8] = grid.pl9.t6.getText();
        tempValues[8][6] = grid.pl9.t7.getText();
        tempValues[8][7] = grid.pl9.t8.getText();
        tempValues[8][8] = grid.pl9.t9.getText();
    }

    public void justDoIt(String as[][])
    {
        if(!as[0][0].equals(""))
        {
            grid.pl1.t1.setEditable(false);
            grid.pl1.t1.setForeground(Color.BLUE);
        }
        if(!as[0][1].equals(""))
        {
            grid.pl1.t2.setEditable(false);
            grid.pl1.t2.setForeground(Color.BLUE);
        }
        if(!as[0][2].equals(""))
        {
            grid.pl1.t3.setEditable(false);
            grid.pl1.t3.setForeground(Color.BLUE);
        }
        if(!as[0][3].equals(""))
        {
            grid.pl1.t4.setEditable(false);
            grid.pl1.t4.setForeground(Color.BLUE);
        }
        if(!as[0][4].equals(""))
        {
            grid.pl1.t5.setEditable(false);
            grid.pl1.t5.setForeground(Color.BLUE);
        }
        if(!as[0][5].equals(""))
        {
            grid.pl1.t6.setEditable(false);
            grid.pl1.t6.setForeground(Color.BLUE);
        }
        if(!as[0][6].equals(""))
        {
            grid.pl1.t7.setEditable(false);
            grid.pl1.t7.setForeground(Color.BLUE);
        }
        if(!as[0][7].equals(""))
        {
            grid.pl1.t8.setEditable(false);
            grid.pl1.t8.setForeground(Color.BLUE);
        }
        if(!as[0][8].equals(""))
        {
            grid.pl1.t9.setEditable(false);
            grid.pl1.t9.setForeground(Color.BLUE);
        }
        if(!as[1][0].equals(""))
        {
            grid.pl2.t1.setEditable(false);
            grid.pl2.t1.setForeground(Color.BLUE);
        }
        if(!as[1][1].equals(""))
        {
            grid.pl2.t2.setEditable(false);
            grid.pl2.t2.setForeground(Color.BLUE);
        }
        if(!as[1][2].equals(""))
        {
            grid.pl2.t3.setEditable(false);
            grid.pl2.t3.setForeground(Color.BLUE);
        }
        if(!as[1][3].equals(""))
        {
            grid.pl2.t4.setEditable(false);
            grid.pl2.t4.setForeground(Color.BLUE);
        }
        if(!as[1][4].equals(""))
        {
            grid.pl2.t5.setEditable(false);
            grid.pl2.t5.setForeground(Color.BLUE);
        }
        if(!as[1][5].equals(""))
        {
            grid.pl2.t6.setEditable(false);
            grid.pl2.t6.setForeground(Color.BLUE);
        }
        if(!as[1][6].equals(""))
        {
            grid.pl2.t7.setEditable(false);
            grid.pl2.t7.setForeground(Color.BLUE);
        }
        if(!as[1][7].equals(""))
        {
            grid.pl2.t8.setEditable(false);
            grid.pl2.t8.setForeground(Color.BLUE);
        }
        if(!as[1][8].equals(""))
        {
            grid.pl2.t9.setEditable(false);
            grid.pl2.t9.setForeground(Color.BLUE);
        }
        if(!as[2][0].equals(""))
        {
            grid.pl3.t1.setEditable(false);
            grid.pl3.t1.setForeground(Color.BLUE);
        }
        if(!as[2][1].equals(""))
        {
            grid.pl3.t2.setEditable(false);
            grid.pl3.t2.setForeground(Color.BLUE);
        }
        if(!as[2][2].equals(""))
        {
            grid.pl3.t3.setEditable(false);
            grid.pl3.t3.setForeground(Color.BLUE);
        }
        if(!as[2][3].equals(""))
        {
            grid.pl3.t4.setEditable(false);
            grid.pl3.t4.setForeground(Color.BLUE);
        }
        if(!as[2][4].equals(""))
        {
            grid.pl3.t5.setEditable(false);
            grid.pl3.t5.setForeground(Color.BLUE);
        }
        if(!as[2][5].equals(""))
        {
            grid.pl3.t6.setEditable(false);
            grid.pl3.t6.setForeground(Color.BLUE);
        }
        if(!as[2][6].equals(""))
        {
            grid.pl3.t7.setEditable(false);
            grid.pl3.t7.setForeground(Color.BLUE);
        }
        if(!as[2][7].equals(""))
        {
            grid.pl3.t8.setEditable(false);
            grid.pl3.t8.setForeground(Color.BLUE);
        }
        if(!as[2][8].equals(""))
        {
            grid.pl3.t9.setEditable(false);
            grid.pl3.t9.setForeground(Color.BLUE);
        }
        if(!as[3][0].equals(""))
        {
            grid.pl4.t1.setEditable(false);
            grid.pl4.t1.setForeground(Color.BLUE);
        }
        if(!as[3][1].equals(""))
        {
            grid.pl4.t2.setEditable(false);
            grid.pl4.t2.setForeground(Color.BLUE);
        }
        if(!as[3][2].equals(""))
        {
            grid.pl4.t3.setEditable(false);
            grid.pl4.t3.setForeground(Color.BLUE);
        }
        if(!as[3][3].equals(""))
        {
            grid.pl4.t4.setEditable(false);
            grid.pl4.t4.setForeground(Color.BLUE);
        }
        if(!as[3][4].equals(""))
        {
            grid.pl4.t5.setEditable(false);
            grid.pl4.t5.setForeground(Color.BLUE);
        }
        if(!as[3][5].equals(""))
        {
            grid.pl4.t6.setEditable(false);
            grid.pl4.t6.setForeground(Color.BLUE);
        }
        if(!as[3][6].equals(""))
        {
            grid.pl4.t7.setEditable(false);
            grid.pl4.t7.setForeground(Color.BLUE);
        }
        if(!as[3][7].equals(""))
        {
            grid.pl4.t8.setEditable(false);
            grid.pl4.t8.setForeground(Color.BLUE);
        }
        if(!as[3][8].equals(""))
        {
            grid.pl4.t9.setEditable(false);
            grid.pl4.t9.setForeground(Color.BLUE);
        }
        if(!as[4][0].equals(""))
        {
            grid.pl5.t1.setEditable(false);
            grid.pl5.t1.setForeground(Color.BLUE);
        }
        if(!as[4][1].equals(""))
        {
            grid.pl5.t2.setEditable(false);
            grid.pl5.t2.setForeground(Color.BLUE);
        }
        if(!as[4][2].equals(""))
        {
            grid.pl5.t3.setEditable(false);
            grid.pl5.t3.setForeground(Color.BLUE);
        }
        if(!as[4][3].equals(""))
        {
            grid.pl5.t4.setEditable(false);
            grid.pl5.t4.setForeground(Color.BLUE);
        }
        if(!as[4][4].equals(""))
        {
            grid.pl5.t5.setEditable(false);
            grid.pl5.t5.setForeground(Color.BLUE);
        }
        if(!as[4][5].equals(""))
        {
            grid.pl5.t6.setEditable(false);
            grid.pl5.t6.setForeground(Color.BLUE);
        }
        if(!as[4][6].equals(""))
        {
            grid.pl5.t7.setEditable(false);
            grid.pl5.t7.setForeground(Color.BLUE);
        }
        if(!as[4][7].equals(""))
        {
            grid.pl5.t8.setEditable(false);
            grid.pl5.t8.setForeground(Color.BLUE);
        }
        if(!as[4][8].equals(""))
        {
            grid.pl5.t9.setEditable(false);
            grid.pl5.t9.setForeground(Color.BLUE);
        }
        if(!as[5][0].equals(""))
        {
            grid.pl6.t1.setEditable(false);
            grid.pl6.t1.setForeground(Color.BLUE);
        }
        if(!as[5][1].equals(""))
        {
            grid.pl6.t2.setEditable(false);
            grid.pl6.t2.setForeground(Color.BLUE);
        }
        if(!as[5][2].equals(""))
        {
            grid.pl6.t3.setEditable(false);
            grid.pl6.t3.setForeground(Color.BLUE);
        }
        if(!as[5][3].equals(""))
        {
            grid.pl6.t4.setEditable(false);
            grid.pl6.t4.setForeground(Color.BLUE);
        }
        if(!as[5][4].equals(""))
        {
            grid.pl6.t5.setEditable(false);
            grid.pl6.t5.setForeground(Color.BLUE);
        }
        if(!as[5][5].equals(""))
        {
            grid.pl6.t6.setEditable(false);
            grid.pl6.t6.setForeground(Color.BLUE);
        }
        if(!as[5][6].equals(""))
        {
            grid.pl6.t7.setEditable(false);
            grid.pl6.t7.setForeground(Color.BLUE);
        }
        if(!as[5][7].equals(""))
        {
            grid.pl6.t8.setEditable(false);
            grid.pl6.t8.setForeground(Color.BLUE);
        }
        if(!as[5][8].equals(""))
        {
            grid.pl6.t9.setEditable(false);
            grid.pl6.t9.setForeground(Color.BLUE);
        }
        if(!as[6][0].equals(""))
        {
            grid.pl7.t1.setEditable(false);
            grid.pl7.t1.setForeground(Color.BLUE);
        }
        if(!as[6][1].equals(""))
        {
            grid.pl7.t2.setEditable(false);
            grid.pl7.t2.setForeground(Color.BLUE);
        }
        if(!as[6][2].equals(""))
        {
            grid.pl7.t3.setEditable(false);
            grid.pl7.t3.setForeground(Color.BLUE);
        }
        if(!as[6][3].equals(""))
        {
            grid.pl7.t4.setEditable(false);
            grid.pl7.t4.setForeground(Color.BLUE);
        }
        if(!as[6][4].equals(""))
        {
            grid.pl7.t5.setEditable(false);
            grid.pl7.t5.setForeground(Color.BLUE);
        }
        if(!as[6][5].equals(""))
        {
            grid.pl7.t6.setEditable(false);
            grid.pl7.t6.setForeground(Color.BLUE);
        }
        if(!as[6][6].equals(""))
        {
            grid.pl7.t7.setEditable(false);
            grid.pl7.t7.setForeground(Color.BLUE);
        }
        if(!as[6][7].equals(""))
        {
            grid.pl7.t8.setEditable(false);
            grid.pl7.t8.setForeground(Color.BLUE);
        }
        if(!as[6][8].equals(""))
        {
            grid.pl7.t9.setEditable(false);
            grid.pl7.t9.setForeground(Color.BLUE);
        }
        if(!as[7][0].equals(""))
        {
            grid.pl8.t1.setEditable(false);
            grid.pl8.t1.setForeground(Color.BLUE);
        }
        if(!as[7][1].equals(""))
        {
            grid.pl8.t2.setEditable(false);
            grid.pl8.t2.setForeground(Color.BLUE);
        }
        if(!as[7][2].equals(""))
        {
            grid.pl8.t3.setEditable(false);
            grid.pl8.t3.setForeground(Color.BLUE);
        }
        if(!as[7][3].equals(""))
        {
            grid.pl8.t4.setEditable(false);
            grid.pl8.t4.setForeground(Color.BLUE);
        }
        if(!as[7][4].equals(""))
        {
            grid.pl8.t5.setEditable(false);
            grid.pl8.t5.setForeground(Color.BLUE);
        }
        if(!as[7][5].equals(""))
        {
            grid.pl8.t6.setEditable(false);
            grid.pl8.t6.setForeground(Color.BLUE);
        }
        if(!as[7][6].equals(""))
        {
            grid.pl8.t7.setEditable(false);
            grid.pl8.t7.setForeground(Color.BLUE);
        }
        if(!as[7][7].equals(""))
        {
            grid.pl8.t8.setEditable(false);
            grid.pl8.t8.setForeground(Color.BLUE);
        }
        if(!as[7][8].equals(""))
        {
            grid.pl8.t9.setEditable(false);
            grid.pl8.t9.setForeground(Color.BLUE);
        }
        if(!as[8][0].equals(""))
        {
            grid.pl9.t1.setEditable(false);
            grid.pl9.t1.setForeground(Color.BLUE);
        }
        if(!as[8][1].equals(""))
        {
            grid.pl9.t2.setEditable(false);
            grid.pl9.t2.setForeground(Color.BLUE);
        }
        if(!as[8][2].equals(""))
        {
            grid.pl9.t3.setEditable(false);
            grid.pl9.t3.setForeground(Color.BLUE);
        }
        if(!as[8][3].equals(""))
        {
            grid.pl9.t4.setEditable(false);
            grid.pl9.t4.setForeground(Color.BLUE);
        }
        if(!as[8][4].equals(""))
        {
            grid.pl9.t5.setEditable(false);
            grid.pl9.t5.setForeground(Color.BLUE);
        }
        if(!as[8][5].equals(""))
        {
            grid.pl9.t6.setEditable(false);
            grid.pl9.t6.setForeground(Color.BLUE);
        }
        if(!as[8][6].equals(""))
        {
            grid.pl9.t7.setEditable(false);
            grid.pl9.t7.setForeground(Color.BLUE);
        }
        if(!as[8][7].equals(""))
        {
            grid.pl9.t8.setEditable(false);
            grid.pl9.t8.setForeground(Color.BLUE);
        }
        if(!as[8][8].equals(""))
        {
            grid.pl9.t9.setEditable(false);
            grid.pl9.t9.setForeground(Color.BLUE);
        }
    }

    public void run()
    {
        do
        {
            ctSeconds++;
            if(ctSeconds > 59)
            {
                ctSeconds = 0;
                ctMinutes++;
            }
            if(ctMinutes > 59)
            {
                ctMinutes = 0;
                ctHours++;
            }
            time = String.valueOf(ctHours) + ":" + String.valueOf(ctMinutes) + ":" + String.valueOf(ctSeconds);
            repaint();
            try
            {
                Thread.currentThread();
                Thread.sleep(1000L);
            }
            catch(InterruptedException interruptedexception) { }
        } while(true);
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.RED);
        g.setFont(new Font("Comic Sans MS", 18, 20));
        g.drawString(time, 210, 20);
    }

    public void start()
    {
        values = generator.f();
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
                toReload[i][j] = values[i][j];

        }

        postAssign2();
        justDoIt(tempValues);
        tp = new Thread(this);
        tp.start();
    }

    public void stop()
    {
        timerStop();
    }

    void timerStart()
    {
        try
        {
            tp.stop();
        }
        catch(Exception exception) { }
        tp = new Thread(this);
        tp.start();
        ctSeconds = -1;
        ctMinutes = 0;
        ctHours = 0;
    }

    void timerStop()
    {
        try
        {
            tp.stop();
        }
        catch(Exception exception) { }
        ctSeconds = -1;
        ctMinutes = 0;
        ctHours = 0;
    }

    

}
