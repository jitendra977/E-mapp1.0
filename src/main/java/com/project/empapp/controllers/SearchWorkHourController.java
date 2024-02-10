package com.project.empapp.controllers;

import com.project.empapp.dto.SearchEmployeeForm;
import com.project.empapp.dto.SearchWorkHourForm;
import com.project.empapp.dto.WorkHourDTO;
import com.project.empapp.repositories.WorkHourRepository;
import com.project.empapp.services.WorkHourService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Data
@RequestMapping("/workhourSearch")
public class SearchWorkHourController {

    private final WorkHourService workHourService;

    @GetMapping("")
    public String showWorkhourSearchForm(Model model) {
        // 表示するフォームを返す
        model.addAttribute("searchWorkHourForm", new SearchWorkHourForm());
        return "workhourSearch";
    }

    @PostMapping("")
    public String resultWorkHourSearch(Model model, SearchWorkHourForm searchWorkHourForm) {
        String condition = searchWorkHourForm.getCondition();
        String searchValue = searchWorkHourForm.getSearchValue();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parsedSearchValue = null;
            // 日付の場合はパース
            if (condition.equals("workDay")) {
                parsedSearchValue = dateFormat.parse(searchValue);
            }

            List<WorkHourDTO> workHours = workHourService.findWorkHoursByCondition(parsedSearchValue, searchValue, condition);
            model.addAttribute("workHours", workHours);
        } catch (ParseException e) {
            // パースエラーの場合の処理を追加
            e.printStackTrace();
        }

        return "workhourSearch";
    }
}
