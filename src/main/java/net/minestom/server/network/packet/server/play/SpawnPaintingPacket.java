package net.minestom.server.network.packet.server.play;

import net.minestom.server.network.packet.server.ServerPacket;
import net.minestom.server.network.packet.server.ServerPacketIdentifier;
import net.minestom.server.utils.BlockPosition;
import net.minestom.server.utils.binary.BinaryWriter;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class SpawnPaintingPacket implements ServerPacket {

    public int entityId;
    public String title;
    public BlockPosition position;
    public byte direction;

    @Override
    public void write(@NotNull BinaryWriter writer) {
        writer.writeVarInt(entityId);
        writer.writeSizedString(title);
        writer.writeBlockPosition(position);
        writer.writeByte(direction);
    }

    @Override
    public int getId() {
        return ServerPacketIdentifier.SPAWN_PAINTING;
    }
}
