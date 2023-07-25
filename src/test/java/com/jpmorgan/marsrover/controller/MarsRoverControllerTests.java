package com.jpmorgan.marsrover.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MarsRoverController.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MarsRoverControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void createRoverAPI_shouldReturn201CreatedResponseWithRoverDetailsInResponseBody() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/v1/mars-rover/create")
                .param("coordinates", "4,4,N")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.rover").value("R1"))
                .andExpect(jsonPath("$.position.coordinate").value("4,4"))
                .andExpect(jsonPath("$.position.direction").value("NORTH"));
    }

    @Test
    public void givenRoverExistsAtTheSameLocation_createRoverAPI_shouldReturn400BadRequestResponse() throws Exception {
        givenRoverExistsAtTheSameLocation();

        mvc.perform(MockMvcRequestBuilders
                .post("/v1/mars-rover/create")
                .param("coordinates", "4,4,N")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(
                        content().string("A rover already exists at the specified coordinates. " +
                                "Unable to create new rover.")
                );
    }

    @Test
    public void givenRoverExists_getRoverAPI_shouldReturnOkHttpStatusWithRoverDetails() throws Exception {
        givenRoverExists();

        mvc.perform(MockMvcRequestBuilders
                .get("/v1/mars-rover/position")
                .param("rover", "R1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rover").value("R1"))
                .andExpect(jsonPath("$.position.coordinate").value("5,5"))
                .andExpect(jsonPath("$.position.direction").value("NORTH"));
    }

    @Test
    public void givenInvalidRoverName_getRoverAPI_shouldReturn400BadRequestWithErrorMessage() throws Exception {
        givenRoverExists();

        mvc.perform(MockMvcRequestBuilders
                .get("/v1/mars-rover/position")
                .param("rover", "aws")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid Rover Name: aws"));
    }

    @Test
    public void givenValidParamValues_moveRoverAPI_shouldReturnOkWithUpdatedRoverPositionDetails() throws Exception {
        givenRoverExists();

        mvc.perform(MockMvcRequestBuilders
                .put("/v1/mars-rover/move")
                .param("rover", "R1")
                .param("commands", "f,f,r,b,b,l")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rover").value("R1"))
                .andExpect(jsonPath("$.position.coordinate").value("3,7"))
                .andExpect(jsonPath("$.position.direction").value("NORTH"));
    }

    @Test
    public void givenInvalidParamValues_moveRoverAPI_shouldReturn400BadRequestWithErrorMessage() throws Exception {
        givenRoverExists();

        mvc.perform(MockMvcRequestBuilders
                .put("/v1/mars-rover/move")
                .param("rover", "aws")
                .param("commands", "f,f,r,b,b,l")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid Rover Name: aws"));
    }

    private void givenRoverExists() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/v1/mars-rover/create")
                .param("coordinates", "5,5,N")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    private void givenRoverExistsAtTheSameLocation() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/v1/mars-rover/create")
                .param("coordinates", "4,4,N")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

}
