package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.Position;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;

    public void createPosition(Position position) {
        positionRepository.save(position);
    }

    public void updatePosition(Position position) {
        positionRepository.save(position);
    }

    public List<Position> getAllPositions() {
        return positionRepository.findAll();
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
