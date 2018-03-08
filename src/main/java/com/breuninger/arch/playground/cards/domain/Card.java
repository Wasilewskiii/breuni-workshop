package com.breuninger.arch.playground.cards.domain;

public class Card {
    private String id;
    private String title;

    private String receiverFirstName;
    private String receiverSurname;
    private String senderFirstName;
    private String senderSurname;


  public Card(String id, String title, String receiverFirstName,
              String receiverSurname, String senderFirstName, String senderSurname) {
        this.id = id;
        this.title = title;

        this.receiverFirstName = receiverFirstName;
        this.receiverSurname = receiverSurname;
        this.senderFirstName = senderFirstName;
        this.senderSurname = senderSurname;

    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReceiverFirstName() {
        return receiverFirstName;
    }

    public void setReceiverFirstName(String receiverFirstName) {
        this.receiverFirstName = receiverFirstName;
    }

    public String getReceiverSurname() {
        return receiverSurname;
    }

    public void setReceiverSurname(String receiverSurname) {
        this.receiverSurname = receiverSurname;
    }

    public String getSenderFirstName() {
        return senderFirstName;
    }

    public void setSenderFirstName(String senderFirstName) {
        this.senderFirstName = senderFirstName;
    }

    public String getSenderSurname() {
        return senderSurname;
    }

    public void setSenderSurname(String senderSurname) {
        this.senderSurname = senderSurname;
    }

    public Card sanitize(){
        //TODO: sanitize!
          return this;
    }
}
