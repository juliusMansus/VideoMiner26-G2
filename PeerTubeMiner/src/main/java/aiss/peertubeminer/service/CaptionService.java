package aiss.peertubeminer.service;

import aiss.peertubeminer.mapper.CaptionMapper;
import aiss.peertubeminer.model.peertube.Caption;
import aiss.peertubeminer.model.peertube.CaptionList;
import aiss.peertubeminer.model.videominer.VMCaption;
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

    @Value("${peertube.baseurl}")
    private String baseUrl;

    public List<VMCaption> getCaptions(String videoId){
        CaptionList captionList = restTemplate.getForObject(
                baseUrl+"/videos/"+ videoId + "/captions", CaptionList.class);
        List<VMCaption> captions = new ArrayList<>();
        if(captionList != null && captionList.getData() != null){
            for(Caption caption : captionList.getData()){
                captions.add(CaptionMapper.toVMCaption(caption));
            }
        }
        return captions;
    }
}
