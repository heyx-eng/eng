package org.engdream.exam.service.impl;

import org.engdream.exam.entity.Exam;
import org.engdream.exam.mapper.ExamMapper;
import org.engdream.exam.service.ExamService;
import org.engdream.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 试卷 服务实现类
 * </p>
 *
 * @author Heyx
 * @since 2017-04-06
 */
@Service
public class ExamServiceImpl extends BaseServiceImpl<Exam, Long> implements ExamService {

    public ExamMapper getExamMapper(){
        return (ExamMapper)baseMapper;
    }

}
