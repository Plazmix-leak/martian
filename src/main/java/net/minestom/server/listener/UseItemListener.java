package net.minestom.server.listener;

import net.minestom.server.entity.Player;
import net.minestom.server.event.item.ArmorEquipEvent;
import net.minestom.server.event.player.PlayerItemAnimationEvent;
import net.minestom.server.event.player.PlayerPreEatEvent;
import net.minestom.server.event.player.PlayerUseItemEvent;
import net.minestom.server.inventory.PlayerInventory;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.network.packet.client.play.ClientPlayerBlockPlacementPacket;

public class UseItemListener {

    public static boolean useItemListener(ClientPlayerBlockPlacementPacket packet, Player player) {
        // Y = -1 = 4096 - 1 = 4095
        if (packet.blockPosition.getX() != -1 || packet.blockPosition.getY() != 4095 || packet.blockPosition.getZ() != -1 || packet.blockFace != null) {
            return false;
        }

        final PlayerInventory inventory = player.getInventory();
        ItemStack itemStack = inventory.getItemInHand();
        itemStack.onRightClick(player);
        PlayerUseItemEvent useItemEvent = new PlayerUseItemEvent(player, itemStack);
        player.callEvent(PlayerUseItemEvent.class, useItemEvent);

        final PlayerInventory playerInventory = player.getInventory();
        if (useItemEvent.isCancelled()) {
            playerInventory.update();
            return true;
        }

        itemStack = useItemEvent.getItemStack();
        final Material material = itemStack.getMaterial();

        // Equip armor with right click
        if (material.isArmor()) {
            ItemStack currentlyEquipped;
            if (material.isHelmet()) {
                currentlyEquipped = playerInventory.getHelmet();
                playerInventory.setHelmet(itemStack);
            } else if (material.isChestplate()) {
                currentlyEquipped = playerInventory.getChestplate();
                playerInventory.setChestplate(itemStack);
            } else if (material.isLeggings()) {
                currentlyEquipped = playerInventory.getLeggings();
                playerInventory.setLeggings(itemStack);
            } else {
                currentlyEquipped = playerInventory.getBoots();
                playerInventory.setBoots(itemStack);
            }
            playerInventory.setItemInHand(currentlyEquipped);
        }

        PlayerItemAnimationEvent.ItemAnimationType itemAnimationType = null;

        if (material == Material.BOW) {
            itemAnimationType = PlayerItemAnimationEvent.ItemAnimationType.BOW;
        } else if (material.isFood()) {
            itemAnimationType = PlayerItemAnimationEvent.ItemAnimationType.EAT;

            // Eating code, contains the eating time customisation
            PlayerPreEatEvent playerPreEatEvent = new PlayerPreEatEvent(player, itemStack, player.getDefaultEatingTime());
            player.callCancellableEvent(PlayerPreEatEvent.class, playerPreEatEvent, () -> player.refreshEating(true, playerPreEatEvent.getEatingTime()));
        }

        if (itemAnimationType != null) {
            PlayerItemAnimationEvent playerItemAnimationEvent = new PlayerItemAnimationEvent(player, itemAnimationType);
            player.callCancellableEvent(PlayerItemAnimationEvent.class, playerItemAnimationEvent, () -> {
                player.sendPacketToViewers(player.getMetadataPacket());
            });
        }

        return true;
    }

}
