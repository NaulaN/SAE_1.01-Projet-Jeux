package game.errors;


public class AttacksListBadIndex extends Error
{
    public AttacksListBadIndex(int i) {
        System.err.println("ERROR: You have entry a bad index in 'attacks' => index: " + i);
    }
}
