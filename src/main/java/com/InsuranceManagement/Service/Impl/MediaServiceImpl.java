package com.InsuranceManagement.Service.Impl;

import com.InsuranceManagement.Repository.MediaRepository;
import com.InsuranceManagement.Service.Interfaces.MediaService;
import com.InsuranceManagement.entity.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    @Autowired
    public MediaServiceImpl(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }


    @Override
    public List<Media> createMultipleMedia(List<Media> medias){
        return mediaRepository.saveAll(medias);

    }
}
