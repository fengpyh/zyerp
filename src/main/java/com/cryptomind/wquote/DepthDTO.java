package com.cryptomind.wquote;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DepthDTO implements Serializable {
    private static final long serialVersionUID = 4115539161221407599L;
    private List<DepthElementDTO> buyDepth;
    private List<DepthElementDTO> sellDepth;
}
