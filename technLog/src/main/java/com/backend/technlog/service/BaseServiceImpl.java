package com.backend.technlog.service;

import com.backend.technlog.base.BaseComponent;
import com.backend.technlog.domain.BaseModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class BaseServiceImpl<T extends BaseModel> extends BaseComponent implements BaseService<T, String> {
    public abstract MongoRepository<T, String> getRepository();

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public T save(T item) {
        logger.trace("Creating {}.", item);
        return getRepository().save(item);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public List<T> saveAll(List<T> items) {
        logger.trace("Creating {}.", items);
        return getRepository().saveAll(items);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void update(T item) {
        logger.trace("Updating {}.", item);
        if (exists(item.getId())) {
            getRepository().save(item);
        } else {
            throw new NoSuchElementException("Could not perform update operation to a non-existent object.");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void delete(T item) {
        logger.trace("Deleting {}.", item);
        if (exists(item.getId())) {
            getRepository().delete(item);
        } else {
            throw new NoSuchElementException("Could not perform delete operation to a non-existent object.");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void deleteById(String id) {
        logger.trace("Deleting item matching id {}.", id);
        if (exists(id)) {
            getRepository().deleteById(id);
        } else {
            throw new NoSuchElementException("Could not perform delete operation to a non-existent object.");
        }
    }

    public boolean exists(T item) {
        logger.trace("Checking if {} exists.", item);
        return exists(item.getId());
    }

    public boolean exists(String id) {
        return getRepository().existsById(id);
    }

    public T findById(String id) {
        logger.trace("Retrieving item with id {}.", id);

        return getRepository().findById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<T> findAll() {
        logger.trace("Retrieving all items.");
        return getRepository().findAll();
    }
}
