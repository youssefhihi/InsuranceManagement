
import com.InsuranceManagement.Repository.InsuranceCarRepository;
import com.InsuranceManagement.Repository.UserRepository;
import com.InsuranceManagement.Service.Impl.InsuranceCarServiceImpl;
import com.InsuranceManagement.Service.Interfaces.InsuranceCarService;
import com.InsuranceManagement.dto.request.InsuranceCarRequestDto;
import com.InsuranceManagement.dto.response.InsuranceCarResponseDto;
import com.InsuranceManagement.entity.InsuranceCar;
import com.InsuranceManagement.entity.User;
import com.InsuranceManagement.enums.CarType;
import com.InsuranceManagement.mapper.InsuranceCarMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InsuranceCarServiceImplTest {

    @Mock
    private InsuranceCarRepository insuranceCarRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private InsuranceCarMapper insuranceCarMapper;

    @InjectMocks
    private InsuranceCarServiceImpl insuranceCarService;

    private InsuranceCarRequestDto requestDto;
    private InsuranceCar insuranceCar;
    private InsuranceCarResponseDto responseDto;
    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(UUID.randomUUID());

        requestDto = new InsuranceCarRequestDto(
                24, "ModelX", "Tesla", true, CarType.luxury, false, user
        );

        insuranceCar = new InsuranceCar();
        insuranceCar.setDriverAge(24);
        insuranceCar.setModel("ModelX");
        insuranceCar.setBrand("Tesla");
        insuranceCar.setUsage(true);
        insuranceCar.setCarType(CarType.luxury);
        insuranceCar.setHadSinister(false);
        insuranceCar.setUser(user);

        responseDto = new InsuranceCarResponseDto(
                UUID.randomUUID(), 24, "ModelX", "Tesla", true, CarType.luxury, false
        );
    }

    @Test
    public void testCreateInsuranceCar() throws Exception {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(insuranceCarMapper.toEntity(requestDto)).thenReturn(insuranceCar);
        when(insuranceCarRepository.save(any(InsuranceCar.class))).thenReturn(insuranceCar);
        when(insuranceCarMapper.toDto(insuranceCar)).thenReturn(responseDto);

        InsuranceCarResponseDto result = insuranceCarService.createInsuranceCar(requestDto);

        assertEquals(responseDto, result);
    }

    @Test
    public void testCreateInsuranceCar_UserNotFound() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> {
            insuranceCarService.createInsuranceCar(requestDto);
        });
    }

    @Test
    public void testCalculateInsuranceCar() {
        double calculatedAmount = insuranceCarService.CalculateInsuranceCar(responseDto);
        double expectedAmount = 500.0 * 1.10 * 1.15 * 1.10 * 0.80;

        assertEquals(expectedAmount, calculatedAmount);
    }
    @Test
    public void testCalculateInsuranceCar_hadSinister() {
       InsuranceCarResponseDto responseDto = new InsuranceCarResponseDto(
                UUID.randomUUID(), 24, "ModelX", "Tesla", true, CarType.luxury, true
        );        double calculatedAmount = insuranceCarService.CalculateInsuranceCar(responseDto);
        double expectedAmount = 500.0 * 1.10 * 1.15 * 1.10 * 1.10;

        assertEquals(expectedAmount, calculatedAmount);
    }

    @Test
    public void testGetInsuranceCarByUserId() {
        List<InsuranceCar> insuranceCars = List.of(insuranceCar);
        List<InsuranceCarResponseDto> expectedResponse = List.of(responseDto);
        when(insuranceCarRepository.findByUserId(user.getId())).thenReturn(insuranceCars);
        when(insuranceCarMapper.toDto(insuranceCar)).thenReturn(responseDto);

        List<InsuranceCarResponseDto> result = insuranceCarService.getInsuranceCarByUserId(user.getId());

        assertEquals(expectedResponse, result);
        verify(insuranceCarRepository, times(1)).findByUserId(user.getId());
        verify(insuranceCarMapper, times(insuranceCars.size())).toDto(insuranceCar);
    }



    @Test
    public void testGetInsuranceCarById() throws Exception {
        when(insuranceCarRepository.findInsuranceCarById(responseDto.id())).thenReturn(Optional.of(insuranceCar));
        when(insuranceCarMapper.toDto(insuranceCar)).thenReturn(responseDto);

        InsuranceCarResponseDto result = insuranceCarService.getInsuranceCarById(responseDto.id());

        assertEquals(responseDto, result);
    }

    @Test
    public void testGetInsuranceCarById_NotFound() {
        when(insuranceCarRepository.findInsuranceCarById(responseDto.id())).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> {
            insuranceCarService.getInsuranceCarById(responseDto.id());
        });
    }
}
