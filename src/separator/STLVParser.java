package separator;

import java.util.ArrayList;
import java.util.List;

import separator.STLVDetails.TLVType;

public class STLVParser {
    private FromArray fr;
    private List<String> fieldArray ;
    private int tlvIndex;
    private STLVDetails sd = new STLVDetails();


    public STLVParser(FromArray fr, List<String> fieldArray ) {
        this.fr=fr;
        this.fieldArray = fieldArray;
    }

    private boolean runParcer(TLVIDClass tlv ) throws Exception {
        TLVType tlvType;

        try {
             tlvType = sd.getTLVType(tlv.tlvID);
        }
        catch (Exception e)
        {
            System.out.println("tlvIndex "+tlvIndex);
            throw new Exception("tlv Exception");

        }

        String s = "";

        switch (tlvType){
            case    tINT16:
            case    tINT32:
            case    tBYTE:
            case    tFLAG8:
            case    tFLAG32:
                                switch (tlv.tlvSize){
                                    case 1: s=fr.ToTLVByteString(tlvIndex);
                                         break;
                                    case 2:
                                        s=fr.ToTLVShortString(tlvIndex);
                                        break;
                                    case 4:
                                        s=fr.ToTLVIntString(tlvIndex);
                                        break;
                                }

                                break;
            case    tVLN:
                                s =  fr.ToTLVVLN(tlvIndex,tlv.tlvSize);
                                break;
            case    tFVLN:
                                s =  fr.ToTLVFVLN(tlvIndex,tlv.tlvSize);
                                break;

            case    tTIME:
                                s=fr.ToTLVDate(tlvIndex,tlv.tlvSize);
                                break ;
            case    tABYTE:
                                s=fr.ToTLVArray(tlvIndex,tlv.tlvSize);
                                break;
            case    tASCII:
                                s=fr.ToTLVString(tlvIndex,tlv.tlvSize);
                                break;
            case    tSTLV:
                                break;
        }


        if (TLVType.tSTLV.equals(tlvType)) {

            fieldArray.add(sd.getTLVFullName(tlv.tlvID)+" "+s);

            int sizeSTLV = tlv.tlvSize;
            tlvIndex +=  4;
            while (sizeSTLV != 0 ) {

                    TLVIDClass tlvidClass = fr.ToTLVID(tlvIndex);
                    int saveIndex = tlvIndex;
                    runParcer(tlvidClass);

                    int n = tlvidClass.tlvSize+4;
                    sizeSTLV -=n;
                    tlvIndex=saveIndex;
                    tlvIndex += n;
                }

        } else
            fieldArray.add(sd.getTLVFullName(tlv.tlvID)+" < "+s+" >");


        return true;
    }

    public boolean parce(){

        tlvIndex = 5;

        try {
            runParcer(fr.ToTLVID(tlvIndex));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }

    public List<String> getFieldArray() {
        return fieldArray;
    }
}
