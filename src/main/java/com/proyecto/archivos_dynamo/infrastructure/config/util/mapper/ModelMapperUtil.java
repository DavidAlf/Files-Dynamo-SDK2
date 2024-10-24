package com.proyecto.archivos_dynamo.infrastructure.config.util.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.proyecto.archivos_dynamo.infrastructure.config.util.tags.Mapper;

@Mapper
public class ModelMapperUtil {

	private ModelMapper modelMapper;

	public ModelMapperUtil(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public <D, T> D map(final T entity, Class<D> outClass) {
		return modelMapper.map(entity, outClass);
	}

	public <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
		return entityList.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
	}

}