package com.codegym.demo.service.exp;

import com.codegym.demo.model.Exp;
import com.codegym.demo.service.IGeneralService;

public interface IExpService extends IGeneralService<Exp> {
    Iterable<Exp>findAllByUser_id(Long id);
}