package emailservice;

public interface Email {

    default boolean isImportant() {
        return false;
    }

    User getFrom();

    User getTo();

    String getSubject();

    String getContent();

}
