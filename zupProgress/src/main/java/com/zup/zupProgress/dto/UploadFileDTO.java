package com.zup.zupProgress.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileDTO {
    private  String fileName;
    private  String fileDowloandUri;
    private  String fileType;
    private long size;
}
