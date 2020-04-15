package service;

import entities.MultiplexDetails;
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
        return mapper.map(multiplexRepo.insertOrUpdateMultiplexDetails(mapper.map(multiplexDto, MultiplexDetails.class)), MultiplexDto.class);
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
}
