package separator;

import java.util.ArrayList;
import java.util.List;

public class FieldSeparator {
    List<String> fieldArray = new ArrayList();

    public FieldSeparator(byte [] answer) throws Exception {

        FromArray ans = new FromArray(answer);
        int comandSize =  ans.ToSize() - 2;

        switch(answer[4]){
            case 0: {
                        int i =  ans.ToInt(5);
                        fieldArray.add(String.valueOf(i));
                        break;
                    }
            case 1: {
                        fieldArray.add(ans.ToString(5,comandSize ));
                        break;
                    }

            case 2:
                STLVParser sp = new STLVParser(ans,fieldArray);
                sp.parce();

                break;
            case 3:
                for(int i =0 ;i<8; i++) {
                    fieldArray.add("ДАTА-" + ans.ToDateString(5+(i*16)));
                    fieldArray.add("ВРЕМЯ-" + ans.ToTimeString(7+(i*16)));
                    fieldArray.add("НОМЕР СМЕНЫ-" + ans.ToShortString(9+(i*16)));
                    fieldArray.add("НОМЕР ДОКУМЕНТА-" + ans.ToShortString(11+(i*16)));
                    fieldArray.add("СУММА-" + ans.ToIntString(13+(i*16)));
                    fieldArray.add("---------");
                }
                break;
            case 4:
                fieldArray.add("ПРИХОД-"+ans.ToIntString(5));
                fieldArray.add("ВОЗВРАТ ПРИХОДА-"+ans.ToIntString(13));
                fieldArray.add("РАСХОД-"+ans.ToIntString(21));
                fieldArray.add("ВОЗВРАТ РАСХОДА-"+ans.ToIntString(29));
                fieldArray.add("ВНЕСЕНИЕ-"+ans.ToIntString(37));
                fieldArray.add("ИНКАССАЦИЯ-"+ans.ToIntString(45));
                fieldArray.add("НАЛИЧНЫЕ-"+ans.ToIntString(53));
                fieldArray.add("КОЛ-ВО ВНЕСЕНИЕ-"+ans.ToShortString(61));
                fieldArray.add("КОЛ-ВО ИНКАССАЦИЯ-"+ans.ToShortString(63));
                fieldArray.add("НОМЕР СМЕНЫ-"+ans.ToShortString(65));
                break;
            default: throw new Exception("Bad answer");
        }
    }

    public List<String> getFieldArray() {
        return fieldArray;
    }

    public int getFieldArrayLen() {
        return fieldArray.size();
    }

    public String getField(int n) {
        return fieldArray.get(n);
    }

}
