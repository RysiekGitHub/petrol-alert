package com.petrol.petrolalert.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.petrol.petrolalert.PetrolName;
import com.petrol.petrolalert.models.Localization;
import com.petrol.petrolalert.models.Petrol;
import com.petrol.petrolalert.models.PetrolStation;
import com.petrol.petrolalert.services.PetrolService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@WebMvcTest(PetrolAlertController.class)
public class PetrolStationsControllersTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PetrolService petrolService;

  @Test
  public void shouldAddPetrolStation() throws Exception {
    var stationName = "stationName";
    var lon = 2.2;
    var lat = 1.2;
    var prices = new HashMap<PetrolName, Petrol>();
    var localization = new Localization(lon, lat);

    PetrolStation petrolStation = new PetrolStation(stationName, prices, localization);

    MockHttpServletResponse response = mockMvc.perform(post("/stations")
            .content(asJsonString(petrolStation))
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON))
            .andReturn().getResponse();

    assertEquals(response.getStatus(), HttpStatus.OK.value());
  }

  @Test
  public void shouldReturnPetrolStationDetails() throws Exception {
    //given
    var stationName = "stationName";
    var lon = 2.2;
    var lat = 1.2;

    var prices = new HashMap<PetrolName, Petrol>();

    var localization = new Localization(lon, lat);

    PetrolStation petrolStation = new PetrolStation(stationName, prices, localization);
    when(petrolService.getPetrolStationByName(stationName)).thenReturn(petrolStation);

    //when
    MockHttpServletResponse response = mockMvc.perform(get("/stations/"+ stationName)).andReturn().getResponse();
    ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    Map json = mapper.readValue(response.getContentAsString(), Map.class);

    var stationNameReceived = json.get("name");
    var pricesReceived = json.get("petrolPrices");
    var localizationReceived = json.get("localization");

    //then
    assertEquals(response.getStatus(), HttpStatus.OK.value());

    assertEquals(stationNameReceived, stationName);
    assertNotEquals(pricesReceived,null);
    assertNotEquals(localizationReceived, null);
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
