package net.minestom.server.network.packet.server.play;

import net.minestom.server.network.packet.server.ServerPacket;
import net.minestom.server.network.packet.server.ServerPacketIdentifier;
import net.minestom.server.utils.binary.BinaryWriter;
import org.jetbrains.annotations.NotNull;

public class ChangeGameStatePacket implements ServerPacket {

    public Reason reason;
    public float value;

    @Override
    public void write(@NotNull BinaryWriter writer) {
        writer.writeByte((byte) reason.ordinal());
        writer.writeFloat(value);
    }

    @Override
    public int getId() {
        return ServerPacketIdentifier.CHANGE_GAME_STATE;
    }

    public enum Reason {
        NO_RESPAWN_BLOCK,
        END_RAINING,
        BEGIN_RAINING,
        CHANGE_GAMEMODE,
        WIN_GAME,
        DEMO_EVENT,
        ARROW_HIT_PLAYER,
        FADE_VALUE,
        FADE_TIME,
        PLAY_MOB_APPEARANCE
    }

}
