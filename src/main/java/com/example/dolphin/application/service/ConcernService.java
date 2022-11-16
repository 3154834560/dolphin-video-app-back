package com.example.dolphin.application.service;

import com.example.dolphin.application.dto.output.ConcernOutput;
import com.example.dolphin.domain.entity.Concern;
import com.example.dolphin.domain.repository.ConcernRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王景阳
 * @date 2022/11/10 18:50
 */
@Service
public class ConcernService {

    @Autowired
    ConcernRepository concernRepository;

    @Autowired
    UserService userService;

    @Transactional(rollbackFor = Exception.class)
    public List<ConcernOutput> getAllConcern(String userName) {
        List<Concern> concerns = concernRepository.findAllByUserName(userName);
        return userService.getBy(concerns.stream().map(Concern::getUserName).collect(Collectors.toList()))
                .stream().map(ConcernOutput::of).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean concern(String userName, String concernedUserName) {
        Concern concern = new Concern(userName, concernedUserName);
        concernRepository.save(concern);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean unconcern(String userName, String concernedUserName) {
        concernRepository.deleteByUserNameAndConcernedUserName(userName, concernedUserName);
        return true;
    }
}
