package org.engdream.exam.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;
import org.engdream.base.web.controller.BaseCRUDController;
import org.engdream.exam.entity.Exam;
import org.engdream.exam.service.ExamService;
import java.util.Date;
/**
 * <p>
 * 试卷
 * </p>
 *
 * @author Heyx
 * @since 2017-04-06
 */
@Controller
@RequestMapping("exam/exam")
public class ExamController extends BaseCRUDController<Exam, Long> {

    private ExamService getExamService(){
        return (ExamService)baseService;
    }
	@Override
	protected String getResourcePrefix() {
		return "exam:exam";
	}
	
	@Override
	protected void setCommonDate(Model model) {
		model.addAttribute("booleanList", BooleanEnum.values());
		super.setCommonDate(model);
	}
}
