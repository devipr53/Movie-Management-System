package service;

import Utils.ListUtils;
import entities.AllotmentDetails;
import entities.MovieDetails;
import entities.MultiplexDetails;
import entities.MultiplexScreenDetails;
import models.AllotmentDto;
import org.modelmapper.ModelMapper;
import repo.AllotmentRepo;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class AllotmentDetailsService {
    @Inject
    ModelMapper mapper;

    @Inject
    AllotmentRepo allotmentRepo;

    @Inject
    ListUtils listUtils;


    public AllotmentDto addAllotmentDetails(AllotmentDto allotmentDto) {
        return mapper.map(allotmentRepo.insertOrUpdateAllotmentDetails(mapper.map(allotmentDto, AllotmentDetails.class)), AllotmentDto.class);
    }

    // To get all available movies
    public List<AllotmentDto> getAllAllotmentDetails(){
        List<AllotmentDto> allotments= new ArrayList<>();
        List<AllotmentDetails> allotmentList=this.allotmentRepo.getAllotmentlist();
        allotments=allotmentList.stream()
                .map(allotment -> new AllotmentDto(allotment.getAllotmentId(),allotment.getMultiplexName(),
                        allotment.getMovieName(),allotment.getAvailableFrom(),allotment.getAvailableTo(),
                        allotment.getScreenName(),allotment.getMovieId(),allotment.getMultiplexId(),allotment.getTicketCost()))
                .collect(Collectors.toList());
        return allotments;
    }

    public void removeAllotmentById(Integer allotmentId){
        this.allotmentRepo.deleteAllotmentById(allotmentId);
    }

    public AllotmentDto findAllotmentDetailsById(Integer allotmentId) {
        return mapper.map(this.allotmentRepo.findAllotmentDetailsById(allotmentId), AllotmentDto.class);
    }

    public List<String> getAllMovieNames(){
        List<String> movieNames=new ArrayList<>();
        List<MovieDetails> moviesList= this.listUtils.getMovielist();
        movieNames.add("----    Please Select  ----");
        if(moviesList.size()>0){
            for(int i=0;i<moviesList.size();i++){
                movieNames.add(moviesList.get(i).getMovieName());
            }
        }
        return movieNames;
    }

    public List<String> getAllMultiplexNames(){
        List<String> multiplexNames=new ArrayList<>();
        List<MultiplexDetails> multiplexList= this.listUtils.getMultiplexlist();
        multiplexNames.add("----    Please Select  ----");
        if(multiplexList.size()>0){
            for(int i=0;i<multiplexList.size();i++){
                multiplexNames.add(multiplexList.get(i).getMultiplexName());
            }
        }
        return multiplexNames;
    }

    public List<String> getScreenNamesByMultiplexName(String multiplexName){
        List<String> screenNames=new ArrayList<>();
        List<MultiplexScreenDetails> screenDetailsList= this.listUtils.findScreenDetailsByMultiName(multiplexName);
        screenNames.add("----    Please Select  ----");
        System.out.println("screenDetailsList "+screenDetailsList.toString());
        if(screenDetailsList!=null){
            for(int i=0;i<screenDetailsList.size();i++){
                screenNames.add(screenDetailsList.get(i).getScreenName());
            }
        }
        return screenNames;
    }


}
