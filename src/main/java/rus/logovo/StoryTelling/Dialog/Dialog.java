package rus.logovo.StoryTelling.Dialog;

import net.minecraft.client.Minecraft;

public class Dialog {
    private final String question;
    private final Rest[] options;

    public Dialog(String question, Rest[] options) {
        this.question = question;
        this.options = options;
    }

    public void show() {
        Minecraft.getInstance().tell(() -> {
            Minecraft.getInstance().setScreen(new DialogGUI(question, options));
        });
    }
}