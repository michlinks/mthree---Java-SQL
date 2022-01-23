package com.bullsandcows.entity;

public class Guesses {

    int id;
    int guessNo;
    int exacts;
    int partials;
    int game;

    public int getId() { return id;}

    public void setId(int id) { this.id = id;}

    public int getGuessNo() { return guessNo;}

    public void setGuessNo(int guessNo) { this.guessNo = guessNo;}

    public int getExacts() { return exacts;}

    public void setExacts(int exacts) { this.exacts = exacts;}

    public int getPartials() { return partials;}

    public void setPartials(int partials) { this.partials = partials;}

    public int getGame() { return game;}

    public void setGame(int game) { this.game = game;}


}
