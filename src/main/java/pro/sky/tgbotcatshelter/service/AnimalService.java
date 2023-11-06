package pro.sky.tgbotcatshelter.service;

import org.springframework.stereotype.Service;
import pro.sky.tgbotcatshelter.entity.Animal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Сервис по работе с животными.
 */
public interface AnimalService {

    public Animal getById(Long id);

}
