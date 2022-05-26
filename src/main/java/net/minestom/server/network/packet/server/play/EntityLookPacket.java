package net.minestom.server.network.packet.server.play;

import net.minestom.server.network.packet.server.ServerPacket;
import net.minestom.server.network.packet.server.ServerPacketIdentifier;
import net.minestom.server.utils.binary.BinaryWriter;
import org.jetbrains.annotations.NotNull;

public class EntityLookPacket implements ServerPacket {

    public int entityId;
    public float yaw, pitch;
    public boolean onGround;

    @Override
    public void write(@NotNull BinaryWriter writer) {
        writer.writeVarInt(entityId);
        writer.writeByte((byte) (yaw * 256 / 360));
        writer.writeByte((byte) (pitch * 256 / 360));
        writer.writeBoolean(onGround);
    }

    @Override
    public int getId() {
        return ServerPacketIdentifier.ENTITY_LOOK;
    }

    @NotNull
    public static EntityLookPacket getPacket(int entityId,
                                             float yaw, float pitch,
                                             boolean onGround) {
        EntityLookPacket entityLookPacket = new EntityLookPacket();
        entityLookPacket.entityId = entityId;
        entityLookPacket.yaw = yaw;
        entityLookPacket.pitch = pitch;
        entityLookPacket.onGround = onGround;

        return entityLookPacket;
    }
}
