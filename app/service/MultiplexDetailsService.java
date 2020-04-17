package service;

import entities.MultiplexDetails;
import entities.MultiplexScreenDetails;
import models.MultiplexDto;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class MultiplexDetailsService {
    @Inject
    private repo.MultiplexRepo multiplexRepo;

    @Inject
    ModelMapper mapper;

     public MultiplexDto addMultiplexDetails(MultiplexDto multiplexDto) {
         MultiplexDetails multiDetails=multiplexRepo.insertOrUpdateMultiplexDetails(mapper.map(multiplexDto, MultiplexDetails.class));
         addMultiplexScreenDetails(multiDetails.getMultiplexId(),Integer.parseInt(multiDetails.getNoOfScreens()),multiplexDto.getEditInd());
         return mapper.map(multiDetails, MultiplexDto.class);
    }

    // To get all available multiplex
    public List<MultiplexDto> getAllMultiplexDetails(){
        List<MultiplexDto> allMultiplexList= new ArrayList<>();
        List<MultiplexDetails> multiplexList=this.multiplexRepo.getAllMultiplexlist();
        allMultiplexList=multiplexList.stream()
                .map(multi -> new MultiplexDto(multi.getMultiplexId(),multi.getMultiplexName(),multi.getMultiplexAddress(),
                        multi.getNoOfScreens(),multi.getTicketCost(),multi.getMovieId())).collect(Collectors.toList());
        return allMultiplexList;
    }

    // To get multiplex details by name
    public List<MultiplexDto> getMultiplexlistByName(String searchParam){
        List<MultiplexDto> allMultiplexList= new ArrayList<>();
        List<MultiplexDetails> multiplexList=this.multiplexRepo.getMultiplexlistByName(searchParam);
        allMultiplexList=multiplexList.stream()
                .map(multi -> new MultiplexDto(multi.getMultiplexId(),multi.getMultiplexName(),multi.getMultiplexAddress(),
                        multi.getNoOfScreens(),multi.getTicketCost(),multi.getMovieId())).collect(Collectors.toList());
        return allMultiplexList;
    }

    public void removeMultiplexById(Integer multiplexId){
        this.multiplexRepo.deleteMuliplexById(multiplexId);
    }

    public MultiplexDto findMultiplexDetailsById(Integer multiplexId) {
        return mapper.map(this.multiplexRepo.findMultiplexDetailsById(multiplexId), MultiplexDto.class);
    }

    public List<MultiplexScreenDetails> generateScreenNames(int multiId,int noOfScreens){
        List<MultiplexScreenDetails> screenList =new ArrayList<>();
        if(multiId > 0 && noOfScreens> 0){
            for (int i=0;i<noOfScreens;i++){
                String screenName= "Screen "+(i+1);
                screenList.add(new MultiplexScreenDetails(multiId,screenName));
            }
        }
        return screenList;
    }

    public void addMultiplexScreenDetails(int multiId,int noOfScreens,String editInd){
        System.out.println("edit Ind : "+editInd);
        if(editInd != null && editInd.equalsIgnoreCase("Y")){
            System.out.println("edit multiId : "+multiId);
            this.multiplexRepo.deleteScreenByMuliplexById(multiId);
        }
        List<MultiplexScreenDetails> screenList=generateScreenNames(multiId,noOfScreens);
        for(MultiplexScreenDetails screenDetails : screenList){
            this.multiplexRepo.insertOrUpdateScreenDetails(screenDetails);
        }
    }
}
