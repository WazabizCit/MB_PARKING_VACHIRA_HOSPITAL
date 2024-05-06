package com.example.mb_parking_vachira_hospital.util;



import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import zj.com.customize.sdk.Other;

public class MiniThermal80MMv4 {





  //region    EMBEDDED_CLASS


  //region    COMMAND
  public static class Command {

    private static final byte ESC = 0x1B;
    private static final byte FS = 0x1C;
    private static final byte GS = 0x1D;
    private static final byte US = 0x1F;
    private static final byte DLE = 0x10;
    private static final byte DC4 = 0x14;
    private static final byte DC1 = 0x11;
    private static final byte SP = 0x20;
    private static final byte NL = 0x0A;
    private static final byte FF = 0x0C;
    public  static final byte PIECE = (byte) 0xFF;
    public  static final byte NUL = (byte) 0x00;

    //打印机初始化
    public static byte[] ESC_Init = new byte[] {ESC, '@' };

    /**
     * 打印命令
     */
    //打印并换行
    public static byte[] LF = new byte[] {NL};

    //打印并走纸
    public static byte[] ESC_J = new byte[] {ESC, 'J', 0x00 };
    public static byte[] ESC_d = new byte[] {ESC, 'd', 0x00 };

    //打印自检页
    public static byte[] US_vt_eot = new byte[] {US, DC1, 0x04 };

    //蜂鸣指令
    public static byte[] ESC_B_m_n = new byte[] {ESC, 'B', 0x00, 0x00 };

    //切刀指令
    public static byte[] GS_V_n = new byte[] {GS, 'V', 0x00 };
    public static byte[] GS_V_m_n = new byte[] {GS, 'V', 'B', 0x00 };
    public static byte[] GS_i = new byte[] {ESC, 'i' };
    public static byte[] GS_m = new byte[] {ESC, 'm' };

    /**
     * 字符设置命令
     */
    //设置字符右间距
    public static byte[] ESC_SP = new byte[] {ESC, SP, 0x00 };

    //设置字符打印字体格式
    public static byte[] ESC_ExclamationMark = new byte[] {ESC, '!', 0x00 };

    //设置字体倍高倍宽
    public static byte[] GS_ExclamationMark = new byte[] {GS, '!', 0x00 };

    //设置反显打印
    public static byte[] GS_B = new byte[] {GS, 'B', 0x00 };

    //取消/选择90度旋转打印
    public static byte[] ESC_V = new byte[] {ESC, 'V', 0x00 };

    //选择字体字型(主要是ASCII码)
    public static byte[] ESC_M = new byte[] {ESC, 'M', 0x00 };

    //选择/取消加粗指令
    public static byte[] ESC_G = new byte[] {ESC, 'G', 0x00 };
    public static byte[] ESC_E = new byte[] {ESC, 'E', 0x00 };

    //选择/取消倒置打印模式
    public static byte[] ESC_LeftBrace = new byte[] {ESC, '{', 0x00 };

    //设置下划线点高度(字符)
    public static byte[] ESC_Minus = new byte[] {ESC, 45, 0x00 };

    //字符模式
    public static byte[] FS_dot = new byte[] {FS, 46 };

    //汉字模式
    public static byte[] FS_and = new byte[] {FS, '&' };

    //设置汉字打印模式
    public static byte[] FS_ExclamationMark = new byte[] {FS, '!', 0x00 };

    //设置下划线点高度(汉字)
    public static byte[] FS_Minus = new byte[] {FS, 45, 0x00 };

    //设置汉字左右间距
    public static byte[] FS_S = new byte[] {FS, 'S', 0x00, 0x00 };

    //选择字符代码页
    public static byte[] ESC_t = new byte[] {ESC, 't', 0x00 };

    /**
     * 格式设置指令
     */
    //设置默认行间距
    public static byte[] ESC_Two = new byte[] {ESC, 50};

    //设置行间距
    public static byte[] ESC_Three = new byte[] {ESC, 51, 0x00 };

    //设置对齐模式
    public static byte[] ESC_Align = new byte[] {ESC, 'a', 0x00 };

    //设置左边距
    public static byte[] GS_LeftSp = new byte[] {GS, 'L', 0x00 , 0x00 };

    //设置绝对打印位置
    //将当前位置设置到距离行首（nL + nH x 256）处。
    //如果设置位置在指定打印区域外，该命令被忽略
    public static byte[] ESC_Relative = new byte[] {ESC, '$', 0x00, 0x00 };

    //设置相对打印位置
    public static byte[] ESC_Absolute = new byte[] {ESC, 92, 0x00, 0x00 };

    //设置打印区域宽度
    public static byte[] GS_W = new byte[] {GS, 'W', 0x00, 0x00 };

    /**
     * 状态指令
     */
    //实时状态传送指令
    public static byte[] DLE_eot = new byte[] {DLE, 0x04, 0x00 };

    //实时弹钱箱指令
    public static byte[] DLE_DC4 = new byte[] {DLE, DC4, 0x00, 0x00, 0x00 };

    //标准弹钱箱指令
    public static byte[] ESC_p = new byte[] {ESC, 'F', 0x00, 0x00, 0x00 };

    /**
     * 条码设置指令
     */
    //选择HRI打印方式
    public static byte[] GS_H = new byte[] {GS, 'H', 0x00 };

    //设置条码高度
    public static byte[] GS_h = new byte[] {GS, 'h', (byte) 0xa2 };

    //设置条码宽度
    public static byte[] GS_w = new byte[] {GS, 'w', 0x00 };

    //设置HRI字符字体字型
    public static byte[] GS_f = new byte[] {GS, 'f', 0x00 };

    //条码左偏移指令
    public static byte[] GS_x = new byte[] {GS, 'x', 0x00 };

    //打印条码指令
    public static byte[] GS_k = new byte[] {GS, 'k', 'A', FF };

    //二维码相关指令
    public static byte[] GS_k_m_v_r_nL_nH = new byte[] { ESC, 'Z', 0x03, 0x03, 0x08, 0x00, 0x00 };

  }
  //endregion COMMAND

  //region    PRINTER_COMMAND
  public static class PrinterCommand {



    public static class Encoding{
      public static final String CHINESE = "GBK";
      public static final String THAI = "CP874";
      public static final String KOREAN = "EUC-KR";
      public static final String BIG5 = "BIG5";
    }

    /**
     * Printer initialization
     * @return
     */
    public static byte[] POS_Set_PrtInit(){

      byte[] data = Other.byteArraysToBytes(new byte[][] {
          Command.ESC_Init});

      return data;
    }

    /**
     * Print and wrap
     * @return
     */
    public static byte[] POS_Set_LF(){
      byte[] data = Other.byteArraysToBytes(new byte[][] {
          Command.LF});

      return data;
    }

    /**
     * Print and feed paper (0~255)
     * @param feed
     * @return
     */
    public static byte[] POS_Set_PrtAndFeedPaper(int feed){
      if(feed > 255 | feed < 0)
        return null;

      Command.ESC_J[2] = (byte)feed;

      byte[] data = Other.byteArraysToBytes(new byte[][] {
          Command.ESC_J});

      return data;
    }

    /**
     * Print a self-test page
     * @return
     */
    public static byte[] POS_Set_PrtSelfTest(){

      byte[] data = Other.byteArraysToBytes(new byte[][]{
          Command.US_vt_eot
      });
      return data;
    }

    /**
     * Beep command
     * @param m  Number of beeps
     * @param t  Time per beep
     * @return
     */
    public static byte[] POS_Set_Beep(int m, int t){

      if((m<1 || m>9) | (t<1 || t>9))
        return null;

      Command.ESC_B_m_n[2] = (byte)m;
      Command.ESC_B_m_n[3] = (byte)t;

      byte[] data = Other.byteArraysToBytes(new byte[][]{
          Command.ESC_B_m_n
      });
      return data;
    }

    /**
     * Cutter command (feed the paper to the cutter position and cut the paper)
     * @param cut  0~255
     * @return
     */
    public static byte[] POS_Set_Cut(int cut){
      if(cut>255 | cut < 0)
        return null;

      Command.GS_V_m_n[3] = (byte)cut;
      byte[] data = Other.byteArraysToBytes(new byte[][]{
          Command.GS_V_m_n
      });
      return data;
    }

    /**
     * Cash drawer command
     * @param nMode
     * @param nTime1
     * @param nTime2
     * @return
     */
    public static byte[] POS_Set_Cashbox(int nMode, int nTime1, int nTime2){

      if((nMode<0 || nMode>1) | nTime1<0 | nTime1 >255 | nTime2 < 0 | nTime2 > 255)
        return null;
      Command.ESC_p[2] = (byte)nMode;
      Command.ESC_p[3] = (byte)nTime1;
      Command.ESC_p[4] = (byte)nTime2;

      byte[] data = Other.byteArraysToBytes(new byte[][]{
          Command.ESC_p
      });
      return data;
    }

    /**
     * Set absolute print position
     * @param absolute
     * @return
     */
    public static byte[] POS_Set_Absolute(int absolute){
      if(absolute >65535 | absolute <0)
        return null;

      Command.ESC_Relative[2] = (byte)(absolute%0x100);
      Command.ESC_Relative[3] = (byte)(absolute/0x100);

      byte[] data = Other.byteArraysToBytes(new byte[][]{
          Command.ESC_Relative
      });
      return data;
    }

    /**
     * Set relative print position
     * @param relative
     * @return
     */
    public static byte[] POS_Set_Relative(int relative){
      if(relative<0 | relative>65535)
        return null;

      Command.ESC_Absolute[2] = (byte)(relative%0x100);
      Command.ESC_Absolute[3] = (byte)(relative/0x100);

      byte[] data = Other.byteArraysToBytes(new byte[][]{
          Command.ESC_Absolute
      });
      return data;
    }

    /**
     * Set left margin
     * @param left
     * @return
     */
    public static byte[] POS_Set_LeftSP(int left){
      if(left > 255 | left < 0)
        return null;

      Command.GS_LeftSp[2] = (byte)(left%100);
      Command.GS_LeftSp[3] = (byte)(left/100);

      byte[] data = Other.byteArraysToBytes(new byte[][]{
          Command.GS_LeftSp
      });
      return data;
    }

    /**
     * Set alignment mode
     * @param align
     * @return
     */
    public static byte[] POS_S_Align(int align) {
//      if ((align < 0 || align > 2) | (align <48 || align >50))
//        return null;
      if ((align < 0 || align > 2)){
        return null;
      }
      byte[] data = Command.ESC_Align;
      data[2] = (byte) align;
      return data;
    }

    /**
     * Set the print area width
     * @param width
     * @return
     */
    public static byte[] POS_Set_PrintWidth(int width){
      if(width<0 | width>255)
        return null;

      Command.GS_W[2] = (byte)(width%100);
      Command.GS_W[3] = (byte)(width/100);

      byte[] data = Other.byteArraysToBytes(new byte[][]{
          Command.GS_W
      });
      return data;
    }

    /**
     * Set default line spacing
     * @return
     */
    public static byte[] POS_Set_DefLineSpace(){

      byte[] data =  Command.ESC_Two;
      return data;
    }

    /**
     * Set line spacing
     * @param space
     * @return
     */
    public static byte[] POS_Set_LineSpace(int space){
      if(space<0 | space > 255)
        return null;

      Command.ESC_Three[2] = (byte)space;

      byte[] data = Other.byteArraysToBytes(new byte[][]{
          Command.ESC_Three
      });
      return data;
    }

    /**
     * Select a character code page
     * @param page
     * @return
     */
    public static byte[] POS_Set_CodePage(int page){
      if(page > 255)
        return null;

      Command.ESC_t[2] = (byte)page;

      byte[] data = Other.byteArraysToBytes(new byte[][]{
          Command.ESC_t
      });

      return data;
    }

    /**
     * Print text document
     * Example. PrinterCommand.POS_Print_Text(msg, THAI, 255, 0, 0, 0);
     * @param pszString  	The string to print
     * @param encoding   	Corresponding encoding of printing characters
     * @param codepage      Set code page (0--255)
     * @param nWidthTimes   Double width (0--4)
     * @param nHeightTimes  Double height (0--4)
     * @param nFontType     Font type (only valid for Ascii code) (0,1 48,49)
     *
     */
    public static byte[] POS_Print_Text(String pszString, String encoding, int codepage,int nWidthTimes, int nHeightTimes, int nFontType) {
      if (codepage <0 || codepage >255 || pszString == null || "".equals(pszString) || pszString.length() < 1) {
        return null;
      }
      byte[] pbString = null;
      try {
        pbString = pszString.getBytes(encoding);
      } catch (UnsupportedEncodingException e) {
        return null;
      }

      byte[] intToWidth = { 0x00, 0x10, 0x20, 0x30 };
      byte[] intToHeight = { 0x00, 0x01, 0x02, 0x03 };
      Command.GS_ExclamationMark[2] = (byte) (intToWidth[nWidthTimes] + intToHeight[nHeightTimes]);

      Command.ESC_t[2] = (byte)codepage;

      Command.ESC_M[2] = (byte)nFontType;

      if(codepage == 0){
        byte[] data = Other.byteArraysToBytes(new byte[][] {
            Command.GS_ExclamationMark, Command.ESC_t, Command.FS_and, Command.ESC_M, pbString });

        return data;
      }else{
        byte[] data = Other.byteArraysToBytes(new byte[][] {
            Command.GS_ExclamationMark, Command.ESC_t, Command.FS_dot, Command.ESC_M, pbString });

        return data;
      }
    }

    /**
     * Bold command (the lowest bit is 1 and valid)
     * @param bold
     * @return
     */
    public static byte[] POS_Set_Bold(int bold){

      Command.ESC_E[2] = (byte)bold;
      Command.ESC_G[2] = (byte)bold;

      byte[] data = Other.byteArraysToBytes(new byte[][]{
          Command.ESC_E, Command.ESC_G
      });
      return data;
    }

    /**
     * Set upside-down printing mode (valid when the lowest bit is 1)
     * @param brace
     * @return
     */
    public static byte[] POS_Set_LeftBrace(int brace){

      Command.ESC_LeftBrace[2] = (byte)brace;
      byte[] data = Other.byteArraysToBytes(new byte[][]{
          Command.ESC_LeftBrace
      });
      return data;
    }

    /**
     * Set underline
     * @param line
     * @return
     */
    public static byte[] POS_Set_UnderLine(int line){

      if((line<0 || line>2))
        return null;

      Command.ESC_Minus[2] = (byte)line;
      Command.FS_Minus[2] = (byte)line;

      byte[] data = Other.byteArraysToBytes(new byte[][]{
          Command.ESC_Minus, Command.FS_Minus
      });
      return data;
    }

    /**
     * Select font size (double height and width)
     * @return
     */
    public static byte[] POS_Set_FontSize(int size1, int size2){
      if(size1<0 | size1>7 | size2<0 | size2>7)
        return null;
      byte[] intToWidth = { 0x00, 0x10, 0x20, 0x30, 0x40, 0x50, 0x60, 0x70 };
      byte[] intToHeight = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 };
      Command.GS_ExclamationMark[2] = (byte) (intToWidth[size1] + intToHeight[size2]);
      byte[] data = Other.byteArraysToBytes(new byte[][]{
          Command.GS_ExclamationMark
      });
      return data;
    }

    /**
     * Set reverse display printing
     * @param inverse
     * @return
     */
    public static byte[] POS_Set_Inverse(int inverse){

      Command.GS_B[2] = (byte)inverse;

      byte[] data = Other.byteArraysToBytes(new byte[][]{
          Command.GS_B
      });

      return data;
    }

    /**
     * Set to rotate 90 degrees to print
     * @param rotate
     * @return
     */
    public static byte[] POS_Set_Rotate(int rotate){
      if(rotate<0 || rotate>1)
        return null;
      Command.ESC_V[2] = (byte)rotate;
      byte[] data = Other.byteArraysToBytes(new byte[][]{
          Command.ESC_V
      });
      return data;
    }

    /**
     * Select font font
     * @param font
     * @return
     */
    public static byte[] POS_Set_ChoseFont(int font){
      if(font > 1 | font < 0)
        return null;

      Command.ESC_M[2] = (byte)font;
      byte[] data = Other.byteArraysToBytes(new byte[][]{
          Command.ESC_M
      });
      return data;

    }

    //***********************************The following functions are public functions***********************************************************//
    /**
     * QR code printing function
     * @param str                     Print QR code data
     * @param nVersion				        QR code type
     * @param nErrorCorrectionLevel   Error correction level
     * @param nMagnification          Gain
     * @return
     */
    public static byte[] getBarCommand(String str, int nVersion, int nErrorCorrectionLevel, int nMagnification){

      if(nVersion<0 | nVersion >19 | nErrorCorrectionLevel<0 | nErrorCorrectionLevel > 3
          | nMagnification < 1 | nMagnification > 8){
        return null;
      }

      byte[] bCodeData = null;
      try
      {
        bCodeData = str.getBytes("GBK");

      }
      catch (UnsupportedEncodingException e)
      {
        e.printStackTrace();
        return null;
      }

      byte[] command = new byte[bCodeData.length + 7];

      command[0] = 27;
      command[1] = 90;
      command[2] = ((byte)nVersion);
      command[3] = ((byte)nErrorCorrectionLevel);
      command[4] = ((byte)nMagnification);
      command[5] = (byte)(bCodeData.length & 0xff);
      command[6] = (byte)((bCodeData.length & 0xff00) >> 8);
      System.arraycopy(bCodeData, 0, command, 7, bCodeData.length);

      return command;
    }

    /**
     * Print 1D barcode
     * @param str            Print barcode characters
     * @param nType					 Barcode type (65~73)
     * @param nWidthX				 Barcode width
     * @param nHeight				 Barcode height
     * @param nHriFontType			HRI font
     * @param nHriFontPosition		HRI Location
     * @return
     */
    public static byte[] getCodeBarCommand(String str, int nType, int nWidthX, int nHeight, int nHriFontType, int nHriFontPosition){

      if (nType < 0x41 | nType > 0x49 | nWidthX < 2 | nWidthX > 6
          | nHeight < 1 | nHeight > 255 | str.length() == 0)
        return null;

      byte[] bCodeData = null;
      try
      {
        bCodeData = str.getBytes("GBK");

      }
      catch (UnsupportedEncodingException e)
      {
        e.printStackTrace();
        return null;
      }

      byte[] command = new byte[bCodeData.length + 16];

      command[0] = 29;
      command[1] = 119;
      command[2] = ((byte)nWidthX);
      command[3] = 29;
      command[4] = 104;
      command[5] = ((byte)nHeight);
      command[6] = 29;
      command[7] = 102;
      command[8] = ((byte)(nHriFontType & 0x01));
      command[9] = 29;
      command[10] = 72;
      command[11] = ((byte)(nHriFontPosition & 0x03));
      command[12] = 29;
      command[13] = 107;
      command[14] = ((byte)nType);
      command[15] = (byte)(byte) bCodeData.length;
      System.arraycopy(bCodeData, 0, command, 16, bCodeData.length);


      return command;
    }

    /**
     * Set the print mode (select the font (font: A font: B), bold, double the height and width of the font (maximum 4 times the height and width))
     * @param str          Printed string
     * @param bold		     Bold
     * @param font		     Select font
     * @param widthsize    Double width
     * @param heigthsize   Double high
     * @return
     */
    public static byte[] POS_Set_Font(String str, int bold, int font, int widthsize, int heigthsize){

      if(str.length() == 0 | widthsize<0 | widthsize >4 | heigthsize<0 | heigthsize>4
          | font<0 | font>1)
        return null;

      byte[] strData = null;
      try
      {
        strData = str.getBytes("GBK");
      }
      catch (UnsupportedEncodingException e)
      {
        e.printStackTrace();
        return null;
      }

      byte[] command = new byte[strData.length + 9];

      byte[] intToWidth = { 0x00, 0x10, 0x20, 0x30 };//最大四倍宽
      byte[] intToHeight = { 0x00, 0x01, 0x02, 0x03 };//最大四倍高

      command[0] = 27;
      command[1] = 69;
      command[2] = ((byte)bold);
      command[3] = 27;
      command[4] = 77;
      command[5] = ((byte)font);
      command[6] = 29;
      command[7] = 33;
      command[8] = (byte) (intToWidth[widthsize] + intToHeight[heigthsize]);

      System.arraycopy(strData, 0, command, 9, strData.length);
      return command;
    }
    //**********************************************************************************************************//

  }
  //endregion PRINTER_COMMAND

  //region    PRINT_PICTURE
  public static class PrintPicture {

    /**
     * Print bitmap function
     * This function is to print a line as a picture, so that the processing is not easy to make mistakes
     * @param mBitmap
     * @param nWidth
     * @param nMode
     * @return
     */
    public static byte[] POS_PrintBMP(Bitmap mBitmap, int nWidth, int nMode) {
      // Convert to black and white first, then call the function to scale the bitmap
      int width = ((nWidth + 7) / 8) * 8;
      int height = mBitmap.getHeight() * width / mBitmap.getWidth();
      height = ((height + 7) / 8) * 8;

      Bitmap rszBitmap = mBitmap;
      if (mBitmap.getWidth() != width){
        rszBitmap = Other.resizeImage(mBitmap, width, height);
      }
      Bitmap grayBitmap = Other.toGrayscale(rszBitmap);
      byte[] dithered = Other.thresholdToBWPic(grayBitmap);
      return Other.eachLinePixToCmd(dithered, width, nMode);
    }

    /**
     * Use the downloaded bitmap to print the picture
     * Receive first before printing
     * @param bmp
     * @return
     */
    public static byte[] Print_1D2A(Bitmap bmp){
      int width = bmp.getWidth();
      int height = bmp.getHeight();
      byte[] data =new byte[1024*10]; // Original -> byte data[]=new byte[1024*10];
      data[0] = 0x1D;
      data[1] = 0x2A;
      data[2] =(byte)( (width - 1)/ 8 + 1);
      data[3] =(byte)( (height - 1)/ 8 + 1);
      byte k = 0;
      int position = 4;
      int i;
      int j;
      byte temp = 0;
      for(i = 0; i <width;  i++){


        System.out.println("Came in...I"); // Original -> System.out.println("进来了...I");
        for(j = 0; j < height; j++){
          System.out.println("Come in...J"); // Original -> System.out.println("进来了...J");
          if(bmp.getPixel(i, j) != -1){
            temp |= (0x80 >> k);
          } // end if
          k++;
          if(k == 8){
            data[position++] = temp;
            temp = 0;
            k = 0;
          } // end if k
        }// end for j
        if(k % 8 != 0){
          data[position ++] = temp;
          temp = 0;
          k = 0;
        }

      }
      System.out.println("data" + Arrays.toString(data)); // Original -> System.out.println("data" + data);

      if( width% 8 != 0){
        i =   height/ 8;
        if(height % 8 != 0) i++;
        j = 8 - (width % 8);
        for(k = 0; k < i*j; k++){
          data[position++] = 0;
        }
      }
      return data;
    }

  }

  //endregion PRINT_PICTURE

  //region    OTHER

//  public static class Other {
//    private static final int WIDTH_58 = 384;
//    private static final int WIDTH_80 = 576;
//    private static final byte[] chartobyte;
//    private static int[] p0;
//    private static int[] p1;
//    private static int[] p2;
//    private static int[] p3;
//    private static int[] p4;
//    private static int[] p5;
//    private static int[] p6;
//    public byte[] buf;
//    public int index;
//
//    static {
//      int[] var0 = new int[]{0, 128};
//      p0 = var0;
//      var0 = new int[]{0, 64};
//      p1 = var0;
//      var0 = new int[]{0, 32};
//      p2 = var0;
//      var0 = new int[]{0, 16};
//      p3 = var0;
//      var0 = new int[]{0, 8};
//      p4 = var0;
//      var0 = new int[]{0, 4};
//      p5 = var0;
//      var0 = new int[]{0, 2};
//      p6 = var0;
//      byte[] var1 = new byte[23];
//      var1[1] = (byte)1;
//      var1[2] = (byte)2;
//      var1[3] = (byte)3;
//      var1[4] = (byte)4;
//      var1[5] = (byte)5;
//      var1[6] = (byte)6;
//      var1[7] = (byte)7;
//      var1[8] = (byte)8;
//      var1[9] = (byte)9;
//      var1[17] = (byte)10;
//      var1[18] = (byte)11;
//      var1[19] = (byte)12;
//      var1[20] = (byte)13;
//      var1[21] = (byte)14;
//      var1[22] = (byte)15;
//      chartobyte = var1;
//    }
//
//    public Other(int var1) {
//      this.buf = new byte[var1];
//      this.index = 0;
//    }
//
//    public static byte HexCharsToByte(char var0, char var1) {
//      return (byte)(chartobyte[var0 - 48] << 4 & 240 | chartobyte[var1 - 48] & 15);
//    }
//
//    public static byte[] HexStringToBytes(String var0) {
//      int var1 = var0.length();
//      byte[] var2 = null;
//      if (var1 % 2 == 0) {
//        var2 = new byte[var1 / 2];
//
//        for(int var3 = 0; var3 < var1; var3 += 2) {
//          char var4 = var0.charAt(var3);
//          char var5 = var0.charAt(var3 + 1);
//          if (!IsHexChar(var4) || !IsHexChar(var5)) {
//            var2 = null;
//            break;
//          }
//
//          char var6 = var4;
//          char var7;
//          if (var4 >= 'a') {
//            var7 = (char)(var4 - 32);
//            var6 = var7;
//          }
//
//          var4 = var5;
//          if (var5 >= 'a') {
//            var7 = (char)(var5 - 32);
//            var4 = var7;
//          }
//
//          var2[var3 / 2] = HexCharsToByte(var6, var4);
//        }
//      }
//
//      return var2;
//    }
//
//    public static boolean IsHexChar(char var0) {
//      boolean var1;
//      if ((var0 < '0' || var0 > '9') && (var0 < 'a' || var0 > 'f') && (var0 < 'A' || var0 > 'F')) {
//        var1 = false;
//      } else {
//        var1 = true;
//      }
//
//      return var1;
//    }
//
//    public static StringBuilder RemoveChar(String var0, char var1) {
//      StringBuilder var2 = new StringBuilder();
//      int var3 = var0.length();
//
//      for(int var4 = 0; var4 < var3; ++var4) {
//        char var5 = var0.charAt(var4);
//        if (var5 != var1) {
//          var2.append(var5);
//        }
//      }
//
//      return var2;
//    }
//
//    public static byte[] StringTOGBK(String var0) {
//      Object var1 = null;
//
//      byte[] var3;
//      try {
//        var3 = var0.getBytes("GBK");
//      } catch (UnsupportedEncodingException var2) {
//        var2.printStackTrace();
//        var3 = (byte[])var1;
//      }
//
//      return var3;
//    }
//
//    public static byte[] byteArraysToBytes(byte[][] var0) {
//      int var1 = 0;
//
//      int var2;
//      for(var2 = 0; var2 < var0.length; ++var2) {
//        var1 += var0[var2].length;
//      }
//
//      byte[] var3 = new byte[var1];
//      var2 = 0;
//
//      for(var1 = 0; var1 < var0.length; ++var1) {
//        for(int var4 = 0; var4 < var0[var1].length; ++var2) {
//          var3[var2] = (byte)var0[var1][var4];
//          ++var4;
//        }
//      }
//
//      return var3;
//    }
//
//    public static byte[] eachLinePixToCmd(byte[] var0, int var1, int var2) {
//      int var3 = var0.length / var1;
//      int var4 = var1 / 8;
//      byte[] var5 = new byte[(var4 + 8) * var3];
//      int var6 = 0;
//
//      for(var1 = 0; var1 < var3; ++var1) {
//        int var7 = var1 * (var4 + 8);
//        var5[var7 + 0] = (byte)29;
//        var5[var7 + 1] = (byte)118;
//        var5[var7 + 2] = (byte)48;
//        var5[var7 + 3] = (byte)((byte)(var2 & 1));
//        var5[var7 + 4] = (byte)((byte)(var4 % 256));
//        var5[var7 + 5] = (byte)((byte)(var4 / 256));
//        var5[var7 + 6] = (byte)1;
//        var5[var7 + 7] = (byte)0;
//
//        for(int var8 = 0; var8 < var4; ++var8) {
//          var5[var7 + 8 + var8] = (byte)((byte)(p0[var0[var6]] + p1[var0[var6 + 1]] + p2[var0[var6 + 2]] + p3[var0[var6 + 3]] + p4[var0[var6 + 4]] + p5[var0[var6 + 5]] + p6[var0[var6 + 6]] + var0[var6 + 7]));
//          var6 += 8;
//        }
//      }
//
//      return var5;
//    }
//
//    private static void format_K_threshold(int[] var0, int var1, int var2, byte[] var3) {
//      int var4 = 0;
//      int var5 = 0;
//
//      int var6;
//      int var7;
//      for(var6 = 0; var6 < var2; ++var6) {
//        for(var7 = 0; var7 < var1; ++var7) {
//          var4 += var0[var5] & 255;
//          ++var5;
//        }
//      }
//
//      var7 = var4 / var2 / var1;
//      var5 = 0;
//
//      for(var6 = 0; var6 < var2; ++var6) {
//        for(var4 = 0; var4 < var1; ++var4) {
//          if ((var0[var5] & 255) > var7) {
//            var3[var5] = (byte)0;
//          } else {
//            var3[var5] = (byte)1;
//          }
//
//          ++var5;
//        }
//      }
//
//    }
//
//    public static void overWriteBitmap(Bitmap var0, byte[] var1) {
//      int var2 = var0.getHeight();
//      int var3 = var0.getWidth();
//      int var4 = 0;
//
//      for(int var5 = 0; var5 < var2; ++var5) {
//        for(int var6 = 0; var6 < var3; ++var6) {
//          if (var1[var4] == 0) {
//            var0.setPixel(var6, var5, -1);
//          } else {
//            var0.setPixel(var6, var5, -16777216);
//          }
//
//          ++var4;
//        }
//      }
//
//    }
//
//    public static Bitmap resizeImage(Bitmap var0, int var1, int var2) {
//      int var3 = var0.getWidth();
//      int var4 = var0.getHeight();
//      float var5 = (float)var1 / (float)var3;
//      float var6 = (float)var2 / (float)var4;
//      Matrix var7 = new Matrix();
//      var7.postScale(var5, var6);
//      return Bitmap.createBitmap(var0, 0, 0, var3, var4, var7, true);
//    }
//
//    public static void saveMyBitmap(Bitmap var0, String var1) {
//
//      File var8 = new File(Environment.getExternalStorageDirectory().getPath(), var1);
//      try {
//        var8.createNewFile();
//      } catch (IOException ignored) {}
//
//      FileOutputStream var2;
//      try {
//        var2 = new FileOutputStream(var8);
//      } catch (Exception var6) {
//        return;
//      }
//
//      try {
//        var0.compress(Bitmap.CompressFormat.PNG, 100, var2);
//        var2.flush();
//        var2.close();
//      } catch (IOException ignored) {}
//
//    }
//
//    public static byte[] thresholdToBWPic(Bitmap var0) {
//      int[] var1 = new int[var0.getWidth() * var0.getHeight()];
//      byte[] var2 = new byte[var0.getWidth() * var0.getHeight()];
//      var0.getPixels(var1, 0, var0.getWidth(), 0, 0, var0.getWidth(), var0.getHeight());
//      format_K_threshold(var1, var0.getWidth(), var0.getHeight(), var2);
//      return var2;
//    }
//
//    public static Bitmap toGrayscale(Bitmap var0) {
//      int var1 = var0.getHeight();
//      Bitmap var2 = Bitmap.createBitmap(var0.getWidth(), var1, Bitmap.Config.ARGB_8888);
//      Canvas var3 = new Canvas(var2);
//      Paint var4 = new Paint();
//      ColorMatrix var5 = new ColorMatrix();
//      var5.setSaturation(0.0F);
//      var4.setColorFilter(new ColorMatrixColorFilter(var5));
//      var3.drawBitmap(var0, 0.0F, 0.0F, var4);
//      return var2;
//    }
//
//  }

  //endregion OTHER



  //endregion EMBEDDED_CLASS




}
