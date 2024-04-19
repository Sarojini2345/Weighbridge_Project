package com.weighbridge.repsitories;

import com.weighbridge.entities.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory,Long> {
    UserHistory findByUserId(String userId);
}
