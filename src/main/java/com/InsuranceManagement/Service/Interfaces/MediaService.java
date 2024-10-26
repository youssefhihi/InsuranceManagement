package com.InsuranceManagement.Service.Interfaces;

import com.InsuranceManagement.entity.Media;

import java.util.List;

public interface MediaService {
    List<Media> createMultipleMedia(List<Media> medias);
}
