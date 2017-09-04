import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;


public class Solution {

 private static final int MODULO_BY = 1000000007;
 private static final BigInteger bigIntModBy = new BigInteger(""+MODULO_BY);

 @SuppressWarnings("deprecation")
 private static int[] getIntArray(DataInputStream in, int[] ret) {
  try {
   String[] line = in.readLine().split(" ");
   for (int i = 0; i < line.length; i++)
    ret[i] = Integer.parseInt(line[i]);
   return ret;
  } catch (IOException e) {
   e.printStackTrace();
   return null;
  }
 }

 private static void getSlope(int[] a, int[] b, int[] slope)
 {
  slope[0] = b[0] - a[0];
  slope[1] = b[1] - a[1];
  if (slope[0]<0 && slope[1]<0)
  {
   slope[0]*=-1;
   slope[1]*=-1;
  }
  else if (slope[0]>=0 && slope[1]<0)
  {
   slope[0]*=-1;
   slope[1]*=-1;
  }
  if (slope[0]<0&&slope[1]<0)
  {
   slope[0] = Math.abs(slope[0]);
   slope[1] = Math.abs(slope[1]);
  }
  if (slope[0] == 0 && slope[1] == 0 )
   return;
  OUTER:
  while (true)
  {
   if (slope[0] == 0)
   {
    slope[1] = 1;
    return;
   }
   if (slope[1] == 0)
   {
    slope[0] = 1;
    return;
   }
   if (slope[0] == 1 || slope[1] == 1 )
    return;
   int to = Math.min(Math.abs(slope[0]), Math.abs(slope[1]));
   for (int i = to; i > 1; --i)
   {
    if (slope[0] == 1 || slope[1] == 1 )
     return;
    if (slope[0]%i != 0 || slope[1]%i != 0)
     continue;
    slope[0] /= i;
    slope[1] /= i;
    continue OUTER;
   }
   return;
  }
 }

 private static int[][] s_on_bit_lookup =
 {
  {-1,-1,-1,-1,-1,-1,-1,-1,},//0
  {0,-1,-1,-1,-1,-1,-1,-1,},//1
  {1,-1,-1,-1,-1,-1,-1,-1,},//2
  {0,1,-1,-1,-1,-1,-1,-1,},//3
  {2,-1,-1,-1,-1,-1,-1,-1,},//4
  {0,2,-1,-1,-1,-1,-1,-1,},//5
  {1,2,-1,-1,-1,-1,-1,-1,},//6
  {0,1,2,-1,-1,-1,-1,-1,},//7
  {3,-1,-1,-1,-1,-1,-1,-1,},//8
  {0,3,-1,-1,-1,-1,-1,-1,},//9
  {1,3,-1,-1,-1,-1,-1,-1,},//10
  {0,1,3,-1,-1,-1,-1,-1,},//11
  {2,3,-1,-1,-1,-1,-1,-1,},//12
  {0,2,3,-1,-1,-1,-1,-1,},//13
  {1,2,3,-1,-1,-1,-1,-1,},//14
  {0,1,2,3,-1,-1,-1,-1,},//15
  {4,-1,-1,-1,-1,-1,-1,-1,},//16
  {0,4,-1,-1,-1,-1,-1,-1,},//17
  {1,4,-1,-1,-1,-1,-1,-1,},//18
  {0,1,4,-1,-1,-1,-1,-1,},//19
  {2,4,-1,-1,-1,-1,-1,-1,},//20
  {0,2,4,-1,-1,-1,-1,-1,},//21
  {1,2,4,-1,-1,-1,-1,-1,},//22
  {0,1,2,4,-1,-1,-1,-1,},//23
  {3,4,-1,-1,-1,-1,-1,-1,},//24
  {0,3,4,-1,-1,-1,-1,-1,},//25
  {1,3,4,-1,-1,-1,-1,-1,},//26
  {0,1,3,4,-1,-1,-1,-1,},//27
  {2,3,4,-1,-1,-1,-1,-1,},//28
  {0,2,3,4,-1,-1,-1,-1,},//29
  {1,2,3,4,-1,-1,-1,-1,},//30
  {0,1,2,3,4,-1,-1,-1,},//31
  {5,-1,-1,-1,-1,-1,-1,-1,},//32
  {0,5,-1,-1,-1,-1,-1,-1,},//33
  {1,5,-1,-1,-1,-1,-1,-1,},//34
  {0,1,5,-1,-1,-1,-1,-1,},//35
  {2,5,-1,-1,-1,-1,-1,-1,},//36
  {0,2,5,-1,-1,-1,-1,-1,},//37
  {1,2,5,-1,-1,-1,-1,-1,},//38
  {0,1,2,5,-1,-1,-1,-1,},//39
  {3,5,-1,-1,-1,-1,-1,-1,},//40
  {0,3,5,-1,-1,-1,-1,-1,},//41
  {1,3,5,-1,-1,-1,-1,-1,},//42
  {0,1,3,5,-1,-1,-1,-1,},//43
  {2,3,5,-1,-1,-1,-1,-1,},//44
  {0,2,3,5,-1,-1,-1,-1,},//45
  {1,2,3,5,-1,-1,-1,-1,},//46
  {0,1,2,3,5,-1,-1,-1,},//47
  {4,5,-1,-1,-1,-1,-1,-1,},//48
  {0,4,5,-1,-1,-1,-1,-1,},//49
  {1,4,5,-1,-1,-1,-1,-1,},//50
  {0,1,4,5,-1,-1,-1,-1,},//51
  {2,4,5,-1,-1,-1,-1,-1,},//52
  {0,2,4,5,-1,-1,-1,-1,},//53
  {1,2,4,5,-1,-1,-1,-1,},//54
  {0,1,2,4,5,-1,-1,-1,},//55
  {3,4,5,-1,-1,-1,-1,-1,},//56
  {0,3,4,5,-1,-1,-1,-1,},//57
  {1,3,4,5,-1,-1,-1,-1,},//58
  {0,1,3,4,5,-1,-1,-1,},//59
  {2,3,4,5,-1,-1,-1,-1,},//60
  {0,2,3,4,5,-1,-1,-1,},//61
  {1,2,3,4,5,-1,-1,-1,},//62
  {0,1,2,3,4,5,-1,-1,},//63
  {6,-1,-1,-1,-1,-1,-1,-1,},//64
  {0,6,-1,-1,-1,-1,-1,-1,},//65
  {1,6,-1,-1,-1,-1,-1,-1,},//66
  {0,1,6,-1,-1,-1,-1,-1,},//67
  {2,6,-1,-1,-1,-1,-1,-1,},//68
  {0,2,6,-1,-1,-1,-1,-1,},//69
  {1,2,6,-1,-1,-1,-1,-1,},//70
  {0,1,2,6,-1,-1,-1,-1,},//71
  {3,6,-1,-1,-1,-1,-1,-1,},//72
  {0,3,6,-1,-1,-1,-1,-1,},//73
  {1,3,6,-1,-1,-1,-1,-1,},//74
  {0,1,3,6,-1,-1,-1,-1,},//75
  {2,3,6,-1,-1,-1,-1,-1,},//76
  {0,2,3,6,-1,-1,-1,-1,},//77
  {1,2,3,6,-1,-1,-1,-1,},//78
  {0,1,2,3,6,-1,-1,-1,},//79
  {4,6,-1,-1,-1,-1,-1,-1,},//80
  {0,4,6,-1,-1,-1,-1,-1,},//81
  {1,4,6,-1,-1,-1,-1,-1,},//82
  {0,1,4,6,-1,-1,-1,-1,},//83
  {2,4,6,-1,-1,-1,-1,-1,},//84
  {0,2,4,6,-1,-1,-1,-1,},//85
  {1,2,4,6,-1,-1,-1,-1,},//86
  {0,1,2,4,6,-1,-1,-1,},//87
  {3,4,6,-1,-1,-1,-1,-1,},//88
  {0,3,4,6,-1,-1,-1,-1,},//89
  {1,3,4,6,-1,-1,-1,-1,},//90
  {0,1,3,4,6,-1,-1,-1,},//91
  {2,3,4,6,-1,-1,-1,-1,},//92
  {0,2,3,4,6,-1,-1,-1,},//93
  {1,2,3,4,6,-1,-1,-1,},//94
  {0,1,2,3,4,6,-1,-1,},//95
  {5,6,-1,-1,-1,-1,-1,-1,},//96
  {0,5,6,-1,-1,-1,-1,-1,},//97
  {1,5,6,-1,-1,-1,-1,-1,},//98
  {0,1,5,6,-1,-1,-1,-1,},//99
  {2,5,6,-1,-1,-1,-1,-1,},//100
  {0,2,5,6,-1,-1,-1,-1,},//101
  {1,2,5,6,-1,-1,-1,-1,},//102
  {0,1,2,5,6,-1,-1,-1,},//103
  {3,5,6,-1,-1,-1,-1,-1,},//104
  {0,3,5,6,-1,-1,-1,-1,},//105
  {1,3,5,6,-1,-1,-1,-1,},//106
  {0,1,3,5,6,-1,-1,-1,},//107
  {2,3,5,6,-1,-1,-1,-1,},//108
  {0,2,3,5,6,-1,-1,-1,},//109
  {1,2,3,5,6,-1,-1,-1,},//110
  {0,1,2,3,5,6,-1,-1,},//111
  {4,5,6,-1,-1,-1,-1,-1,},//112
  {0,4,5,6,-1,-1,-1,-1,},//113
  {1,4,5,6,-1,-1,-1,-1,},//114
  {0,1,4,5,6,-1,-1,-1,},//115
  {2,4,5,6,-1,-1,-1,-1,},//116
  {0,2,4,5,6,-1,-1,-1,},//117
  {1,2,4,5,6,-1,-1,-1,},//118
  {0,1,2,4,5,6,-1,-1,},//119
  {3,4,5,6,-1,-1,-1,-1,},//120
  {0,3,4,5,6,-1,-1,-1,},//121
  {1,3,4,5,6,-1,-1,-1,},//122
  {0,1,3,4,5,6,-1,-1,},//123
  {2,3,4,5,6,-1,-1,-1,},//124
  {0,2,3,4,5,6,-1,-1,},//125
  {1,2,3,4,5,6,-1,-1,},//126
  {0,1,2,3,4,5,6,-1,},//127
  {7,-1,-1,-1,-1,-1,-1,-1,},//128
  {0,7,-1,-1,-1,-1,-1,-1,},//129
  {1,7,-1,-1,-1,-1,-1,-1,},//130
  {0,1,7,-1,-1,-1,-1,-1,},//131
  {2,7,-1,-1,-1,-1,-1,-1,},//132
  {0,2,7,-1,-1,-1,-1,-1,},//133
  {1,2,7,-1,-1,-1,-1,-1,},//134
  {0,1,2,7,-1,-1,-1,-1,},//135
  {3,7,-1,-1,-1,-1,-1,-1,},//136
  {0,3,7,-1,-1,-1,-1,-1,},//137
  {1,3,7,-1,-1,-1,-1,-1,},//138
  {0,1,3,7,-1,-1,-1,-1,},//139
  {2,3,7,-1,-1,-1,-1,-1,},//140
  {0,2,3,7,-1,-1,-1,-1,},//141
  {1,2,3,7,-1,-1,-1,-1,},//142
  {0,1,2,3,7,-1,-1,-1,},//143
  {4,7,-1,-1,-1,-1,-1,-1,},//144
  {0,4,7,-1,-1,-1,-1,-1,},//145
  {1,4,7,-1,-1,-1,-1,-1,},//146
  {0,1,4,7,-1,-1,-1,-1,},//147
  {2,4,7,-1,-1,-1,-1,-1,},//148
  {0,2,4,7,-1,-1,-1,-1,},//149
  {1,2,4,7,-1,-1,-1,-1,},//150
  {0,1,2,4,7,-1,-1,-1,},//151
  {3,4,7,-1,-1,-1,-1,-1,},//152
  {0,3,4,7,-1,-1,-1,-1,},//153
  {1,3,4,7,-1,-1,-1,-1,},//154
  {0,1,3,4,7,-1,-1,-1,},//155
  {2,3,4,7,-1,-1,-1,-1,},//156
  {0,2,3,4,7,-1,-1,-1,},//157
  {1,2,3,4,7,-1,-1,-1,},//158
  {0,1,2,3,4,7,-1,-1,},//159
  {5,7,-1,-1,-1,-1,-1,-1,},//160
  {0,5,7,-1,-1,-1,-1,-1,},//161
  {1,5,7,-1,-1,-1,-1,-1,},//162
  {0,1,5,7,-1,-1,-1,-1,},//163
  {2,5,7,-1,-1,-1,-1,-1,},//164
  {0,2,5,7,-1,-1,-1,-1,},//165
  {1,2,5,7,-1,-1,-1,-1,},//166
  {0,1,2,5,7,-1,-1,-1,},//167
  {3,5,7,-1,-1,-1,-1,-1,},//168
  {0,3,5,7,-1,-1,-1,-1,},//169
  {1,3,5,7,-1,-1,-1,-1,},//170
  {0,1,3,5,7,-1,-1,-1,},//171
  {2,3,5,7,-1,-1,-1,-1,},//172
  {0,2,3,5,7,-1,-1,-1,},//173
  {1,2,3,5,7,-1,-1,-1,},//174
  {0,1,2,3,5,7,-1,-1,},//175
  {4,5,7,-1,-1,-1,-1,-1,},//176
  {0,4,5,7,-1,-1,-1,-1,},//177
  {1,4,5,7,-1,-1,-1,-1,},//178
  {0,1,4,5,7,-1,-1,-1,},//179
  {2,4,5,7,-1,-1,-1,-1,},//180
  {0,2,4,5,7,-1,-1,-1,},//181
  {1,2,4,5,7,-1,-1,-1,},//182
  {0,1,2,4,5,7,-1,-1,},//183
  {3,4,5,7,-1,-1,-1,-1,},//184
  {0,3,4,5,7,-1,-1,-1,},//185
  {1,3,4,5,7,-1,-1,-1,},//186
  {0,1,3,4,5,7,-1,-1,},//187
  {2,3,4,5,7,-1,-1,-1,},//188
  {0,2,3,4,5,7,-1,-1,},//189
  {1,2,3,4,5,7,-1,-1,},//190
  {0,1,2,3,4,5,7,-1,},//191
  {6,7,-1,-1,-1,-1,-1,-1,},//192
  {0,6,7,-1,-1,-1,-1,-1,},//193
  {1,6,7,-1,-1,-1,-1,-1,},//194
  {0,1,6,7,-1,-1,-1,-1,},//195
  {2,6,7,-1,-1,-1,-1,-1,},//196
  {0,2,6,7,-1,-1,-1,-1,},//197
  {1,2,6,7,-1,-1,-1,-1,},//198
  {0,1,2,6,7,-1,-1,-1,},//199
  {3,6,7,-1,-1,-1,-1,-1,},//200
  {0,3,6,7,-1,-1,-1,-1,},//201
  {1,3,6,7,-1,-1,-1,-1,},//202
  {0,1,3,6,7,-1,-1,-1,},//203
  {2,3,6,7,-1,-1,-1,-1,},//204
  {0,2,3,6,7,-1,-1,-1,},//205
  {1,2,3,6,7,-1,-1,-1,},//206
  {0,1,2,3,6,7,-1,-1,},//207
  {4,6,7,-1,-1,-1,-1,-1,},//208
  {0,4,6,7,-1,-1,-1,-1,},//209
  {1,4,6,7,-1,-1,-1,-1,},//210
  {0,1,4,6,7,-1,-1,-1,},//211
  {2,4,6,7,-1,-1,-1,-1,},//212
  {0,2,4,6,7,-1,-1,-1,},//213
  {1,2,4,6,7,-1,-1,-1,},//214
  {0,1,2,4,6,7,-1,-1,},//215
  {3,4,6,7,-1,-1,-1,-1,},//216
  {0,3,4,6,7,-1,-1,-1,},//217
  {1,3,4,6,7,-1,-1,-1,},//218
  {0,1,3,4,6,7,-1,-1,},//219
  {2,3,4,6,7,-1,-1,-1,},//220
  {0,2,3,4,6,7,-1,-1,},//221
  {1,2,3,4,6,7,-1,-1,},//222
  {0,1,2,3,4,6,7,-1,},//223
  {5,6,7,-1,-1,-1,-1,-1,},//224
  {0,5,6,7,-1,-1,-1,-1,},//225
  {1,5,6,7,-1,-1,-1,-1,},//226
  {0,1,5,6,7,-1,-1,-1,},//227
  {2,5,6,7,-1,-1,-1,-1,},//228
  {0,2,5,6,7,-1,-1,-1,},//229
  {1,2,5,6,7,-1,-1,-1,},//230
  {0,1,2,5,6,7,-1,-1,},//231
  {3,5,6,7,-1,-1,-1,-1,},//232
  {0,3,5,6,7,-1,-1,-1,},//233
  {1,3,5,6,7,-1,-1,-1,},//234
  {0,1,3,5,6,7,-1,-1,},//235
  {2,3,5,6,7,-1,-1,-1,},//236
  {0,2,3,5,6,7,-1,-1,},//237
  {1,2,3,5,6,7,-1,-1,},//238
  {0,1,2,3,5,6,7,-1,},//239
  {4,5,6,7,-1,-1,-1,-1,},//240
  {0,4,5,6,7,-1,-1,-1,},//241
  {1,4,5,6,7,-1,-1,-1,},//242
  {0,1,4,5,6,7,-1,-1,},//243
  {2,4,5,6,7,-1,-1,-1,},//244
  {0,2,4,5,6,7,-1,-1,},//245
  {1,2,4,5,6,7,-1,-1,},//246
  {0,1,2,4,5,6,7,-1,},//247
  {3,4,5,6,7,-1,-1,-1,},//248
  {0,3,4,5,6,7,-1,-1,},//249
  {1,3,4,5,6,7,-1,-1,},//250
  {0,1,3,4,5,6,7,-1,},//251
  {2,3,4,5,6,7,-1,-1,},//252
  {0,2,3,4,5,6,7,-1,},//253
  {1,2,3,4,5,6,7,-1,},//254
  {0,1,2,3,4,5,6,7,},//255
 };
 
 private static byte[] s_bit_counts =
 { 
  0,1,1,2,1,2,2,3,1,2,2,3,2,3,3,4,1,2,2,3,2,3,3,4,2,3,3,4,3,4,4,5,
  1,2,2,3,2,3,3,4,2,3,3,4,3,4,4,5,2,3,3,4,3,4,4,5,3,4,4,5,4,5,5,6,
  1,2,2,3,2,3,3,4,2,3,3,4,3,4,4,5,2,3,3,4,3,4,4,5,3,4,4,5,4,5,5,6,
  2,3,3,4,3,4,4,5,3,4,4,5,4,5,5,6,3,4,4,5,4,5,5,6,4,5,5,6,5,6,6,7,
  1,2,2,3,2,3,3,4,2,3,3,4,3,4,4,5,2,3,3,4,3,4,4,5,3,4,4,5,4,5,5,6,
  2,3,3,4,3,4,4,5,3,4,4,5,4,5,5,6,3,4,4,5,4,5,5,6,4,5,5,6,5,6,6,7,
  2,3,3,4,3,4,4,5,3,4,4,5,4,5,5,6,3,4,4,5,4,5,5,6,4,5,5,6,5,6,6,7,
  3,4,4,5,4,5,5,6,4,5,5,6,5,6,6,7,4,5,5,6,5,6,6,7,5,6,6,7,6,7,7,8 
 };

 private static int[][] s_non_colinear_results =
 {
  {1,1,1},
  {1,1,1},
  {1,1,1},
  {2,6,3},
  {2,6,3},
  {3,90,15},
  {3,90,15},
  {4, 2520,105},
  {4, 2520,105},
  {5, 113400,945},
  {5, 113400,945},
  {6, 7484400,10395},
  {6, 7484400,10395},
  {7, 681080400,135135},
  {7, 681080400,135135},
  {8, 729647433, 2027025},
  {8, 729647433, 2027025},
 };
 
 private static int[] s_shifted = {1,2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384,32768};
 private static int[] s_permutations = {1,1,2,6,24,120,720,5040,40320,362880,3628800,39916800,479001600};

 private static int[][] s_1x1masks = {
  {1,3,5,9,17,33,65,129,257,513,1025,2049,4097,8193,16385,32769,},
  {3,2,6,10,18,34,66,130,258,514,1026,2050,4098,8194,16386,32770,},
  {5,6,4,12,20,36,68,132,260,516,1028,2052,4100,8196,16388,32772,},
  {9,10,12,8,24,40,72,136,264,520,1032,2056,4104,8200,16392,32776,},
  {17,18,20,24,16,48,80,144,272,528,1040,2064,4112,8208,16400,32784,},
  {33,34,36,40,48,32,96,160,288,544,1056,2080,4128,8224,16416,32800,},
  {65,66,68,72,80,96,64,192,320,576,1088,2112,4160,8256,16448,32832,},
  {129,130,132,136,144,160,192,128,384,640,1152,2176,4224,8320,16512,32896,},
  {257,258,260,264,272,288,320,384,256,768,1280,2304,4352,8448,16640,33024,},
  {513,514,516,520,528,544,576,640,768,512,1536,2560,4608,8704,16896,33280,},
  {1025,1026,1028,1032,1040,1056,1088,1152,1280,1536,1024,3072,5120,9216,17408,33792,},
  {2049,2050,2052,2056,2064,2080,2112,2176,2304,2560,3072,2048,6144,10240,18432,34816,},
  {4097,4098,4100,4104,4112,4128,4160,4224,4352,4608,5120,6144,4096,12288,20480,36864,},
  {8193,8194,8196,8200,8208,8224,8256,8320,8448,8704,9216,10240,12288,8192,24576,40960,},
  {16385,16386,16388,16392,16400,16416,16448,16512,16640,16896,17408,18432,20480,24576,16384,49152,},
  {32769,32770,32772,32776,32784,32800,32832,32896,33024,33280,33792,34816,36864,40960,49152,32768,},
 };

 static private int getOnCount(int srt, int cnt)
 {
  int ret = 0;
  for (int i = 0; i < cnt; i++) {
   ret += s_bit_counts[srt&0xff];
   srt>>=8;
  }
  return ret;
 }

 static private int getOnIndeces(int[] ret, int val, int cnt)
 {
  int len = 0;
  for (int i = 0, idx=0; i < cnt; ++i, ++idx, val >>= 8) {
   int bte = (val&0xff);
   int[] onbits = s_on_bit_lookup[bte];
   for (int k = 0; k<onbits.length && onbits[k] >= 0; k++) {
    int cov_idx = idx*8+onbits[k];
    ret[len++] = cov_idx;
   }
  }
  return len;
 }

 private static int getsubsets(int numPts, int[][] points, int[][] slopes, byte[] hash, short[][] sets, int[] sets_sizes, byte[][] colinear, byte TRUTH, int[] res)
 {
  int largest_subset_idx = -1; 
  Arrays.fill(sets_sizes, 0);
  int[] indeces = new int[16];
  for (int i = 0; i < numPts; i++)
  {
   for (int j = 0; j < numPts; j++) {
    getSlope(points[i], points[j], slopes[j]);
    slopes[j][2] = j;
   }
   final int key = i;
   Arrays.sort(slopes, 0, numPts, new Comparator<int[]>()
   {
    public int compare(int[] slp1, int[] slp2) {
     if (slp1[2] == key) return -1;
     if (slp2[2] == key) return 1;
     double m1 = 0;
     double m2 = 0;
     if (slp1[1] == 0 || slp2[1] == 0 )
     {
      m1 = (slp1[1]==0)?((slp1[0]<0)?Integer.MAX_VALUE:Integer.MIN_VALUE):(slp1[0]*100/slp1[1]);
      m2 = (slp2[1]==0)?((slp2[0]<0)?Integer.MAX_VALUE:Integer.MIN_VALUE):(slp2[0]*100/slp2[1]);
     }
     else
     {
      m1 = ((double)slp1[0])/slp1[1];
      m2 = ((double)slp2[0])/slp2[1];
     }
     if (m1 < m2) return -1;
     if (m1 > m2) return 1;
     return 0;
    }
   });
   int desc = 0;
   int setsize = 0;
   int indeces_size = 0;
   for (int j = 1; j < numPts; ++j)
   {
    if (slopes[j][0] == slopes[j-1][0] && slopes[j][1] == slopes[j-1][1])
    {
     if (desc == 0)
     {
      desc |= s_shifted[slopes[j-1][2]];
      indeces[indeces_size++] = slopes[j-1][2];
      ++setsize;
     }
     desc |= s_shifted[slopes[j][2]];
     indeces[indeces_size++] = slopes[j][2];
     ++setsize;
    }
    else
    {
     if (desc != 0)
     {
      ++setsize;
      desc |= s_shifted[slopes[0][2]];
      indeces[indeces_size++] = slopes[0][2];
      if (hash[desc] != TRUTH)
      {
       hash[desc] = TRUTH;
       int idx = setsize-3;
       sets[idx][sets_sizes[idx]++] = (short) desc;
       largest_subset_idx = Math.max(idx, largest_subset_idx);
       for (int k = 0; k < indeces_size; k++)
       {
        res[indeces[k]] = TRUTH;
        ++points[indeces[k]][2];
        for (int l = 0; l < indeces_size; l++)
         colinear[indeces[k]][indeces[l]] = (k != l)?TRUTH:0;
       }
      }
     }
     desc = 0;
     setsize = 0;
     indeces_size = 0;
    }
   }
   if (desc != 0)
   {
    ++setsize;
    desc |= s_shifted[slopes[0][2]];
    indeces[indeces_size++] = slopes[0][2];
    if (hash[desc] != TRUTH)
    {
     hash[desc] = TRUTH;
     int idx = setsize-3;
     sets[idx][sets_sizes[idx]++] = (short) desc;
     largest_subset_idx = Math.max(idx, largest_subset_idx);
     for (int k = 0; k < indeces_size; k++)
     {
      res[indeces[k]] = TRUTH;
      ++points[indeces[k]][2];
      for (int l = 0; l < indeces_size; l++)
       colinear[indeces[k]][indeces[l]] = (k != l)?TRUTH:0;
     }
    }
   }
  }
  return largest_subset_idx;
 }

 private static void iterate(short[][] sets, int[] sets_sizes, int top, int covered, int uncovered, int level, int ptcount, byte[][] colinear, byte[] hash, int[] res, int[] buff, int[] noncolinear_lookup, byte TRUTH)
 {
  int remaining = getOnCount(uncovered, 2);
  if (remaining <= 2)
  {
   if (remaining == 0)
    --level;
   if (level < res[0])
   {
    res[0] = level;
    res[1] = 0;
    res[2] = 0;
   }
   if (level == res[0])
   {
    res[1] += s_permutations[level];
    res[1] %= MODULO_BY;
    ++res[2];
   }
   return;
  }
  if (level>res[0])
   return;
  int new_level = level+1;
  int[] temp = new int[16];
  getOnIndeces(temp, uncovered, 2);
  int uncoveredNum = temp[0];
  int uncoveredMask = s_shifted[uncoveredNum];
  boolean arecolinear = false;
  int noncolinear = 0;
  SEARCH:
  for (int i = 0; i < remaining; i++)
  {
   for (int j = i+1; j < remaining; j++)
   {
    if (colinear[temp[i]][temp[j]] == TRUTH)
    {
     arecolinear = true;
     continue SEARCH;
    }
   }
   int[] result = s_non_colinear_results[++noncolinear];
   int anticipated_level = (level-1)+result[0];
   if (anticipated_level > res[0])
    return;
  }
  if (!arecolinear)
  { 
   int[] result = s_non_colinear_results[remaining];
   int anticipated_level = (level-1)+result[0];
   if (anticipated_level > res[0])
    return;
   if (anticipated_level < res[0])
   {
    res[0] = anticipated_level;
    res[1] = 0;
    res[2] = 0;
   }
   BigInteger rslt = new BigInteger(""+result[2]).multiply(new BigInteger(""+s_permutations[anticipated_level])).mod(bigIntModBy);
   res[1] += rslt.intValue();
   res[1] %= MODULO_BY;
   res[2] += result[2];
   return;
  }
  if (noncolinear_lookup[uncoveredNum]==TRUTH)
  {
   for (int i = top; arecolinear && (i >= 0); --i)
   {
    if (sets_sizes[i] == 0) continue;
    for (int j = 0; j < sets_sizes[i]; j++)
    {
     int cvr = sets[i][j]&0xffff;
     if ((cvr&uncoveredMask) == 0) continue;
     if ((covered | cvr) == covered) continue;
     int about_to_cover = cvr & (~covered);
     int new_cover_size = getOnCount(about_to_cover, 2);
     if (new_cover_size < 3)
      continue;
     if (new_cover_size == 3)
     {
      int new_covered = covered | cvr;
      int new_uncovered = uncovered & (~new_covered);
      iterate(sets, sets_sizes, top, new_covered, new_uncovered, new_level, ptcount, colinear, hash, res, buff, noncolinear_lookup, TRUTH);
     }
     else
     {
      int lim = getOnIndeces(temp, about_to_cover, 2);
      int size = s_shifted[lim-1];
      for (int k = 3; k < size; k++) {
       if (s_bit_counts[k] == 1) continue;
       int n = getOnIndeces(buff, k, 2);
       int cvr2 = uncoveredMask;
       for (int l = 0; l < n; l++) {
        cvr2 |= s_shifted[temp[buff[l]+1]];
       }
       int new_covered = covered | cvr2;
       int new_uncovered = uncovered & (~new_covered);
       iterate(sets, sets_sizes, top, new_covered, new_uncovered, new_level, ptcount, colinear, hash, res, buff, noncolinear_lookup, TRUTH);
      }
     }
    }
   }
  }
  int new_covered = covered | uncoveredMask;
  int new_uncovered = uncovered & (~new_covered);
  //Do the covers with size 2
  for (int i = uncoveredNum+1; i < ptcount; ++i)
  {
   if ((covered | s_shifted[i]) == covered) continue;
   int cvr2 = s_1x1masks[uncoveredNum][i];
   int new_covered2 = new_covered | cvr2;
   int new_uncovered2 = new_uncovered & (~cvr2);
   iterate(sets, sets_sizes, top, new_covered2, new_uncovered2, new_level, ptcount, colinear, hash, res, buff, noncolinear_lookup, TRUTH);
  }
  //Do the covers with size 1
  iterate(sets, sets_sizes, top, new_covered, new_uncovered, new_level, ptcount, colinear, hash, res, buff, noncolinear_lookup, TRUTH);
 }
 
 public static void main(String[] args)
 {
  DataInputStream in = new DataInputStream(new BufferedInputStream(System.in));
  PrintStream out = new PrintStream(new BufferedOutputStream( System.out));
  int[][] points = new int[16][3];
  int[][] slopes = new int[16][3];
  byte[] hash = new byte[65536];
  byte[][] colinear = new byte[16][16];
  short[][] sets = new short[14][16];
  int[] sets_sizes = new int[14];
  final int[] res = new int[3];
  int[] noncolinear = new int[16];
  int[] buff = getIntArray(in, new int[16]);
  int T = buff[0];
  int truth_counter = 0;
  for (int t=0; t< T; ++t)
  {
   buff = getIntArray(in, buff);
   int numPts = buff[0];
   for (int i = 0; i < numPts; i++)
    getIntArray(in, points[i]);
   
   byte TRUTH = (byte) ((++truth_counter) | 0x80);
   int largest = getsubsets(numPts, points, slopes, hash, sets, sets_sizes, colinear, TRUTH, noncolinear);
   res[0] = 1;
   Arrays.sort(points, new Comparator<int[]>() {
    public int compare(int[] o1, int[] o2) {
     int rslt = o2[2]-o1[2];
     if (rslt < 0)
      res[0] = 0;
     return rslt;
    }
   });
   boolean bredo = (res[0] == 0);
   for (int i = 0; i < points.length; i++) points[i][2] = 0;
   if (bredo)
   {
    TRUTH = (byte) ((++truth_counter) | 0x80);
    largest = getsubsets(numPts, points, slopes, hash, sets, sets_sizes, colinear, TRUTH, noncolinear);
    for (int i = 0; i < points.length; i++) points[i][2] = 0;
   }
   if (largest >= -1)
   {
    int all_vertices = 0;
    for (int i = 0; i < numPts; i++) all_vertices |= s_shifted[i];
    res[0] = Integer.MAX_VALUE;
    res[1] = 0;
    iterate(sets, sets_sizes, largest, 0, all_vertices, 1, numPts, colinear, hash, res, buff, noncolinear, TRUTH);
   }
   else
   {
    res[0] = s_non_colinear_results[numPts][0];
    res[1] = s_non_colinear_results[numPts][1];
   }
   out.println(res[0] + " " + res[1]);
  }
  out.flush();
 }

 }