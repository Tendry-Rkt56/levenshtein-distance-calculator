public class TransformationSnapshot {
    private int position;

    private char character;

    private String word;

    private TransformationOperation operation;

    public TransformationSnapshot(TransformationOperation operation, int position, char character, String word) {
        this.operation = operation;
        this.position = position;
        this.word = word;
        this.character = character;
    }

    public void describe() {
        String description = "";
        switch (operation) {
            case TransformationOperation.Addition:
                description = "Inserted the character '" + character + "' at the position " + position;
                break;
            case TransformationOperation.Subtraction:
                description = "Removed the character '" + character + "' from the position " + position;
                break;
            case TransformationOperation.Substitution:
                description = "Replaced the character at the position " + position + " with the character " + "'" + character + "'";
                break;
        }
        description += "\nSnapshot of the word: " + word;
        System.out.println(description);
    }
}
