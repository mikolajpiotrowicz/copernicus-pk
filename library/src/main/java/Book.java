public class Book {
    private String  id;
    private String authorName;
    private String title;
    private String studentIndex;
    private boolean rent;

    public Book(String id, String authorName, String title, String studentIndex, boolean rent) {
        this.id = id;
        this.authorName = authorName;
        this.title = title;
        this.rent = rent;
        this.studentIndex = studentIndex;
    }


    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return id + ": " + authorName + " - " + title;
    }

}
