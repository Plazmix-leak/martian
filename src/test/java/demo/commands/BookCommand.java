package demo.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.entity.Player;
import net.minestom.server.item.metadata.WrittenBookMeta;

import java.util.List;

public class BookCommand extends Command {
    public BookCommand() {
        super("book");

        setCondition(this::playerCondition);

        setDefaultExecutor(this::execute);
    }

    private boolean playerCondition(CommandSender sender, String commandString) {
        if (!sender.isPlayer()) {
            sender.sendMessage("The command is only available for players");
            return false;
        }
        return true;
    }

    private void execute(CommandSender sender, CommandContext context) {
        Player player = sender.asPlayer();

        final WrittenBookMeta bookMeta = new WrittenBookMeta();
        bookMeta.setAuthor(Component.text(player.getUsername(), NamedTextColor.RED));
        bookMeta.setGeneration(WrittenBookMeta.WrittenBookGeneration.ORIGINAL);
        bookMeta.setTitle(Component.text(player.getUsername() + "'s Book", NamedTextColor.BLUE));
        bookMeta.setPages(List.of(
                Component.text("Page one", NamedTextColor.RED),
                Component.text("Page two", NamedTextColor.GREEN),
                Component.text("Page three", NamedTextColor.BLUE)
        ));

        player.openBook(bookMeta);
    }
}
