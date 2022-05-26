package net.minestom.server.network.packet.server.status;

import net.minestom.server.network.packet.server.ServerPacket;
import net.minestom.server.utils.binary.BinaryWriter;
import org.jetbrains.annotations.NotNull;

public class PongPacket implements ServerPacket {

    public long number;

    public PongPacket(long number) {
        this.number = number;
    }

    @Override
    public void write(@NotNull BinaryWriter writer) {
        writer.writeLong(number);
    }

    @Override
    public int getId() {
        return 0x01;
    }
}
