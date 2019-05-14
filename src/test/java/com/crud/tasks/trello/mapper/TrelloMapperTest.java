package com.crud.tasks.trello.mapper;

import com.crud.tasks.trello.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void shouldMapToBoards() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1", "testList", false));

        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(new TrelloBoardDto("2", "testBoard", trelloListsDto));

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);

        //Then
        assertEquals("2", trelloBoards.get(0).getId());
        assertEquals("testBoard", trelloBoards.get(0).getName());
        assertEquals("1", trelloBoards.get(0).getLists().get(0).getId());
        assertEquals("testList", trelloBoards.get(0).getLists().get(0).getName());
    }

    @Test
    public void shouldMapToBoardsDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "testList", false));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("2", "testBoard", trelloLists));

        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);

        assertEquals("2", trelloBoardsDto.get(0).getId());
        assertEquals("testBoard", trelloBoardsDto.get(0).getName());
        assertEquals("1", trelloBoardsDto.get(0).getLists().get(0).getId());
        assertEquals("testList", trelloBoardsDto.get(0).getLists().get(0).getName());
    }

    @Test
    public void shouldMapToList() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1", "testList", false));

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);

        //Then
        assertEquals("1", trelloLists.get(0).getId());
        assertEquals("testList", trelloLists.get(0).getName());
    }

    @Test
    public void shouldMapToListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "testList", false));

        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals("1", trelloListDtos.get(0).getId());
        assertEquals("testList", trelloListDtos.get(0).getName());
    }

    @Test
    public void shouldMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card", "cardDescription",
                "cardPos", "cardListId");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("card", trelloCardDto.getName());
        assertEquals("cardDescription", trelloCardDto.getDescription());
        assertEquals("cardPos", trelloCardDto.getPos());
        assertEquals("cardListId", trelloCardDto.getListId());
    }

    @Test
    public void shouldMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card", "cardDescription",
                "cardPos", "cardListId");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("card", trelloCard.getName());
        assertEquals("cardDescription", trelloCard.getDescription());
        assertEquals("cardPos", trelloCard.getPos());
        assertEquals("cardListId", trelloCard.getListId());
    }
}
