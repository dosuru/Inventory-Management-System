package com.example.admin.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.admin.Entity.Admin;
import com.example.admin.exception.InvalidCustomerIDException;
import com.example.admin.exception.InvalidProductIdException;
import com.example.admin.exception.NoAdminFoundException;
import com.example.admin.modal.CustomerDto;
import com.example.admin.modal.ProductDto;
import com.example.admin.repo.AdminRepo;
import com.example.admin.service.AdminServiceImpl;

public class AdminServiceImplTest {

	private AdminServiceImpl adminService;
	private AdminRepo adminRepo;

	@BeforeEach
	public void setup() {
		adminRepo = mock(AdminRepo.class);
		adminService = new AdminServiceImpl();
	}

	@Test
	public void testDeleteAdmin_ExistingId_DeletesAdmin() {
		// Arrange
		int id = 1;
		Admin admin = new Admin();
		admin.setId(id);
		when(adminRepo.findById(id)).thenReturn(Optional.of(admin));

		// Act
		String result = adminService.deleteAdmin(id);

		// Assert
		assertEquals("Deleted Successfully", result);
		verify(adminRepo).deleteById(id);
	}

	@Test
	public void testDeleteAdmin_NonExistingId_ThrowsNoAdminFoundException() {
		// Arrange
		int id = 1;
		when(adminRepo.findById(id)).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(NoAdminFoundException.class, () -> adminService.deleteAdmin(id));
	}
	   @Test
	    public void testDeleteAdmin_ExistingAdmin_DeletesAdmin() {
	        // Arrange
	        int id = 1;
	        Admin admin = new Admin();
	        admin.setId(id);
	        when(adminRepo.findById(id)).thenReturn(Optional.of(admin));

	        // Act
	        String result = adminService.deleteAdmin(id);

	        // Assert
	        assertEquals("Deleted Successfully", result);
	        verify(adminRepo).deleteById(id);
	    }

	    @Test
	    public void testDeleteAdmin_NonExistingAdmin_ThrowsNoAdminFoundException() {
	        // Arrange
	        int id = 1;
	        when(adminRepo.findById(id)).thenReturn(Optional.empty());

	        // Act & Assert
	        assertThrows(NoAdminFoundException.class, () -> adminService.deleteAdmin(id));
	        verify(adminRepo, never()).deleteById(id);
	    }
	    

}
