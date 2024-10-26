import com.InsuranceManagement.Repository.InsuranceHomeRepository;
import com.InsuranceManagement.Repository.UserRepository;
import com.InsuranceManagement.Service.Impl.InsuranceHomeServiceImpl;
import com.InsuranceManagement.dto.request.InsuranceHomeRequestDto;
import com.InsuranceManagement.dto.response.InsuranceHomeResponseDto;
import com.InsuranceManagement.entity.InsuranceHome;
import com.InsuranceManagement.entity.User;
import com.InsuranceManagement.enums.PropertyType;
import com.InsuranceManagement.mapper.InsuranceHomeMapper;
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

public class InsuranceHomeServiceImplTest {
    @Mock
    private InsuranceHomeRepository insuranceHomeRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private InsuranceHomeMapper insuranceHomeMapper;
    @InjectMocks
    private InsuranceHomeServiceImpl insuranceHomeService;

    private InsuranceHomeRequestDto requestDto;
    private InsuranceHome insuranceHome;
    private InsuranceHomeResponseDto responseDto;
    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(UUID.randomUUID());
        requestDto = new InsuranceHomeRequestDto(
                250000.0, PropertyType.HOUSE,true , false, user
        );
        insuranceHome = new InsuranceHome();
        insuranceHome.setPropertyValue(250000.0);
        insuranceHome.setIsRiskZone(true);
        insuranceHome.setPropertyType(PropertyType.HOUSE);
        insuranceHome.setHasSecuritySystem(false);
        insuranceHome.setUser(user);
        responseDto = new InsuranceHomeResponseDto(
                UUID.randomUUID(), 250000.0, PropertyType.HOUSE,true,  false
        );
    }

    @Test
    public void testCreateInsuranceHome() throws Exception {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(insuranceHomeMapper.toEntity(requestDto)).thenReturn(insuranceHome);
        when(insuranceHomeRepository.save(any(InsuranceHome.class))).thenReturn(insuranceHome);
        when(insuranceHomeMapper.toDto(insuranceHome)).thenReturn(responseDto);

        InsuranceHomeResponseDto result = insuranceHomeService.createInsuranceHome(requestDto);

        assertEquals(responseDto, result);
        verify(userRepository, times(1)).findById(user.getId());
        verify(insuranceHomeRepository, times(1)).save(insuranceHome);
        verify(insuranceHomeMapper, times(1)).toDto(insuranceHome);
    }

    @Test
    public void testCreateInsuranceHome_UserNotFound() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> {
            insuranceHomeService.createInsuranceHome(requestDto);
        });
        verify(userRepository, times(1)).findById(user.getId());
        verifyNoMoreInteractions(insuranceHomeRepository, insuranceHomeMapper);
    }

    @Test
    public void testCalculateInsuranceHome() {
        double calculatedAmount = insuranceHomeService.CalculateInsuranceHome(responseDto);
        double expectedAmount = 300 * 1.05 * 1.02 * 1.1 * 1.15;
        assertEquals(expectedAmount, calculatedAmount);
    }

    @Test
    public void testCalculateInsuranceHome_hasSecuritySystem() {
        responseDto = new InsuranceHomeResponseDto(
                UUID.randomUUID(), 250000.0, PropertyType.HOUSE,true,  true
        );
        double calculatedAmount = insuranceHomeService.CalculateInsuranceHome(responseDto);
        double expectedAmount = 300 * 1.05 * 1.02 * 1.1 * 0.85;
        assertEquals(expectedAmount, calculatedAmount);
    }


    @Test
    public void testGetInsuranceHomeByUserId() {
        List<InsuranceHome> insuranceHomes = List.of(insuranceHome);
        List<InsuranceHomeResponseDto> expectedResponse = List.of(responseDto);

        when(insuranceHomeRepository.findByUserId(user.getId())).thenReturn(insuranceHomes);
        when(insuranceHomeMapper.toDto(insuranceHome)).thenReturn(responseDto);

        List<InsuranceHomeResponseDto> result = insuranceHomeService.getInsuranceHomeByUserId(user.getId());

        assertEquals(expectedResponse, result);
        verify(insuranceHomeRepository, times(1)).findByUserId(user.getId());
        verify(insuranceHomeMapper, times(insuranceHomes.size())).toDto(insuranceHome);
    }

    @Test
    public void testGetInsuranceHomeById() throws Exception {
        when(insuranceHomeRepository.findInsuranceHomeById(responseDto.id())).thenReturn(Optional.of(insuranceHome));
        when(insuranceHomeMapper.toDto(insuranceHome)).thenReturn(responseDto);

        InsuranceHomeResponseDto result = insuranceHomeService.getInsuranceHomeById(responseDto.id());

        assertEquals(responseDto, result);
        verify(insuranceHomeRepository, times(1)).findInsuranceHomeById(responseDto.id());
        verify(insuranceHomeMapper, times(1)).toDto(insuranceHome);
    }

    @Test
    public void testGetInsuranceHomeById_NotFound() {
        when(insuranceHomeRepository.findInsuranceHomeById(responseDto.id())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> {
            insuranceHomeService.getInsuranceHomeById(responseDto.id());
        });
        verify(insuranceHomeRepository, times(1)).findInsuranceHomeById(responseDto.id());
    }
}
