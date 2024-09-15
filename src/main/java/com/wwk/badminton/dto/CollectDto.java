package com.wwk.badminton.dto;

import com.wwk.badminton.pojo.Collect;
import lombok.Data;

@Data
public class CollectDto extends Collect {
    private EquiptmentDto equiptmentDto;
}
