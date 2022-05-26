package net.minestom.server.inventory;

import net.minestom.server.entity.Entity;
import net.minestom.server.item.ItemStack;
import net.minestom.server.network.packet.server.play.EntityEquipmentPacket;
import net.minestom.server.utils.validate.Check;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an {@link Entity} which can have {@link ItemStack} in hands and armor slots.
 */
public interface EquipmentHandler {

    /**
     * Gets the {@link ItemStack} in main hand.
     *
     * @return the {@link ItemStack} in main hand
     */
    @NotNull
    ItemStack getItemInHand();

    /**
     * Changes the main hand {@link ItemStack}.
     *
     * @param itemStack the main hand {@link ItemStack}
     */
    void setItemInHand(@NotNull ItemStack itemStack);

    /**
     * Gets the helmet.
     *
     * @return the helmet
     */
    @NotNull
    ItemStack getHelmet();

    /**
     * Changes the helmet.
     *
     * @param itemStack the helmet
     */
    void setHelmet(@NotNull ItemStack itemStack);

    /**
     * Gets the chestplate.
     *
     * @return the chestplate
     */
    @NotNull
    ItemStack getChestplate();

    /**
     * Changes the chestplate.
     *
     * @param itemStack the chestplate
     */
    void setChestplate(@NotNull ItemStack itemStack);

    /**
     * Gets the leggings.
     *
     * @return the leggings
     */
    @NotNull
    ItemStack getLeggings();

    /**
     * Changes the leggings.
     *
     * @param itemStack the leggings
     */
    void setLeggings(@NotNull ItemStack itemStack);

    /**
     * Gets the boots.
     *
     * @return the boots
     */
    @NotNull
    ItemStack getBoots();

    /**
     * Changes the boots.
     *
     * @param itemStack the boots
     */
    void setBoots(@NotNull ItemStack itemStack);

    /**
     * Gets the equipment in a specific slot.
     *
     * @param slot the equipment to get the item from
     * @return the equipment {@link ItemStack}
     */
    @NotNull
    default ItemStack getEquipment(@NotNull EntityEquipmentPacket.Slot slot) {
        switch (slot) {
            case HAND:
                return getItemInHand();
            case HELMET:
                return getHelmet();
            case CHESTPLATE:
                return getChestplate();
            case LEGGINGS:
                return getLeggings();
            case BOOTS:
                return getBoots();
        }
        throw new IllegalStateException("Something weird happened");
    }

    /**
     * Sends a specific equipment to viewers.
     *
     * @param slot the slot of the equipment
     */
    default void syncEquipment(@NotNull EntityEquipmentPacket.Slot slot) {
        Check.stateCondition(!(this instanceof Entity), "Only accessible for Entity");

        Entity entity = (Entity) this;

        final ItemStack itemStack = getEquipment(slot);

        EntityEquipmentPacket entityEquipmentPacket = new EntityEquipmentPacket();
        entityEquipmentPacket.entityId = entity.getEntityId();
        entityEquipmentPacket.slot = slot;
        entityEquipmentPacket.itemStack = itemStack;

        entity.sendPacketToViewers(entityEquipmentPacket);
    }

    /**
     * Gets the packet with all the equipments.
     *
     * @return the packet with the equipments
     * @throws IllegalStateException if 'this' is not an {@link Entity}
     */
    @NotNull
    default EntityEquipmentPacket[] getEquipmentsPacket() {
        Check.stateCondition(!(this instanceof Entity), "Only accessible for Entity");

        final Entity entity = (Entity) this;

        final EntityEquipmentPacket.Slot[] slots = EntityEquipmentPacket.Slot.values();

        final EntityEquipmentPacket[] packets = new EntityEquipmentPacket[slots.length];

        // Fill items
        for (int i = 0; i < slots.length; i++) {
            final ItemStack equipment = getEquipment(slots[i]);

            // Create equipment packet
            EntityEquipmentPacket equipmentPacket = new EntityEquipmentPacket();
            equipmentPacket.entityId = entity.getEntityId();
            equipmentPacket.slot = slots[i];
            equipmentPacket.itemStack = equipment;

            packets[i] = equipmentPacket;
        }

        return packets;
    }

}
