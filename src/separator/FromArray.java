package separator;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FromArray {
    byte [] array ;

    public FromArray(byte[] array) {
        this.array = array;
    }


    public TLVIDClass ToTLVID(int offs){
        int tlvID = ToShort(offs);
        int tlvSize = ToShort(offs+2);
        return new TLVIDClass(tlvID,tlvSize);
    }

    public long ToLong(int offs ){
        long l = (array[offs ]&0xff)+
                ((array[offs+1 ]&0xff)<<8)+
                ((array[offs+2 ]&0xff)<<16)+
                ((array[offs+3 ]&0xff)<<24)+
                ((array[offs+4 ]&0xff)<<32)+
                ((array[offs+5 ]&0xff)<<40)+
                ((array[offs+6 ]&0xff)<<48)+
                ((array[offs+7 ]&0xff)<<56);
        return l;
    }

    public int ToInt(int offs ){
        int i = (array[offs ]&0xff)+
                ((array[offs+1 ]&0xff)<<8)+
                ((array[offs+2 ]&0xff)<<16)+
                ((array[offs+3 ]&0xff)<<24);

        return i;
    }

    public int ToShort(int offs ){
        int i = (array[offs ]&0xff)+
                ((array[offs+1 ]&0xff)<<8);
        return i;
    }

    public int ToSize(){
        return ToShort(1);
    }

    public String ToString(int offs, int len ){
        return new String(array,offs,len);
    }

    public String ToTLVString(int offs, int len ) throws UnsupportedEncodingException {

        byte[] data = new byte[len];
        System.arraycopy(array,offs+4,data,0,len);
        return  new String(data, "cp866");

    }


    public String ToShortString(int offs){
        return String.valueOf(ToShort(offs));
    }

    public String ToTLVShortString(int offs){
        return String.valueOf(ToShort(offs+4));
    }

    public String ToByteString(int offs){
        return String.valueOf(array[offs]);
    }

    public String ToTLVByteString(int offs){
        return String.valueOf(array[offs+4]);
    }

    public String ToDateString(int offs){
        int i = ToShort(offs);
        int day = i & 0x1f;
        int month =  (i >> 5) & 0xf;
        int year = 1996 + (i >> 9);
        return String.valueOf(day)+String.valueOf(month)+String.valueOf(year);
    }

    public String ToTimeString(int offs){
        int i = ToShort(offs);
        int hour = i / 60;
        int min = i % 60;

        return String.valueOf(hour)+String.valueOf(min);

    }

    public String ToIntString(int offs){

        return String.valueOf(ToInt(offs));
    }


    public String ToTLVIntString(int offs){

        return String.valueOf(ToInt(offs+4));
    }

    private  long arrayToLong(int offs, int len) {
        long l = 0;

        if (len!=0) {
            for (int i = len-1; i >=0; i--) {
              l<<=8;
              l+=(array[i+offs]&0x0ff);
            }
        }
        return l;
    }

    private String getStringValue(long value, double exponenta){
        float f = value ;
        return String.valueOf(f/exponenta);
    }

    public String ToTLVVLN(int offs, int len){
        return getStringValue(arrayToLong(offs+4,len),100);
    }

    public String ToTLVFVLN(int offs, int len){
        double exponenta = Math.pow(10,array[offs+4]);

        return getStringValue(arrayToLong(offs+5,len-1),exponenta );
    }

    public String ToTLVDate(int offs, int len){
        long timeStamp = ToInt(offs+4);

        // time format
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");

        // timezone
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));

        Date date = new java.util.Date(timeStamp*1000);
        return sdf.format(date);
    }

    public String ToTLVArray(int offs, int len){

        StringBuffer s= new StringBuffer("");
        offs+=4;

        for (int i = 0;i<len;i++) {
            int b = (array[offs++]&0xff);
            s.append(String.format("%02x", b)+" ");
        }

        return s.toString();
    }

    public int getSize(){
        return array.length;
    }



}
