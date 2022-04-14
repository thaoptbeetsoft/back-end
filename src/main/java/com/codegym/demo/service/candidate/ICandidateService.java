package com.codegym.demo.service.candidate;

import com.codegym.demo.model.Candidate;
import com.codegym.demo.service.IGeneralService;

public interface ICandidateService extends IGeneralService<Candidate> {
    Iterable<Candidate> findAllByPostId(Long id);

    Boolean existsByCv_IdAndPostId(Long cvId, Long postId);

}
