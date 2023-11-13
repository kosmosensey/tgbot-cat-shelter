package pro.sky.tgbotcatshelter.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.tgbotcatshelter.entity.Animal;
import pro.sky.tgbotcatshelter.exception.AnimalNotFoundException;
import pro.sky.tgbotcatshelter.repository.AnimalRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AnimalServiceImplTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalServiceImpl animalService;

    @Test
    public void testGetById() {
        Long id = 1L;
        Animal animal = new Animal();
        animal.setId(id);
        when(animalRepository.findById(id)).thenReturn(Optional.of(animal));

        Animal result = animalService.getById(id);

        assertEquals(animal, result);
    }

    @Test
    public void testGetById_notFound() {
        Long id = 1L;
        when(animalRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(pro.sky.tgbotcatshelter.exception.AnimalNotFoundException.class,
                () -> animalService.getById(id), "Ожидалось исключение AnimalNotFoundException");
    }

    @Test
    public void testGetAll() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal());
        animals.add(new Animal());
        when(animalRepository.findAll()).thenReturn(animals);

        Collection<Animal> result = animalService.getAll();

        assertEquals(animals, result);
    }

    @Test
    public void testCreate() {
        Animal animal = new Animal();
        when(animalRepository.save(animal)).thenReturn(animal);

        Animal result = animalService.create(animal);

        assertEquals(animal, result);
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        Animal existingAnimal = new Animal();
        existingAnimal.setId(id);
        Animal updatedAnimal = new Animal();
        updatedAnimal.setId(id);
        updatedAnimal.setName("New Name");
        when(animalRepository.findById(id)).thenReturn(Optional.of(existingAnimal));
        when(animalRepository.save(updatedAnimal)).thenReturn(updatedAnimal);

        Animal result = animalService.update(id, updatedAnimal);

        assertEquals(updatedAnimal, result);
    }

    @Test
    public void testUpdate_notFound() {
        Long id = 1L;
        Animal updatedAnimal = new Animal();
        updatedAnimal.setId(id);
        when(animalRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(AnimalNotFoundException.class,
                () -> animalService.update(id, updatedAnimal),
                "Ожидалось исключение AnimalNotFoundException");
    }

    @Test
    public void testRemove() {
        Long id = 1L;
        Animal existingAnimal = new Animal();
        existingAnimal.setId(id);
        when(animalRepository.findById(id)).thenReturn(Optional.of(existingAnimal));

        Animal result = animalService.remove(id);

        assertEquals(existingAnimal, result);
        verify(animalRepository).delete(existingAnimal);
    }

    @Test
    public void testRemoveNotFound() {
        long id = 1L;
        when(animalRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(AnimalNotFoundException.class,
                () -> animalService.remove(id),
                "Ожидалось исключение AnimalNotFoundException");
    }
}
