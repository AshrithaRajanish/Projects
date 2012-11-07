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


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Font;
import java.awt.TextField;

class Grid extends Panel
{
	public TextField t1,t2,t3,t4,t5,t6,t7,t8,t9;
	Grid(Color color)
	{
		setLayout(new GridLayout(3, 3));
		Font font = new Font("TimesNewRoman", 1, 14);
		setFont(font);
		t1 = new TextField("", 1);
		add(t1);
		t1.setBackground(color);
		t2 = new TextField("", 1);
		add(t2);
		t2.setBackground(color);
		t3 = new TextField("", 1);
		add(t3);
		t3.setBackground(color);
		t4 = new TextField("", 1);
		add(t4);
		t4.setBackground(color);
		t5 = new TextField("", 1);
		add(t5);
		t5.setBackground(color);
		t6 = new TextField("", 1);
		add(t6);
		t6.setBackground(color);
		t7 = new TextField("", 1);
		add(t7);
		t7.setBackground(color);
		t8 = new TextField("", 1);
		add(t8);
		t8.setBackground(color);
		t9 = new TextField("", 1);
		add(t9);
		t9.setBackground(color);
	}

	public void a1()
	{
		t1.setEditable(true);
		t1.setForeground(Color.BLACK);
		t2.setEditable(true);
		t2.setForeground(Color.BLACK);
		t3.setEditable(true);
		t3.setForeground(Color.BLACK);
		t4.setEditable(true);
		t4.setForeground(Color.BLACK);
		t5.setEditable(true);
		t5.setForeground(Color.BLACK);
		t6.setEditable(true);
		t6.setForeground(Color.BLACK);
		t7.setEditable(true);
		t7.setForeground(Color.BLACK);
		t8.setEditable(true);
		t8.setForeground(Color.BLACK);
		t9.setEditable(true);
		t9.setForeground(Color.BLACK);
	}   
}

public class Grid3x3 extends Panel
{
	public Grid pl1,pl2,pl3,pl4,pl5,pl6,pl7,pl8,pl9;
	
	Grid3x3()
	{
		setLayout(new GridLayout(3, 3, 3, 3));
		pl1 = new Grid(Color.lightGray);
		pl2 = new Grid(Color.pink);
		pl3 = new Grid(Color.lightGray);
		pl4 = new Grid(Color.pink);
		pl5 = new Grid(Color.lightGray);
		pl6 = new Grid(Color.pink);
		pl7 = new Grid(Color.lightGray);
		pl8 = new Grid(Color.pink);
		pl9 = new Grid(Color.lightGray);
		add(pl1);
		add(pl2);
		add(pl3);
		add(pl4);
		add(pl5);
		add(pl6);
		add(pl7);
		add(pl8);
		add(pl9);
	}

	public void a1()
	{
		pl1.a1();
		pl2.a1();
		pl3.a1();
		pl4.a1();
		pl5.a1();
		pl6.a1();
		pl7.a1();
		pl8.a1();
		pl9.a1();
	}
  
}