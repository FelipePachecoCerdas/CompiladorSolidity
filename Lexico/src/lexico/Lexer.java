/* The following code was generated by JFlex 1.6.1 */

package lexico;
import static lexico.Token.*;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>src/lexico/lexer.flex</tt>
 */
class Lexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\22\1\12\1\24\1\24\1\23\22\0\1\22\1\26\1\10"+
    "\2\17\1\27\1\32\1\11\1\27\1\27\1\21\1\26\1\27\1\16"+
    "\1\3\1\20\1\2\1\62\1\65\1\64\1\66\1\67\1\63\1\2"+
    "\1\61\1\2\1\27\1\35\1\30\1\34\1\31\1\27\1\17\6\4"+
    "\11\1\1\56\12\1\1\27\1\13\1\27\1\27\2\17\1\36\1\42"+
    "\1\47\1\37\1\6\1\53\1\55\1\5\1\51\1\1\1\45\1\44"+
    "\1\52\1\14\1\43\1\54\1\1\1\40\1\41\1\15\1\50\1\57"+
    "\1\60\1\7\1\46\1\70\1\27\1\33\1\27\1\27\6\17\1\25"+
    "\u1e6e\17\u0134\0\1\24\1\24\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udfe6\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\1\1\2\1\3\1\4\2\2\2\1\1\5\1\2"+
    "\3\4\1\0\7\4\20\2\1\0\1\6\2\3\1\6"+
    "\5\2\1\0\1\3\2\0\2\2\1\3\1\0\1\5"+
    "\1\0\1\2\1\7\41\2\7\1\1\0\1\3\1\0"+
    "\1\7\4\2\7\1\2\2\1\10\21\2\1\7\15\2"+
    "\1\11\1\1\2\0\1\2\1\0\1\3\1\0\1\1"+
    "\1\2\2\10\3\2\1\12\7\2\1\7\1\12\2\2"+
    "\1\7\14\2\6\0\1\2\3\10\1\3\32\2\1\1"+
    "\1\0\1\13\1\3\1\0\1\13\1\3\1\1\1\2"+
    "\1\0\1\10\3\0\1\2\1\7\12\2\6\1\1\13"+
    "\6\1\1\2\2\10\4\2\1\10\6\2";

  private static int [] zzUnpackAction() {
    int [] result = new int[279];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\71\0\162\0\253\0\344\0\u011d\0\u0156\0\u018f"+
    "\0\u01c8\0\71\0\u0201\0\u023a\0\u0273\0\u02ac\0\u01c8\0\u02e5"+
    "\0\u01c8\0\u031e\0\u0357\0\u0390\0\u03c9\0\71\0\u0402\0\u043b"+
    "\0\u0474\0\u04ad\0\u04e6\0\u051f\0\u0558\0\u0591\0\u05ca\0\u0603"+
    "\0\u063c\0\u0675\0\u06ae\0\u06e7\0\u0720\0\u0759\0\u0792\0\u07cb"+
    "\0\u0804\0\u083d\0\u0876\0\u08af\0\u08e8\0\u0921\0\u095a\0\u0993"+
    "\0\u09cc\0\71\0\u0a05\0\u0a3e\0\u0a77\0\u0ab0\0\u0ae9\0\u0b22"+
    "\0\u0b5b\0\u0b94\0\u0bcd\0\u0759\0\u0c06\0\u0c3f\0\u0c78\0\u0cb1"+
    "\0\u0cea\0\u0d23\0\u0d5c\0\u0d95\0\u0dce\0\u0e07\0\u0e40\0\u0e79"+
    "\0\u0eb2\0\u0eeb\0\u0f24\0\u0f5d\0\u0f96\0\u0fcf\0\u1008\0\u1041"+
    "\0\u107a\0\u10b3\0\u10ec\0\u1125\0\u115e\0\u1197\0\u11d0\0\u1209"+
    "\0\u1242\0\u127b\0\u12b4\0\u12ed\0\u1326\0\u135f\0\u1398\0\u13d1"+
    "\0\u140a\0\u1443\0\u147c\0\u14b5\0\u14ee\0\u1527\0\u1527\0\u1560"+
    "\0\u1599\0\u15d2\0\u160b\0\u1644\0\u167d\0\u16b6\0\u16ef\0\u1728"+
    "\0\u1761\0\u179a\0\u17d3\0\u180c\0\u1845\0\u187e\0\u18b7\0\u18f0"+
    "\0\u1929\0\u1962\0\u199b\0\u19d4\0\u1a0d\0\u1a46\0\u1a7f\0\u1ab8"+
    "\0\u1af1\0\u1b2a\0\u1b63\0\u1b9c\0\u1bd5\0\u1c0e\0\u1c47\0\u1c80"+
    "\0\u1cb9\0\u1cf2\0\u1d2b\0\u1d64\0\u1d9d\0\u1dd6\0\u1e0f\0\u1e48"+
    "\0\u1e81\0\u1eba\0\u1ef3\0\u1f2c\0\u1f65\0\u0759\0\u1f9e\0\u1fd7"+
    "\0\u2010\0\u2049\0\u018f\0\u01c8\0\u2082\0\u20bb\0\u20f4\0\u212d"+
    "\0\u2166\0\u219f\0\u21d8\0\u2211\0\u0759\0\u224a\0\u2283\0\u22bc"+
    "\0\u22f5\0\u232e\0\u2367\0\u23a0\0\u23d9\0\u2412\0\u244b\0\u2484"+
    "\0\u24bd\0\u24f6\0\u252f\0\u2568\0\u25a1\0\u25da\0\u2613\0\u264c"+
    "\0\u2685\0\u26be\0\u26f7\0\u2730\0\u2769\0\u27a2\0\u27db\0\u2814"+
    "\0\u284d\0\u2886\0\u28bf\0\u28f8\0\u2931\0\u296a\0\u29a3\0\u29dc"+
    "\0\u2a15\0\u2a4e\0\u2a87\0\u2ac0\0\u2af9\0\u2b32\0\u2b6b\0\u2ba4"+
    "\0\u24bd\0\u2bdd\0\u2c16\0\u2c4f\0\u2c88\0\u2cc1\0\u2cfa\0\u2d33"+
    "\0\u2d6c\0\u2da5\0\u2dde\0\u2e17\0\u2e50\0\u2e89\0\u2ec2\0\u2efb"+
    "\0\u2f34\0\u2f6d\0\u2fa6\0\u2fdf\0\u27db\0\u27db\0\u3018\0\u284d"+
    "\0\u284d\0\u3051\0\u308a\0\u30c3\0\u30fc\0\u29dc\0\u3135\0\u316e"+
    "\0\u31a7\0\u180c\0\u31e0\0\u3219\0\u3252\0\u328b\0\u32c4\0\u32fd"+
    "\0\u3336\0\u336f\0\u33a8\0\u33e1\0\u341a\0\u3453\0\u348c\0\u34c5"+
    "\0\u34fe\0\u3537\0\71\0\u3570\0\u35a9\0\u35e2\0\u361b\0\u3654"+
    "\0\u368d\0\u36c6\0\u36ff\0\u3738\0\u3771\0\u37aa\0\u37e3\0\u381c"+
    "\0\u3855\0\u388e\0\u38c7\0\u3900\0\u3939\0\u3972\0\u39ab";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[279];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\3\1\6\1\7\1\3"+
    "\1\10\1\11\1\12\1\11\1\3\1\13\1\14\1\11"+
    "\1\15\1\16\2\12\1\0\1\17\1\20\1\21\1\22"+
    "\1\23\1\24\1\25\1\20\1\26\1\27\1\30\1\31"+
    "\1\32\1\33\3\3\1\34\1\35\1\36\1\37\1\40"+
    "\1\41\1\42\1\3\1\43\1\44\1\45\7\4\1\3"+
    "\72\0\2\46\1\0\4\46\4\0\2\46\1\47\17\0"+
    "\33\46\1\0\1\50\1\51\1\52\2\50\1\53\1\50"+
    "\4\0\2\50\1\47\17\0\23\50\7\51\1\50\2\0"+
    "\1\52\13\0\1\47\42\0\7\52\2\0\2\46\1\0"+
    "\2\46\1\54\1\46\4\0\2\46\1\47\17\0\5\46"+
    "\1\55\25\46\1\0\2\46\1\0\4\46\4\0\1\56"+
    "\1\57\1\47\17\0\6\46\1\60\24\46\10\61\1\62"+
    "\1\61\1\0\1\63\2\61\1\64\52\61\16\0\1\47"+
    "\53\0\2\46\1\0\1\46\1\65\2\46\4\0\2\46"+
    "\1\47\17\0\2\46\1\66\30\46\2\0\1\67\1\70"+
    "\12\0\1\47\15\0\1\26\24\0\7\67\17\0\1\47"+
    "\1\0\1\71\1\72\12\0\1\26\52\0\1\47\2\0"+
    "\1\26\12\0\1\26\52\0\1\47\15\0\1\26\52\0"+
    "\1\47\11\0\1\26\3\0\1\26\52\0\1\47\12\0"+
    "\1\26\2\0\1\26\52\0\1\47\13\0\1\26\54\0"+
    "\1\47\14\0\1\26\36\0\2\46\1\0\4\46\4\0"+
    "\2\46\1\47\17\0\1\46\1\73\1\46\1\74\27\46"+
    "\1\0\2\46\1\0\2\46\1\75\1\46\4\0\2\46"+
    "\1\47\17\0\1\76\4\46\1\74\25\46\1\0\2\46"+
    "\1\0\2\46\1\77\1\46\4\0\2\46\1\47\17\0"+
    "\33\46\1\0\2\46\1\0\2\46\1\100\1\46\4\0"+
    "\1\46\1\101\1\47\17\0\5\46\1\102\24\46\1\103"+
    "\1\0\2\46\1\0\4\46\4\0\2\46\1\47\17\0"+
    "\1\104\1\46\1\105\2\46\1\106\2\46\1\107\22\46"+
    "\1\0\2\46\1\0\2\46\1\110\1\46\4\0\2\46"+
    "\1\47\17\0\33\46\1\0\2\46\1\0\4\46\4\0"+
    "\2\46\1\47\17\0\1\111\4\46\1\112\25\46\1\0"+
    "\2\46\1\0\4\46\4\0\2\46\1\47\17\0\13\46"+
    "\1\113\1\46\1\114\15\46\1\0\2\46\1\0\4\46"+
    "\4\0\1\115\1\46\1\47\17\0\14\46\1\116\1\74"+
    "\15\46\1\0\2\46\1\0\4\46\4\0\2\46\1\47"+
    "\17\0\1\117\4\46\1\120\5\46\1\121\17\46\1\0"+
    "\2\46\1\0\4\46\4\0\2\46\1\47\17\0\1\122"+
    "\1\46\1\123\2\46\1\124\4\46\1\125\1\126\17\46"+
    "\1\0\2\46\1\0\4\46\4\0\2\46\1\47\17\0"+
    "\1\127\1\46\1\130\7\46\1\131\20\46\1\0\2\46"+
    "\1\0\4\46\4\0\2\46\1\47\17\0\2\46\1\132"+
    "\30\46\1\0\2\46\1\0\4\46\4\0\2\46\1\47"+
    "\17\0\1\124\12\46\1\133\17\46\1\0\2\46\1\0"+
    "\1\46\1\134\1\135\1\46\4\0\2\46\1\47\17\0"+
    "\33\46\1\0\2\46\1\0\4\46\4\0\2\46\20\0"+
    "\33\46\3\0\1\136\12\0\1\137\1\0\1\137\1\140"+
    "\4\0\1\137\1\136\1\141\1\142\1\143\1\144\1\137"+
    "\1\136\34\0\1\50\2\0\4\50\4\0\2\50\20\0"+
    "\23\50\7\0\1\50\1\0\1\50\1\51\1\52\2\50"+
    "\1\53\1\50\4\0\2\50\20\0\23\50\7\51\1\50"+
    "\2\0\1\52\3\0\1\145\52\0\7\52\2\0\1\50"+
    "\1\146\1\0\4\50\4\0\2\50\1\147\17\0\23\50"+
    "\7\146\1\50\1\0\2\46\1\0\3\46\1\150\4\0"+
    "\2\46\20\0\33\46\1\0\2\46\1\0\4\46\4\0"+
    "\2\46\20\0\12\46\1\151\20\46\1\0\2\46\1\0"+
    "\4\46\4\0\2\46\20\0\12\46\1\152\20\46\1\0"+
    "\2\46\1\0\1\46\1\153\2\46\4\0\2\46\20\0"+
    "\33\46\1\0\2\46\1\0\4\46\4\0\2\46\20\0"+
    "\3\46\1\154\27\46\10\61\1\62\1\61\1\0\1\63"+
    "\55\61\10\0\1\61\2\0\3\61\53\0\3\61\1\155"+
    "\4\61\1\62\1\61\1\0\1\63\2\61\1\156\1\61"+
    "\1\156\1\157\4\61\1\156\1\155\1\160\1\161\1\162"+
    "\1\163\1\156\1\155\33\61\1\0\2\46\1\0\4\46"+
    "\4\0\2\46\20\0\13\46\1\164\17\46\1\0\2\46"+
    "\1\0\4\46\4\0\2\46\20\0\1\165\11\46\1\154"+
    "\20\46\2\0\1\67\1\52\2\0\1\145\52\0\7\67"+
    "\3\0\1\52\56\0\7\52\1\0\12\71\1\0\10\71"+
    "\3\0\43\71\21\0\1\166\50\0\2\46\1\0\4\46"+
    "\4\0\2\46\20\0\1\46\1\167\31\46\1\0\2\46"+
    "\1\0\4\46\4\0\2\46\20\0\6\46\1\170\24\46"+
    "\1\0\2\46\1\0\4\46\4\0\2\46\20\0\10\46"+
    "\1\171\22\46\1\0\2\46\1\0\4\46\4\0\1\46"+
    "\1\172\20\0\33\46\1\0\2\46\1\0\4\46\4\0"+
    "\1\173\1\46\20\0\11\46\1\174\21\46\1\0\2\46"+
    "\1\0\4\46\4\0\2\46\20\0\2\46\1\175\30\46"+
    "\1\0\2\46\1\0\4\46\4\0\2\46\20\0\6\46"+
    "\1\176\24\46\1\0\2\46\1\0\4\46\4\0\2\46"+
    "\20\0\1\177\32\46\1\0\2\46\1\0\4\46\4\0"+
    "\2\46\20\0\6\46\1\200\24\46\1\0\2\46\1\0"+
    "\2\46\1\201\1\46\4\0\2\46\20\0\33\46\1\0"+
    "\2\46\1\0\4\46\4\0\2\46\20\0\5\46\1\202"+
    "\25\46\1\0\2\46\1\0\4\46\4\0\1\46\1\203"+
    "\20\0\33\46\1\0\2\46\1\0\4\46\4\0\2\46"+
    "\20\0\1\151\32\46\1\0\2\46\1\0\4\46\4\0"+
    "\2\46\20\0\6\46\1\204\24\46\1\0\2\46\1\0"+
    "\4\46\4\0\1\205\1\46\20\0\33\46\1\0\2\46"+
    "\1\0\4\46\4\0\1\206\1\46\20\0\33\46\1\0"+
    "\2\46\1\0\4\46\4\0\2\46\20\0\13\46\1\207"+
    "\17\46\1\0\2\46\1\0\4\46\4\0\1\46\1\210"+
    "\20\0\33\46\1\0\2\46\1\0\4\46\4\0\2\46"+
    "\20\0\16\46\1\211\14\46\1\0\2\46\1\0\4\46"+
    "\4\0\2\46\20\0\16\46\1\212\14\46\1\0\2\46"+
    "\1\0\4\46\4\0\2\46\20\0\1\46\1\213\31\46"+
    "\1\0\2\46\1\0\4\46\4\0\1\214\1\46\20\0"+
    "\33\46\1\0\2\46\1\0\4\46\4\0\2\46\20\0"+
    "\6\46\1\60\24\46\1\0\2\46\1\0\4\46\4\0"+
    "\2\46\20\0\5\46\1\152\25\46\1\0\2\46\1\0"+
    "\4\46\4\0\2\46\20\0\2\46\1\74\30\46\1\0"+
    "\2\46\1\0\4\46\4\0\1\215\1\46\20\0\33\46"+
    "\1\0\2\46\1\0\4\46\4\0\1\216\1\46\20\0"+
    "\33\46\1\0\2\46\1\0\4\46\4\0\2\46\20\0"+
    "\10\46\1\217\22\46\1\0\2\46\1\0\4\46\4\0"+
    "\2\46\20\0\13\46\1\220\17\46\1\0\2\46\1\0"+
    "\4\46\4\0\2\46\20\0\4\46\1\221\26\46\1\0"+
    "\2\46\1\0\4\46\4\0\2\46\20\0\1\222\32\46"+
    "\1\0\2\46\1\0\2\46\1\223\1\46\4\0\2\46"+
    "\20\0\33\46\1\0\2\46\1\0\4\46\4\0\2\46"+
    "\20\0\13\46\1\224\17\46\1\0\2\46\1\0\2\46"+
    "\1\225\1\46\4\0\2\46\20\0\13\46\1\226\17\46"+
    "\1\0\11\17\1\0\7\17\3\0\10\17\1\0\33\17"+
    "\1\0\11\17\1\0\7\17\3\0\7\17\1\227\1\0"+
    "\33\17\1\0\11\17\1\0\6\17\1\227\3\0\7\17"+
    "\1\227\1\0\33\17\1\0\11\17\1\0\7\17\3\0"+
    "\3\17\1\227\3\17\1\227\1\0\33\17\1\0\11\17"+
    "\1\0\7\17\3\0\4\17\1\227\2\17\1\227\1\0"+
    "\33\17\1\0\11\17\1\0\7\17\3\0\5\17\1\227"+
    "\2\17\1\0\33\17\1\0\11\17\1\0\7\17\3\0"+
    "\6\17\1\227\1\17\1\0\33\17\2\0\1\146\13\0"+
    "\1\147\42\0\7\146\3\0\1\146\56\0\7\146\2\0"+
    "\2\46\1\0\4\46\1\230\1\231\2\0\2\46\20\0"+
    "\33\46\1\0\2\46\1\0\4\46\4\0\2\46\20\0"+
    "\2\46\1\171\30\46\1\0\2\46\1\0\4\46\4\0"+
    "\2\46\20\0\14\46\1\74\16\46\1\0\2\46\1\0"+
    "\2\46\1\232\1\46\4\0\2\46\20\0\33\46\1\0"+
    "\2\46\1\0\2\46\1\74\1\46\4\0\2\46\20\0"+
    "\33\46\1\61\7\233\1\234\1\233\1\0\1\235\6\233"+
    "\3\61\10\233\1\61\33\233\1\61\7\233\1\234\1\233"+
    "\1\0\1\235\6\233\3\61\7\233\1\236\1\61\33\233"+
    "\1\61\7\233\1\234\1\233\1\0\1\235\5\233\1\236"+
    "\3\61\7\233\1\236\1\61\33\233\1\61\7\233\1\234"+
    "\1\233\1\0\1\235\6\233\3\61\3\233\1\236\3\233"+
    "\1\236\1\61\33\233\1\61\7\233\1\234\1\233\1\0"+
    "\1\235\6\233\3\61\4\233\1\236\2\233\1\236\1\61"+
    "\33\233\1\61\7\233\1\234\1\233\1\0\1\235\6\233"+
    "\3\61\5\233\1\236\2\233\1\61\33\233\1\61\7\233"+
    "\1\234\1\233\1\0\1\235\6\233\3\61\6\233\1\236"+
    "\1\233\1\61\33\233\1\0\2\46\1\0\4\46\4\0"+
    "\2\46\20\0\3\46\1\74\27\46\1\0\2\46\1\0"+
    "\4\46\4\0\1\237\1\46\20\0\33\46\12\166\1\240"+
    "\6\166\1\241\47\166\1\0\2\46\1\0\4\46\4\0"+
    "\2\46\20\0\2\46\1\242\30\46\1\0\2\46\1\0"+
    "\2\46\1\243\1\46\4\0\2\46\20\0\33\46\1\0"+
    "\2\46\1\0\4\46\4\0\2\46\20\0\3\46\1\226"+
    "\27\46\1\0\2\46\1\0\4\46\4\0\2\46\20\0"+
    "\12\46\1\244\20\46\1\0\2\46\1\0\4\46\4\0"+
    "\2\46\20\0\1\46\1\245\31\46\1\0\2\46\1\0"+
    "\4\46\4\0\2\46\20\0\5\46\1\246\25\46\1\0"+
    "\2\46\1\0\4\46\4\0\2\46\20\0\12\46\1\247"+
    "\1\250\17\46\1\0\2\46\1\0\4\46\4\0\2\46"+
    "\20\0\13\46\1\251\17\46\1\0\2\46\1\0\4\46"+
    "\4\0\2\46\20\0\4\46\1\252\26\46\1\0\2\46"+
    "\1\0\4\46\4\0\2\46\20\0\1\253\32\46\1\0"+
    "\2\46\1\0\4\46\4\0\2\46\20\0\1\254\32\46"+
    "\1\0\2\46\1\0\4\46\4\0\2\46\20\0\6\46"+
    "\1\74\24\46\1\0\2\46\1\0\2\46\1\255\1\46"+
    "\4\0\2\46\20\0\33\46\1\0\2\46\1\0\4\46"+
    "\4\0\2\46\20\0\6\46\1\256\24\46\1\0\2\46"+
    "\1\0\4\46\4\0\1\46\1\257\20\0\3\46\1\260"+
    "\27\46\1\0\2\46\1\0\4\46\4\0\1\46\1\261"+
    "\20\0\33\46\1\0\2\46\1\0\3\46\1\262\4\0"+
    "\2\46\20\0\33\46\1\0\2\46\1\0\2\46\1\263"+
    "\1\46\4\0\2\46\20\0\33\46\1\0\2\46\1\0"+
    "\4\46\4\0\2\46\20\0\5\46\1\264\25\46\1\0"+
    "\2\46\1\0\4\46\4\0\2\46\20\0\16\46\1\265"+
    "\14\46\1\0\2\46\1\0\4\46\4\0\2\46\20\0"+
    "\13\46\1\266\17\46\1\0\2\46\1\0\4\46\4\0"+
    "\2\46\20\0\12\46\1\267\20\46\1\0\2\46\1\0"+
    "\4\46\4\0\2\46\20\0\11\46\1\270\21\46\1\0"+
    "\2\46\1\0\4\46\4\0\1\271\1\46\20\0\33\46"+
    "\1\0\2\46\1\0\4\46\4\0\2\46\20\0\1\272"+
    "\32\46\1\0\2\46\1\0\4\46\4\0\2\46\20\0"+
    "\21\46\1\273\11\46\1\0\2\46\1\0\4\46\4\0"+
    "\2\46\20\0\6\46\1\274\24\46\1\0\2\46\1\0"+
    "\4\46\4\0\2\46\20\0\17\46\1\275\13\46\1\0"+
    "\2\46\1\0\4\46\4\0\2\46\20\0\22\46\1\74"+
    "\10\46\1\0\2\46\1\0\4\46\4\0\2\46\20\0"+
    "\6\46\1\154\24\46\1\0\2\46\1\0\4\46\4\0"+
    "\2\46\20\0\7\46\1\171\23\46\1\0\11\17\1\0"+
    "\3\17\1\276\3\17\3\0\10\17\1\0\33\17\2\277"+
    "\1\300\1\277\1\300\1\277\1\300\27\277\2\300\2\277"+
    "\1\300\4\277\1\300\3\277\1\300\5\277\7\300\1\277"+
    "\2\301\1\302\1\301\1\302\1\301\1\302\27\301\2\302"+
    "\2\301\1\302\4\301\1\302\3\301\1\302\5\301\7\302"+
    "\1\301\1\0\2\46\1\0\4\46\4\0\2\46\20\0"+
    "\2\46\1\226\30\46\10\0\1\61\2\0\3\61\1\47"+
    "\52\0\1\61\7\233\1\234\1\233\1\0\1\235\2\233"+
    "\1\303\3\233\3\61\10\233\1\61\33\233\1\0\2\46"+
    "\1\0\4\46\4\0\2\46\20\0\3\46\1\304\27\46"+
    "\12\305\1\306\5\305\1\0\1\241\1\307\46\305\12\166"+
    "\1\240\5\166\1\310\1\241\47\166\1\0\2\46\1\0"+
    "\2\46\1\311\1\46\4\0\2\46\20\0\33\46\1\0"+
    "\2\46\1\0\4\46\4\0\1\46\1\154\20\0\17\46"+
    "\1\312\13\46\1\0\2\46\1\0\4\46\4\0\2\46"+
    "\20\0\2\46\1\313\30\46\1\0\2\46\1\0\4\46"+
    "\4\0\1\314\1\46\20\0\33\46\1\0\2\46\1\0"+
    "\4\46\4\0\2\46\20\0\11\46\1\315\21\46\1\0"+
    "\2\46\1\0\4\46\4\0\1\316\1\46\20\0\33\46"+
    "\1\0\2\46\1\0\4\46\4\0\2\46\20\0\1\46"+
    "\1\317\31\46\1\0\2\46\1\0\4\46\4\0\2\46"+
    "\20\0\5\46\1\226\25\46\1\0\2\46\1\0\4\46"+
    "\4\0\1\320\1\46\20\0\33\46\1\0\2\46\1\0"+
    "\4\46\4\0\2\46\20\0\7\46\1\74\23\46\1\0"+
    "\2\46\1\0\4\46\4\0\2\46\20\0\3\46\1\321"+
    "\27\46\1\0\2\46\1\0\4\46\4\0\2\46\20\0"+
    "\11\46\1\322\21\46\1\0\2\46\1\0\4\46\4\0"+
    "\2\46\20\0\2\46\1\323\10\46\1\324\17\46\1\0"+
    "\2\46\1\0\4\46\4\0\1\46\1\325\20\0\33\46"+
    "\1\0\2\46\1\0\4\46\4\0\2\46\20\0\23\46"+
    "\1\74\1\326\1\327\1\330\1\331\3\46\1\0\2\46"+
    "\1\0\2\46\1\332\1\46\4\0\2\46\20\0\33\46"+
    "\1\0\2\46\1\0\4\46\4\0\2\46\20\0\2\46"+
    "\1\333\30\46\1\0\2\46\1\0\4\46\4\0\2\46"+
    "\20\0\2\46\1\315\30\46\1\0\2\46\1\0\4\46"+
    "\4\0\2\46\20\0\13\46\1\250\17\46\1\0\2\46"+
    "\1\0\4\46\4\0\2\46\20\0\15\46\1\334\15\46"+
    "\1\0\2\46\1\0\4\46\4\0\1\46\1\335\20\0"+
    "\33\46\1\0\2\46\1\0\4\46\4\0\1\46\1\336"+
    "\20\0\33\46\1\0\2\46\1\0\2\46\1\337\1\46"+
    "\4\0\2\46\20\0\33\46\1\0\2\46\1\0\4\46"+
    "\4\0\2\46\20\0\4\46\1\224\26\46\1\0\2\46"+
    "\1\0\4\46\4\0\2\46\20\0\1\340\32\46\1\0"+
    "\2\46\1\0\4\46\4\0\2\46\20\0\13\46\1\341"+
    "\17\46\1\0\2\46\1\0\4\46\4\0\2\46\20\0"+
    "\14\46\1\342\16\46\3\0\1\136\12\0\1\343\1\0"+
    "\1\137\1\140\4\0\1\137\1\136\1\141\1\142\1\143"+
    "\1\144\1\137\1\136\33\0\2\277\1\344\1\277\1\344"+
    "\1\277\1\344\1\277\1\345\25\277\2\344\2\277\1\344"+
    "\4\277\1\344\3\277\1\344\5\277\7\344\3\277\1\300"+
    "\1\277\1\300\1\277\1\300\1\277\1\346\25\277\2\300"+
    "\2\277\1\300\4\277\1\300\3\277\1\300\5\277\7\300"+
    "\1\277\2\301\1\347\1\301\1\347\1\301\1\347\2\301"+
    "\1\350\24\301\2\347\2\301\1\347\4\301\1\347\3\301"+
    "\1\347\5\301\7\347\3\301\1\302\1\301\1\302\1\301"+
    "\1\302\2\301\1\351\24\301\2\302\2\301\1\302\4\301"+
    "\1\302\3\301\1\302\5\301\7\302\1\301\3\61\1\155"+
    "\4\61\1\62\1\61\1\0\1\63\2\61\1\352\1\61"+
    "\1\156\1\157\4\61\1\156\1\155\1\160\1\161\1\162"+
    "\1\163\1\156\1\155\33\61\1\0\2\46\1\0\4\46"+
    "\4\0\2\46\20\0\15\46\1\353\15\46\12\305\1\306"+
    "\6\305\1\306\1\305\3\354\55\305\1\306\5\305\1\355"+
    "\1\306\1\305\3\354\55\305\1\306\5\305\1\354\1\241"+
    "\1\307\46\305\12\356\1\357\6\356\1\360\47\356\1\0"+
    "\2\46\1\0\4\46\4\0\2\46\20\0\3\46\1\164"+
    "\27\46\1\0\2\46\1\0\4\46\4\0\2\46\20\0"+
    "\1\361\32\46\1\0\2\46\1\0\4\46\4\0\1\362"+
    "\1\46\20\0\33\46\1\0\2\46\1\0\4\46\4\0"+
    "\2\46\20\0\1\46\1\171\31\46\1\0\2\46\1\0"+
    "\4\46\4\0\1\46\1\74\20\0\33\46\1\0\2\46"+
    "\1\0\4\46\4\0\2\46\20\0\17\46\1\74\13\46"+
    "\1\0\2\46\1\0\4\46\4\0\2\46\20\0\13\46"+
    "\1\363\17\46\1\0\2\46\1\0\4\46\4\0\2\46"+
    "\20\0\11\46\1\364\21\46\1\0\2\46\1\0\4\46"+
    "\4\0\2\46\20\0\5\46\1\365\25\46\1\0\2\46"+
    "\1\0\4\46\4\0\2\46\20\0\1\247\32\46\1\0"+
    "\2\46\1\0\4\46\4\0\1\366\1\46\20\0\33\46"+
    "\1\0\2\46\1\0\4\46\4\0\2\46\20\0\2\46"+
    "\1\367\30\46\1\0\2\46\1\0\4\46\4\0\2\46"+
    "\20\0\25\46\1\74\1\46\1\370\3\46\1\0\2\46"+
    "\1\0\4\46\4\0\2\46\20\0\30\46\1\74\2\46"+
    "\1\0\2\46\1\0\4\46\4\0\2\46\20\0\27\46"+
    "\1\74\3\46\1\0\2\46\1\0\4\46\4\0\2\46"+
    "\20\0\31\46\1\371\1\46\1\0\2\46\1\0\4\46"+
    "\4\0\2\46\20\0\1\46\1\74\31\46\1\0\2\46"+
    "\1\0\4\46\4\0\1\372\1\46\20\0\33\46\1\0"+
    "\2\46\1\0\4\46\4\0\2\46\20\0\13\46\1\373"+
    "\17\46\1\0\2\46\1\0\2\46\1\171\1\46\4\0"+
    "\2\46\20\0\33\46\1\0\2\46\1\0\4\46\4\0"+
    "\2\46\20\0\13\46\1\374\17\46\1\0\2\46\1\0"+
    "\4\46\4\0\2\46\20\0\10\46\1\226\22\46\1\0"+
    "\2\46\1\0\4\46\4\0\1\46\1\154\20\0\33\46"+
    "\1\0\2\46\1\0\4\46\4\0\2\46\20\0\11\46"+
    "\1\74\21\46\1\0\2\46\1\0\4\46\4\0\2\46"+
    "\20\0\1\74\32\46\1\0\2\17\1\227\6\17\1\0"+
    "\3\17\1\375\1\17\1\375\1\376\3\0\1\17\1\375"+
    "\1\227\1\377\1\u0100\1\u0101\1\u0102\1\375\1\136\33\17"+
    "\2\0\1\344\1\0\1\344\1\0\1\344\1\0\1\u0103"+
    "\25\0\2\344\2\0\1\344\4\0\1\344\3\0\1\344"+
    "\5\0\7\344\3\0\1\347\1\0\1\347\1\0\1\347"+
    "\2\0\1\u0103\24\0\2\347\2\0\1\347\4\0\1\347"+
    "\3\0\1\347\5\0\7\347\1\0\1\61\2\233\1\236"+
    "\4\233\1\234\1\233\1\0\1\235\2\233\1\u0104\1\233"+
    "\1\u0104\1\u0105\3\61\1\233\1\u0104\1\236\1\u0106\1\u0107"+
    "\1\u0108\1\u0109\1\u0104\1\155\33\233\1\0\2\46\1\0"+
    "\2\46\1\u010a\1\46\4\0\2\46\20\0\33\46\12\354"+
    "\1\u010b\6\354\1\u010c\47\354\23\355\3\0\43\355\21\0"+
    "\1\360\1\357\46\0\12\356\1\357\5\356\1\310\1\360"+
    "\47\356\1\0\2\46\1\0\4\46\4\0\1\46\1\u010d"+
    "\20\0\33\46\1\0\2\46\1\0\4\46\4\0\1\46"+
    "\1\u010e\20\0\33\46\1\0\2\46\1\0\2\46\1\245"+
    "\1\46\4\0\2\46\20\0\33\46\1\0\2\46\1\0"+
    "\4\46\4\0\2\46\20\0\1\46\1\364\31\46\1\0"+
    "\2\46\1\0\4\46\4\0\2\46\20\0\12\46\1\154"+
    "\20\46\1\0\2\46\1\0\4\46\4\0\2\46\20\0"+
    "\12\46\1\u010f\20\46\1\0\2\46\1\0\4\46\4\0"+
    "\2\46\20\0\23\46\1\74\7\46\1\0\2\46\1\0"+
    "\4\46\4\0\2\46\20\0\25\46\1\74\5\46\1\0"+
    "\2\46\1\0\4\46\4\0\2\46\20\0\1\202\32\46"+
    "\1\0\2\46\1\0\2\46\1\124\1\46\4\0\2\46"+
    "\20\0\33\46\1\0\2\46\1\0\4\46\4\0\2\46"+
    "\20\0\5\46\1\u0110\25\46\1\0\11\17\1\0\3\17"+
    "\1\276\3\17\3\0\7\17\1\227\1\0\33\17\1\0"+
    "\11\17\1\0\3\17\1\276\2\17\1\227\3\0\7\17"+
    "\1\227\1\0\33\17\1\0\11\17\1\0\3\17\1\276"+
    "\3\17\3\0\3\17\1\227\3\17\1\227\1\0\33\17"+
    "\1\0\11\17\1\0\3\17\1\276\3\17\3\0\4\17"+
    "\1\227\2\17\1\227\1\0\33\17\1\0\11\17\1\0"+
    "\3\17\1\276\3\17\3\0\5\17\1\227\2\17\1\0"+
    "\33\17\1\0\11\17\1\0\3\17\1\276\3\17\3\0"+
    "\6\17\1\227\1\17\1\0\33\17\1\61\7\233\1\234"+
    "\1\233\1\0\1\235\2\233\1\303\3\233\3\61\7\233"+
    "\1\236\1\61\33\233\1\61\7\233\1\234\1\233\1\0"+
    "\1\235\2\233\1\303\2\233\1\236\3\61\7\233\1\236"+
    "\1\61\33\233\1\61\7\233\1\234\1\233\1\0\1\235"+
    "\2\233\1\303\3\233\3\61\3\233\1\236\3\233\1\236"+
    "\1\61\33\233\1\61\7\233\1\234\1\233\1\0\1\235"+
    "\2\233\1\303\3\233\3\61\4\233\1\236\2\233\1\236"+
    "\1\61\33\233\1\61\7\233\1\234\1\233\1\0\1\235"+
    "\2\233\1\303\3\233\3\61\5\233\1\236\2\233\1\61"+
    "\33\233\1\61\7\233\1\234\1\233\1\0\1\235\2\233"+
    "\1\303\3\233\3\61\6\233\1\236\1\233\1\61\33\233"+
    "\1\0\2\46\1\0\4\46\4\0\2\46\20\0\2\46"+
    "\1\245\30\46\12\354\1\u010b\5\354\1\0\1\u010c\1\u0111"+
    "\60\354\1\u010b\5\354\1\0\1\u010c\47\354\1\0\2\46"+
    "\1\0\2\46\1\u0112\1\46\4\0\2\46\20\0\33\46"+
    "\1\0\2\46\1\0\4\46\4\0\2\46\20\0\10\46"+
    "\1\74\22\46\1\0\2\46\1\0\4\46\4\0\2\46"+
    "\20\0\11\46\1\u0113\21\46\1\0\2\46\1\0\4\46"+
    "\4\0\1\74\1\46\20\0\33\46\12\354\1\u010b\6\354"+
    "\1\u010c\1\u0111\46\354\1\0\2\46\1\0\4\46\4\0"+
    "\2\46\20\0\11\46\1\u0114\21\46\1\0\2\46\1\0"+
    "\4\46\4\0\1\46\1\u0115\20\0\33\46\1\0\2\46"+
    "\1\0\4\46\4\0\2\46\20\0\1\u0116\32\46\1\0"+
    "\2\46\1\0\4\46\4\0\2\46\20\0\5\46\1\124"+
    "\25\46\1\0\2\46\1\0\4\46\4\0\2\46\20\0"+
    "\6\46\1\u0117\24\46\1\0\2\46\1\0\4\46\4\0"+
    "\2\46\20\0\6\46\1\245\24\46";

  private static int [] zzUnpackTrans() {
    int [] result = new int[14820];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\1\1\11\7\1\1\11\4\1\1\0\6\1\1\11"+
    "\20\1\1\0\11\1\1\0\1\11\2\0\3\1\1\0"+
    "\1\1\1\0\52\1\1\0\1\1\1\0\60\1\2\0"+
    "\1\1\1\0\1\1\1\0\40\1\6\0\40\1\1\0"+
    "\2\1\1\0\4\1\1\0\1\1\3\0\22\1\1\11"+
    "\24\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[279];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
public String lexeme;


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Lexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 202) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public Token yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          zzR = false;
          break;
        case '\r':
          yyline++;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
          }
          break;
        default:
          zzR = false;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { lexeme=yytext()+" "+(yyline+1);return ERROR_CARACTERES_NO_VALIDOS;
            }
          case 12: break;
          case 2: 
            { lexeme=yytext()+" "+(yyline+1); return IDENTIFICADOR;
            }
          case 13: break;
          case 3: 
            { lexeme=yytext()+" "+(yyline+1); return LITERAL;
            }
          case 14: break;
          case 4: 
            { lexeme=yytext()+" "+(yyline+1); return OPERADOR;
            }
          case 15: break;
          case 5: 
            { /* ignore */
            }
          case 16: break;
          case 6: 
            { lexeme=yytext()+" "+(yyline+1);return ERROR_IDENTIFICADOR;
            }
          case 17: break;
          case 7: 
            { lexeme=yytext()+" "+(yyline+1); return PALABRA_RESERVADA;
            }
          case 18: break;
          case 8: 
            { lexeme=yytext()+" "+(yyline+1);return ERROR_COMENTARIO;
            }
          case 19: break;
          case 9: 
            { lexeme=yytext()+" "+(yyline+1); return UNIDAD;
            }
          case 20: break;
          case 10: 
            { lexeme=yytext()+" "+(yyline+1); return TRANSAC;
            }
          case 21: break;
          case 11: 
            { lexeme=yytext()+" "+(yyline+1);return ERROR_HEXADECIMAL;
            }
          case 22: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
