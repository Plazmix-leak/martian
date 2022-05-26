package net.minestom.server.network.packet.client.play;

import net.minestom.server.entity.Player;
import net.minestom.server.network.packet.client.ClientPlayPacket;
import net.minestom.server.utils.binary.BinaryReader;
import org.jetbrains.annotations.NotNull;

public class ClientSettingsPacket extends ClientPlayPacket {

    public String locale;
    public byte viewDistance;
    public Player.ChatMode chatMode;
    public boolean chatColors;
    public byte displayedSkinParts;

    @Override
    public void read(@NotNull BinaryReader reader) {
        this.locale = reader.readSizedString(128);
        this.viewDistance = reader.readByte();
        this.chatMode = Player.ChatMode.values()[reader.readVarInt()];
        this.chatColors = reader.readBoolean();
        this.displayedSkinParts = reader.readByte();
    }
}
