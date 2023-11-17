package pro.sky.tgbotcatshelter.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.tgbotcatshelter.entity.Shelter;
import pro.sky.tgbotcatshelter.service.ShelterService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация сервиса для работы с приютами.
 */
@Service
public class ShelterServiceImpl implements ShelterService {
    private final Map<Long, Shelter> shelters = new HashMap<>();
    private long nextId = 1;

    /**
     * Получить список всех приютов.
     *
     * @return коллекция всех приютов
     */
    @Override
    public Collection<Shelter> getAllShelters() {
        return shelters.values();
    }

    /**
     * Получить приют по его идентификатору.
     *
     * @param id идентификатор приюта
     * @return информация о приюте с указанным идентификатором или null, если приют не найден
     */
    @Override
    public Shelter getShelterById(Long id) {
        return shelters.getOrDefault(id, null);
    }

    /**
     * Обновить информацию о приюте по его идентификатору.
     *
     * @param id      идентификатор приюта
     * @param shelter новая информация о приюте
     * @return обновленная информация о приюте или null, если приют с указанным идентификатором не найден
     */
    @Override
    public Shelter updateShelter(Long id, Shelter shelter) {
        if (shelters.containsKey(id)) {
            shelter.setId(id);
            shelters.put(id, shelter);
            return shelter;
        }
        return null; // Or handle differently based on your use case
    }

    /**
     * Создать новую запись о приюте.
     *
     * @param shelter информация о новом приюте
     * @return информация о созданном приюте
     */
    @Override
    public Shelter createShelter(Shelter shelter) {
        shelter.setId(nextId++);
        shelters.put(shelter.getId(), shelter);
        return shelter;
    }

    /**
     * Удалить информацию о приюте по его идентификатору.
     *
     * @param id идентификатор приюта для удаления
     * @return удаленная информация о приюте или null, если приют с указанным идентификатором не найден
     */
    @Override
    public Shelter removeShelter(Long id) {
        return shelters.remove(id);
    }
}
