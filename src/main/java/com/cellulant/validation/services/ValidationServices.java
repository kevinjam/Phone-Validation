package com.cellulant.validation.services;

import com.cellulant.validation.model.ValidateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class ValidationServices {

    @Autowired
    private RedisTemplate<String , ValidateModel> redisTemplate;

    private static final String REDIS_PREFIX_VALIDATION="validation";

    private static final String REDIS_KEY_SEPARATOR =":";


    public List<ValidateModel> findByPattern(final String pattern){
        return getValuesOperations().multiGet(redisTemplate.keys(getRedisKey(pattern)));
    }

    public ValidateModel findById(final String validateID){
        final ValidateModel validateModel = getValuesOperations().get(getRedisKey(UUID.fromString(validateID).toString()));
        if (validateModel == null){
            throw new NotFoundException("User does not exist in the DB");
        }
        return validateModel;
    }


    public void save (final ValidateModel validateModel){
        validateModel.setId(UUID.randomUUID().toString());
        getValuesOperations().set(getRedisKey(validateModel.getId()), validateModel);
    }

    public void update(final ValidateModel validateModel){
        findById(validateModel.getId());
        getValuesOperations().set(getRedisKey(validateModel.getId()), validateModel);
    }

    public void delete(final String validateID){
        if (!redisTemplate.delete(getRedisKey(UUID.fromString(validateID).toString()))){
            System.out.println("User does not exist in the DB");
            throw new NotFoundException("User does not exist in the DB");
        }
    }



    private String getRedisKey(final String validateID) {
        return REDIS_PREFIX_VALIDATION + REDIS_KEY_SEPARATOR + validateID;
    }

    private ValueOperations<String,ValidateModel> getValuesOperations() {

        return redisTemplate.opsForValue();
    }

}
