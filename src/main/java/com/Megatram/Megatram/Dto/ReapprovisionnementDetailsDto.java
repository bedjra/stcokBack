package com.Megatram.Megatram.Dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReapprovisionnementDetailsDto {

    public Long id;
    public String source;
    public String agent;
    public LocalDateTime date;
    public List<LigneReapprovisionnementDto> lignes;

}
