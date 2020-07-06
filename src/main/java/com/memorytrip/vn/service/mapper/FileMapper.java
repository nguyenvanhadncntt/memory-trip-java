package com.memorytrip.vn.service.mapper;


import com.memorytrip.vn.domain.File;
import com.memorytrip.vn.service.dto.FileDTO;

/**
 * Mapper for the entity {@link File} and its DTO {@link FileDTO}.
 */
public interface FileMapper extends EntityMapper<FileDTO, File> {

    FileDTO toDto(File file);

    File toEntity(FileDTO fileDTO);

    default File fromId(Long id) {
        if (id == null) {
            return null;
        }
        File file = new File();
        file.setId(id);
        return file;
    }
}
