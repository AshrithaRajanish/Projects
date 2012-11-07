
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


class DataObject
{

	DataObject()
	{
		possiblevalues = new int[10];
		finalvalue = 0;
		confirm = 0;
	}

	int possiblevalues[];
	int confirm;
	int finalvalue;
}


public class Solver
{

    Solver()
    {
        for(int i = 0; i <= 8; i++)
        {
            for(int j = 0; j <= 8; j++)
                a[i][j] = new DataObject();

        }

    }

    public int[][] solve(int ai[][])
    {
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                a[i][j].finalvalue = ai[i][j];
                if(a[i][j].finalvalue == 0)
                    a[i][j].confirm = 0;
                else
                    a[i][j].confirm = 1;
            }

        }

        for(int k = 0; k < 15; k++)
        {
            rsc();
            toirc(k);
            oop();
        }

        for(int l = 0; l < 9; l++)
        {
            for(int i1 = 0; i1 < 9; i1++)
                ai[l][i1] = a[l][i1].finalvalue;

        }

        return ai;
    }

    void rsc()
    {
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                int ai[] = {
                    0, 1, 2, 3, 4, 5, 6, 7, 8, 9
                };
                if(a[i][j].finalvalue == 0 || a[i][j].confirm == 0)
                {
                    for(int k = 0; k < 9; k++)
                    {
                        if(a[i][k].finalvalue != 0 && j != k)
                        {
                            for(int l = 0; l < 10; l++)
                                if(ai[l] == a[i][k].finalvalue)
                                    ai[l] = 0;

                        }
                        if(a[k][j].finalvalue != 0 && i != k)
                        {
                            for(int i1 = 0; i1 < 10; i1++)
                                if(ai[i1] == a[k][j].finalvalue)
                                    ai[i1] = 0;

                        }
                    }

                    if(i == 0 || i == 1 || i == 2)
                    {
                        if(j == 0 || j == 1 || j == 2)
                        {
                            if(a[0][0].finalvalue != 0 && (i != 0 || j != 0))
                            {
                                for(int j1 = 0; j1 < 10; j1++)
                                    if(ai[j1] == a[0][0].finalvalue)
                                        ai[j1] = 0;

                            }
                            if(a[0][1].finalvalue != 0 && (i != 0 || j != 1))
                            {
                                for(int k1 = 0; k1 < 10; k1++)
                                    if(ai[k1] == a[0][1].finalvalue)
                                        ai[k1] = 0;

                            }
                            if(a[0][2].finalvalue != 0 && (i != 0 || j != 2))
                            {
                                for(int l1 = 0; l1 < 10; l1++)
                                    if(ai[l1] == a[0][2].finalvalue)
                                        ai[l1] = 0;

                            }
                            if(a[1][0].finalvalue != 0 && (i != 1 || j != 0))
                            {
                                for(int i2 = 0; i2 < 10; i2++)
                                    if(ai[i2] == a[1][0].finalvalue)
                                        ai[i2] = 0;

                            }
                            if(a[1][1].finalvalue != 0 && (i != 1 || j != 1))
                            {
                                for(int j2 = 0; j2 < 10; j2++)
                                    if(ai[j2] == a[1][1].finalvalue)
                                        ai[j2] = 0;

                            }
                            if(a[1][2].finalvalue != 0 && (i != 1 || j != 2))
                            {
                                for(int k2 = 0; k2 < 10; k2++)
                                    if(ai[k2] == a[1][2].finalvalue)
                                        ai[k2] = 0;

                            }
                            if(a[2][0].finalvalue != 0 && (i != 2 || j != 0))
                            {
                                for(int l2 = 0; l2 < 10; l2++)
                                    if(ai[l2] == a[2][0].finalvalue)
                                        ai[l2] = 0;

                            }
                            if(a[2][1].finalvalue != 0 && (i != 2 || j != 1))
                            {
                                for(int i3 = 0; i3 < 10; i3++)
                                    if(ai[i3] == a[2][1].finalvalue)
                                        ai[i3] = 0;

                            }
                            if(a[2][2].finalvalue != 0 && (i != 2 || j != 2))
                            {
                                for(int j3 = 0; j3 < 10; j3++)
                                    if(ai[j3] == a[2][2].finalvalue)
                                        ai[j3] = 0;

                            }
                        }
                        if(j == 3 || j == 4 || j == 5)
                        {
                            if(a[0][3].finalvalue != 0 && (i != 0 || j != 3))
                            {
                                for(int k3 = 0; k3 < 10; k3++)
                                    if(ai[k3] == a[0][3].finalvalue)
                                        ai[k3] = 0;

                            }
                            if(a[0][4].finalvalue != 0 && (i != 0 || j != 4))
                            {
                                for(int l3 = 0; l3 < 10; l3++)
                                    if(ai[l3] == a[0][4].finalvalue)
                                        ai[l3] = 0;

                            }
                            if(a[0][5].finalvalue != 0 && (i != 0 || j != 5))
                            {
                                for(int i4 = 0; i4 < 10; i4++)
                                    if(ai[i4] == a[0][5].finalvalue)
                                        ai[i4] = 0;

                            }
                            if(a[1][3].finalvalue != 0 && (i != 1 || j != 3))
                            {
                                for(int j4 = 0; j4 < 10; j4++)
                                    if(ai[j4] == a[1][3].finalvalue)
                                        ai[j4] = 0;

                            }
                            if(a[1][4].finalvalue != 0 && (i != 1 || j != 4))
                            {
                                for(int k4 = 0; k4 < 10; k4++)
                                    if(ai[k4] == a[1][4].finalvalue)
                                        ai[k4] = 0;

                            }
                            if(a[1][5].finalvalue != 0 && (i != 1 || j != 5))
                            {
                                for(int l4 = 0; l4 < 10; l4++)
                                    if(ai[l4] == a[1][5].finalvalue)
                                        ai[l4] = 0;

                            }
                            if(a[2][3].finalvalue != 0 && (i != 2 || j != 3))
                            {
                                for(int i5 = 0; i5 < 10; i5++)
                                    if(ai[i5] == a[2][3].finalvalue)
                                        ai[i5] = 0;

                            }
                            if(a[2][4].finalvalue != 0 && (i != 2 || j != 4))
                            {
                                for(int j5 = 0; j5 < 10; j5++)
                                    if(ai[j5] == a[2][4].finalvalue)
                                        ai[j5] = 0;

                            }
                            if(a[2][5].finalvalue != 0 && (i != 2 || j != 5))
                            {
                                for(int k5 = 0; k5 < 10; k5++)
                                    if(ai[k5] == a[2][5].finalvalue)
                                        ai[k5] = 0;

                            }
                        }
                        if(j == 6 || j == 7 || j == 8)
                        {
                            if(a[0][6].finalvalue != 0 && (i != 0 || j != 6))
                            {
                                for(int l5 = 0; l5 < 10; l5++)
                                    if(ai[l5] == a[0][6].finalvalue)
                                        ai[l5] = 0;

                            }
                            if(a[0][7].finalvalue != 0 && (i != 0 || j != 7))
                            {
                                for(int i6 = 0; i6 < 10; i6++)
                                    if(ai[i6] == a[0][7].finalvalue)
                                        ai[i6] = 0;

                            }
                            if(a[0][8].finalvalue != 0 && (i != 0 || j != 8))
                            {
                                for(int j6 = 0; j6 < 10; j6++)
                                    if(ai[j6] == a[0][8].finalvalue)
                                        ai[j6] = 0;

                            }
                            if(a[1][6].finalvalue != 0 && (i != 1 || j != 6))
                            {
                                for(int k6 = 0; k6 < 10; k6++)
                                    if(ai[k6] == a[1][6].finalvalue)
                                        ai[k6] = 0;

                            }
                            if(a[1][7].finalvalue != 0 && (i != 1 || j != 7))
                            {
                                for(int l6 = 0; l6 < 10; l6++)
                                    if(ai[l6] == a[1][7].finalvalue)
                                        ai[l6] = 0;

                            }
                            if(a[1][8].finalvalue != 0 && (i != 1 || j != 8))
                            {
                                for(int i7 = 0; i7 < 10; i7++)
                                    if(ai[i7] == a[1][8].finalvalue)
                                        ai[i7] = 0;

                            }
                            if(a[2][6].finalvalue != 0 && (i != 2 || j != 6))
                            {
                                for(int j7 = 0; j7 < 10; j7++)
                                    if(ai[j7] == a[2][6].finalvalue)
                                        ai[j7] = 0;

                            }
                            if(a[2][7].finalvalue != 0 && (i != 2 || j != 7))
                            {
                                for(int k7 = 0; k7 < 10; k7++)
                                    if(ai[k7] == a[2][7].finalvalue)
                                        ai[k7] = 0;

                            }
                            if(a[2][8].finalvalue != 0 && (i != 2 || j != 8))
                            {
                                for(int l7 = 0; l7 < 10; l7++)
                                    if(ai[l7] == a[2][8].finalvalue)
                                        ai[l7] = 0;

                            }
                        }
                    }
                    if(i == 3 || i == 4 || i == 5)
                    {
                        if(j == 0 || j == 1 || j == 2)
                        {
                            if(a[3][0].finalvalue != 0 && (i != 3 || j != 0))
                            {
                                for(int i8 = 0; i8 < 10; i8++)
                                    if(ai[i8] == a[3][0].finalvalue)
                                        ai[i8] = 0;

                            }
                            if(a[3][1].finalvalue != 0 && (i != 3 || j != 1))
                            {
                                for(int j8 = 0; j8 < 10; j8++)
                                    if(ai[j8] == a[3][1].finalvalue)
                                        ai[j8] = 0;

                            }
                            if(a[3][2].finalvalue != 0 && (i != 3 || j != 2))
                            {
                                for(int k8 = 0; k8 < 10; k8++)
                                    if(ai[k8] == a[3][2].finalvalue)
                                        ai[k8] = 0;

                            }
                            if(a[4][0].finalvalue != 0 && (i != 4 || j != 0))
                            {
                                for(int l8 = 0; l8 < 10; l8++)
                                    if(ai[l8] == a[4][0].finalvalue)
                                        ai[l8] = 0;

                            }
                            if(a[4][1].finalvalue != 0 && (i != 4 || j != 1))
                            {
                                for(int i9 = 0; i9 < 10; i9++)
                                    if(ai[i9] == a[4][1].finalvalue)
                                        ai[i9] = 0;

                            }
                            if(a[4][2].finalvalue != 0 && (i != 4 || j != 2))
                            {
                                for(int j9 = 0; j9 < 10; j9++)
                                    if(ai[j9] == a[4][2].finalvalue)
                                        ai[j9] = 0;

                            }
                            if(a[5][0].finalvalue != 0 && (i != 5 || j != 0))
                            {
                                for(int k9 = 0; k9 < 10; k9++)
                                    if(ai[k9] == a[5][0].finalvalue)
                                        ai[k9] = 0;

                            }
                            if(a[5][1].finalvalue != 0 && (i != 5 || j != 1))
                            {
                                for(int l9 = 0; l9 < 10; l9++)
                                    if(ai[l9] == a[5][1].finalvalue)
                                        ai[l9] = 0;

                            }
                            if(a[5][2].finalvalue != 0 && (i != 5 || j != 2))
                            {
                                for(int i10 = 0; i10 < 10; i10++)
                                    if(ai[i10] == a[5][2].finalvalue)
                                        ai[i10] = 0;

                            }
                        }
                        if(j == 3 || j == 4 || j == 5)
                        {
                            if(a[3][3].finalvalue != 0 && (i != 3 || j != 3))
                            {
                                for(int j10 = 0; j10 < 10; j10++)
                                    if(ai[j10] == a[3][3].finalvalue)
                                        ai[j10] = 0;

                            }
                            if(a[3][4].finalvalue != 0 && (i != 3 || j != 4))
                            {
                                for(int k10 = 0; k10 < 10; k10++)
                                    if(ai[k10] == a[3][4].finalvalue)
                                        ai[k10] = 0;

                            }
                            if(a[3][5].finalvalue != 0 && (i != 0 || j != 5))
                            {
                                for(int l10 = 0; l10 < 10; l10++)
                                    if(ai[l10] == a[3][5].finalvalue)
                                        ai[l10] = 0;

                            }
                            if(a[4][3].finalvalue != 0 && (i != 4 || j != 3))
                            {
                                for(int i11 = 0; i11 < 10; i11++)
                                    if(ai[i11] == a[4][3].finalvalue)
                                        ai[i11] = 0;

                            }
                            if(a[4][4].finalvalue != 0 && (i != 4 || j != 4))
                            {
                                for(int j11 = 0; j11 < 10; j11++)
                                    if(ai[j11] == a[4][4].finalvalue)
                                        ai[j11] = 0;

                            }
                            if(a[4][5].finalvalue != 0 && (i != 4 || j != 5))
                            {
                                for(int k11 = 0; k11 < 10; k11++)
                                    if(ai[k11] == a[4][5].finalvalue)
                                        ai[k11] = 0;

                            }
                            if(a[5][3].finalvalue != 0 && (i != 5 || j != 3))
                            {
                                for(int l11 = 0; l11 < 10; l11++)
                                    if(ai[l11] == a[5][3].finalvalue)
                                        ai[l11] = 0;

                            }
                            if(a[5][4].finalvalue != 0 && (i != 5 || j != 4))
                            {
                                for(int i12 = 0; i12 < 10; i12++)
                                    if(ai[i12] == a[5][4].finalvalue)
                                        ai[i12] = 0;

                            }
                            if(a[5][5].finalvalue != 0 && (i != 5 || j != 5))
                            {
                                for(int j12 = 0; j12 < 10; j12++)
                                    if(ai[j12] == a[5][5].finalvalue)
                                        ai[j12] = 0;

                            }
                        }
                        if(j == 6 || j == 7 || j == 8)
                        {
                            if(a[3][6].finalvalue != 0 && (i != 3 || j != 6))
                            {
                                for(int k12 = 0; k12 < 10; k12++)
                                    if(ai[k12] == a[3][6].finalvalue)
                                        ai[k12] = 0;

                            }
                            if(a[3][7].finalvalue != 0 && (i != 3 || j != 7))
                            {
                                for(int l12 = 0; l12 < 10; l12++)
                                    if(ai[l12] == a[3][7].finalvalue)
                                        ai[l12] = 0;

                            }
                            if(a[3][8].finalvalue != 0 && (i != 3 || j != 8))
                            {
                                for(int i13 = 0; i13 < 10; i13++)
                                    if(ai[i13] == a[3][8].finalvalue)
                                        ai[i13] = 0;

                            }
                            if(a[4][6].finalvalue != 0 && (i != 4 || j != 6))
                            {
                                for(int j13 = 0; j13 < 10; j13++)
                                    if(ai[j13] == a[4][6].finalvalue)
                                        ai[j13] = 0;

                            }
                            if(a[4][7].finalvalue != 0 && (i != 4 || j != 7))
                            {
                                for(int k13 = 0; k13 < 10; k13++)
                                    if(ai[k13] == a[4][7].finalvalue)
                                        ai[k13] = 0;

                            }
                            if(a[4][8].finalvalue != 0 && (i != 4 || j != 8))
                            {
                                for(int l13 = 0; l13 < 10; l13++)
                                    if(ai[l13] == a[4][8].finalvalue)
                                        ai[l13] = 0;

                            }
                            if(a[5][6].finalvalue != 0 && (i != 5 || j != 6))
                            {
                                for(int i14 = 0; i14 < 10; i14++)
                                    if(ai[i14] == a[5][6].finalvalue)
                                        ai[i14] = 0;

                            }
                            if(a[5][7].finalvalue != 0 && (i != 5 || j != 7))
                            {
                                for(int j14 = 0; j14 < 10; j14++)
                                    if(ai[j14] == a[5][7].finalvalue)
                                        ai[j14] = 0;

                            }
                            if(a[5][8].finalvalue != 0 && (i != 5 || j != 8))
                            {
                                for(int k14 = 0; k14 < 10; k14++)
                                    if(ai[k14] == a[5][8].finalvalue)
                                        ai[k14] = 0;

                            }
                        }
                    }
                    if(i == 6 || i == 7 || i == 8)
                    {
                        if(j == 0 || j == 1 || j == 2)
                        {
                            if(a[6][0].finalvalue != 0 && (i != 6 || j != 0))
                            {
                                for(int l14 = 0; l14 < 10; l14++)
                                    if(ai[l14] == a[6][0].finalvalue)
                                        ai[l14] = 0;

                            }
                            if(a[6][1].finalvalue != 0 && (i != 6 || j != 1))
                            {
                                for(int i15 = 0; i15 < 10; i15++)
                                    if(ai[i15] == a[6][1].finalvalue)
                                        ai[i15] = 0;

                            }
                            if(a[6][2].finalvalue != 0 && (i != 6 || j != 2))
                            {
                                for(int j15 = 0; j15 < 10; j15++)
                                    if(ai[j15] == a[6][2].finalvalue)
                                        ai[j15] = 0;

                            }
                            if(a[7][0].finalvalue != 0 && (i != 7 || j != 0))
                            {
                                for(int k15 = 0; k15 < 10; k15++)
                                    if(ai[k15] == a[7][0].finalvalue)
                                        ai[k15] = 0;

                            }
                            if(a[7][1].finalvalue != 0 && (i != 7 || j != 1))
                            {
                                for(int l15 = 0; l15 < 10; l15++)
                                    if(ai[l15] == a[7][1].finalvalue)
                                        ai[l15] = 0;

                            }
                            if(a[7][2].finalvalue != 0 && (i != 7 || j != 2))
                            {
                                for(int i16 = 0; i16 < 10; i16++)
                                    if(ai[i16] == a[7][2].finalvalue)
                                        ai[i16] = 0;

                            }
                            if(a[8][0].finalvalue != 0 && (i != 8 || j != 0))
                            {
                                for(int j16 = 0; j16 < 10; j16++)
                                    if(ai[j16] == a[8][0].finalvalue)
                                        ai[j16] = 0;

                            }
                            if(a[8][1].finalvalue != 0 && (i != 8 || j != 1))
                            {
                                for(int k16 = 0; k16 < 10; k16++)
                                    if(ai[k16] == a[8][1].finalvalue)
                                        ai[k16] = 0;

                            }
                            if(a[8][2].finalvalue != 0 && (i != 8 || j != 2))
                            {
                                for(int l16 = 0; l16 < 10; l16++)
                                    if(ai[l16] == a[8][2].finalvalue)
                                        ai[l16] = 0;

                            }
                        }
                        if(j == 3 || j == 4 || j == 5)
                        {
                            if(a[6][3].finalvalue != 0 && (i != 6 || j != 3))
                            {
                                for(int i17 = 0; i17 < 10; i17++)
                                    if(ai[i17] == a[6][3].finalvalue)
                                        ai[i17] = 0;

                            }
                            if(a[6][4].finalvalue != 0 && (i != 6 || j != 4))
                            {
                                for(int j17 = 0; j17 < 10; j17++)
                                    if(ai[j17] == a[6][4].finalvalue)
                                        ai[j17] = 0;

                            }
                            if(a[6][5].finalvalue != 0 && (i != 6 || j != 5))
                            {
                                for(int k17 = 0; k17 < 10; k17++)
                                    if(ai[k17] == a[6][5].finalvalue)
                                        ai[k17] = 0;

                            }
                            if(a[7][3].finalvalue != 0 && (i != 7 || j != 3))
                            {
                                for(int l17 = 0; l17 < 10; l17++)
                                    if(ai[l17] == a[7][3].finalvalue)
                                        ai[l17] = 0;

                            }
                            if(a[7][4].finalvalue != 0 && (i != 7 || j != 4))
                            {
                                for(int i18 = 0; i18 < 10; i18++)
                                    if(ai[i18] == a[7][4].finalvalue)
                                        ai[i18] = 0;

                            }
                            if(a[7][5].finalvalue != 0 && (i != 7 || j != 5))
                            {
                                for(int j18 = 0; j18 < 10; j18++)
                                    if(ai[j18] == a[7][5].finalvalue)
                                        ai[j18] = 0;

                            }
                            if(a[8][3].finalvalue != 0 && (i != 8 || j != 3))
                            {
                                for(int k18 = 0; k18 < 10; k18++)
                                    if(ai[k18] == a[8][3].finalvalue)
                                        ai[k18] = 0;

                            }
                            if(a[8][4].finalvalue != 0 && (i != 8 || j != 4))
                            {
                                for(int l18 = 0; l18 < 10; l18++)
                                    if(ai[l18] == a[8][4].finalvalue)
                                        ai[l18] = 0;

                            }
                            if(a[8][5].finalvalue != 0 && (i != 8 || j != 5))
                            {
                                for(int i19 = 0; i19 < 10; i19++)
                                    if(ai[i19] == a[8][5].finalvalue)
                                        ai[i19] = 0;

                            }
                        }
                        if(j == 6 || j == 7 || j == 8)
                        {
                            if(a[6][6].finalvalue != 0 && (i != 6 || j != 6))
                            {
                                for(int j19 = 0; j19 < 10; j19++)
                                    if(ai[j19] == a[6][6].finalvalue)
                                        ai[j19] = 0;

                            }
                            if(a[6][7].finalvalue != 0 && (i != 6 || j != 7))
                            {
                                for(int k19 = 0; k19 < 10; k19++)
                                    if(ai[k19] == a[6][7].finalvalue)
                                        ai[k19] = 0;

                            }
                            if(a[6][8].finalvalue != 0 && (i != 6 || j != 8))
                            {
                                for(int l19 = 0; l19 < 10; l19++)
                                    if(ai[l19] == a[6][8].finalvalue)
                                        ai[l19] = 0;

                            }
                            if(a[7][6].finalvalue != 0 && (i != 7 || j != 6))
                            {
                                for(int i20 = 0; i20 < 10; i20++)
                                    if(ai[i20] == a[7][6].finalvalue)
                                        ai[i20] = 0;

                            }
                            if(a[7][7].finalvalue != 0 && (i != 7 || j != 7))
                            {
                                for(int j20 = 0; j20 < 10; j20++)
                                    if(ai[j20] == a[7][7].finalvalue)
                                        ai[j20] = 0;

                            }
                            if(a[7][8].finalvalue != 0 && (i != 7 || j != 8))
                            {
                                for(int k20 = 0; k20 < 10; k20++)
                                    if(ai[k20] == a[7][8].finalvalue)
                                        ai[k20] = 0;

                            }
                            if(a[8][6].finalvalue != 0 && (i != 8 || j != 6))
                            {
                                for(int l20 = 0; l20 < 10; l20++)
                                    if(ai[l20] == a[8][6].finalvalue)
                                        ai[l20] = 0;

                            }
                            if(a[8][7].finalvalue != 0 && (i != 8 || j != 7))
                            {
                                for(int i21 = 0; i21 < 10; i21++)
                                    if(ai[i21] == a[8][7].finalvalue)
                                        ai[i21] = 0;

                            }
                            if(a[8][8].finalvalue != 0 && (i != 8 || j != 8))
                            {
                                for(int j21 = 0; j21 < 10; j21++)
                                    if(ai[j21] == a[8][8].finalvalue)
                                        ai[j21] = 0;

                            }
                        }
                    }
                }
                a[i][j].possiblevalues = ai;
            }

        }

    }

    void toirc(int i)
    {
        if(i % 3 == 0)
        {
            for(int j = 1; j <= 9; j++)
            {
                for(int l = 0; l < 9; l++)
                {
                    int k1 = 0;
                    int k2 = 0;
                    for(int l3 = 0; l3 < 9; l3++)
                        if(a[l][l3].possiblevalues[j] == j && a[l][l3].confirm == 0)
                        {
                            k1++;
                            k2 = l3;
                        }

                    if(k1 == 1)
                    {
                        a[l][k2].finalvalue = j;
                        a[l][k2].confirm = 1;
                    }
                }

            }

        }
        if(i % 3 == 1)
        {
            for(int k = 1; k <= 9; k++)
            {
                for(int i1 = 0; i1 < 9; i1++)
                {
                    int l1 = 0;
                    int l2 = 0;
                    for(int i4 = 0; i4 < 9; i4++)
                        if(a[i4][i1].possiblevalues[k] == k && a[i4][i1].confirm == 0)
                        {
                            l1++;
                            l2 = i4;
                        }

                    if(l1 == 1)
                    {
                        a[l2][i1].finalvalue = k;
                        a[l2][i1].confirm = 1;
                    }
                }

            }

        }
        if(i % 3 == 2)
        {
            for(int j1 = 1; j1 <= 9; j1++)
            {
                int i2 = 0;
                int i3 = 0;
                int j4 = 0;
                for(int j5 = 0; j5 < 3; j5++)
                {
                    for(int k6 = 0; k6 < 3; k6++)
                        if(a[j5][k6].possiblevalues[j1] == j1 && a[j5][k6].confirm == 0)
                        {
                            i2++;
                            i3 = j5;
                            j4 = k6;
                        }

                }

                if(i2 == 1)
                {
                    a[i3][j4].finalvalue = j1;
                    a[i3][j4].confirm = 1;
                }
            }

            for(int j2 = 1; j2 <= 9; j2++)
            {
                int j3 = 0;
                int k4 = 0;
                int k5 = 0;
                for(int l6 = 0; l6 < 3; l6++)
                {
                    for(int i8 = 3; i8 < 6; i8++)
                        if(a[l6][i8].possiblevalues[j2] == j2 && a[l6][i8].confirm == 0)
                        {
                            j3++;
                            k4 = l6;
                            k5 = i8;
                        }

                }

                if(j3 == 1)
                {
                    a[k4][k5].finalvalue = j2;
                    a[k4][k5].confirm = 1;
                }
            }

            for(int k3 = 1; k3 <= 9; k3++)
            {
                int l4 = 0;
                int l5 = 0;
                int i7 = 0;
                for(int j8 = 0; j8 < 3; j8++)
                {
                    for(int k9 = 6; k9 < 9; k9++)
                        if(a[j8][k9].possiblevalues[k3] == k3 && a[j8][k9].confirm == 0)
                        {
                            l4++;
                            l5 = j8;
                            i7 = k9;
                        }

                }

                if(l4 == 1)
                {
                    a[l5][i7].finalvalue = k3;
                    a[l5][i7].confirm = 1;
                }
            }

            for(int i5 = 1; i5 <= 9; i5++)
            {
                int i6 = 0;
                int j7 = 0;
                int k8 = 0;
                for(int l9 = 3; l9 < 6; l9++)
                {
                    for(int i11 = 0; i11 < 3; i11++)
                        if(a[l9][i11].possiblevalues[i5] == i5 && a[l9][i11].confirm == 0)
                        {
                            i6++;
                            j7 = l9;
                            k8 = i11;
                        }

                }

                if(i6 == 1)
                {
                    a[j7][k8].finalvalue = i5;
                    a[j7][k8].confirm = 1;
                }
            }

            for(int j6 = 1; j6 <= 9; j6++)
            {
                int k7 = 0;
                int l8 = 0;
                int i10 = 0;
                for(int j11 = 3; j11 < 6; j11++)
                {
                    for(int k12 = 3; k12 < 6; k12++)
                        if(a[j11][k12].possiblevalues[j6] == j6 && a[j11][k12].confirm == 0)
                        {
                            k7++;
                            l8 = j11;
                            i10 = k12;
                        }

                }

                if(k7 == 1)
                {
                    a[l8][i10].finalvalue = j6;
                    a[l8][i10].confirm = 1;
                }
            }

            for(int l7 = 1; l7 <= 9; l7++)
            {
                int i9 = 0;
                int j10 = 0;
                int k11 = 0;
                for(int l12 = 3; l12 < 6; l12++)
                {
                    for(int l13 = 6; l13 < 9; l13++)
                        if(a[l12][l13].possiblevalues[l7] == l7 && a[l12][l13].confirm == 0)
                        {
                            i9++;
                            j10 = l12;
                            k11 = l13;
                        }

                }

                if(i9 == 1)
                {
                    a[j10][k11].finalvalue = l7;
                    a[j10][k11].confirm = 1;
                }
            }

            for(int j9 = 1; j9 <= 9; j9++)
            {
                int k10 = 0;
                int l11 = 0;
                int i13 = 0;
                for(int i14 = 6; i14 < 9; i14++)
                {
                    for(int l14 = 0; l14 < 3; l14++)
                        if(a[i14][l14].possiblevalues[j9] == j9 && a[i14][l14].confirm == 0)
                        {
                            k10++;
                            l11 = i14;
                            i13 = l14;
                        }

                }

                if(k10 == 1)
                {
                    a[l11][i13].finalvalue = j9;
                    a[l11][i13].confirm = 1;
                }
            }

            for(int l10 = 1; l10 <= 9; l10++)
            {
                int i12 = 0;
                int j13 = 0;
                int j14 = 0;
                for(int i15 = 6; i15 < 9; i15++)
                {
                    for(int k15 = 3; k15 < 6; k15++)
                        if(a[i15][k15].possiblevalues[l10] == l10 && a[i15][k15].confirm == 0)
                        {
                            i12++;
                            j13 = i15;
                            j14 = k15;
                        }

                }

                if(i12 == 1)
                {
                    a[j13][j14].finalvalue = l10;
                    a[j13][j14].confirm = 1;
                }
            }

            for(int j12 = 1; j12 <= 9; j12++)
            {
                int k13 = 0;
                int k14 = 0;
                int j15 = 0;
                for(int l15 = 6; l15 < 9; l15++)
                {
                    for(int i16 = 6; i16 < 9; i16++)
                        if(a[l15][i16].possiblevalues[j12] == j12 && a[l15][i16].confirm == 0)
                        {
                            k13++;
                            k14 = l15;
                            j15 = i16;
                        }

                }

                if(k13 == 1)
                {
                    a[k14][j15].finalvalue = j12;
                    a[k14][j15].confirm = 1;
                }
            }

        }
    }

    void oop()
    {
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                int k = 0;
                int l = 0;
                for(int i1 = 0; i1 < 10; i1++)
                    if(a[i][j].possiblevalues[i1] != 0)
                    {
                        l = a[i][j].possiblevalues[i1];
                        k++;
                    }

                if(k == 1)
                {
                    a[i][j].finalvalue = l;
                    a[i][j].confirm = 1;
                }
            }

        }

    }

    static DataObject a[][] = new DataObject[9][9];

}
