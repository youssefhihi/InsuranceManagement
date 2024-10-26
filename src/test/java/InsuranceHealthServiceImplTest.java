import com.InsuranceManagement.Repository.InsuranceHealthRepository;
import com.InsuranceManagement.Repository.UserRepository;
import com.InsuranceManagement.Service.Impl.InsuranceHealthServiceImpl;
import com.InsuranceManagement.dto.request.InsuranceHealthRequestDto;
import com.InsuranceManagement.dto.response.InsuranceHealthResponseDto;
import com.InsuranceManagement.entity.InsuranceHealth;
import com.InsuranceManagement.entity.User;
import com.InsuranceManagement.enums.CoverageType;
import com.InsuranceManagement.mapper.InsuranceHealthMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InsuranceHealthServiceImplTest {
    @Mock
    private InsuranceHealthRepository insuranceHealthRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private InsuranceHealthMapper insuranceHealthMapper;
    @InjectMocks
    private InsuranceHealthServiceImpl insuranceHealthService;

    private InsuranceHealthRequestDto requestDto;
    private InsuranceHealth insuranceHealth;
    private InsuranceHealthResponseDto responseDto;
    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(UUID.randomUUID());
        requestDto = new InsuranceHealthRequestDto(
                65, "chronic diseases", CoverageType.premium, user
        );
        insuranceHealth = new InsuranceHealth();
        insuranceHealth.setAge(65);
        insuranceHealth.setHealthStatus("chronic diseases");
        insuranceHealth.setCoverageType(CoverageType.premium);
        insuranceHealth.setUser(user);
        responseDto = new InsuranceHealthResponseDto(
                UUID.randomUUID(), 65, "chronic diseases", CoverageType.premium
        );
    }

    @Test
    public void testCreateInsuranceHealth() throws Exception {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(insuranceHealthMapper.toEntity(requestDto)).thenReturn(insuranceHealth);
        when(insuranceHealthRepository.save(any(InsuranceHealth.class))).thenReturn(insuranceHealth);
        when(insuranceHealthMapper.toDto(insuranceHealth)).thenReturn(responseDto);

        InsuranceHealthResponseDto result = insuranceHealthService.createInsuranceHealth(requestDto);

        assertEquals(responseDto, result);
        verify(userRepository, times(1)).findById(user.getId());
        verify(insuranceHealthRepository, times(1)).save(insuranceHealth);
        verify(insuranceHealthMapper, times(1)).toDto(insuranceHealth);
    }

    @Test
    public void testCreateInsuranceHealth_UserNotFound() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> {
            insuranceHealthService.createInsuranceHealth(requestDto);
        });
        verify(userRepository, times(1)).findById(user.getId());
        verifyNoMoreInteractions(insuranceHealthRepository, insuranceHealthMapper);
    }

    @Test
    public void testCalculateInsuranceHealth_coverageTypePremium() {
        double calculatedAmount = insuranceHealthService.CalculateInsuranceHealth(responseDto);
        double expectedAmount = 150 * 1.2 * 1.05 * 1.3;
        assertEquals(expectedAmount, calculatedAmount);
    }
    @Test
    public void testCalculateInsuranceHealth_coverageTypeBasic() {
        responseDto = new InsuranceHealthResponseDto(
                UUID.randomUUID(), 65, "chronic diseases", CoverageType.basic
        );
        double calculatedAmount = insuranceHealthService.CalculateInsuranceHealth(responseDto);
        double expectedAmount = 150 * 1.2 * 0.9 * 1.3;
        assertEquals(expectedAmount, calculatedAmount);
    }

    @Test
    public void testGetInsuranceHealthByUserId() {
        List<InsuranceHealth> insuranceHealths = List.of(insuranceHealth);
        List<InsuranceHealthResponseDto> expectedResponse = List.of(responseDto);

        when(insuranceHealthRepository.findByUserId(user.getId())).thenReturn(insuranceHealths);
        when(insuranceHealthMapper.toDto(insuranceHealth)).thenReturn(responseDto);

        List<InsuranceHealthResponseDto> result = insuranceHealthService.getInsuranceHealthByUserId(user.getId());

        assertEquals(expectedResponse, result);
        verify(insuranceHealthRepository, times(1)).findByUserId(user.getId());
        verify(insuranceHealthMapper, times(insuranceHealths.size())).toDto(insuranceHealth);
    }

    @Test
    public void testGetInsuranceHealthById() throws Exception {
        when(insuranceHealthRepository.findInsuranceHealthById(responseDto.id())).thenReturn(Optional.of(insuranceHealth));
        when(insuranceHealthMapper.toDto(insuranceHealth)).thenReturn(responseDto);

        InsuranceHealthResponseDto result = insuranceHealthService.getInsuranceHealthById(responseDto.id());

        assertEquals(responseDto, result);
        verify(insuranceHealthRepository, times(1)).findInsuranceHealthById(responseDto.id());
        verify(insuranceHealthMapper, times(1)).toDto(insuranceHealth);
    }

    @Test
    public void testGetInsuranceHealthById_NotFound() {
        when(insuranceHealthRepository.findInsuranceHealthById(responseDto.id())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> {
            insuranceHealthService.getInsuranceHealthById(responseDto.id());
        });
        verify(insuranceHealthRepository, times(1)).findInsuranceHealthById(responseDto.id());
    }
}
