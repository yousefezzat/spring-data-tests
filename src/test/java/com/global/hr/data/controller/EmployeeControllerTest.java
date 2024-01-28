package com.global.hr.data.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.hr.data.exception.EmployeeNotFoundException;
import com.global.hr.data.model.entity.Department;
import com.global.hr.data.model.entity.Employee;
import com.global.hr.data.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(EmployeeController.class)
//@ExtendWith(MockitoExtension.class) --> already included in @WebMvcTest
class  EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;



    @Test
    void testGetByID() throws Exception {
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
    void testGetByIDNotFound() throws Exception {
        // Mocking the behavior of employeeService.getEmp to throw EmployeeNotFoundException
        when(employeeService.getEmp(any(Integer.class))).thenThrow(new EmployeeNotFoundException("Employee not found"));

        mockMvc.perform(get("/empData/get/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Employee not found"));

        // Verify that the employeeService.getEmp method was called with the correct ID
        verify(employeeService, times(1)).getEmp(1);
    }
    @Test
    void testGetByIDNotFoundUsingAssertThrows() {
        when(employeeService.getEmp(any(Integer.class))).thenThrow(new EmployeeNotFoundException("Employee not found with this id"));

        // Using assertThrows to assert that NotFoundException is thrown
        EmployeeNotFoundException exception = assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmp(1));

        // Assert on the exception message or any other details if needed
        assertEquals("Employee not found with this id", exception.getMessage());

        verify(employeeService, times(1)).getEmp(1);
    }



    @Test
    void testFindAll() throws Exception {
        List<Employee> mockEmployees = Arrays.asList(
                new Employee(1L, "Shrief", "Ali", 50000.0, new Department(1L, "IT")),
                new Employee(2L, "Ahmed", "Amer", 60000.0, new Department(2L, "HR"))
        );

        when(employeeService.findAll()).thenReturn(mockEmployees);

        mockMvc.perform(get("/empData/findAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("Shrief"))
                .andExpect(jsonPath("$[0].lastName").value("Ali"))
                .andExpect(jsonPath("$[0].salary").value(50000.0))
                .andExpect(jsonPath("$[0].department.id").value(1))
                .andExpect(jsonPath("$[0].department.name").value("IT"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].firstName").value("Ahmed"))
                .andExpect(jsonPath("$[1].lastName").value("Amer"))
                .andExpect(jsonPath("$[1].salary").value(60000.0))
                .andExpect(jsonPath("$[1].department.id").value(2))
                .andExpect(jsonPath("$[1].department.name").value("HR"));
    }

    @Test
    void testInsert() throws Exception {
        // Mocking data for the new employee to be inserted
        Employee mockEmployeeToInsert = new Employee(null, "Ahmed", "Amer", 50000.0, new Department(1L, "IT"));

        // Mocking data for the newly inserted employee
        Employee mockInsertedEmployee = new Employee(1L, "Ahmed", "Amer", 50000.0, new Department(1L, "IT"));

        // Mocking the behavior of insert method to return the mockInsertedEmployee when called
        when(employeeService.insert(any(Employee.class))).thenReturn(mockInsertedEmployee);

        mockMvc.perform(post("/empData/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockEmployeeToInsert)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Ahmed"))
                .andExpect(jsonPath("$.lastName").value("Amer"))
                .andExpect(jsonPath("$.salary").value(50000.0))
                .andExpect(jsonPath("$.department.id").value(1))
                .andExpect(jsonPath("$.department.name").value("IT"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/empData/deleteByID")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(employeeService, times(1)).delete(1);
    }


}
