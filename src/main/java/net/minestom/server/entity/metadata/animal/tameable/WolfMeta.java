package net.minestom.server.entity.metadata.animal.tameable;

import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Metadata;
import org.jetbrains.annotations.NotNull;

public class WolfMeta extends TameableAnimalMeta {

    private final static byte MASK_INDEX = 16;

    private final static byte ANGRY_BIT = 0x02;

    public WolfMeta(@NotNull Entity entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public boolean isAngry() {
        return getMaskBit(MASK_INDEX, ANGRY_BIT);
    }

    public void setAngry(boolean value) {
        setMaskBit(MASK_INDEX, ANGRY_BIT, value);
    }

    public float getHealth() {
        return this.metadata.getIndex((byte) 18, (short) 0);
    }

    public void setHealth(float value) {
        this.metadata.setIndex((byte) 18, Metadata.Float(value));
    }

    public boolean isBegging() {
        return super.metadata.getIndex((byte) 19, false);
    }

    public void setBegging(boolean value) {
        super.metadata.setIndex((byte) 19, Metadata.Boolean(value));
    }

    public byte getCollarColor() {
        return super.metadata.getIndex((byte) 20, (byte) 14);
    }

    public void setCollarColor(byte value) {
        super.metadata.setIndex((byte) 20, Metadata.Byte(value));
    }

}
