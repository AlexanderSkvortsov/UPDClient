package separator;

import java.util.HashMap;

import static separator.STLVDetails.TLVType.*;

public class STLVDetails {

    public class TLV{
        int tlvType;
        String tlsFullName;
        String tlsName;

        public TLV(int tlvType, String tlsFullName,String tlsName) {
            this.tlvType = tlvType;
            this.tlsName = tlsName;
            this.tlsFullName = tlsFullName;
        }
    }

    final int tF1  =   0x100; // not printed
    final int tF2  =   0x200; // separate name
    final int tF3  =   0x400; // add oper type

    public enum TLVType{
        tINT16,
        tINT32,
        tVLN,
        tFVLN,
        tASCII,
        tSTLV,
        tTIME,
        tABYTE,
        tBYTE,
        tFLAG8,
        tFLAG32,
    };

    private HashMap<Integer,TLV> tlv = new HashMap<Integer,TLV>() {{

        put(1,       new TLV( tSTLV.ordinal(), "ОТЧЕТ О РЕГИСТРАЦИИ", "ОТЧЕТ О РЕГ."));
        put(11,      new TLV( tSTLV.ordinal(), "ОТЧЕТ ОБ ИЗМ. ПАРАМЕТРОВ РЕГИСТРАЦИИ", "ОТЧЕТ О ПЕРЕРЕГ."));
        put(2,       new TLV( tSTLV.ordinal(), "ОТЧЕТ ОБ ОТКРЫТИИ СМЕНЫ", "ОТЧЕТ ОБ ОТКР. СМ."));
        put(21,      new TLV( tSTLV.ordinal(), "ОТЧЕТ О ТЕКУЩЕМ СОСТ. РАСЧЕТОВ", "ОТЧЕТ О РАСЧ."));
        put(3,       new TLV( tSTLV.ordinal(), "КАССОВЫЙ ЧЕК", "КАССОВЫЙ ЧЕК"));
        put(31,      new TLV( tSTLV.ordinal(), "КАССОВЫЙ ЧЕК КОРРЕКЦИИ", "ЧЕК КОРРЕКЦИИ"));
        put(4,       new TLV( tSTLV.ordinal(), "БЛАНК СТРОГОЙ ОТЧЕТНОСТИ", "БСО"));
        put(41,      new TLV( tSTLV.ordinal(), "БЛАНК СТРОГОЙ ОТЧЕТНОСТИ КОРРЕКЦИИ", "БСО КОРРЕКЦИИ"));
        put(5,       new TLV( tSTLV.ordinal(), "ОТЧЕТ О ЗАКРЫТИИ СМЕНЫ", "ОТЧ. О ЗАКР. СМ."));
        put(6,       new TLV( tSTLV.ordinal(), "ОТЧЕТ О ЗАКРЫТИИ ФН", "ОТЧ. О ЗАКР. ФН"));
        put(7,       new TLV( tSTLV.ordinal(), "ПОДТВЕРЖДЕНИЕ", "ПОДТВЕРЖДЕНИЕ"));
        put(1000,    new TLV( tASCII.ordinal(), "наименование документа", "-"));
        put(1001,    new TLV( tBYTE.ordinal(), "пр. автоматического режима", "АВТОМАТ. РЕЖИМ"));
        put(1002,    new TLV( tBYTE.ordinal(), "пр. автономного режима", "АВТОНОМН. РЕЖИМ"));
        put(1002,    new TLV( tBYTE.ordinal(), "пр. автономного режима", "АВТОНОМН. РЕЖИМ"));
        put(1005,    new TLV( tASCII.ordinal(), "адрес оператора перевода", "АДР. ОП. ПЕРЕВОДА"));
        put(1008,    new TLV(tF2 + tASCII.ordinal(), "телефон или электронный адрес покупателя", "-")); // go to 0x45xx
        put(1009,    new TLV(tF1 + tASCII.ordinal(), "адрес расчетов", "-"));
        put(1012,    new TLV(tF1 + tTIME.ordinal(), "дата, время", "-"));
        put(1013,    new TLV( tASCII.ordinal(), "заводской номер ККТ", "ЗН ККТ"));
        put(1016,    new TLV( tASCII.ordinal(), "ИНН оператора перевода ", "ИНН ОП. ПЕРЕВОДА"));
        put(1017,    new TLV( tASCII.ordinal(), "ИНН ОФД", "ИНН ОФД"));
        put(1018,    new TLV( tASCII.ordinal(), "ИНН пользователя", "ИНН"));
        put(1020,    new TLV( tVLN.ordinal(), "сумма расчета, указанного в чеке (БСО)", "ИТОГ"));
        put(1021,    new TLV( tASCII.ordinal(), "кассир", "КАССИР"));
        put(1022,    new TLV(tF1 + tBYTE.ordinal(), "код ответа ОФД", "-"));
        put(1023,    new TLV( tFVLN.ordinal(), "количество предмета расчета", "-"));
        put(1026,    new TLV( tASCII.ordinal(), "наименование оператора перевода", "ОПЕРАТОР ПЕРЕВОДА"));
        put(1030,    new TLV( tASCII.ordinal(), "наименование предмета расчета", "-"));
        put(1031,    new TLV( tVLN.ordinal(), "сумма наличными", "НАЛИЧНЫМИ"));
        put(1036,    new TLV( tASCII.ordinal(), "номер автомата", "АВТОМАТ"));
        put(1037,    new TLV( tASCII.ordinal(), "регистрационный номер ККТ", "РН ККТ"));
        put(1038,    new TLV( tINT32.ordinal(), "номер смены", "СМЕНА"));
        put(1040,    new TLV( tINT32.ordinal(), "номер ФД", "ФД"));
        put(1041,    new TLV( tASCII.ordinal(), "номер ФН", "ФН"));
        put(1042,    new TLV(tF2 + tINT32.ordinal(), "номер чека за смену", "-"));  //go to 0x44XX
        put(1043,    new TLV(tF1 + tVLN.ordinal(), "стоим. пр. расчета с уч. скид. и нацен.", "-"));
        put(1044,    new TLV( tASCII.ordinal(), "операция платежного агента", "ОП. АГЕНТА"));
        put(1046,    new TLV( tASCII.ordinal(), "наименование ОФД", "ОФД"));
        put(1048,    new TLV( tASCII.ordinal(), "наименование пользователя", "-"));
        put(1050,    new TLV( tBYTE.ordinal(), "пр. исчерпания ресурса ФН", "РЕСУРС ФН МЕНЕЕ 30 ДНЕЙ"));
        put(1051,    new TLV( tBYTE.ordinal(), "пр. окончания ресурса ФН ", "РЕСУРС ФН МЕНЕЕ 3 ДНЕЙ"));
        put(1052,    new TLV( tBYTE.ordinal(), "пр. заполнения памяти ФН", "ПАМЯТЬ ФН ЗАПОЛНЕНА"));
        put(1053,    new TLV( tBYTE.ordinal(), "пр. прев. времени ожидания ответа ОФД", "ОФД НЕ ОТВЕЧАЕТ"));
        put(1054,    new TLV( tF1+tBYTE.ordinal(), "пр. расчета", "-"));
        put(1055,    new TLV( tFLAG8.ordinal(), "сист. налогообложения", "СНО"));
        put(1056,    new TLV(tF1 + tBYTE.ordinal(), "пр. шифрования", "ШФД"));
        put(1057,    new TLV(tF1 + tFLAG8.ordinal(), "пр. агента", "пр. агента"));
        put(1059,    new TLV(tF1 + tSTLV.ordinal(), "предмет расчета", "-"));
        put(1060,    new TLV( tASCII.ordinal(), "адрес сайта ФНС", "САЙТ ФНС"));
        put(1062,    new TLV( tBYTE.ordinal(), "системы налогообложения", "СНО"));
        put(1068,    new TLV( tSTLV.ordinal(), "сообщение оператора для ФН", "-"));
        put(1073,    new TLV( tASCII.ordinal(), "телефон платежного агента", "ТЛФ. ПЛ. АГЕНТА"));
        put(1074,    new TLV( tASCII.ordinal(), "телефон опер. по приему платежей", "ТЛФ. ОП. ПР. ПЛАТЕЖА"));
        put(1075,    new TLV( tASCII.ordinal(), "телефон оператора перевода", "ТЛФ. ОП. ПЕРЕВОДА"));
        put(1077,    new TLV( tABYTE.ordinal(), "ФПД", "ФП"));
        put(1078,    new TLV( tABYTE.ordinal(), "ФПО", "-"));
        put(1079,    new TLV( tVLN.ordinal(), "цена за единицу с уч. скид. и нацен.", "-"));
        put(1081,    new TLV( tVLN.ordinal(), "сумма безналичными", "БЕЗНАЛИЧНЫМИ"));
        put(1084,    new TLV( tSTLV.ordinal(), "доп. реквизит пользователя", "-"));
        put(1085,    new TLV( tASCII.ordinal(), "наименование доп. реквизита пользователя", "-"));
        put(1086,    new TLV( tASCII.ordinal(), "значение доп. реквизита пользователя", "-"));
        put(1097,    new TLV( tINT32.ordinal(), "количество непереданных ФД", "НЕПЕРЕДАННЫХ ФД"));
        put(1098,    new TLV( tTIME.ordinal(), "дата первого из непереданных ФД", "ФД НЕ ПЕРЕДАНЫ С"));
        put(1101,    new TLV( tBYTE.ordinal(), "код причины перерегистрации", "ИЗМ. СВЕД. О ККТ"));
        put(1102,    new TLV( tVLN.ordinal(), "сумма НДС чека по ставке 18%", "СУММА НДС 18%"));
        put(1103,    new TLV( tVLN.ordinal(), "сумма НДС чека по ставке 10%", "СУММА НДС 10%"));
        put(1104,    new TLV( tVLN.ordinal(), "сумма расчета по чеку с НДС по ставке 0%", "СУММА С НДС 0%"));
        put(1105,    new TLV( tVLN.ordinal(), "сумма расчета по чеку без НДС", "СУММА БЕЗ НДС"));
        put(1106,    new TLV( tVLN.ordinal(), "сумма НДС чека по расч. ставке 18/118", "СУММА НДС 18/118"));
        put(1107,    new TLV( tVLN.ordinal(), "сумма НДС чека по расч. ставке 10/110", "СУММА НДС 10/110"));
        put(1108,    new TLV( tBYTE.ordinal(), "пр. ККТ для расчетов только в Интернет", "ККТ ДЛЯ ИНТЕРНЕТ"));
        put(1109,    new TLV( tBYTE.ordinal(), "пр. расчетов за услуги", "ККТ ДЛЯ УСЛУГ"));
        put(1110,    new TLV( tBYTE.ordinal(), "пр. АС БСО", "АС БСО"));
        put(1111,    new TLV( tINT32.ordinal(), "общее количество ФД за смену", "ФД ЗА СМЕНУ"));
        put(1116,    new TLV( tINT32.ordinal(), "номер перв. непереданного документа", "ПЕРВЫЙ НЕПЕРЕДАННЫЙ ФД"));
        put(1117,    new TLV( tASCII.ordinal(), "адрес эл. почты отправителя чека", "ЭЛ. АДР. ОТПРАВИТЕЛЯ"));
        put(1118,    new TLV(tF2 + tINT32.ordinal(), "количество кассовых чеков (БСО) за смену", "-")); // go to 0x46xx
        put(1126,    new TLV( tBYTE.ordinal(), "пр. проведения лотереи", "ПРОВЕДЕНИЕ ЛОТЕРЕИ"));
        put(1129,    new TLV(tF1 + tSTLV.ordinal(), "счетчики операций приход", "-"));
        put(1130,    new TLV(tF1 + tSTLV.ordinal(), "счетчики операций возврат прихода", "-"));
        put(1131,    new TLV(tF1 + tSTLV.ordinal(), "счетчики операций расход", "-"));
        put(1132,    new TLV(tF1 + tSTLV.ordinal(), "счетчики операций возврат расхода", "-"));
        put(1133,    new TLV(tF1 + tSTLV.ordinal(), "счетчики операций по чекам коррекции (БСО коррекции)", "ЧЕКИ(БСО) КОРРЕКЦИИ"));// goto 0x4Exx
        put(1134,    new TLV(tF2 + tINT32.ordinal(), "количество чеков (БСО) со всеми пр.ами расчетов", "-")); // goto 0x47xx
        put(1135,    new TLV(tF3 + tF2 + tINT32.ordinal(), "количество чеков по пр.у расчетов", "-")); /// goto 0x48xx
        put(1136,    new TLV(tF3 + tVLN.ordinal(), "итоговая сумма в чеках (БСО) наличными", "СУММА $ НАЛИЧН."));
        put(1138,    new TLV(tF3 + tVLN.ordinal(), "итоговая сумма в чеках (БСО) безналичными", "СУММА $ БЕЗНАЛИЧ."));
        put(1139,    new TLV(tF3 + tVLN.ordinal(), "сумма НДС по ставке 18%", "СУММА НДС 18% $"));
        put(1140,    new TLV(tF3 + tVLN.ordinal(), "сумма НДС по ставке 10%", "СУММА НДС 10% $"));
        put(1141,    new TLV(tF3 + tVLN.ordinal(), "сумма НДС по расч. ставке 18/118", "СУМ. НДС РАСЧ. 18/118 $"));
        put(1142,    new TLV(tF3 + tVLN.ordinal(), "сумма НДС по расч. ставке 10/110", "СУМ. НДС РАСЧ. 10/110 $"));
        put(1143,    new TLV(tF3 + tVLN.ordinal(), "сумма расчетов с НДС по ставке 0%", "ОБОРОТ С НДС 0% $"));
        put(1144,    new TLV(tF2 + tINT32.ordinal(), "количество чеков коррекции", "ЧЕКОВ(БСО)")); // goto 0x4axx
        put(1145,    new TLV(tF1 + tSTLV.ordinal(), "счетчики коррекций приход", "-"));
        put(1146,    new TLV(tF1 + tSTLV.ordinal(), "счетчики коррекций расход", "-"));
        put(1148,    new TLV(tF3 + tINT32.ordinal(), "количество самостоятельных корректировок", "КОЛ. КОРР. $ САМОСТ."));
        put(1149,    new TLV(tF3 + tINT32.ordinal(), "количество корректировок по предписанию", "КОЛ. КОРР. $ ПО ПРЕДПИС."));
        put(1151,    new TLV(tF3 + tVLN.ordinal(), "сумма коррекций НДС по ставке 18%", "СУММА КОР. НДС 18% $"));
        put(1152,    new TLV(tF3 + tVLN.ordinal(), "сумма коррекций НДС по ставке 10%", "СУММА КОР. НДС 10% $"));
        put(1153,    new TLV(tF3 + tVLN.ordinal(), "сумма коррекций НДС по расч. ставке 18/118", "СУММА КОР. НДС РАСЧ. 18/118 $"));
        put(1154,    new TLV(tF3 + tVLN.ordinal(), "сумма коррекций НДС по расч. ставке 10/110", "СУММА КОР. НДС РАСЧ. 10/110 $"));
        put(1155,    new TLV(tF3 + tVLN.ordinal(), "сумма коррекций с НДС по ставке 0%", "КОР. ОБОРОТА С НДС0% $"));
        put(1157,    new TLV(tF1 + tSTLV.ordinal(), "счетчики итогов ФН", "-"));
        put(1158,    new TLV(tF1 + tSTLV.ordinal(), "счетчики итогов непереданных ФД", "НЕПЕРЕДАНО"));
        put(1162,    new TLV( tASCII.ordinal(), "код товара", "КТ"));
        put(1171,    new TLV( tASCII.ordinal(), "телефон поставщика", "ТЛФ. ПОСТ."));
        put(1173,    new TLV( tBYTE.ordinal(), "тип коррекции", "ТИП КОРРЕКЦИИ"));
        put(1174,    new TLV( tSTLV.ordinal(), "основание для коррекции", "ОСН. ДЛЯ КОРР."));
        put(1177,    new TLV(tF1 + tASCII.ordinal(), "наименование основания для коррекции", "-"));
        put(1178,    new TLV(tF1 + tTIME.ordinal(), "дата документа основания для коррекции", "-"));
        put(1179,    new TLV(tF1 + tASCII.ordinal(), "номер документа основания для коррекции", "-"));
        put(1183,    new TLV(tF3 + tVLN.ordinal(), "сумма расчетов без НДС", "ОБОРОТ БЕЗ НДС $"));
        put(1184,    new TLV(tF3 + tVLN.ordinal(), "сумма коррекций без НДС", "КОР. ОБОРОТА БЕЗ НДС $"));
        put(1187,    new TLV( tASCII.ordinal(), "место расчетов", "МЕСТО РАСЧЕТОВ:"));
        put(1188,    new TLV( tASCII.ordinal(), "версия ККТ", "ВЕР. ККТ"));
        put(1189,    new TLV( tBYTE.ordinal(), "версия ФФД ККТ", "ФФД ККТ"));
        put(1190,    new TLV( tBYTE.ordinal(), "версия ФФД ФН", "ФФД ФН"));
        put(1191,    new TLV(tF1 + tASCII.ordinal(), "дополнительный реквизит предмета расчета", "-"));
        put(1192,    new TLV(tF1 + tASCII.ordinal(), "дополнительный реквизит чека (БСО)", "-"));
        put(1193,    new TLV( tBYTE.ordinal(), "пр. проведения азартных игр", "ПРОВЕДЕНИЕ АЗАРТНОЙ ИГРЫ"));
        put(1194,    new TLV(tF1 + tSTLV.ordinal(), "счетчики итогов смены", "-"));
        put(1196,    new TLV(tF1 + tASCII.ordinal(), "QR-код", "-"));
        put(1197,    new TLV(tF1 + tASCII.ordinal(), "единица измерения предмета расчета", "-"));
        put(1198,    new TLV(tF1 + tVLN.ordinal(), "размер НДС за единицу предмета расчета", "-"));
        put(1199,    new TLV(tF1 + tBYTE.ordinal(), "ставка НДС ", "-"));
        put(1200,    new TLV(tF1 + tVLN.ordinal(), "сумма НДС за предмет расчета", "-"));
        put(1201,    new TLV( tVLN.ordinal(), "общая итоговая сумма в чеках (БСО)", "СУММА $ всего"));
        put(1203,    new TLV( tASCII.ordinal(), "ИНН кассира", "ИНН КАССИРА"));
        put(1205,    new TLV( tFLAG32.ordinal(), "коды причин изменения сведений о ККТ", "ИЗМ. СВЕД. О ККТ"));
        put(1206,    new TLV( tFLAG8.ordinal(), "сообщение оператора", "-"));
        put(1207,    new TLV( tBYTE.ordinal(), "пр. торговли подакцизными товарами", "ПОДАКЦИЗНЫЕ ТОВАРЫ"));
        put(1208,    new TLV( tASCII.ordinal(), "сайт для получения чека", "САЙТ ЧЕКОВ"));
        put(1209,    new TLV(tF1 + tBYTE.ordinal(), "версия ФФД", "-"));
        put(1212,    new TLV( tBYTE.ordinal(), "пр. предмета расчета", "-"));
        put(1213,    new TLV( tINT16.ordinal(), "ресурс ключей ФП", "РЕСУРС КЛЮЧЕЙ"));
        put(1214,    new TLV( tBYTE.ordinal(), "пр. способа расчета", " "));
        put(1215,    new TLV(tF1 + tVLN.ordinal(), "сумма предоплатой (зачетом аванса)", "ПРЕДВАРИТЕЛЬНАЯ ОПЛАТА (АВАНС)"));
        put(1216,    new TLV(tF1 + tVLN.ordinal(), "сумма постоплатой (в кредит)", "ПОСЛЕДУЮЩАЯ ОПЛАТА (КРЕДИТ)"));
        put(1217,    new TLV(tF1 + tVLN.ordinal(), "сумма встречным предоставлением", "ИНАЯ ФОРМА ОПЛАТЫ"));
        put(1218,    new TLV(tF1 + tVLN.ordinal(), "итоговая сумма предоплатами (авансами)", "СУММА $ АВАНСОВ"));
        put(1219,    new TLV(tF1 + tVLN.ordinal(), "итоговая сумма постоплатами (кредитами)", "СУММА $ КРЕДИТОВ"));
        put(1220,    new TLV(tF1 + tVLN.ordinal(), "итоговая сумма встречными предоставлениями", "СУММА $ ИНОЙ ФОРМОЙ ОПЛАТЫ"));
        put(1221,    new TLV( tBYTE.ordinal(), "пр. установки принтера в автомате", "ПРИНТЕР В АВТОМАТЕ"));
        put(1222,    new TLV( tFLAG8.ordinal(), "пр. агента по предмету расчета", "-"));
        put(1223,    new TLV( tSTLV.ordinal(), "данные агента", "-"));
        put(1224,    new TLV( tSTLV.ordinal(), "данные поставщика", "-"));
        put(1225,    new TLV( tASCII.ordinal(), "наименование поставщика", "-"));
        put(1226,    new TLV( tASCII.ordinal(), "ИНН поставщика", "ИНН ПОСТАВЩИКА"));
        put(1227,    new TLV( tASCII.ordinal(), "покупатель", "ПОКУПАТЕЛЬ"));
        put(1228,    new TLV( tASCII.ordinal(), "ИНН покупателя", "ИНН ПОКУПАТЕЛЯ"));
        put(1229,    new TLV( tVLN.ordinal(), "акциз", "АКЦИЗ"));
        put(1230,    new TLV( tASCII.ordinal(), "код страны происхождения", "КОД СТРАНЫ"));
        put(1231,    new TLV( tASCII.ordinal(), "номер таможенной декларации", "ДЕКЛАРАЦИЯ"));
        put(1232,    new TLV( tSTLV.ordinal(), "счетчики по признаку 'ВОЗВРАТ ПРИХОДА'", "-"));
        put(1233,    new TLV( tSTLV.ordinal(), "счетчики по признаку 'ВОЗВРАТ РАСХОДА'", "-"));
        put(0x4000,  new TLV( tASCII.ordinal(), "ОСН", "ОСН"));
        put(0x4001,  new TLV( tASCII.ordinal(), "УСН доход", "УСН доход"));
        put(0x4002,  new TLV( tASCII.ordinal(), "УСН доход-расход", "УСН доход-расход"));
        put(0x4003,  new TLV( tASCII.ordinal(), "ЕНВД", "ЕНВД"));
        put(0x4004,  new TLV( tASCII.ordinal(), "ЕСXН", "ЕСXН"));
        put(0x4005,  new TLV( tASCII.ordinal(), "Патент", "Патент"));
        put(0x4101,  new TLV( tASCII.ordinal(), "Замена ФН", "Замена ФН"));
        put(0x4102,  new TLV( tASCII.ordinal(), "Замена ОФД", "Замена ОФД"));
        put(0x4103,  new TLV( tASCII.ordinal(), "Изменение реквизитов", "Изменение реквизитов"));
        put(0x4104,  new TLV( tASCII.ordinal(), "Изменение настроек ККТ", "Изменение настроек ККТ"));
        put(0x4200,  new TLV( tASCII.ordinal(), "БАНК.ПЛ.АГЕНТ", "БАНК.ПЛ.АГЕНТ"));
        put(0x4201,  new TLV( tASCII.ordinal(), "БАНК.ПЛ.СУБАГЕНТ", "БАНК.ПЛ.СУБАГЕНТ"));
        put(0x4202,  new TLV( tASCII.ordinal(), "ПЛ.АГЕНТ", "ПЛ.АГЕНТ"));
        put(0x4203,  new TLV( tASCII.ordinal(), "ПЛ.СУБАГЕНТ", "ПЛ.СУБАГЕНТ"));
        put(0x4204,  new TLV( tASCII.ordinal(), "ПОВЕРЕННЫЙ", "ПОВЕРЕННЫЙ"));
        put(0x4205,  new TLV( tASCII.ordinal(), "КОМИССИОНЕР", "КОМИССИОНЕР"));
        put(0x4206,  new TLV( tASCII.ordinal(), "АГЕНТ", "АГЕНТ"));
        put(0x4301,  new TLV( tASCII.ordinal(), "Ошибка ФЛК", "Ошибка ФЛК"));
        put(0x4306,  new TLV( tASCII.ordinal(), "ТРЕБ. НАСТР. ККТ", "ТРЕБ. НАСТР. ККТ"));
        put(0x4307,  new TLV( tASCII.ordinal(), "ОФД АННУЛИРОВАН", "ОФД АННУЛИРОВАН"));
        put(0x4403,  new TLV( tASCII.ordinal(), "ЧЕК", "ЧЕК"));
        put(0x441F,  new TLV( tASCII.ordinal(), "ЧЕК КОР.", "ЧЕК КОР."));
        put(0x4404,  new TLV( tASCII.ordinal(), "БСО", "БСО"));
        put(0x4429,  new TLV( tASCII.ordinal(), "БСО КОР.", "БСО КОР."));
        put(0x4500,  new TLV( tASCII.ordinal(), "ТЕЛ. ПОКУПАТЕЛЯ", "ТЕЛ. ПОКУПАТЕЛЯ"));
        put(0x4501,  new TLV( tASCII.ordinal(), "ЭЛ. АДР. ПОКУПАТЕЛЯ", "ЭЛ. АДР. ПОКУПАТЕЛЯ"));
        put(0x4600,  new TLV( tASCII.ordinal(), "ЧЕКОВ ЗА СМЕНУ", "ЧЕКОВ ЗА СМЕНУ"));
        put(0x4601,  new TLV( tASCII.ordinal(), "БСО ЗА СМЕНУ", "БСО ЗА СМЕНУ"));
        put(0x4700,  new TLV( tASCII.ordinal(), "ВСЕГО ЧЕКОВ", "ВСЕГО ЧЕКОВ"));
        put(0x4701,  new TLV( tASCII.ordinal(), "ВСЕГО БСО", "ВСЕГО БСО"));
        put(0x4800,  new TLV( tASCII.ordinal(), "КОЛ.ЧЕКОВ $", "КОЛ.ЧЕКОВ $"));
        put(0x4801,  new TLV( tASCII.ordinal(), "КОЛ.БСО $", "КОЛ.БСО $"));
        put(0x4900,  new TLV( tASCII.ordinal(), "ПРИХ.", "ПРИХ."));
        put(0x4901,  new TLV( tASCII.ordinal(), "ВОЗВР. ПРИХ.", "ВОЗВР. ПРИХ."));
        put(0x4902,  new TLV( tASCII.ordinal(), "РАСХ.", "РАСХ."));
        put(0x4903,  new TLV( tASCII.ordinal(), "ВОЗВР. РАСХ.", "ВОЗВР. РАСХ."));
        put(0x4A00,  new TLV( tASCII.ordinal(), "ЧЕКОВ КОРРЕКЦИИ", "ЧЕКОВ КОРРЕКЦИИ"));
        put(0x4A01,  new TLV( tASCII.ordinal(), "БСО КОРРЕКЦИИ", "БСО КОРРЕКЦИИ"));
        put(0x4B00,  new TLV( tASCII.ordinal(), "ПРЕДОПЛАТА 100%", "ПРЕДОПЛАТА 100%"));
        put(0x4B01,  new TLV( tASCII.ordinal(), "ПРЕДОПЛАТА", "ПРЕДОПЛАТА"));
        put(0x4B02,  new TLV( tASCII.ordinal(), "АВАНС", "АВАНС"));
        put(0x4B03,  new TLV( tASCII.ordinal(), "ПОЛНЫЙ РАСЧЕТ", "ПОЛНЫЙ РАСЧЕТ"));
        put(0x4B04,  new TLV( tASCII.ordinal(), "ЧАСТИЧНЫЙ РАСЧЕТ И КРЕДИТ", "ЧАСТИЧНЫЙ РАСЧЕТ И КРЕДИТ"));
        put(0x4B05,  new TLV( tASCII.ordinal(), "ПЕРЕДАЧА В КРЕДИТ", "ПЕРЕДАЧА В КРЕДИТ"));
        put(0x4B06,  new TLV( tASCII.ordinal(), "ОПЛАТА КРЕДИТА", "ОПЛАТА КРЕДИТА"));
        put(0x4C00,  new TLV( tASCII.ordinal(), "НДС 18%", "НДС 18%"));
        put(0x4C01,  new TLV( tASCII.ordinal(), "НДС 10%", "НДС 10%"));
        put(0x4C02,  new TLV( tASCII.ordinal(), "НДС 18/118", "НДС 18/118"));
        put(0x4C03,  new TLV( tASCII.ordinal(), "НДС 10/110", "НДС 10/110"));
        put(0x4C04,  new TLV( tASCII.ordinal(), "НДС 0%", "НДС 0%"));
        put(0x4D00,  new TLV( tASCII.ordinal(), "САМОСТОЯТЕЛЬНО", "САМОСТОЯТЕЛЬНО"));
        put(0x4D01,  new TLV( tASCII.ordinal(), "ПО ПРЕДПИСАНИЮ", "ПО ПРЕДПИСАНИЮ"));
        put(0x4E00,  new TLV( tASCII.ordinal(), "ЧЕКИ КОРРЕКЦИИ", "ЧЕКИ КОРРЕКЦИИ"));
        put(0x4E01,  new TLV( tASCII.ordinal(), "БСО КОРРЕКЦИИ", "БСО КОРРЕКЦИИ"));
        put(0x5000,  new TLV( tASCII.ordinal(), "", "доход от долевого участия в других организациях"));
        put(0x5001,  new TLV( tASCII.ordinal(), "", "доход  в виде курсовой разницы, образующейся вследствие отклонения курса продажи (покупки) иностранной валюты от официального курса"));
        put(0x5002,  new TLV( tASCII.ordinal(), "", "доход  в виде подлежащих уплате должником штрафов, пеней и (или) иных санкций за нарушение договорных обязательств"));
        put(0x5003,  new TLV( tASCII.ordinal(), "", "доход от сдачи имущества (включая земельные участки) в аренду (субаренду)"));
        put(0x5004,  new TLV( tASCII.ordinal(), "", "доход от предоставления в пользование прав на результаты интеллектуальной деятельности"));
        put(0x5005,  new TLV( tASCII.ordinal(), "", "доход в виде процентов, полученных по договорам займа и другим долговым обязательствам"));
        put(0x5006,  new TLV( tASCII.ordinal(), "", "доход в виде сумм восстановленных резервов"));
        put(0x5007,  new TLV( tASCII.ordinal(), "", "доход в виде безвозмездно полученного имущества (работ, услуг) или имущественных прав"));
        put(0x5008,  new TLV( tASCII.ordinal(), "", "доход в виде дохода, распределяемого в пользу налогоплательщика при его участии в простом товариществе"));
        put(0x5009,  new TLV( tASCII.ordinal(), "", "доход в виде дохода прошлых лет, выявленного в отчетном (налоговом) периоде"));
        put(0x500a,  new TLV( tASCII.ordinal(), "", "доход в виде положительной курсовой разницы"));
        put(0x500b,  new TLV( tASCII.ordinal(), "", "доход в виде основных средств и нематериальных активов, безвозмездно полученных атомными станциями"));
        put(0x500c,  new TLV( tASCII.ordinal(), "", "доход в виде стоимости полученных материалов при ликвидации выводимых из эксплуатации основных средств"));
        put(0x500d,  new TLV( tASCII.ordinal(), "", "доход в виде использованных не по целевому назначению имущества, работ, услуг"));
        put(0x500e,  new TLV( tASCII.ordinal(), "", "доход в виде использованных не по целевому назначению средств, предназначенных для формирования резервов по обеспечению безопасности производств"));
        put(0x500f,  new TLV( tASCII.ordinal(), "", "доход в виде сумм, на которые уменьшен уставной (складочный) капитал (фонд) организации"));
        put(0x5010,  new TLV( tASCII.ordinal(), "", "доход в виде сумм возврата от некоммерческой организации ранее уплаченных взносов (вкладов)"));
        put(0x5011,  new TLV( tASCII.ordinal(), "", "доход в виде сумм кредиторской задолженности, списанной в связи с истечением срока исковой давности или по другим основаниям"));
        put(0x5012,  new TLV( tASCII.ordinal(), "", "доход в виде доходов, полученных от операций с производными финансовыми инструментами"));
        put(0x5013,  new TLV( tASCII.ordinal(), "", "доход в виде стоимости излишков материально производственных запасов и прочего имущества, которые выявлены в результате инвентаризации"));
        put(0x5014,  new TLV( tASCII.ordinal(), "", "доход в виде стоимости продукции СМИ и книжной продукции, подлежащей замене при возврате либо при списании"));
        put(0x5015,  new TLV( tASCII.ordinal(), "", "доход в виде сумм корректировки прибыли налогоплательщика"));
        put(0x5016,  new TLV( tASCII.ordinal(), "", "доход в виде возвращенного денежного эквивалента недвижимого имущества и (или) ценных бумаг, переданных на пополнение целевого капитала некоммерческой организации"));
        put(0x5017,  new TLV( tASCII.ordinal(), "", "доход в виде разницы между суммой налоговых вычетов из сумм акциза и указанных сумм акциза"));
        put(0x5018,  new TLV( tASCII.ordinal(), "", "доход в виде прибыли контролируемой иностранной компании"));
        put(0x5019,  new TLV( tASCII.ordinal(), "", "взносы на ОПС"));
        put(0x501a,  new TLV( tASCII.ordinal(), "", "взносы на ОСС в связи с нетрудоспособностью"));
        put(0x501b,  new TLV( tASCII.ordinal(), "", "взносы на ОМС"));
        put(0x501c,  new TLV( tASCII.ordinal(), "", "взносы на ОСС от несчастных случаев"));
        put(0x501d,  new TLV( tASCII.ordinal(), "", "пособие по временной нетрудоспособности"));
        put(0x501e,  new TLV( tASCII.ordinal(), "", "платежи по добровольному личному страхованию"));
    }};

    public TLVType getTLVType(int num){
        return  TLVType.values()[tlv.get(num).tlvType &0xff];
    }

    public String getTLVFullName(int num){
        return tlv.get(num).tlsFullName;
    }

}
