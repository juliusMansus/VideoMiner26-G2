package aiss.dailyMotionMiner.service;

import aiss.dailyMotionMiner.mapper.CaptionMapper;
import aiss.dailyMotionMiner.model.dailymotion.Subtitle;
import aiss.dailyMotionMiner.model.dailymotion.SubtitleList;
import aiss.dailyMotionMiner.model.videominer.VMCaption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaptionService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${dailymotion.baseurl}")
    private String baseUrl;

    public List<VMCaption> getCaptions(String videoId){
        SubtitleList subtitleList = restTemplate.getForObject(
                baseUrl+"/video/"+ videoId + "/subtitles", SubtitleList.class);
        List<VMCaption> captions = new ArrayList<>();
        if(subtitleList != null && subtitleList.getData() != null){
            for(Subtitle subtitle : subtitleList.getData()){
                captions.add(CaptionMapper.toVMCaption(subtitle));
            }
        }
        return captions;
    }
}
