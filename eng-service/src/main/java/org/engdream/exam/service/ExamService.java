package org.engdream.exam.service;

import org.engdream.base.service.BaseService;
import org.engdream.exam.entity.Exam;
import org.engdream.exam.mapper.ExamMapper;
import org.springframework.stereotype.Service;

/**
* <p>
    * 试卷 服务类
    * </p>
*
* @author Heyx
* @since 2017-04-06
*/
@Service
public class ExamService extends BaseService<Exam, Long> {
    private ExamMapper getExamMapper(){
        return (ExamMapper)baseMapper;
    }
}
