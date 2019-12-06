package separator;


public class TLVClass{
    int tlvID;
    int tlvSize;
    byte[] tlvData;

    public TLVClass(int tlvID, int tlvSize, byte[] tlvData) {
        this.tlvID = tlvID;
        this.tlvSize = tlvSize;
        this.tlvData = tlvData;
    }

}