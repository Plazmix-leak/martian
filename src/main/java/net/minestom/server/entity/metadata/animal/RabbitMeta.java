package net.minestom.server.entity.metadata.animal;

import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Metadata;
import org.jetbrains.annotations.NotNull;

public class RabbitMeta extends AnimalMeta {

    public RabbitMeta(@NotNull Entity entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    @NotNull
    public Type getType() {
        int id = super.metadata.getIndex((byte) 18, (byte)0);
        if (id == 99) {
            return Type.KILLER_BUNNY;
        }
        return Type.VALUES[id];
    }

    public void setType(@NotNull Type value) {
        byte id = (byte) (value == Type.KILLER_BUNNY ? 99 : value.ordinal());
        super.metadata.setIndex((byte) 18, Metadata.Byte(id));
    }

    public enum Type {
        BROWN,
        WHITE,
        BLACK,
        BLACK_AND_WHITE,
        GOLD,
        SALT_AND_PEPPER,
        KILLER_BUNNY;

        private final static Type[] VALUES = values();
    }

}
