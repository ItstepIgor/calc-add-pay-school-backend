package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.Position;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;


    @Transactional
    public void createPosition(Position position) {
        checkSorting(position.getSorting());
        positionRepository.save(position);
    }


    public void checkSorting(int sorting) {
        List<Position> positions = getAllPositions();
        for (int i = positions.size() - 1; i > 0; i--) {
            if (positions.get(i).getSorting() >= sorting) {
                int sortingNumber = positions.get(i).getSorting();
                positionRepository.updatePositionSorting(positions.get(i).getId(), sortingNumber + 1);
            } else {
                break;
            }
        }
    }

    public void updatePosition(Position position) {
        positionRepository.save(position);
    }

    public List<Position> getAllPositions() {
        return positionRepository.findAll(Sort.by(Sort.Direction.ASC, "sorting"));
    }

    public void deletePosition(Position position) {
        positionRepository.delete(position);
    }

    public void deletePositionById(Long id) {
        positionRepository.deleteById(id);
    }

    public Position findPositionById(long id) {
        return positionRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(String.format("Должность с id %d не найден", id)));
    }

}
