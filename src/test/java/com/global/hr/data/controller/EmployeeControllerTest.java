package com.global.hr.data.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.hr.data.controller.EmployeeController;
import com.global.hr.data.model.entity.Department;
import com.global.hr.data.model.entity.Employee;
import com.global.hr.data.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(EmployeeController.class)
class  EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void testGetByIDSuccess() throws Exception {
        // Mocking data
        Employee mockEmployee = new Employee(1L, "Shrief", "Ali", 50000.0, new Department(1L, "IT"));

        when(employeeService.getEmp(1)).thenReturn(mockEmployee);

        mockMvc.perform(get("/empData/get/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Shrief"))
                .andExpect(jsonPath("$.lastName").value("Ali"))
                .andExpect(jsonPath("$.salary").value(50000.0))
                .andExpect(jsonPath("$.department.id").value(1))
                .andExpect(jsonPath("$.department.name").value("IT"));
    }

    @Test
    void testFindAll() throws Exception {
        List<Employee> mockEmployees = Arrays.asList(
                new Employee(1L, "John", "Doe", 50000.0, new Department(1L, "IT")),
                new Employee(2L, "Jane", "Smith", 60000.0, new Department(2L, "HR"))
        );

        when(employeeService.findAll()).thenReturn(mockEmployees);

        mockMvc.perform(get("/empData/findAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].salary").value(50000.0))
                .andExpect(jsonPath("$[0].department.id").value(1))
                .andExpect(jsonPath("$[0].department.name").value("IT"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].firstName").value("Jane"))
                .andExpect(jsonPath("$[1].lastName").value("Smith"))
                .andExpect(jsonPath("$[1].salary").value(60000.0))
                .andExpect(jsonPath("$[1].department.id").value(2))
                .andExpect(jsonPath("$[1].department.name").value("HR"));
    }

    @Test
    void testInsert() throws Exception {
        // Mocking data for the new employee to be inserted
        Employee mockEmployeeToInsert = new Employee(null, "John", "Doe", 50000.0, new Department(1L, "IT"));

        // Mocking data for the newly inserted employee
        Employee mockInsertedEmployee = new Employee(1L, "John", "Doe", 50000.0, new Department(1L, "IT"));

        // Stubbing the behavior of insert method to return the mockInsertedEmployee when called
        when(employeeService.insert(any(Employee.class))).thenReturn(mockInsertedEmployee);

        // Perform the POST request and validate the response
        mockMvc.perform(post("/empData/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockEmployeeToInsert)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.salary").value(50000.0))
                .andExpect(jsonPath("$.department.id").value(1))
                .andExpect(jsonPath("$.department.name").value("IT"));
    }

    @Test
    void testDelete() throws Exception {
        // Perform the DELETE request and validate the response
        mockMvc.perform(delete("/empData/deleteByID")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Verify that the delete method of EmployeeService was called with the correct ID
        verify(employeeService, times(1)).delete(1);
    }

    // Utility method to convert objects to JSON
    private String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
