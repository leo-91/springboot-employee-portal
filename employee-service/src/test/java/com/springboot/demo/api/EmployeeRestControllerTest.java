package com.springboot.demo.api;

import com.springboot.demo.jpa.entity.Employee;
import com.springboot.demo.jpa.repo.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeRestControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void getAllEmployee_whenListEmployees_thenReturnJsonArray()
            throws Exception {

        Employee employee1 = new Employee();
        employee1.setFirstName("Anil");
        employee1.setLastName("Gupta");
        employee1.setGender(Employee.GenderEnum.MALE);

        List<Employee> allEmployees = new ArrayList<>();
        allEmployees.add(employee1);

        given(employeeRepository.findAll()).willReturn(allEmployees);

        mvc.perform(get("/employee/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(employee1.getFirstName())));
    }

    @Test
    public void getAllEmployee_whenEmptyListEmployees_thenReturnNoContentStatus() throws Exception {
        given(employeeRepository.findAll()).willReturn(new ArrayList<>());
        mvc.perform(get("/employee/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
