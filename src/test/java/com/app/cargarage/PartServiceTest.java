package com.app.cargarage;

import com.app.cargarage.model.Part;
import com.app.cargarage.repository.PartRepository;
import com.app.cargarage.service.PartServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class PartServiceTest {

    @Autowired
    PartServiceImpl partService;
    @MockBean
    PartRepository partRepository;

    @Test
    void savePartTest() {
        Part part = Part.builder()
                .id(1)
                .name("Brake")
                .price(500)
                .build();

        when(partRepository.save(part)).thenReturn(part);
        assertEquals(part, partService.save(part).getResult());
    }

    @Test
    void listOfPartsTest() {
        Part part = Part.builder()
                .id(1)
                .name("Brake")
                .price(500)
                .build();

        List<Part> partsList = new ArrayList<>();
        partsList.add(part);

        when(partRepository.findAll()).thenReturn(partsList);
        assertEquals(partsList, partService.list().getResult());
    }

    @Test
    void deletePartTest() {
        Part part = Part.builder()
                .id(1)
                .name("Brake")
                .price(500)
                .stock(100)
                .build();

        when(partRepository.findById(part.getId())).thenReturn(Optional.of(part));
        assertEquals(200, partService.deletePart(1).getStatusCode());
    }

    @Test
    void updatePartTest() {
        Part part = Part.builder()
                .id(1)
                .name("Brake")
                .price(500)
                .stock(100)
                .build();

        Part updatedPart = Part.builder()
                .id(1)
                .name("Brake")
                .price(50000)
                .stock(200)
                .build();

        when(partRepository.findById(part.getId())).thenReturn(Optional.of(part));
        when(partRepository.saveAndFlush(updatedPart)).thenReturn(updatedPart);
        assertEquals(updatedPart, partService.updatePart(updatedPart).getResult());
    }

    @Test
    void changeStockOfPartTest() {
        Part part = Part.builder()
                .id(1)
                .name("Brake")
                .price(500)
                .stock(100)
                .build();
        when(partRepository.save(part)).thenReturn(part);
        when(partRepository.findById(part.getId())).thenReturn(Optional.of(part));
        assertEquals(200, partService.changeStockOfPart(1, 400).getStatusCode());
    }
}
