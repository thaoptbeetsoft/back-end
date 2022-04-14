package com.codegym.demo.service.cv;

import com.codegym.demo.model.CV;
import com.codegym.demo.service.IGeneralService;

public interface ICVService extends IGeneralService<CV> {
    Iterable<CV> getAllByUserId(Long id);
}
