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


import java.util.Random;

public class Generator
{
	int lastSquareEnd,extraNumber,numberEachSquare,numberOfNumbers;
	String arr_b,newPuzzle;
	static int puzzle[][];

    public Generator()
    {
    }

    int[][] f()
    {
        String puzzles[] = {
            "h.g.bc.ea.idfa.ci.bf.dgehfe.dih.gcbab.fi.ehg.acdh.dcfabi.geag.edc.ib.fhdah.gbe.ficebf.ci.ad.hggi.ch.df.e.ab",
             "deb.cg.a.ihff.ah.dbi.egccgi.h.fe.bda.gcdf.bihae.a.fb.hd.ec.i.geihg.acfb.dbi.ca.d.hefgge.dbc.fi.haah.f.ie.gdcb",
              "ge.c.h.i.fdabaif.bg.d.echdh.bae.cf.gic.he.fbdig.a.dbageifh.c.ifgca.hb.deb.dh.efg.acicf.e.ia.bhdggia.h.c.d.ebf"
        };
        Random random = new Random();
        puzzle = new int[9][9];
        numberEachSquare = random.nextInt(5);
        String alpha = scramble("abcdefghi");
        newPuzzle = puzzles[random.nextInt(3)];
        lastSquareEnd = 0;
        extraNumber = 0;
        for(int i = 0; i < 9; i++)
        {
            boolean flag = false;
            boolean flag1 = false;
            int l = 9;
            String s1 = "";
            int i1 = 0;
            for(int j1 = lastSquareEnd; j1 < lastSquareEnd + l; j1++)
            {
                if(newPuzzle.charAt(j1) == '.')
                {
                    l++;
                    i1++;
                }
                s1 = s1 + newPuzzle.charAt(j1);
            }

            lastSquareEnd = lastSquareEnd + l;
            numberOfNumbers = numberEachSquare - extraNumber;
            extraNumber = 0;
            if(i1 > numberOfNumbers)
            {
                if(getRandom() > 5)
                    extraNumber = i1 - numberOfNumbers;
                numberOfNumbers = i1;
            }
            arr_b = new String();
            for(int j = 0; j < numberOfNumbers; j++)
            {
                int k = s1.indexOf('.');
                if(k + 1 > 0)
                {
                    s1 = s1.substring(0, k) + s1.substring(k + 1, s1.length());
                } else
                {
                    k = uniqueRandom(arr_b);
                    k--;
                }
                arr_b = arr_b + Integer.toString(k + 1);
                int k1 = alpha.indexOf(s1.charAt(k));
                k++;
                puzzle[i][k - 1] = k1;
            }

        }

        return puzzle;
    }

    String scramble(String s)
    {
        String s1 = "abcdefghi";
        String newOrder = new String();
        newOrder = Integer.toString(getRandom());
        String numbers = "";
        for(int i = 0; i < 9; i++)
        {
            String newNumbers = Integer.toString(uniqueRandom(numbers));
            numbers = numbers + newNumbers;
        }

        for(int j = 0; j < s.length(); j++)
            if(s.charAt(j) == '.' || s.charAt(j) == '*' || s.charAt(j) == 'x' || s.charAt(j) == '_' || s.charAt(j) == '-' || s.charAt(j) == '+')
                newOrder = newOrder + '.';
            else
                try
                {
                    String s5 = Integer.toString(j + 1);
                    newOrder = newOrder + s1.charAt(numbers.indexOf(s5.charAt(0)));
                }
                catch(Exception exception) { }

        return newOrder;
    }

    int uniqueRandom(String s)
    {
        int i = getRandom();
        Random random = new Random();
        for(int j = 0; j < s.length(); j++)
        {
            String s1 = Integer.toString(i);
            if(s.charAt(j) == s1.charAt(0))
            {
                do
                    i = random.nextInt(10);
                while(i < 1 || i > 9);
                j = -1;
            }
        }

        return i;
    }

    int getRandom()
    {
        Random random = new Random();
        int i;
        for(i = random.nextInt(9); i < 1 || i > 9; i = random.nextInt(9));
        return i;
    }

    
}
