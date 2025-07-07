package com.example.employeeapi.service;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService service;

    private Employee mockEmployee;

    @BeforeEach
    void setup() {
        mockEmployee = new Employee(1L, "Alice", "Developer");
    }

    @Test
    void shouldReturnAllEmployees() {
        when(repository.findAll()).thenReturn(Arrays.asList(mockEmployee));

        List<Employee> result = service.getAll();

        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldReturnEmployeeById() {
        when(repository.findById(1L)).thenReturn(Optional.of(mockEmployee));

        Optional<Employee> result = service.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("Alice", result.get().getName());
        verify(repository).findById(1L);
    }

    @Test
    void shouldSaveEmployee() {
        when(repository.save(any(Employee.class))).thenReturn(mockEmployee);

        Employee saved = service.save(mockEmployee);

        assertNotNull(saved);
        assertEquals("Alice", saved.getName());
        verify(repository).save(mockEmployee);
    }

    @Test
    void shouldDeleteEmployeeById() {
        doNothing().when(repository).deleteById(1L);

        service.delete(1L);

        verify(repository).deleteById(1L);
    }
}
