package net.minestom.server.network.packet.server.login;

import net.minestom.server.network.packet.server.ServerPacket;
import net.minestom.server.network.packet.server.ServerPacketIdentifier;
import net.minestom.server.utils.binary.BinaryWriter;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class LoginSuccessPacket implements ServerPacket {

    public UUID uuid;
    public String username;

    public LoginSuccessPacket(@NotNull UUID uuid, @NotNull String username) {
        this.uuid = uuid;
        this.username = username;
    }

    @Override
    public void write(@NotNull BinaryWriter writer) {
        writer.writeSizedString(uuid.toString());
        writer.writeSizedString(username);
    }

    @Override
    public int getId() {
        return ServerPacketIdentifier.LOGIN_SUCCESS;
    }
}
