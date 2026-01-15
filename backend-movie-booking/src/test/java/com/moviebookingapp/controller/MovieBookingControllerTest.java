package com.moviebookingapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.moviebookingapp.service.MovieService;
import com.moviebookingapp.service.TicketService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;


 
@WebMvcTest(MovieBookingController.class)
@AutoConfigureMockMvc(addFilters = false)
class MovieBookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @MockBean
    private TicketService ticketService;

    @Test
    void shouldReturnOkForAllMovies() throws Exception {
        mockMvc.perform(get("/api/v1.0/moviebooking/all"))
               .andExpect(status().isOk());
    }
}
