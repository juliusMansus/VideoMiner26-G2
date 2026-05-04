package aiss.dailymotionminer.mapper;

import aiss.dailymotionminer.model.dailymotion.Subtitle;
import aiss.dailymotionminer.model.videominer.VMCaption;

public class CaptionMapper {
    public static VMCaption toVMCaption(Subtitle caption){
        VMCaption newCaption= new VMCaption();
        newCaption.setId(caption.getId());
        newCaption.setName(caption.getId());
        if(caption.getLanguage() != null){
            newCaption.setLanguage(caption.getLanguage());
        }
        return newCaption;
    }
}
