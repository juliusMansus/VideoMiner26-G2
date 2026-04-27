package aiss.peerTubeMiner.mapper;

import aiss.peerTubeMiner.model.peertube.Caption;
import aiss.peerTubeMiner.model.videominer.VMCaption;

public class CaptionMapper {
    public static VMCaption toVMCaption(Caption caption) {
        VMCaption newCaption = new VMCaption();
        newCaption.setId(caption.getCaptionPath());
        newCaption.setName(caption.getCaptionPath());
        if (caption.getLanguage() != null)
            newCaption.setLanguage(caption.getLanguage().getId());
        return newCaption;
    }
}
