package com.restaurantapi.restaurantapi.builder;

import com.restaurantapi.restaurantapi.dto.MediaDTO;
import com.restaurantapi.restaurantapi.entity.Media;

public class MediaDTOBuilder extends Builder {

    private byte[] fileContent;
    private String fileName;

    @Override
    public MediaDTO build(){
        MediaDTO mediaDTO=new MediaDTO();
        mediaDTO.setId(super.id);
        mediaDTO.setFileContent(this.fileContent);
        mediaDTO.setFileName(this.fileName);
        return mediaDTO;
    }
    public MediaDTOBuilder fileName(String name){
        this.fileName=name;
        return this;
    }
    public MediaDTOBuilder id(int id){
        super.id=id;
        return this;
    }
    public MediaDTOBuilder fileContent(byte[] fileContent){
        this.fileContent=fileContent;
        return this;
    }

}
