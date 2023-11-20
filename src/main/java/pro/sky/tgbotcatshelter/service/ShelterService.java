package pro.sky.tgbotcatshelter.service;

import pro.sky.tgbotcatshelter.entity.Shelter;

import java.util.Collection;

/**
 * Сервис по работе приюта.
 */
public interface ShelterService {
    Collection<Shelter> getAllShelters();

    Shelter getShelterById(Long id);

    Shelter updateShelter(Long id, Shelter shelter);

    Shelter createShelter(Shelter shelter);

    Shelter removeShelter(Long id);
}
