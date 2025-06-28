package com.Megatram.Megatram.Dto;

import java.util.List;

public class ReapprovisionnementResponseDto {
    public Long id;
    public String source;
    public String agent;

    public List<LigneReapprovisionnementResponseDto> lignes;

}
