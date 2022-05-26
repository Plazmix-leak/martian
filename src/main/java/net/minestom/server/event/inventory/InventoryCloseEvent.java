package net.minestom.server.event.inventory;

import net.minestom.server.entity.Player;
import net.minestom.server.event.InventoryEvent;
import net.minestom.server.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Called when an {@link Inventory} is closed by a player.
 */
public class InventoryCloseEvent extends InventoryEvent {

    private final Player player;
    private Inventory newInventory;

    public InventoryCloseEvent(@Nullable Inventory inventory, @NotNull Player player) {
        super(inventory);
        this.player = player;
    }

    /**
     * Gets the player who closed the inventory.
     *
     * @return the player who closed the inventory
     */
    @NotNull
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the new inventory to open.
     *
     * @return the new inventory to open, null if there isn't any
     */
    @Nullable
    public Inventory getNewInventory() {
        return newInventory;
    }

    /**
     * Can be used to open a new inventory after closing the previous one.
     *
     * @param newInventory the inventory to open, null to do not open any
     */
    public void setNewInventory(@Nullable Inventory newInventory) {
        this.newInventory = newInventory;
    }
}
