package com.example.spels.service;

import com.example.spels.model.PageDocument;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PageDocumentCrudService implements CrudService<PageDocument>{

    @Override
    public PageDocument getById(Integer id) {
        return null;
    }

    @Override
    public Collection<PageDocument> getAll() {
        return null;
    }

    @Override
    public void create(PageDocument item) {

    }

    @Override
    public void update(PageDocument item) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
