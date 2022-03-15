package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.StaffList;
import com.calcaddpayschoolbackend.exception.NoSuchEntityException;
import com.calcaddpayschoolbackend.repository.StaffListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffListService {
    private final StaffListRepository staffListRepository;

    public void createStaffList(StaffList staffList) {
        staffListRepository.save(staffList);
    }

    public void updateStaffList(StaffList staffList) {
        staffListRepository.save(staffList);
    }

    public List<StaffList> getAllStaffLists() {
        return staffListRepository.findAllByOrderByIdAsc();
    }

    public void deleteStaffList(StaffList staffList) {
        staffListRepository.delete(staffList);
    }

    public void deleteStaffListById(Long id) {
        staffListRepository.deleteById(id);
    }

    public StaffList findStaffListById(long id) {
        return staffListRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(String.format("Штатное с id %d не найден", id)));
    }

    public void calcPercentSalary() {
        System.out.println("Посчитали премию");
    }
}
