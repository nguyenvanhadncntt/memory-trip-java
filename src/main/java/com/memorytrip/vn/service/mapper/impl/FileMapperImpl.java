package com.memorytrip.vn.service.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.memorytrip.vn.domain.File;
import com.memorytrip.vn.service.dto.FileDTO;
import com.memorytrip.vn.service.mapper.FileMapper;

@Component
public class FileMapperImpl implements FileMapper {

	@Override
	public List<File> toEntity(List<FileDTO> dtoList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FileDTO> toDto(List<File> entityList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileDTO toDto(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File toEntity(FileDTO fileDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
