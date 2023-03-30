package com.example.dolphin.application.service;

import com.example.dolphin.application.dto.output.ConcernOutput;
import com.example.dolphin.domain.model.Concern;
import com.example.dolphin.domain.repository.ConcernRepository;
import com.example.dolphin.domain.repository.UserRepository;
import com.example.dolphin.domain.specs.ConcernSpec;
import com.example.dolphin.domain.specs.UserSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王景阳
 * @date 2022/11/10 18:50
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConcernService {

    private final ConcernRepository concernRepository;

    private final UserRepository userRepository;

    public List<ConcernOutput> getAllConcern(String userName) {
        List<Concern> concerns = concernRepository.findAll(ConcernSpec.userName(userName));
        return concerns.stream().map(ConcernOutput::of).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean concern(String userName, String concernedUserName) {
        Concern concern = new Concern(userRepository.getBy(UserSpec.userName(userName)), userRepository.getBy(UserSpec.userName(concernedUserName)));
        concernRepository.save(concern);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean unconcern(String userName, String concernedUserName) {
        Concern concern = concernRepository.getBy(ConcernSpec.userName(userName).and(ConcernSpec.concernedUserName(concernedUserName)));
        concernRepository.delete(concern);
        return true;
    }
}
