package com.calcaddpayschoolbackend.service;

import com.calcaddpayschoolbackend.entity.AddPay;
import com.calcaddpayschoolbackend.entity.StaffList;
import com.calcaddpayschoolbackend.repository.StaffListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return staffListRepository.findAll();
    }

    public void deleteStaffList(StaffList staffList) {
        staffListRepository.delete(staffList);
    }

    public void deleteStaffListById(Long id) {
        staffListRepository.deleteById(id);
    }

    public Optional<StaffList> findAll(long id) {
        return staffListRepository.findById(id);
    }
}
