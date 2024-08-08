package br.com.java.tcc.application.adresses;

import br.com.java.tcc.application.adresses.builders.AdressEntityBuilder;
import br.com.java.tcc.application.adresses.persistence.AdressEntity;
import br.com.java.tcc.application.adresses.persistence.AdressRepository;
import br.com.java.tcc.application.adresses.resources.AdressResponse;
import br.com.java.tcc.application.adresses.util.AdressMapper;
import br.com.java.tcc.configuration.MessageConfiguration;
import br.com.java.tcc.exceptions.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class AdressServiceTest {

    private final AdressMapper adressMapper = new AdressMapper();


    @InjectMocks
    AdressServiceImpl adressService;

    @Mock
    MessageConfiguration messageConfiguration;

    @Mock
    AdressRepository adressRepository;

    @Test
    @DisplayName("Deve retornar erro quando não for localizado o registro")
    public void shouldReturnErrorWhenNotFoundTheRegistry() {
        final Long id = 1L;
        final Optional<AdressEntity> optionalAdressEntity = Optional.empty();
        //when(adressRepository.findById(id)).thenReturn(optionalAdressEntity);
        //when(messageConfiguration.getMessageByCode(any(), any())).thenReturn("Mensagem de erro");

        //when(adressMapper.toAdressDTO(any(AdressEntity.class)))
              //  .thenThrow(new NullPointerException("Mapper está gerando NullPointerException"));

        assertThrows(CustomException.class, () -> adressService.findById(id));
    }

    @Test
    @DisplayName("Não deve retornar erro quando o registro for encontrado")
    public void shouldNotReturnErrorWhenTheRegistryWasFound() {
        final Long id = 1L;
        final AdressEntity adressEntity = AdressEntityBuilder.getInstance(id).getAdressEntity();
        final Optional<AdressEntity> optionalAdressEntity = Optional.of(adressEntity);
        when(adressRepository.findById(id)).thenReturn(optionalAdressEntity);
        compare(adressEntity, assertDoesNotThrow(() -> adressService.findById(id)));
    }

    private void compare(final AdressEntity adressEntity, final AdressResponse adressResponse) {
        assertNotNull(adressEntity);
        assertNotNull(adressResponse);
        assertEquals(adressEntity.getId(), adressResponse.getId());
        assertEquals(adressEntity.getAdress(), adressResponse.getAdress());
        assertEquals(adressEntity.getNumber(), adressResponse.getNumber());
        assertEquals(adressEntity.getDistrict(), adressResponse.getDistrict());
        assertEquals(adressEntity.getCep(), adressResponse.getCep());
        assertEquals(adressEntity.getCity(), adressResponse.getCity());
        assertEquals(adressEntity.getUf(), adressResponse.getUf());
    }
}
