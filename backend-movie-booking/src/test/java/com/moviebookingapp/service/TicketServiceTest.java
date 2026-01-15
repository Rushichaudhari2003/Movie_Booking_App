package com.moviebookingapp.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.moviebookingapp.repository.MovieRepository;
import com.moviebookingapp.repository.TicketRepository;
import com.moviebookingapp.repository.UserRepository;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    void shouldFailWhenUserNotFound() {
        assertThatThrownBy(() -> ticketService.bookTicket("movie", null))
                .isInstanceOf(RuntimeException.class);
    }
}
