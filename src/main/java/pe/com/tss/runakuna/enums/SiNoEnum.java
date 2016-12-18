package pe.com.tss.runakuna.enums;

import pe.com.tss.runakuna.util.EnumBase;
import pe.com.tss.runakuna.util.EnumUtils;

import java.util.List;

/**
 * Created by josediaz on 8/11/2016.
 */
public enum SiNoEnum implements EnumBase<SiNoEnum> {
    SI("S", "Si"),
    NO("N", "No");


    private String code;
    private String label;

    SiNoEnum(String code, String label) {
        this.code = code;
        this.label = label;
    }
    public static SiNoEnum resolve(boolean flag) {
        return flag?SI:NO ;
    }

    public static List<SiNoEnum> findAll() {
        return EnumUtils.findAll(SiNoEnum.class);
    }

    public static SiNoEnum findByCode(String value) {
        SiNoEnum result = EnumUtils.findByCode(SiNoEnum.class, value);
        if (result==null && value!=null && value.equals("1")){
            return SI;
        }
        else if (result==null && value!=null && value.equals("0")){
            return NO;
        }
        return result;
    }


    public static SiNoEnum findByValue(String value){
        SiNoEnum[] array= SiNoEnum.values();
        SiNoEnum result=null;
        for (int i=0;i<array.length;i++){
            if(array[i].getLabel().equalsIgnoreCase(value)){
                result=array[i];
                break;
            }
        }
        return result;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getLabel() {
        return label;
    }

}
